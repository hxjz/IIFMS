<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>涉案财物-财物统计</title>
    <%@include file="/WEB-INF/jsp/common/common.jsp" %>
</head>

<body>
<div class="t_rightcontainer">
    <div class="t_oneblock">
        <div class="t_oneblock_h">
            <h3>财物统计</h3>
        </div>
        <!-- t_oneblock_h-->
        <form>
            <div class="t_oneblock_c pr">
                <div class="nobortable pt10 pl10">
                    <span class="t_span02">起止时间：</span>
                        <input class="easyui-datebox t_text w150" editable="false"
                               name="filter_and_updateTime_GE_T" type="text"/>
                        &nbsp;<span class="t_span02">起</span>
                        <input class="easyui-datebox t_text w150" editable="false"
                               name="filter_and_updateTime_LE_T" type="text"/>
                        &nbsp;<span class="t_span02">止</span>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <input class="t_btnsty01" id="find" name="select" type="button" value="查询" />
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <input class="t_btnsty04" id="export" name="select" type="button" value="导出财物统计表" />
                </div>
                <!--nobortable -->
            </div>
        </form>
        <!-- t_oneblock_c-->
    </div>
    <!--t_oneblock -->
</div>
<!--t_rightcontainer -->
<div class="t_rightcontainer">
    <div class="t_oneblock">
        <div class="t_oneblock_h">
            <h3>财物统计表</h3>
        </div>
        <!-- t_oneblock_h-->
        <div class="t_oneblock_c pr">
            <div class="hastable mt10">
                <table id="dg1" style="width:100%; height:350px" data-options="singleSelect:true,collapsible:false,
			            url:'${path}/finances/showStatistics.action',
			            method:'post',
			            fitColumns:true,
			            rownumbers:true,
						autoRowHeight:true,
						pagination:true,
						pageNumber:1,
						pageSize:50,
						pageList:[10,30,50]">
                    <thead>
                    <tr>
                        <th data-options="field:'typeName',width:80" align="center">财物种类</th>
                        <th data-options="field:'total',width:80" align="center">在库</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <!--hastable -->
        </div>
        <!-- t_oneblock_c-->
    </div>
    <!--t_oneblock -->
</div>
<!--t_rightcontainer -->


<script type="text/javascript">
    $(function () {
        createTable('dg1');

        // 双击行显示财物详细信息
//        $('#dg1').datagrid({
//            onDblClickRow: function (rowIdex, rowData) {
//                toDetailPage();
//            }
//        });



        $('#export').click(function(){
          $.ajax({
              type: 'POST',
              url: "${path}/finances/export.action",
              success:function(returnData){
                  alert(returnData);
//                  data = JSON.parse(returnData); // 转换成json对象
//                  if(data.status == "success"){
//                      parent.alertInfo(data.data);
//                      parent.afterCloseInstock();
//                  } else if(data.status="fail"){
//                      alertInfo(data.data);
//                  } else {
//                      alertInfo("未知错误");
//                  }
              },
              error:function(){
                  alert("失败");
              }
          });
        });

    });

</script>
</body>
</html>