package com.abasus.kos;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Sequence;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.UID;
import org.dcm4che3.data.VR;
import org.dcm4che3.imageio.plugins.dcm.DicomImageReadParam;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.io.DicomOutputStream;
import org.dcm4che3.util.UIDUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Service
@Qualifier("makeToKos")
public class MakeToKos {

	@Value("${kos.temp.path}")
	private String kosTempPath;

	public String createKosFile(String dcmFilePath,  String retrieveAET, String retrieveURL,
			String hospitalName, String skrs,String fileName,String tcKimlikNo) throws Exception {

		
		
		int indexDotDcm = dcmFilePath.lastIndexOf(".dcm");
		/*String temp = dcmFilePath.substring(0, indexDotDcm);
		String kosFilePath = temp + "-KOS.dcm";
		*/
		String kosFilePath = kosTempPath +fileName;
		
		String thumbFilePath = getThumbNailPath(dcmFilePath);

		DicomInputStream dis = new DicomInputStream(new File(dcmFilePath));
		Attributes ds = dis.readDataset(-1, Tag.PixelData);

		File kosFile = new File(kosFilePath);
		File thumbFile = new File(thumbFilePath);

		DicomOutputStream dos = new DicomOutputStream(kosFile);

		dos.writeAttribute(Tag.SeriesInstanceUID, VR.UI, ds.getBytes(Tag.SeriesInstanceUID));

		String locationUID = "1.3.6.1.4.1.21367.2017.10.26." + skrs;

		String studyIUID = ds.getString(Tag.StudyInstanceUID);
		String seriesIUID = ds.getString(Tag.SeriesInstanceUID);
		String iuid = ds.getString(Tag.SOPInstanceUID);
		String cuid = ds.getString(Tag.SOPClassUID);
		String modality = ds.getString(Tag.Modality);
		String seriesDescription = ds.getString(Tag.SeriesDescription);

		Attributes attrs = new Attributes();
		attrs.setString(Tag.StudyInstanceUID, VR.UI, studyIUID);
		attrs.setString(Tag.SOPClassUID, VR.UI, cuid);
		attrs.setString(Tag.SOPInstanceUID, VR.UI, iuid);
		attrs.setString(Tag.ReferencedSOPClassUID, VR.UI, cuid);
		attrs.setString(Tag.ReferencedSOPInstanceUID, VR.UI, iuid);

		attrs.setString(Tag.InstitutionName, VR.LO, hospitalName + "^^^SKRS" + skrs);
		attrs.setString(Tag.AccessionNumber, VR.SH, ds.getString(Tag.AccessionNumber));
		attrs.setString(Tag.PatientName, VR.PN, ds.getString(Tag.PatientName));
		attrs.setString(Tag.PatientID, VR.LO, ds.getString(Tag.PatientID));
		attrs.setString(Tag.OtherPatientIDs, VR.LO, tcKimlikNo);
		
		
		attrs.setString(Tag.StudyDate, VR.DA, ds.getString(Tag.StudyDate));
		attrs.setString(Tag.StudyTime, VR.TM, ds.getString(Tag.StudyTime));
		attrs.setString(Tag.AcquisitionDate, VR.DA, ds.getString(Tag.AcquisitionDate));
		attrs.setString(Tag.AcquisitionTime, VR.TM, ds.getString(Tag.AcquisitionTime));

		attrs.setString(Tag.PatientBirthDate, VR.DA, ds.getString(Tag.PatientBirthDate));
		attrs.setString(Tag.PatientSex, VR.CS, ds.getString(Tag.PatientSex));
		attrs.setString(Tag.SOPClassUID, VR.UI, UID.KeyObjectSelectionDocumentStorage);
		attrs.setString(Tag.SOPInstanceUID, VR.UI, UIDUtils.createUID());
		attrs.setDate(Tag.ContentDateAndTime, new Date());
		attrs.setString(Tag.Modality, VR.CS, "KO");
		attrs.setNull(Tag.ReferencedPerformedProcedureStepSequence, VR.SQ);
		attrs.setString(Tag.SeriesInstanceUID, VR.UI, ds.getString(Tag.SeriesInstanceUID));
		attrs.setString(Tag.SeriesNumber, VR.IS, ds.getString(Tag.SeriesNumber));
		attrs.setString(Tag.InstanceNumber, VR.IS, ds.getString(Tag.InstanceNumber));
		attrs.setString(Tag.ValueType, VR.CS, "CONTAINER");
		attrs.setString(Tag.ContinuityOfContent, VR.CS, "SEPARATE");

		Sequence evidenceSeq = attrs.newSequence(Tag.CurrentRequestedProcedureEvidenceSequence, 1);

		Attributes refStudy = new Attributes(2);
		Sequence refSeriesSeq = refStudy.newSequence(Tag.ReferencedSeriesSequence, 10);
		refStudy.setString(Tag.StudyInstanceUID, VR.UI, ds.getString(Tag.StudyInstanceUID));
		evidenceSeq.add(refStudy);

		Attributes refSeries = new Attributes(6);
		if (retrieveAET != null)
			refSeries.setString(Tag.RetrieveAETitle, VR.AE, retrieveAET);
		if (retrieveURL != null)
			refSeries.setString(Tag.RetrieveURL, VR.UR, retrieveURL);
		@SuppressWarnings("unused")
		Sequence refSOPSeq = refSeries.newSequence(Tag.ReferencedSOPSequence, 100);
		Sequence iconImgSeq = refSeries.newSequence(Tag.IconImageSequence, 1);

		BufferedImage jpgThumbBuf = ImageIO.read(thumbFile);
		byte[] thumbnailImageBytes = bufferedImageToBytes(jpgThumbBuf);

		Attributes iconImg = new Attributes(7);
		iconImg.setInt(Tag.Rows, VR.US, jpgThumbBuf.getHeight());
		iconImg.setInt(Tag.Columns, VR.US, jpgThumbBuf.getWidth());
		iconImg.setInt(Tag.BitsAllocated, VR.US, 8);
		iconImg.setInt(Tag.BitsStored, VR.US, 8);
		iconImg.setInt(Tag.HighBit, VR.US, 7);
		iconImg.setInt(Tag.SamplesPerPixel, VR.US, 3);
		iconImg.setString(Tag.PhotometricInterpretation, VR.CS, "YBR_FULL_422");
		iconImg.setString(Tag.PixelAspectRatio, VR.IS, "1");

		try {
			iconImg.setBytes(Tag.PixelData, VR.OB, thumbnailImageBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		iconImgSeq.add(iconImg);
		refSeries.setString(Tag.SeriesInstanceUID, VR.UI, seriesIUID);
		refSeries.setString(Tag.SeriesDescription, VR.UI, seriesDescription);
		refSeries.setString(Tag.Modality, VR.CS, modality);
		if (locationUID != null)
			refSeries.setString(Tag.RetrieveLocationUID, VR.UI, locationUID);
		refSeriesSeq.add(refSeries);

		dos.writeDataset(null, attrs);

		dos.close();
		dis.close();
		
		return fileName;
	}

	private static byte[] bufferedImageToBytes(BufferedImage bufferedImage) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "jpg", baos);
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();
		return imageInByte;
	}

	private static String getThumbNailPath(String dcmFilePath) {
		// String filePath = "C:\\Xerox\\ws\\working\\MR201100000000198.dcm";
		int maxWidHei = 64;
		BufferedImage myJpegImage = null;

		int indexDotDcm = dcmFilePath.lastIndexOf(".dcm");
		String temp = dcmFilePath.substring(0, indexDotDcm);
		String jpgFilePath = temp + "Thumb.jpg";
		String thumbFilePath = temp + "Thumb.jpg";

		File dcmFile = new File(dcmFilePath);
		File jpgFile = new File(jpgFilePath);
		File thumbFile = new File(thumbFilePath);

		if (thumbFile.exists())
			return thumbFilePath;

		Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName("DICOM");

		if (iterator.hasNext()) {
			ImageReader imageReader = (ImageReader) iterator.next();
			DicomImageReadParam dicomImageReadParam = (DicomImageReadParam) imageReader.getDefaultReadParam();
			try {
				ImageInputStream iis = ImageIO.createImageInputStream(dcmFile);
				imageReader.setInput(iis, false);
				myJpegImage = imageReader.read(0, dicomImageReadParam);
				iis.close();
				if (myJpegImage == null) {
					System.out.println("Could not read image!!");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {

				OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(jpgFile));
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
				encoder.encode(myJpegImage);
				outputStream.close();

				int w = myJpegImage.getWidth();
				int h = myJpegImage.getHeight();
				int newW = w;
				int newH = h;
				if (w > h) {
					newW = maxWidHei;
					newH = newW * h / w;
				} else {
					newH = maxWidHei;
					newW = newH * w / h;
				}

				BufferedImage myJpegImage2 = resize(myJpegImage, newW, newH);
				OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(thumbFile));
				JPEGImageEncoder encoder2 = JPEGCodec.createJPEGEncoder(outputStream2);
				encoder2.encode(myJpegImage2);
				outputStream2.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

		return thumbFilePath;

	}

	private static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_DEFAULT);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_RGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}
}