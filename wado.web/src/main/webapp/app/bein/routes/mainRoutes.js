	ptBossApp.config(['$routeProvider',
	 function($routeProvider) {
     $routeProvider.
	     when('/patient', {
	         templateUrl: '/wado/bein/patient/index.html'
	     }).
	     otherwise({
         redirectTo: '/patient'
         });
     
     }]);	