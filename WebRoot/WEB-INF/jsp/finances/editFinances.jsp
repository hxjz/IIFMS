<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>新增/修改财务</title>
	<%@include file="/WEB-INF/jsp/common/common.jsp"%>
</head>
<body>
	<form id="edit" name="editForm" method="post">
		<input type="submit" class="t_btnsty02" id="saveBtn" value="添加">
		<input type="button" id="cancel" class="t_btnsty02" onclick="closeCases();" value="取消">
		<input type="hidden" name="id" id="id" value="${finances.id}">
		<br>
	    	<table border="0">
	          <tr>
	    		   <td><span class="t_span01">案件名称：</span></td>
				   <td>
	               	  <input class="easyui-validatebox t_text w100" data-options="required:true,missingMessage:'请输入案件名称'" name="caseNum" type="text" value="${finances.cases.caseName}"/>
                       <span class="t_span02">*</span>
                   </td>
				   <td><input type="submit" class="t_btnsty01" id="selectBtn" value="选择"></td>
	               <td><span class="t_span01">案件编号：</span></td>
	               <td >
	               	  <input class="easyui-validatebox t_text w100"  data-options="required:true,missingMessage:'请输入案件名称'" name="caseName" type="text" value="${finances.financeNum}" /><span class="t_span02">*</span>
				   </td>
				  <td><input type="submit" class="t_btnsty01" id="addBtn" value="添加"></td>
	          </tr>
	          <tr>
				  <td><span class="t_span01">财务名称：</span></td>
				  <td colspan="2">
					  <input class="easyui-validatebox t_text w140"  data-options="required:true,missingMessage:'请输入财务名称'" name="financeName" type="text" value="${finances.financeName}" /><span class="t_span02">*</span>
				  </td>
	          	<td><span class="t_span01">财务种类：</span></td>
	          	<td colspan="2">
	          	    <input type="hidden" name="financeTypeHid" id="financeTypeHid" value="${finances.financeType}">
                 	<select class="w120" name="financeType" id="financeType">
                 		<option value="">请选择</option>
                 		<option value="1">手迹痕迹</option>
                 		<option value="2">足迹痕迹</option>
                 		<option value="3">……</option>
                 	</select>
                 	<span class="t_span02">*</span>
                 </td>
	          </tr>
				<tr>
					<td><span class="t_span01">财务编号：</span></td>
					<td>
						<input class="easyui-validatebox t_text w140"  data-options="" name="financeNum" type="text" value="${finances.financeNum}" />
					</td>
                    <td><span class="t_span01">财务来源：</span></td>
                    <td>
                        <input type="hidden" name="financeSourceHid" id="financeSourceHid" value="${finances.financeSource}">
                        <select class="w50" name="financeSource" id="financeSource">
                            <option value="0">请选择</option>
                            <option value="1">现场勘验</option>
                            <option value="2">移交</option>
                            <option value="3">……</option>
                        </select>
                    </td>
                    <td><span class="t_span01">来源单位：</span></td>
                    <td>
                        <input type="hidden" name="sourceOfficeHid" id="sourceOfficeHid" value="${finances.sourceOffice}">
                        <select class="w50" name="sourceOffice" id="sourceOffice">
                            <option value="0">请选择</option>
                            <option value="1">单位1</option>
                            <option value="2">单位2</option>
                            <option value="3">……</option>
                        </select>
                    </td>
				</tr>
	          <tr>
                  <td><span class="t_span01">查获人：</span></td>
                  <td>
                      <input class="easyui-validatebox t_text w140"  data-options="" name="seizedMan" type="text" value="${finances.seizedMan}" />
                  </td>
	               <td><span class="t_span01">查获时间：</span></td>
	               <td colspan="4">
	               	  <input type="hidden" name="oldSeizedTimeStart" value="${finances.seizedTimeStart}">
	               	  <div id="test1">
	               	  	<input class="easyui-datetimebox w150" data-options="" name="seizedTimeStart" id="seizedTimeStart" type="text" value="${finances.seizedTimeStart}"/><span class="t_span01">起</span><input type="button" class="t_btnsty01" id="changeTimeStart1" value="模糊"/>
	               	  </div>
	               	  <div id="test2" hidden="true">
	               	  	<input class="easyui-datebox w150" data-options="" name="seizedTimeStart" id="seizedTimeStart" type="text" value="${finances.seizedTimeStart}"/><span class="t_span01">起</span><input  type="button" class="t_btnsty01" id="changeTimeStart2" value="精确"/>
	               	  </div>
	               	  <input class="easyui-datetimebox w150" data-options="" name="seizedTimeEnd" id="seizedTimeEnd" type="text" value="${finances.seizedTimeEnd}"/><span class="t_span01">止</span>
	               </td>
			  </tr>
	          <tr>
	          	   <td><span class="t_span01">财务说明：</span></td>
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
                    <td><span class="t_span01">财务照片：</span></td>
                    <td colspan="4">
                        <textarea id="imageSign" name="caseDesc" style="width:370px;height:100px">${finances.imageSign}</textarea>
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
                        <input class="easyui-validatebox t_text w370" data-options="" name="storeLocation" type="text" value="${finances.storeLocation}"/>
                    </td>
                     <td>  <input type="submit" class="t_btnsty01" id="chooseLocation" value="选择"></td>
                </tr>
                <tr>
                    <td><span class="t_span01">财务识别码：</span></td>
                    <td colspan="4">
                        <input class="easyui-validatebox t_text w370" data-options="" name="financeCode" type="text" value="${finances.financeCode}"/>
                    </td>
                    <td>  <input type="submit" class="t_btnsty01" id="print" value="打印"></td>
                </tr>
                <tr>
                    <td><span class="t_span01">电子识别码：</span></td>
                    <td colspan="4">
                        <input class="easyui-validatebox t_text w370" data-options="" name="digitalCode" type="text" value="${finances.digitalCode}"/>
                    </td>
                    <td>  <input type="submit" class="t_btnsty01" id="write" value="写入"></td>
                </tr>
	</table>
	</form>
	<script type="text/javascript">
		$(document).ready(function(){
		   //Temp Start
		   var caseTypev = $("#caseTypeHid").val();
		   $("#caseType").val(caseTypev);
		   //Temp End

		   // 初始化第二个隐藏时间控件的值
		    $("input[textboxname='caseTimeStart']:eq(1)").attr("value","");
			$("#test2").hide();
		   	$("#changeTimeStart1").click(function(){
		   		$("input[textboxname='caseTimeStart']").attr("value","");
		   		$("input[name='caseTimeStart']").attr("value","");
		  		$("#test1").hide();
		  		$("#test2").show();
		   	});
		   	$("#changeTimeStart2").click(function(){
		   		$("input[textboxname='caseTimeStart']").attr("value","");
		   		$("input[name='caseTimeStart']").attr("value","");
		  		$("#test2").hide();
		  		$("#test1").show();
		   	});
		});

		//表单提交
		<%--$('#edit').form({--%>
		    <%--url:'${path}/finances/saveFinances.action',--%>
		    <%--onSubmit:function(){--%>
		        <%--return $(this).form('validate');--%>
		    <%--},--%>
		    <%--success:function(returnData){--%>
				<%--alert(returnData);--%>
		    	<%--var data = JSON.parse(returnData); // 转换成json对象--%>
		    	<%--if(data.status == "success"){--%>
		    		<%--parent.alertInfo(data.data);--%>
		    		<%--if($("#id").val() == ''){--%>
		    			<%--parent.afterCloseAddCases();--%>
		    		<%--}else{--%>
		    			<%--parent.afterCloseEditCases();--%>
		    		<%--}--%>
		    	<%--} else if(data.status="fail"){--%>
		    		<%--alertInfo(data.data);--%>
		    	<%--} else {--%>
		    		<%--alertInfo("未知错误");--%>
		    	<%--}--%>
		    <%--}--%>
		<%--});--%>

		//表单提交
		$('#edit').form({
			url:'${path}/finances/saveFinances.action',
			onSubmit:function(){
				return $(this).form('validate');
			},
			success:function(returnData){
				data = JSON.parse(returnData); // 转换成json对象
				if(data.status == "success"){
					parent.alertInfo(data.data);
					if($("#id").val() == ''){
						parent.afterCloseAddCases();
					}else{
						parent.afterCloseEditCases();
					}
				} else if(data.status="fail"){
					alertInfo(data.data);
				} else {
					alertInfo("未知错误");
				}
			}
		});
		function setVal() {
			if(true==$("#isMurder").is(':checked')) {
				$("#isMurder").attr("value","1");
			}else {
				$("#isMurder").attr("value","0");
			}
		}

		//取消新增或修改操作
		function closeCases(){
			if($("#id").val() == ''){
				parent.afterCloseAddCases();
			}else{
				parent.afterCloseEditCases();
			}
		}
	</script>
</body>
</html>