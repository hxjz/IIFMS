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
function formatProject(value, rowData, index){
	if(rowData.projectId == null||rowData.projectId == ""){
		return rowData.projectId;
	}else{
		return rowData.projectId.name;
	}
}

// 查询列表中户型
function formatHouseType(value, rowData, index){
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
                enumChinsesName='出库原因';
                break;
            case 'StorageTypeEnum':
                enumChinsesName='存储类型';
                break;
            case 'DepartmentTypeEnum':
                enumChinsesName='单位名称';
                break;
            case 'JurisdictionSectionEnum':
                enumChinsesName='管辖单位';
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
            return "登记";
        }else if(2===row.financeState){
            return "入库";
        }else if(3===row.financeState){
        	return  '出库';
		}else {
            return "登记";
        }
	}else{
		return '登记';
	}
}

// TODO 若后期类型在其他地方用到为 String 则需要将 switch 换位 if(type==1)
// 格式化财物类型
function formatFinanceType(value,row,index) {
	var financeType=row.financeType;
	var typeName;
    if(financeType){
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
}

//格式化财物类型
function formatJurisdiction(value,row,index) {
    if('1'==row.jurisdiction){
    		return '县局';
		}else if('2'==row.jurisdiction) {
			return '市局';
		}else {
			return '其他';
		}
}

// 格式化案件类型
function formatCaseType(value){
	if(value){
		var  valueName;
		switch (value){
			case 1:
				valueName='危害国家安全罪';
				break;
            case 2:
                valueName='危害公共安全罪';
                break;
            case 3:
                valueName='放火案';
                break;
            case 4:
                valueName='决水案';
                break;
            case 5:
                valueName='爆炸案';
                break;
            case 6:
                valueName='投毒案';
                break;
            case 7:
                valueName='以其他方法危害公共安全案';
                break;
            case 8:
                valueName='失火案';
                break;
            case 9:
                valueName='过失决水案';
                break;
            case 10:
                valueName='过失爆炸案';
                break;
            case 11:
                valueName='过失投毒案';
                break;
            case 12:
                valueName='过失以其他方法危害公共安全案';
                break;
            case 13:
                valueName='破坏社会主义市场经济秩序案';
                break;
            case 14:
                valueName='侵害公民人身权利、民主权利案';
                break;
            case 15:
                valueName='故意杀人案';
                break;
			case 16:
            	valueName='过失致人死亡案';
            	break;
			case 17:
            	valueName='故意伤害案';
            	break;
            case 18:
                valueName='过失致人重伤案';
                break;
            case 19:
                valueName='强奸案';
                break;
            case 20:
                valueName='绑架案';
                break;
            case 21:
                valueName='侵犯财产案';
                break;
            case 22:
                valueName='抢劫案';
                break;
            case 23:
                valueName='盗窃案';
                break;
            case 24:
                valueName='诈骗案';
                break;
            case 25:
                valueName='抢夺案';
                break;
            case 26:
                valueName='妨碍社会管理秩序案';
                break;
            case 27:
                valueName='危害国防利益案';
                break;
            case 28:
                valueName='贪污贿赂案';
                break;
            case 29:
                valueName='渎职案';
                break;
            case 30:
                valueName='军人违反职责案';
                break;
            case 31:
                valueName='其他案件';
                break;
        }
		return valueName;

	}
}


// 格式化时间
function formatSingelDate(value){
	if(value&&value.length>10){
        if(value.indexOf(':')>-1){  // 2017-07-15 01:12:13 切割
                value=value.substr(0,10);
        }else{  // getTime() 格式的
            var date=new Date(value);
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
            value= date.getFullYear()+"-"+month+"-"+day;
        }
	}
    return value;
}

//勘验时间(起-止)
function formatInspectionTime(value,row,index){
    if(row.cases&&row.cases.id){
        var inspectionTimeStart=row.cases.inspectionTimeStart;
        var inspectionTimeEnd=row.cases.inspectionTimeEnd;
        return formatSingelDate(inspectionTimeStart) +' - '+ formatSingelDate(inspectionTimeEnd);
    }
}

//案发时间(起-止)
function formatCaseTime(value,row,index){
    if(row.cases&&row.cases.id){
        var caseTimeStart=row.cases.caseTimeStart;
        var caseTimeEnd=row.cases.caseTimeEnd;
        return  formatSingelDate(caseTimeStart)+' - '+formatSingelDate(caseTimeEnd);
    }
}