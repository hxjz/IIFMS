<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>新增/修改财物</title>
    <%@include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
<form id="edit" name="editForm" method="post">
    <input type="submit" class="t_btnsty02" id="saveBtn" value="添加">
    <input type="button" id="cancel" class="t_btnsty02" onclick="cancelAddOrEdit();" value="取消">
    <input type="hidden" name="id" id="id" value="${finances.id}">
    <input type="hidden" name="fromSource" id="fromSource" value="${fromSource}"/>
    <br>
    <table border="0">
        <tr>
            <td><span class="t_span01">案件名称：</span></td>
            <td>
                <input class="easyui-validatebox t_text w100" data-options="required:true,missingMessage:'请输入案件名称'"
                       name="caseName" type="text" value="${finances.cases.caseName}" readonly="readonly"/>
                <span class="t_span02">*</span>
            </td>
            <td><input type="button" class="t_btnsty01" id="toSelectCase" onclick="toSelectCases()" value="选择"></td>
            <td><span class="t_span01">案件编号：</span></td>
            <td>
                <input class="easyui-validatebox t_text w100" data-options="required:true,missingMessage:'请输入案件编号'"
                       name="caseNum" type="text" value="${finances.cases.caseNum}" readonly="readonly"/><span class="t_span02">*</span>
            </td>
            <td><input type="button" class="t_btnsty01" id="toAddCase" onclick="toAddCases()" value="添加"></td>
        </tr>
        <tr>
            <td><span class="t_span01">财物名称：</span></td>
            <td colspan="2">
                <input class="easyui-validatebox t_text w140" data-options="required:true,missingMessage:'请输入财物名称'"
                       name="financeName" type="text" value="${finances.financeName}"/><span class="t_span02">*</span>
            </td>
            <td><span class="t_span01">财物种类：</span></td>
            <td colspan="2">
                <input type="hidden" name="financeTypeHid" id="financeTypeHid" value="${finances.financeType}">
                <select class="w140" name="financeType" id="financeType">
                    <option value="">请选择</option>
                    <c:forEach items="${financeTypeList}" var="object">
                        <option value="${object.key}">${object.value}</option>
                    </c:forEach>
                </select>
                <span class="t_span02">*</span>
            </td>
        </tr>
        <tr>
            <td><span class="t_span01">财物编号：</span></td>
            <td colspan="2">
                <input class="easyui-validatebox t_text w140" data-options="" name="financeNum" type="text"
                       value="${finances.financeNum}"/>
            </td>
            <td><span class="t_span01">财物来源：</span></td>
            <td colspan="2">
                <input type="hidden" name="financeSourceHid" id="financeSourceHid" value="${finances.financeSource}">
                <select class="w140" name="financeSource" id="financeSource">
                    <option value="">请选择</option>
                    <c:forEach items="${financeSourceList}" var="object">
                        <option value="${object.key}">${object.value}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><span class="t_span01">查获人：</span></td>
            <td colspan="2">
                <input class="easyui-validatebox t_text w140" data-options="" name="seizedMan" type="text"
                       value="${finances.seizedMan}"/>
            </td>
            <td><span class="t_span01">来源单位：</span></td>
            <td colspan="2">
                <input type="hidden" name="sourceOfficeHid" id="sourceOfficeHid" value="${finances.sourceOffice}">
                <select class="w140" name="sourceOffice" id="sourceOffice">
                    <option value="">请选择</option>
                    <option value="1">单位1</option>
                    <option value="2">单位2</option>
                    <option value="3">……</option>
                </select>
            </td>
        </tr>
        <tr>
            <td><span class="t_span01">查获时间：</span></td>
            <td colspan="3">
                <div id="test1">
                    <input class="easyui-datetimebox w150" data-options="" name="seizedTimeStart" id="seizedTimeStart"
                           type="text" value="${finances.seizedTimeStart}"/><span class="t_span01">起</span>
                    <input type="button" class="t_btnsty01" id="changeTimeStart1" value="模糊"/>
                </div>
                <div id="test2" hidden="true">
                    <input class="easyui-datebox w150" data-options="" name="seizedTimeStart" id="seizedTimeStart"
                           type="text" value="${finances.seizedTimeStart}"/><span class="t_span01">起</span>
                    <input type="button" class="t_btnsty01" id="changeTimeStart2" value="精确"/>
                </div>
            </td>
            <td colspan="2">
                <input class="easyui-datetimebox w150" data-options="" name="seizedTimeEnd" id="seizedTimeEnd"
                       type="text" value="${finances.seizedTimeEnd}"/><span class="t_span01">止</span>
            </td>
        </tr>
        <tr>
            <td><span class="t_span01">财物说明：</span></td>
            <td colspan="5">
                <input class="easyui-validatebox t_text w420" data-options="" name="financeDesc" type="text" value="${finances.financeDesc}"/>
            </td>
        </tr>
        <tr>
            <td><span class="t_span01">备注：</span></td>
            <td colspan="5">
                <input class="easyui-validatebox t_text w420" data-options="" name="financeMemo" type="text" value="${finances.financeMemo}"/>
            </td>
        </tr>
        <tr>
            <td><span class="t_span01">财物照片：</span></td>
            <td colspan="4">
                <textarea id="imageSign" name="caseDesc"
                          style="width:370px;height:100px">${finances.imageSign}</textarea>
            </td>
            <td>
                <input type="submit" class="t_btnsty01" id="capturePicture" value="拍照">
                <input type="submit" class="t_btnsty01" id="importPicture" value="导入">
                <input type="submit" class="t_btnsty01" id="removePicture" value="移除">
            </td>
        </tr>
        <tr>
            <td><span class="t_span01">存放位置：</span></td>
            <td colspan="4">
                <input class="easyui-validatebox t_text w370" data-options="" name="storeLocation" type="text"
                       value="${finances.storeLocation}"/>
            </td>
            <td><input type="submit" class="t_btnsty01" id="chooseLocation" value="选择"></td>
        </tr>
        <tr>
            <td><span class="t_span01">财物识别码：</span></td>
            <td colspan="4">
                <input class="easyui-validatebox t_text w370" data-options="" name="financeCode" type="text"
                       value="${finances.financeCode}"/>
            </td>
            <td><input type="submit" class="t_btnsty01" id="print" value="打印"></td>
        </tr>
        <tr>
            <td><span class="t_span01">电子识别码：</span></td>
            <td colspan="4">
                <input class="easyui-validatebox t_text w370" data-options="" name="digitalCode" type="text"
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
		
		afterCloseSelectWindow();
	}
	
	function handleAddCaseBack(caseName, caseNum) {
		$("input[name='caseName']").attr("value", caseName);
		$("input[name='caseNum']").attr("value", caseNum);
		
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
</script>
</body>
</html>