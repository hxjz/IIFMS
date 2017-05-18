// JavaScript Document
/*
此插件控制左侧树形导航
*/
(function($){
    $.fn.extend({
    t_controlLeftNav: function(options) {
        var aDl=$('.slideNav').children('dl');
		var aSecDl=$('.slideNav dd').children('dl');
		slideNav(aDl);
		slideNav(aSecDl)
		aSecDl.children('dt').each(function(index){
			if($(this).next().length==0){$(this).css("background","none");}
		});
		function slideNav(obj)
		{
			obj.each(function()
			{
				var _this=$(this);
				var aDt=_this.children('dt');
				var aDd=_this.children('dd');
				
				aDt.click(function()
				{
					if($(this).next().length>0){
						if(!$(this).hasClass('active'))
						{
							_this.siblings().children('dt').removeClass();
							$(this).addClass('active');
							_this.siblings().children('dd').slideUp(200);
							aDd.stop().slideDown(200);
						}
						else
						{
							$(this).removeClass('active');
							aDd.stop().slideUp(200);
						}
					}
				});
			});
		}
		
    }
});
})(jQuery);

$(document).ready(function() {
	$(".leftBox").t_controlLeftNav();
});