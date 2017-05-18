function loadUrl(id,url,name,currentId){
	if($('#'+id,'#tt').length > 0 ){
		var index = $('div.panel','#tt').index($('#'+id,'#tt').parents('.panel:first'));
		$('#tt').tabs('close', index);
	}
	if(!currentId) {
		currentId = "";
	}
	var content = '<div style="height:100%; border:0px solid #f00;" class="iif_frame_tab_content" id="'+id+'" parentId="'+currentId+'"><iframe src="'+url+'" width="100%" height="710"  frameborder="0" scrolling="auto"></iframe></div>';
	$('#tt').tabs('add',{
		title: name,
		content: content,
		closable: true
	});
	adjsutWH();
};

function closeTab(id,parentId,tableId){
	if($('#'+id,'#tt').length > 0 ){
		var index = $('div.panel','#tt').index($('#'+id,'#tt').parents('.panel:first'));
		$('#tt').tabs('close', index);
	};
	if(parentId) {
		if(!tableId ){
			tableId = $('#'+parentId,'#tt').find('iframe:first').contents().find('.datagrid-view').children('table').attr('id');
		}
		$('#'+parentId,'#tt').find('iframe:first')[0].contentWindow.reloadgrid(tableId);
	} 
	adjsutWH();
};

function getCurrentTabId(){
	var pp = $('#tt').tabs('getSelected');
	return $(pp).find('div.iif_frame_tab_content').attr('id');
};

function getfrmTabId(){
	var pp = $('#tt').tabs('getSelected');
	return $(pp).find('div.iif_frame_tab_content').attr('parentId');
};

//关闭当前标签
function closeThis(){
	var index = $('#tt ul.tabs li').index($selectedTab);
	if(!isProvider || index >0 ){
		$('#tt').tabs('close', index);
	}
};

//关闭其他标签页
function closeOthers(){
	var index = $('#tt ul.tabs li').index($selectedTab);
	$('#tt ul.tabs li').each(function(i,el){
		if(i != index) {
			if(!isProvider || i >0 ){
				var ind = $('#tt ul.tabs li').index($(el));
				$('#tt').tabs('close', ind);
			}
		}
	});
};

//关闭所有标签页
function closeAll(){
	$('#tt ul.tabs li').each(function(i,el){
		if(!isProvider || i >0 ){
			var ind = $('#tt ul.tabs li').index($(el));
			$('#tt').tabs('close', ind);
		}
	});
};
