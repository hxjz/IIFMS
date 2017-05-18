//项目同时联动查询楼栋+户型
function linkBuilding(){
	
	// 选中元素的value
	var id=$("#projectId").val();
	
	//alert(contextPath);
	
	if(id=='' || id==undefined){
		$("#bildingId").empty();
		$("<option value=''>请选择</option>").appendTo("#bildingId");
		$("#housetypeId").empty();
		$("<option value=''>请选择</option>").appendTo("#housetypeId");
		$("#floorNumber").empty();
		$("<option value=''>请选择</option>").appendTo("#floorNumber");
		return;
	}
	// 查询楼栋+户型
	$.ajax({
		url:contextPath + "/basesource/room.action?method=buildingNo",
		data:{'id':id},
		dataType:"json",
		success:function(buildMap){
			if (buildMap.status == "success") {
				$("#bildingId").empty();
				$("<option value=''>请选择</option>").appendTo("#bildingId");
				$("#housetypeId").empty();
				$("<option value=''>请选择</option>").appendTo("#housetypeId");
				if(buildMap.data != null){
					var buildingJson = eval(buildMap.data); //数组         
		             $.each(buildingJson, function (index, item) {  
		                 //循环获取数据    
		                 $("<option value="+buildingJson[index].id+">"+buildingJson[index].buildingName+"</option>").appendTo("#bildingId");
		             });
				}
				if(buildMap.houseType != null){
					for(var i=0;i<buildMap.houseType.length;i++){
						$("<option value="+buildMap.houseType[i].hid+">"+buildMap.houseType[i].houseName+"</option>").appendTo("#housetypeId");
					}
				}
			}else{
				alert(result.status);
			}
		}
	});
}

// 联动楼层数
function linkFloorNo(){
	// 选中元素的value
	var id=$("#bildingId").val();
	if(id=='' || id==undefined){
		$("#floorNumber").empty();
		$("<option value=''>请选择</option>").appendTo("#floorNumber");
		return;
	}
	// 查询楼层
	$.ajax({
		url:contextPath + "/basesource/room.action?method=floorInfo",
		data:{'id':id},
		dataType:"json",
		success:function(floorMap){
			if (floorMap.status == "success") {
				$("#floorNumber").empty();
				$("<option value=''>请选择</option>").appendTo("#floorNumber");
				for(var i=1;i<=floorMap.floorNo;i++){
					var floorName = "第" + i + "层";
					$("<option value=" + i + ">" + floorName + "</option>").appendTo("#floorNumber");
				}
			}else{
				alert(floorMap.data);
			}
		}
	}); 
}

//联动查询房间号
function linkRoomNumber(){
	
	// 选中元素的value
	var projectId = $("#projectId").val();
	var buildingId=$("#bildingId").val();
	var floorNumber=$("#floorNumber").val();
	if(floorNumber=='' || floorNumber==undefined){
		$("#roomNumber").empty();
		$("<option value=''>请选择</option>").appendTo("#roomNumber");
		$("select[name='currentState'] option[value='']").attr("selected","selected");
		return;
	}
	// 查询房间号
	$.ajax({
		url:contextPath + "/basesource/room.action?method=getRoomNumberList",
		data:{'projectID':projectId, 'buildingId':buildingId, 'floorNumber':floorNumber},
		dataType:"json",
		success:function(result){
			if (result.status == "success") {
				$("#roomNumber").empty();
				$("<option value=''>请选择</option>").appendTo("#roomNumber");
				$("select[name='currentState'] option[value='']").attr("selected","selected");
				if(result.data != null){
					var resultArr = result.data; //数组    
		             for(var i=0; i<resultArr.length; i++){
		            	 $("<option value="+resultArr[i].roomId+">"+resultArr[i].roomNumber+"</option>").appendTo("#roomNumber");
		             }
				}
			}else{
				alert(result.status);
			}
		}
	});
}

//项目只联动查询户型信息
function linkHouseType(){
	// 选中元素的value
	var id=$("#projectId").val();
	
	if(id=='' || id==undefined){
		$("#houseTypeId").empty();
		$("<option value=''>请选择</option>").appendTo("#houseTypeId");
		return;
	}
	// 查询楼栋
	$.ajax({
		url:contextPath + "/basesource/availableroom.action?method=queryHouse",
		data:{'id':id},
		dataType:"json",
		success:function(buildMap){
			if (buildMap.status == "success") {
				$("#houseTypeId").empty();
				$("<option value=''>请选择</option>").appendTo("#houseTypeId");
				var json = eval(buildMap.data); //数组         
	             $.each(json, function (index, item) {  
	                 //循环获取数据    
	                 $("<option value="+json[index].id+">"+json[index].houseName+"</option>").appendTo("#houseTypeId");
	             });
			}else{
				alert(result.data);
			}
		}
	});
	
}

/*************************格式化******************************/
//查询列表中项目名称
function formateProject(value, rowData, index){
	if(rowData.projectId == null||rowData.projectId == ""){
		return rowData.projectId;
	}else{
		return rowData.projectId.name;
	}
}

// 查询列表中户型
function formateHouseType(value, rowData, index){
	if(rowData.houseTypeId == null||rowData.houseTypeId == ""){
		return rowData.houseTypeId;
	}else{
		return rowData.houseTypeId.housetypeName;
	}
}
