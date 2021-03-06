<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>新增/修改财物</title>
    <%@include file="/WEB-INF/jsp/common/common.jsp" %>
    <style type="text/css">
	*{
		margin-bottom:5px;
	}
</style>
</head>
<body>
<form id="edit" name="editForm" method="post" enctype="multipart/form-data">
    <input type="submit" class="t_btnsty02" id="saveBtn" value="保存">
    <input type="button" id="cancel" class="t_btnsty02" onclick="cancelAddOrEdit();" value="取消">
    <input type="hidden" name="id" id="id" value="${finances.id}">
    <input type="hidden" name="fromSource" id="fromSource" value="${fromSource}"/>
    <input type="hidden" name="caseId" id="caseId" value="${finances.cases.id}"/>
    <br>
    <table border="0">
        <tr>
            <td class="tr" width=100><span class="t_span01">案件名称：</span></td>
            <td>
                <input class="easyui-validatebox t_text w180" data-options="required:true,missingMessage:'请输入案件名称'"
                       name="caseName" type="text" value="${finances.cases.caseName}" readonly="readonly"/><span class="t_span02">*</span>
            </td>
            <td><input type="button" class="t_btnsty01" id="toSelectCase" onclick="toSelectCases()" value="选择"></td>
            <td class="tr" width=100><span class="t_span01">案件编号：</span></td>
            <td>
                <input class="easyui-validatebox t_text w180" data-options="required:true,missingMessage:'请输入案件编号'"
                       name="caseNum" type="text" value="${finances.cases.caseNum}" readonly="readonly"/><span class="t_span02">*</span>
            </td>
            <td><input type="button" class="t_btnsty01" id="toAddCase" onclick="toAddCases()" value="添加"></td>
        </tr>
        <tr>
            <td class="tr" width=100><span class="t_span01">财物名称：</span></td>
            <td colspan="2">
                <input class="easyui-validatebox t_text w180" data-options="required:true,missingMessage:'请输入财物名称'"
                       name="financeName" type="text" value="${finances.financeName}"/><span class="t_span02">*</span>
            </td>
            <td class="tr" width=100><span class="t_span01">财物种类：</span></td>
            <td colspan="2">
                <input type="hidden" name="financeTypeHid" id="financeTypeHid" value="${finances.financeType}">
                <select class="w180" name="financeType" id="financeType" required="true">
                    <option value="">请选择</option>
                    <c:forEach items="${financeTypeList}" var="object">
                        <option value="${object.key}">${object.value}</option>
                    </c:forEach>
                </select>
                <span class="t_span02">*</span>
            </td>
        </tr>
        <tr>
            <td class="tr" width=100><span class="t_span01">财物编号：</span></td>
            <td colspan="2">
                <input class="easyui-validatebox t_text w180" data-options="" name="financeNum" type="text"
                       value="${finances.financeNum}"/>
            </td>
            <td class="tr" width=100><span class="t_span01">财物来源：</span></td>
            <td colspan="2">
                <input type="hidden" name="financeSourceHid" id="financeSourceHid" value="${finances.financeSource}">
                <select class="w180" name="financeSource" id="financeSource">
                    <option value="">请选择</option>
                    <c:forEach items="${financeSourceList}" var="object">
                        <option value="${object.key}">${object.value}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td class="tr" width=100><span class="t_span01">查获人：</span></td>
            <td colspan="2">
                <input class="easyui-validatebox t_text w180" data-options="" name="seizedMan" type="text"
                       value="${finances.seizedMan}"/>
            </td>
            <td class="tr" width=100><span class="t_span01">来源单位：</span></td>
            <td colspan="2">
                <input type="hidden" name="sourceOfficeHid" id="sourceOfficeHid" value="${finances.sourceOffice}">
                <select class="w180" name="sourceOffice" id="sourceOffice">
                    <option value="">请选择</option>
                    <c:forEach items="${sourceOfficeList}" var="object">
                        <option value="${object.key}">${object.value}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td class="tr" width=100><span class="t_span01">查获时间：</span></td>
            <td colspan="3">
                <div id="test1">
                    <input class="easyui-datetimebox w180" data-options="" name="seizedTimeStart" id="seizedTimeStart"
                           type="text" value="${finances.seizedTimeStart}"/><span class="t_span01">起</span>
                    <!-- <input type="button" class="t_btnsty01" id="changeTimeStart1" value="模糊"/> -->
                </div>
                <div id="test2" hidden="true">
                    <input class="easyui-datebox w180" data-options="" name="seizedTimeStart" id="seizedTimeStart"
                           type="text" value="${finances.seizedTimeStart}"/><span class="t_span01">起</span>
                    <input type="button" class="t_btnsty01" id="changeTimeStart2" value="精确"/>
                </div>
            </td>
            <td colspan="2">
                <input class="easyui-datetimebox w180" data-options="" name="seizedTimeEnd" id="seizedTimeEnd"
                       type="text" value="${finances.seizedTimeEnd}"/><span class="t_span01">止</span>
            </td>
        </tr>
        <tr>
            <td class="tr" width=100><span class="t_span01">财物说明：</span></td>
            <td colspan="5">
                <input class="easyui-validatebox t_text w520" data-options="" name="financeDesc" type="text" value="${finances.financeDesc}"/>
            </td>
        </tr>
        <tr>
            <td class="tr" width=100><span class="t_span01">备注：</span></td>
            <td colspan="5">
                <input class="easyui-validatebox t_text w520" data-options="" name="financeMemo" type="text" value="${finances.financeMemo}"/>
            </td>
        </tr>
        <tr>
            <td class="tr" width=100><span class="t_span01">财物照片：</span></td>
            <td colspan="4">
                <textarea id="imageSign" name="caseDesc"
                          style="width:520px;height:100px">${finances.imageSign}</textarea>
            </td>
            <td>
                <%--<input type="submit" class="t_btnsty01" id="capturePicture" onclick="takePhoto()" value="拍照"><br>--%>
                <input type = "file" class="t_btnsty01" name="propertyImage"/><br/>
                <input type = "file" class="t_btnsty01" name="propertyImage"/>
                <%--<input type="file" class="t_btnsty01" id="importPicture"    accept="image/*" multiple="multiple" onchange="uploadImage()" value="导入"  style="width:70px"><br>--%>
                <input type="submit" class="t_btnsty01" id="removePicture" value="移除">
            </td>
        </tr>
        <tr>
            <td class="tr" width=100><span class="t_span01">存放位置：</span></td>
            <td colspan="4">
                <input class="easyui-validatebox t_text w520" data-options="" name="storeLocation" type="text"
                       value="${finances.storeLocation}"/>
            </td>
            <td><input type="button" class="t_btnsty01" id="chooseLocation" value="选择" onclick="chooseStorage()"></td>
        </tr>
        <tr>
            <td class="tr" width=100><span class="t_span01">财物识别码：</span></td>
            <td colspan="4">
                <input class="easyui-validatebox t_text w520" data-options="" name="financeCode" type="text"
                       value="${finances.financeCode}"/>
            </td>
            <td><input type="submit" class="t_btnsty01" id="print" value="打印"></td>
        </tr>
        <tr>
            <td class="tr" width=100><span class="t_span01">电子识别码：</span></td>
            <td colspan="4">
                <input class="easyui-validatebox t_text w520" data-options="" name="digitalCode" type="text"
                       value="${finances.digitalCode}"/>
            </td>
            <td><input type="submit" class="t_btnsty01" id="write" value="写入"></td>
        </tr>
    </table>
</form>

<%--案件列表--%>
<div id="selectCase" class="easyui-window" title="案件信息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 480px; height: 340px; padding: 20px;">
    <iframe id="frame_selectCase" width="400" height="270" scrolling="no" src="" frameborder="0"> </iframe>

</div>
<%--添加案件--%>
<div id="addCase" class="easyui-window" title="新增案件信息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 600px; height: 500px; padding: 20px;">
    <iframe id="frame_addCase" width="520" height="404" scrolling="no" src="" frameborder="0"> </iframe>
</div>

<div id="addStorage" class="easyui-window" title="选择存储位置" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 710px; height: 500px; padding: 20px;">
    <iframe id="frame_addStorage" width="680" height="400" scrolling="no" src="" frameborder="0"> </iframe>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        // 财物来源
        var financeSourceValue = $("#financeSourceHid").val();
        $("#financeSource").val(financeSourceValue);
        // 来源单位
        var sourceOfficeValue = $("#sourceOfficeHid").val();
        $("#sourceOffice").val(sourceOfficeValue);
        // 财物种类
        var financeTypeValue = $("#financeTypeHid").val();
        $("#financeType").val(financeTypeValue);

        // 初始化第二个隐藏时间控件的值
        $("input[textboxname='seizedTimeStart']:eq(1)").attr("value", "");
        $("#test2").hide();
        $("#changeTimeStart1").click(function () {
            $("input[textboxname='seizedTimeStart']").attr("value", "");
            $("input[name='seizedTimeStart']").attr("value", "");
            $("#test1").hide();
            $("#test2").show();
        });
        $("#changeTimeStart2").click(function () {
            $("input[textboxname='seizedTimeStart']").attr("value", "");
            $("input[name='seizedTimeStart']").attr("value", "");
            $("#test2").hide();
            $("#test1").show();
        });
    });


    //表单提交
    $('#edit').form({
        url: '${path}/finances/saveFinances.action',
        onSubmit: function () {
        	var startseizedTime = $("input[name='seizedTimeStart']").val();
	        var endseizedTime = $("input[name='seizedTimeEnd']").val();
	        if(startseizedTime>endseizedTime) {
	        	alert("查获开始时间不能早于查获结束时间");
	        	return false;
	        }
            return $(this).form('validate');
        },
        success: function (returnData) {
            data = JSON.parse(returnData); // 转换成json对象
            if (data.status == "success") {
                parent.alertInfo(data.data);
                if(!($("#fromSource").val() == '')) {
                	parent.afterCloseFinPage();
                	return true;
                }
                if ($("#id").val() == '') {
                    parent.afterCloseAddWindow();
                } else {
                    parent.afterCloseEditWindow();
                }
            } else if (data.status = "fail") {
                alertInfo(data.data);
            } else {
                alertInfo("未知错误");
            }
        }
    });
    function setVal() {
        if (true == $("#isMurder").is(':checked')) {
            $("#isMurder").attr("value", "1");
        } else {
            $("#isMurder").attr("value", "0");
        }
    }

    // 取消新增或修改操作
    function cancelAddOrEdit() {
    	if(!($("#fromSource").val() == '')) {
        	parent.afterCloseFinPage();
        	return;
        }
    	
        if ($("#id").val() == '') {
            parent.afterCloseAddWindow();
        } else {
            parent.afterCloseEditWindow();
        }
    }
    
  	// 案件信息
	function toSelectCases(){
		// 添加iframeSrc
		$("#frame_selectCase").attr("src", "${path}/cases/toSelectCase.action");
		// 打开弹出框
		$("#selectCase").window('open');
		adjustTanboxCenter(); // 弹窗位置居中
	}
  	
	function handleSelectCase(data) {
		$("input[name='caseName']").attr("value", data.caseName);
		$("input[name='caseNum']").attr("value", data.caseNum);
        $("input[name='caseId']").attr("value", data.id);

		afterCloseSelectWindow();
	}

	function handleAddCaseBack(caseId,caseName, caseNum) {
		$("input[name='caseName']").attr("value", caseName);
		$("input[name='caseNum']").attr("value", caseNum);
        $("input[name='caseId']").attr("value", caseId);

		afterCloseAddCases();
	}
	
	// 修改之后返回
	function afterCloseSelectWindow() {
		$("#selectCase").window('close');
	}
	
	// 新增案件信息跳转
	function toAddCases() {
		// 添加iframeSrc
		$("#frame_addCase").attr("src", "${path}/cases/toEditCases.action?fromSource="+"editFinances");
		// 打开弹出框
		$("#addCase").window('open');
		adjustTanboxCenter(); // 弹窗位置居中
	}
	
	// 添加之后返回
	function afterCloseAddCases() {
		$("#addCase").window('close');
	}

	// 选择存储位置
    function chooseStorage(){
        // 添加iframeSrc
        $("#frame_addStorage").attr("src", "${path}/storage/toSelectStorage.action");
        // 打开弹出框
        $("#addStorage").window('open');
        adjustTanboxCenter(); // 弹窗位置居中
    }


    function uploadImage(){
        var imagePath=$('#importPicture').val();
        if(!imagePath){
            alert('请上传文件！');
            return;
        }
        //判断上传文件的后缀名
        var strExtension = imagePath.substr(imagePath.lastIndexOf('.') + 1);
        if (strExtension != 'jpg' && strExtension != 'gif'
            && strExtension != 'png' && strExtension != 'bmp') {
            alert("请选择图片文件");
            return;
        }
        debugger;
        alert(imagePath);
        $.ajax({
            type: "POST",
            url: "${path}/finances/upload.action",
            data: { imgPath: $("#uploadFile").val() },
            cache: false,
            success: function(data) {
                alert("上传成功");
                $("#imgDiv").empty();
                $("#imgDiv").html(data);
                $("#imgDiv").show();
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert("上传失败，请检查网络后重试");
            }
        });
    }

    function handleSelectStorage(data) {
		$("input[name='storeLocation']").attr("value", data.name);
		afterCloseSelectStorageWindow();
	}
	
	// 选择之后返回
	function afterCloseSelectStorageWindow() {
		$("#addStorage").window('close');
	}
	
	function takePhoto() {
		//弹出高拍仪控件，返回图片物理路径跟图片base64编码
		window.open("${path}/resources/html/eloamScan.html","","width=600px; height=800px;");	
	}

</script>
</body>
</html>