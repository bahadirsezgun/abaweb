ptBossLoginApp.controller('LoginController', function($scope,$translate,$http) {
	
	
	$scope.version;
	$scope.logosRight=new Array();
	$scope.logosLeft=new Array();
	
	
	var user=new Object();
	user.userName=localStorage.getItem('username');
 
	if(user.userName==null){
		user.userName="";
	}
	
	user.password="";
	
	
	   toastr.options = {
            "debug": false,
            "newestOnTop": false,
            "positionClass": "toast-top-center",
            "closeButton": true,
            "toastClass": "animated fadeInDown",
        };
	
	
	var days = ["sunday","monday","tuesday","wednesday","thursday","friday","saturday"];
	var monthNames = ["january", "february", "march", "april", "may", "june","july", "august", "september", "october", "november", "december"];
	
	var d=new Date();
	$scope.time=d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
	$scope.date=$translate.instant(days[d.getDay()])+", "+$translate.instant(monthNames[d.getMonth()+1])+" "+(d.getMonth()+1)+","+d.getFullYear();
	//Friday, February 27, 2015
	   
	$scope.user=user;
	
	$scope.userName_PlaceHolder="enterUserName";
	$scope.password_PlaceHolder="enterPassword";
	
	$scope.init=function(){
		
		
		$translate.use("tr");
		
		
	};
	
    	
    	$scope.loginKey=function(keyEvent){
    		if (keyEvent.which === 13){
    			$scope.login();
    		}
    	}
    	
   
	$scope.login=function(){
		
		var frmDatum={"username":$scope.user.userName,"password":$scope.user.password};
		
		$http({
			  method:'POST',
			  url: "/wado/wado/login/control/"+$scope.user.userName+"/"+$scope.user.password,
			}).then(function successCallback(res) {
				if(res.data.result=="success"){
					$(location).attr("href","./wado/bein/index.html/#/patient");
				}else{
					$scope.resultMessage=res.resultMessage;
					toastr.error($translate.instant(res.resultMessage));
				}
			}, function errorCallback(response) {
				toastr.error($translate.instant("error"));
			});
	};
	
	
	
	
	
	
});