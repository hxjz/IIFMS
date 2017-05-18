/**
 * 扩展datagrid textarea以及日期
 */
jQuery.extend(jQuery.fn.datagrid.defaults.editors, {
	textarea: {
		init : function(container, options) {
			var input;
			if(typeof options != 'undefined' && typeof options.width != 'undefined') {
				input = $(
					'<textarea class="datagrid-editable-input" style="resize:none;width:'+options.width+'"></textarea>').appendTo(container);
			} else {
				input = $(
					'<textarea class="datagrid-editable-input" style="resize:none;"></textarea>').appendTo(container);
			}
			input.tah({
				moreSpace:15,   // 输入框底部预留的空白, 默认15, 单位像素
				maxHeight:600,  // 指定Textarea的最大高度, 默认600, 单位像素
				animateDur:200  // 调整高度时的动画过渡时间, 默认200, 单位毫秒
			});
			return input;
		},
		getValue : function(target) {
			return $(target).val();
		},
		setValue : function(target, value) {
			$(target).val(value);
		}
	},
	my97 : {
		init : function(container, options) {
			var input;
			if(options.minDate) {
				input = $('<input class="Wdate" width="100" onclick="var minDate='+options.minDate+'();WdatePicker({minDate:minDate,dateFmt:\''+options.format+'\',readOnly:true});"  />').appendTo(container);
			} else {
				input = $('<input class="Wdate" width="100" onclick="WdatePicker({dateFmt:\''+options.format+'\',readOnly:true});"  />').appendTo(container);
			}
			return input;
		},
		getValue : function(target) {
			return $(target).val();
		},
		setValue : function(target, value) {
			$(target).val(value);
		},
		resize : function(target, width) {
			var input = $(target);
			if ($.boxModel == true) {
				input.width(width - (input.outerWidth() - input.width()));
			} else {
				input.width(width);
			}
		}
	}
});

//改变下拉框选项
function changeCombox(tabId,index,field,data,defaultVal) {
	var target = $('#'+tabId).datagrid('getEditor',{'index':index,'field':field}).target;
	target.combobox('clear');
	target.combobox('loadData',data);
	if('undefined' != typeof defaultVal) {
		target.combobox('setValue',defaultVal);
	}
};

/*
 *重新更新datagrid中显示数据
 *参数：id:页面ID
 *      pageNumber:加载页数
 *      pageSize:一页显示条数
 */
function reloadgrid (id,pageNumber,pageSize)  {
	if(pageNumber) {
		$('#'+id).datagrid('options').pageNumber = pageNumber;
		$('#'+id).datagrid('getPager').pagination({pageNumber: pageNumber});
	}
	if(pageSize) {
		$('#'+id).datagrid('options').pageSize = pageSize;	
		$('#'+id).datagrid('getPager').pagination({pageNumber: pageNumber});
	} 
	var page = {};
	page.pageNum = $('#'+id).datagrid('options').pageNumber;
	page.pageSize = $('#'+id).datagrid('options').pageSize;
	var queryParams = $('#'+id).datagrid('options').queryParams;
	 $.extend(queryParams,page);
	 $('#'+id).datagrid('options').queryParams = queryParams;
	 $('#'+id).datagrid('reload');
};

/*
 *重新更新datagrid中显示数据
 *参数：id:页面ID
 *参数：pageNumber:加载页数
 *参数：pageSize:一页显示条数
 */
function createTable(id){
    var params = buildSearchCond();
	//创建table
	$('#'+id).datagrid({
		queryParams:params,
		loadFilter:function(data){
			 if($('#'+id).datagrid('options').pagination) {
				 if('undefined' == typeof(data.total) || 'undefined' == (data.rows)){	 
					 if(data && data.message) {
						alertWarning(data.message);
					 } else {
						 alertWarning("数据错误,请联系管理员.");
					 }
					 return {total:0,rows:[]};
				 }
				 return data;
			 } else if(data.constructor != Array && 'undefined' == data.rows) {
				 if(data.message) {
					 alertWarning(data.message); 
				 } else {
					 alertWarning("数据错误,请联系管理员.");
				 }
				 return {total:0,rows:[]};
			 } else if(data.constructor == Array) {
				 return {total:1000,rows:data}; 
			 } else {
				 return data;
			 }
		}
	});
	
	//获取翻页控件
	var pg = $('#'+id).datagrid("getPager");
	if(pg){   
	   $(pg).pagination({
		   onSelectPage:function(pageNumber,pageSize){  //翻页/改变页面显示条数
			  reloadgrid(id,pageNumber,pageSize);
			  return false;
			}    
	   });    
	}

	//默认id为find是查询按钮--》显示第一页（查询条件只会在点击查询按钮或者触发查询按钮点击事件的时候重置）
	$('#find').bind('click',function(){
		findByCondition(id);
	});
};

function findByCondition(id){
		//查询参数直接添加在queryParams中
		var queryParams = $('#'+id).datagrid('options').queryParams;

		for (o in queryParams)
		{
			if(o.indexOf('filter_')>=0 || o.indexOf('order_')>=0) {
				queryParams[o] = null;
			}
		}
		var conds = buildSearchCond();
		$.extend(queryParams,conds);
		$('#'+id).datagrid('options').queryParams = queryParams;
		reloadgrid (id,1);
}

//封装查询条件
function buildSearchCond(){
	var params = {};
	$('input[name^="filter"]').each(function(){
		params[$(this).attr('name')] = $.trim($(this).val());
	});
	$('input[name^="order"]').each(function(){
		params[$(this).attr('name')] = $.trim($(this).val());
	});
	$('select[name^="filter"]').each(function(){
		var selectedItems = $(this).find("option:selected");
		if(selectedItems.length >0 ) {
			params[$(this).attr('name')] = $.trim($(this).find("option:selected").val());
		} else {
			params[$(this).attr('name')] = '';
		}
	});
	$('select[name^="order_"]').each(function(){
		var selectedItems = $(this).find("option:selected");
		if(selectedItems.length >0 ) {
			params[$(this).attr('name')] = $.trim($(this).find("option:selected").val());
		} else {
			params[$(this).attr('name')] = '';
		}
	});
	$('input[name*="LT_T"]').each(function(){
		if($(this).val()) {
			var date = stringToDate($(this).val());
			if(date){
				date = date.add("d",1);
				var name = $(this).attr('name');
				var text = date.format('yyyy-MM-dd');
				params[name] = text;
			}
		}
	});

	return params;
}

//修正弹框居中位置
function adjustTanboxCenter() {
	$(".window-shadow").remove();
	var winWidth = $(window).width();
	var winHeight = $(window).height();
	$(".window").each(function(){
		var $el = $(this);
		var boxWidth = $el.width();
		var boxHeight = $el.height();
		var top = (winHeight - boxHeight) / 2;
		var left = (winWidth - boxWidth) / 2;
		if (winHeight < boxHeight) {
			top = 0;
		} else {
			top = (winHeight - boxHeight) / 2;
		}
		if (winWidth < boxWidth) {
			left = 0;
		} else {
			left = (winWidth - boxWidth) / 2;
		}
		left+=$(window).scrollLeft();
		top+=$(window).scrollTop();
		
		$el.css({
		 	"position":"absolute",
			"top" : top,
			"left" : left
		});
	});
}

// 锁定页面提示
function showWarnDiv(title1,msg1){
	// 提示
	var win = $.messager.progress({
        title:title1,
        msg:msg1
    });
}

// 解锁页面
function closeWarnDiv(){
	$.messager.progress('close'); // 关闭提示
}