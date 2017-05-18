
//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外  
function banBackSpace(e){     
    var ev = e || window.event;//获取event对象 
    var obj = ev.target || ev.srcElement;//获取事件源     

    var t = obj.type || obj.getAttribute('type');//获取事件源类型    
 
    //获取作为判断条件的事件类型  
    var vReadOnly = obj.getAttribute('readonly');  
    var vEnabled = obj.getAttribute('enabled');  
    //处理null值情况  
    vReadOnly = (vReadOnly == null) ? "false" : vReadOnly;  
    vEnabled = (vEnabled == null) ? "true" : vEnabled;  

    //当敲Backspace键时，事件源类型为密码或单行、多行文本的，  
    //并且readonly属性为true或enabled属性为false的，则退格键失效  
    var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")   
                && (vReadOnly=="true" || vEnabled!="true"))?true:false;  
    //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效  
    var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea") ?true:false;

    //判断  
    if(flag2){  
        return false; 
    }  
    if(flag1){     
        return false;     
    }     
};

//禁止后退键 作用于Firefox、Opera  
document.onkeypress=banBackSpace;  
//禁止后退键  作用于IE、Chrome  
document.onkeydown=banBackSpace;

//基础变量
//以来jQuery(开发用1.8版本)
//注意在此文件中添加的初始化处理功能、不能同别的功能强相关
$(function(){
	$('input:text:enabled:visible:first').focus();
	//查询页面在查询条件焦点按回车查询
	if($('#find').length == 1) {
		$('input[name^="filter"]:text').live('keypress',function(e){
			if(e.keyCode ==13){
				$("#find").trigger("click");
				return false;
			}
		});
	}
	//更多搜索条件展开和隐藏
	$(".t_oneblockopen").click(function(){
			$(this).prev().toggle();
			if($(this).prev().css("display")=="none"){
				$(this).parent().prev().css("padding-bottom","10px");
				$(".t_oneblockopen").removeClass("t_oneblockclose");
			}else{
				$(this).parent().prev().css("padding-bottom","0px");
				$(this).prev().css("padding-top","0px");
				$(".t_oneblockopen").addClass("t_oneblockclose");
			}
	});
	if(parent.getCurrentTabId) {
		tabId = parent.getCurrentTabId();
	}
	if(parent.getfrmTabId){
		frmTabId = parent.getfrmTabId();
	}
});