require.config({
   //urlArgs: "version="+(new Date()).getTime(),
   waitSeconds: 200,
   paths: {
		    'angular': '/wado/jslib/lib/angular/angular',
			'anSanitize': '/wado/jslib/lib/angular/angular-sanitize',
			'uiMask': '/wado/jslib/lib/mask/mask.min',
	        'ptbossapp':'/wado/app/main/ptbossapp',
	        'anroute': '/wado/jslib/lib/angular/angular-route',
	        'modulloader':'/wado/app/main/modulloader',
			'antranslate': '/wado/jslib/lib/angular/angular-translate',
			'ngDraggable': '/wado/homerlib/vendor/draggable/ngDraggable'
   },
   shim: {
	   'angular': {
           exports: 'angular'
       },
      'ptbossapp': {
           deps: ['angular']
       },
      'modulloader':{
    	   deps: ['angular','main']
       },
       'anSanitize': {
           deps: ['angular']
       },
       'antranslate': {
            deps: ['angular']
       },
       'uiMask': {
           deps: ['angular']
       },
      	'anroute': {
           deps: ['angular']
       },
     	'ngDraggable': {
            deps: ['angular']
        }
      
   },
    deps :[]
});

requirejs(['angular','anSanitize','ptbossapp','modulloader','antranslate','anroute','uiMask','ngDraggable'],
  function   (angular ,anSanitize,ptbossapp,modulloader,antranslate,anroute,uiMask,ngDraggable ) {
	 
	  angular.element(document).ready(function() {
	      angular.bootstrap(document, ['PTBossApp']);
	  });
	  
  });

