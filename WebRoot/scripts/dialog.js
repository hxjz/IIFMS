var infoTitle="操作提示";
var warningTitle="警告";
var reMakeUpTitle="补做";

OPRATION_WAIT="正在处理，请耐心等待...";
OPRATION_SUCCESS="操作成功！";
OPRATION_SENDSUCCESS="发送完毕！";
OPRATION_FAILED="操作失败，请稍后尝试！";
OPRATION_DELETE_FAILED="您删除的数据已被其他模块数据引用，请手动解除关联后再进行删除！";
OPRATION_CONFIRM_DELETE="您确定要删除吗？";
OPRATION_CONFIRM_AUDIT="您确定要审核确认吗？";
OPRATION_CONFIRM_FINISH="您确定要办结确认吗？";
OPRATION_CONFIRM_REMAKEUP="您确定要补做吗？";
OPRATION_CONFIRM_SETTLE_ALL="您确定要全部结算吗？";
OPRATION_CONFIRM_REVOKE="您确定要撤销吗？";
OPRATION_CONFIRM_BATCHSUBMIT="您确定全部提交吗？";
OPRATION_CONFIRM_BATCHSEND="您确定发送邮件吗？";
OPRATION_CONFIRM_BATCHCLOSE="您确定关闭吗？";
OPRATION_PLEASE_CHOOSE_ONE="请先选择一条记录再进行操作！";
OPRATION_CONFIRM_BATCHCANCEL="您确定全部作废吗？";
ENDDATE_COMPARETO_STARTDATE="截止时间不可以早于开始时间！";
ERROR="网络连接中断,请您重新登录！";


function alertInfo(info){
	if(!info){
		$.messager.alert(infoTitle,OPRATION_SUCCESS,'info');
	}else {
		$.messager.alert(infoTitle,info,'info');
	}
}

function alertWarning(warning){
	if(!warning){
		$.messager.alert(warningTitle,OPRATION_FAILED,'warning');
	}else{
		$.messager.alert(warningTitle,warning,'warning');
	}
}

function alertConfirm(message,fun){
	if(!message)
		message=OPRATION_CONFIRM_DELETE;
	$.messager.confirm(warningTitle, message, function(r){
		if (r){
			fun();
		}
	});
}
function alertConfirmInfo(message,fun){
	$.messager.confirm(infoTitle, message, function(r){
		if (r){
			fun();
		}
	});
}
function alertConfirmReMakeUp(message,fun){
	$.messager.confirm(reMakeUpTitle, message, function(r){
		if (r){
			fun();
		}
	});
}
/* $('#dialog_content').dialog({
title:'补做',
iconCls:'icon-ok',
buttons:[{
    text:'确定',
    iconCls:'icon-ok',
    handler:function(){
        alert('ok');
    }
},{
    text:'取消',
    handler:function(){
        $('#dialog_content').dialog('close');
    }
}]
}); */