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


// 格式化 枚举类型
function formatEnumType(value,row,index) {
	if(row.enumName){
		var enumEnglishName=row.enumName;
		var enumChinsesName;
		switch (enumEnglishName){
			case 'CaseTypeEnum':
				enumChinsesName='案件类型';
				break;
            case 'FinanceTypeEnum':
                enumChinsesName='财物类型';
                break;
            case 'FinanceSourceEnum':
                enumChinsesName='财物来源';
                break;
            case 'FinanceStateEnum':
                enumChinsesName='财物状态';
                break;
            case 'OutstockReasonTypeEnum':
                enumChinsesName='出库状态';
                break;
            case 'StorageTypeEnum':
                enumChinsesName='存储类型';
                break;
            case 'DepartmentTypeEnum':
                enumChinsesName='单位名称';
                break;
			default:
				enumChinsesName='未知类型'
		}
		return enumChinsesName;
	}else{
		return '未知类型';
	}
}


// 格式化财物状态
function formatFinanceState(value,row,index) {  // TODO  0为false
	if(row.financeState){
        if(1===row.financeState) {
            return "在库";
        }else if(2===row.financeState){
            return "出库";
        }else {
            return "登记";
        }
	}else{
		return '异常';
	}
}

// TODO 若后期类型在其他地方用到为 String 则需要将 switch 换位 if(type==1)
// 格式化财物类型
function formatFinanceType(value,row,index) {
	var financeType=row.financeType;
	var typeName;
    if(row.financeType){
    	switch (financeType){
			case 1:
				typeName='手印痕迹';
				break;
            case 2:
                typeName='足迹痕迹';
                break;
            case 3:
                typeName='工具痕迹';
                break;
            case 4:
                typeName='文件痕迹';
                break;
            case 5:
                typeName='枪弹痕迹';
                break;
            case 6:
                typeName='特殊痕迹';
                break;
            case 7:
                typeName='理化物证';
                break;
            case 8:
                typeName='电子物证';
                break;
            case 9:
                typeName='电子物证';
                break;
            case 10:
                typeName='视听物证';
                break;
            case 11:
                typeName='毒化物证';
                break;
			default:
				typeName='普通物证';
				break;
		}
		return typeName;
    }else{
    	return '无';
	}


	function formateSingelDate(value,row,index){
			if(value.indexOf(':')>0){  // 字符串
				if(value.length>10){
					value.substr(0,10);
				}
			}else{  // 时间戳格式
					var date=new Date(value);
               	     value= date.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
			}
			return value;
	}
}