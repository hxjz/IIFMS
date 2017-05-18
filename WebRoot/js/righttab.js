$(document).ready(function() {
	$.tabs({
		tabs: $('#tab_ulpart1').find('.tab'),
		contents: $('#tab_contents1').find('.info'),
		evtType: 'click'
	});
	$.tabs({
		tabs: $('#tab_ulpart2').find('.tab'),
		contents: $('#tab_contents2').find('.info'),
		evtType: 'click'
	});
});