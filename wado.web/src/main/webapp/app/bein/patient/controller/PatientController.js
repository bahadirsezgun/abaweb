ptBossApp.controller('PatientController', function($rootScope,$scope,$location,$http) {
	$scope.headerPage="";
	$scope.showQuery=true;
	$scope.customers=new Object();
	$scope.patients;
	$scope.dateFormat="dd/MM/yyyy";
	$scope.patId="";
	
	$scope.customerDbs=new Array();
	$scope.production="0";
	
	$scope.logs;
	
	$scope.init=function(){
		$scope.patients;
		$scope.patId="";
		$scope.startTime=new Date($scope.startTime);
		$scope.endTime=new Date($scope.endTime);
		
	}
	
	$scope.findPatientById=function(){
		$http({
			  method: 'POST',
			  url: '/wado/wado/pacs/patient/findPatientByIdToSendKOS/'+$scope.patId
			}).then(function successCallback(response) {
				$scope.patients=response.data;
				$scope.showQuery=false;
			});
	}
	
	$scope.findPatientsToSendKOS=function(){
		
		var searchUtil=new Object();
		searchUtil.startTime=new Date($scope.startTime);
		searchUtil.endTime=new Date($scope.endTime);
		
		$http({
			  method: 'POST',
			  url: '/wado/wado/pacs/patient/findPatientsToSendKOS',
			  data:angular.toJson(searchUtil)
			}).then(function successCallback(response) {
				$scope.patients=response.data;
				$scope.showQuery=false;
			});
	}
	
	$scope.sendPatientToSend=function(patient){
		$(".splash").css("display",'');
		
		$http({
			  method: 'POST',
			  url: '/wado/wado/pacs/patient/sendPatientToSend/'+$scope.production,
		      data:angular.toJson(patient)
			}).then(function successCallback(response) {
				toastr.success(response.data.resultStatu);
				$scope.logs=response.data.resultDetails;
				$(".splash").css("display",'none');
				console.log($scope.logs);
				
			}, function errorCallback(response) {
				toastr.error("ZAMAN ASIMI OLMUS OLABILIR");
				$(".splash").css("display",'none');
			});
	}
	
	$scope.sendAllPatientToSend=function(){
		$(".splash").css("display",'');
		$http({
			  method: 'POST',
			  url: '/wado/wado/pacs/patient/sendAllPatientToSend/'+$scope.production,
			  data:angular.toJson($scope.patients)
			}).then(function successCallback(response) {
				toastr.success(response.data.resultStatu);
				
				$scope.logs=response.data.resultDetails;
				$(".splash").css("display",'none');
				console.log($scope.logs);
			}, function errorCallback(response) {
				toastr.error("ZAMAN ASIMI OLMUS OLABILIR");
				$(".splash").css("display",'none');
			});
	}
	
});

