function adjsutWH(){
		var $t_right=$(".t_rightbox");
		var $t_mainbox=$(".t_mainbox");
		var $t_topbox=$(".t_topbox");
		var $t_rightboxin=$(".t_rightboxin");
		var $t_righttabContant=$(".t_righttabContant");
		var $tabs_panels=$(".tabs-panels");
		var t_topbox_Height=$t_topbox.height();
		var winWidth=$(window).width();
		var winHeight=$(window).height();
		
		if (jQuery.browser.msie && (jQuery.browser.version == "6.0") && !jQuery.support.style){
			if(winWidth<1000){
				$t_mainbox.width(1200);
				$t_topbox.width(1200);
				
			}else{
				$t_mainbox.width("100%");
				$t_topbox.width(winWidth);
			}
		}
		
		$t_mainbox.height(winHeight-t_topbox_Height-8);
		$t_righttabContant.height(winHeight-t_topbox_Height-35);
		$tabs_panels.height(winHeight-t_topbox_Height-65);
		$tabs_panels.children("div").width("100%");
		$tabs_panels.children("div").height("100%");
		$tabs_panels.children("div").children("div").width("100%");
		$tabs_panels.children("div").children("div").height("100%");
			
		
}
$(document).ready(function() {
	adjsutWH();
	$(window).bind("resize",adjsutWH);
	$(window).bind("scroll",adjsutWH);
	
	$("#t_btnleftbox").click(function(){
		var $t_leftbox=$(".t_leftbox");
		$t_leftbox.toggle();
		if($t_leftbox.css("display")!="none"){
			$("#t_btnleftbox").removeClass("t_btnleftboxselect");
		}else{
			$("#t_btnleftbox").addClass("t_btnleftboxselect");
		}
		adjsutWH();
	});
	$("#t_btntopbox").click(function(){
		var $t_topbox=$(".t_topbox");
		if($t_topbox.css("height")!="0px"){
			$t_topbox.height(0);
			$("#t_btntopbox").addClass("t_btntopboxselect");
			
		}else{
			$t_topbox.height(80);
			$("#t_btntopbox").removeClass("t_btntopboxselect");
		}
		adjsutWH();
	});
	//左侧导航
	
	
});

