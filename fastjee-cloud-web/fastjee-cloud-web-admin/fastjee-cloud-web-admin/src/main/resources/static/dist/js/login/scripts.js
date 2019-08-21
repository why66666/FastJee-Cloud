
jQuery(document).ready(function() {
	$(document.body).css({
		"overflow-x":"hidden",
		"overflow-y":"hidden"
	});
    /*
        Fullscreen background
    */
	$(".container").css({ opacity: 1});//内容透明度
    $.backstretch(["/dist/img/login/backgrounds/BistiBadlands_ZH-CN5428677883_1920x1080.jpg","/dist/img/login/backgrounds/BigWindDay_ZH-CN1837859776_1920x1080.jpg","/dist/img/login/backgrounds/QingmingBridge_ZH-CN3844222543_1920x1080.jpg","/dist/img/login/backgrounds/BagpipeOpera_ZH-CN9506207351_1920x1080.jpg","/dist/img/login/backgrounds/TofinoCoast_ZH-CN0950198582_1920x1080.jpg"],{duration:4000});
    
    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $('.login-form').on('submit', function(e) {
    	
    	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	
    });
    
    
});
