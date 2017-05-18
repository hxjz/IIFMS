/**
 * 
 */
function toHide1(){
	$(document).find('#dlzh1').hide();
}
function toHide2(){
	$(document).find('#dlzh2').hide();
} 

function toHide4(){
	$(document).find('#txlan').slideToggle();
}
function dis(){
	$('#txlan').slideToggle(1000);
	
}
function blur1(){
	if($('#j_username').attr('value')=='') $(document).find('#dlzh1').show();
	if($('#j_username').attr('value')!='') $('#j_username').attr('value',this.value);
	
}
function blur2(){
	if($('#j_password').attr('value')=='') $(document).find('#dlzh2').show();
	if($('#j_password').attr('value')!='') $('#j_password').attr('value',this.value);
}
