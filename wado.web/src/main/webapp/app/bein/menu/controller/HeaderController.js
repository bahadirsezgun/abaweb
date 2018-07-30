ptBossApp.controller('HeaderController', function($rootScope,$scope,$translate) {
	
	
	
	
	$scope.init=function(){
		 
		
		 $('.animate-panel').animatePanel();
		
		 $('.closebox').unbind('click').bind('click', function (event) {
		        event.preventDefault();
		        var hpanel = $(this).closest('div.hpanel');
		        hpanel.remove();
		        if($('body').hasClass('fullscreen-panel-mode')) { $('body').removeClass('fullscreen-panel-mode');}
		    });
		 
		 
		 $('.showhide').unbind('click').bind('click', function (event) {
		        event.preventDefault();
		        var hpanel = $(this).closest('div.hpanel');
		        var icon = $(this).find('i:first');
		        var body = hpanel.find('div.panel-body');
		        var footer = hpanel.find('div.panel-footer');
		        body.slideToggle(300);
		        footer.slideToggle(200);

		        icon.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
		        hpanel.toggleClass('').toggleClass('panel-collapse');
		        setTimeout(function () {
		            hpanel.resize();
		            hpanel.find('[id^=map-]').resize();
		        }, 50);
		    });
		 
		 	$('.hide-menu').unbind('click').bind('click', function(event){
		        event.preventDefault();
		        if ($(window).width() < 769) {
		            $("body").toggleClass("show-sidebar");
		        } else {
		            $("body").toggleClass("hide-sidebar");
		        }
		    });

		 	$('.hide-menu').click();
			
		    
		    $('.fullscreen').unbind('click').bind('click', function() {
		        var hpanel = $(this).closest('div.hpanel');
		        var icon = $(this).find('i:first');
		        $('body').toggleClass('fullscreen-panel-mode');
		        icon.toggleClass('fa-expand').toggleClass('fa-compress');
		        hpanel.toggleClass('fullscreen');
		        setTimeout(function() {
		            $(window).trigger('resize');
		        }, 100);
		    });

		  
		
		    
		    $('.small-header-action').unbind('click').bind('click', function(event){
		        event.preventDefault();
		        var icon = $(this).find('i:first');
		        var breadcrumb  = $(this).parent().find('#hbreadcrumb');
		        $(this).parent().parent().parent().toggleClass('small-header');
		        breadcrumb.toggleClass('m-t-lg');
		        icon.toggleClass('fa-arrow-up').toggleClass('fa-arrow-down');
		    });
		    
		
		 // Open close right sidebar
		    $('.right-sidebar-toggle').unbind('click').bind('click', function () {
		        $('#right-sidebar').toggleClass('sidebar-open');
		    });
		    
		    
	};
	
	
	
	
	
	
		
	
	
	
	
	
});
