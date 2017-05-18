/**
 * @author 
 * @downlaod 
 */
jQuery.tabsView = function(config){	
	// 初始化配置项	
	this.setting = {
		tabs: null,
		contents: null,
		lastTab: null,
		lastContent: null,
		lastIndex: 0,
		defaultIndex: 0,
		hashFirst: false,
		showAll: false,
		ajax: null,
		evtType: 'click',
		focusEvt: true,
		callback: null
	};
	
	// 合并配置信息
	$.extend(this.setting, config);
	
	// 初始化
	this.initializer();
	
	return this;
};

jQuery.tabsView.prototype = {
	/**
	 * 初始化 tabView
	 * 
	 * @method initializer
	 * @return {Object} jQuery.tabsView
	 */
	initializer: function(){
		// 获得默认的 lastTab 和 lastContent
		this.getDefTab();
		this.getDefContent();
		
		// 设置默认的选中内容
		this.setDefSelection();
		
		// 绑定 tabView 组件的 UI 处理函数
		this.bindUI();
		
		return this;
	},
	setDefSelection: function(){
		var tabsView = this,
		    setting = this.setting, 
			tabs = setting.tabs,
			contents = setting.contents,
			tabHash = this._getTabHash(),
			showAll = setting.showAll;
		
		if (!showAll) {
			// 如果有 url 的 hash值，默认显示项的设置以 hash 值为优先设置
			if (tabHash && setting.hashFirst) {
				$(tabs).each(function(i, tab){
					var curTitle = $(tab).attr('title');
					
					if (curTitle === tabHash) {
						tabsView.setCurrent(i);
					}
				});
			}
			else {
				// 没有 URL 的 hash 值，则显示配置项中设置的默认项
				this.setCurrent(setting.defaultIndex);
			}
		}
		else{
			// 显示所有的 contents
			$(contents).removeClass('hide');
		}
		
		return this;
	},
	/**
	 * 给 tabs 绑定事件
	 * 
	 * @method fire
	 * @return {Object} jQuery.tabsView
	 */
	bindUI: function(){
		var tabsView = this,
		    setting = this.setting, 
			tabs = setting.tabs;
		
		// 给每个 tab 绑定处理方法
		$(tabs).each(function(i, tab){
			var curTab = $(tab);
			
			curTab[setting.evtType](function(evt){
				// 当前 tab 不触发事件
				if (!curTab.hasClass('current')) {
					tabsView.setCurrent(i);
				}
				// 阻止冒泡和默认行为事件
				evt.preventDefault();
				evt.stopPropagation();
			});
			
			// Tab 键切换事件，使用 focusein 事件就可以了
			if (setting.focusEvt) {
				curTab.focusin(function(evt){
					// 当前 tab 不触发事件
					if (!curTab.hasClass('current')) {
						tabsView.setCurrent(i);
					}
				});
			}
		});
		
		return this;
	},
	_afterTabSelectedChange: function(){
		var callback = this.setting.callback;
		
		// 如果设置了 tab 切换完成后的自定义方法，则执行它
		if (callback && $.isFunction(callback)) {
			callback();
		}
		
		return this;
	},
	/**
	 * 设置当前选中项的样式和内容
	 * 
	 * @method setCurrent
	 * @param {Number} curTabIndex
	 * @return {Object} jQuery.tabsView
	 */ 
	setCurrent: function(curTabIndex){
		var setting = this.setting,
		    tabs = setting.tabs, 
		    contents = setting.contents, 
			curTab = tabs[curTabIndex], 
			curCnt = contents[curTabIndex],
			fileName = '';
		
		// 移除上次选中的 tab 的选中样式
		if (setting.lastTab) {
			$(setting.lastTab).removeClass('current');
		}
		// 隐藏上次 tab 项相关的内容
		if (setting.lastContent) {
			$(setting.lastContent).addClass('hide');
		}
		
		// 将当前选中的 tab 显示为选中样式
		$(curTab).addClass('current');
		// 当前标签显示为选中样式后，当前标签就是上次选中标签了
		setting.lastTab = curTab;
		// 当前标签显示为选中样式后，当前标签的索引值就是上次选中标签索引值了
		setting.lastIndex = curTabIndex;
		
		// 如果是 ajaxTab
		if (setting.ajax) {
			fileName = $(curTab).attr('title');
			this.ajaxInsert(fileName);
		}
		else {
			// 如果不是 ajaxTab				
			$(curCnt).removeClass('hide'); // 显示当前选中项的相关内容
			setting.lastContent = curCnt; // 当前选中项的相关内容在现实后就是上次的选中项显示内容了
			
			this._afterTabSelectedChange();
		}
		
		return this;
	},
	/**
	 * 显示 ajax 请求返回来的数据
	 * 
	 * @method ajaxInsert
	 * @param {String} fileName
	 * @return {Object} jQuery.tabsView
	 */
	ajaxInsert: function(fileName){
		var tabView = this,
		    setting = this.setting,
		    ajaxSet = setting.ajax,
		    curAjaxType = 'GET', 
			curAjaxTarget = null, 
			curAjaxPath = '', 
			curExtName = '.js', 
			curAjaxDataType = 'JSON';
		
		if (!ajaxSet.target || !ajaxSet.path) {
			return this;
		}
		
		// 获得请求类型
		if (ajaxSet.type) {
			curAjaxType = ajaxSet.type.toUpperCase();
		}
		// 获得请求数据类型
		if (ajaxSet.dataType) {
			curAjaxDataType = ajaxSet.dataType.toUpperCase();
		}
		// 根据请求数据类型，获得文件扩展名，并组合成完整的请求路径
		switch (curAjaxDataType) {
			case 'JSON':
				curExtName = '.js';
				break;
			case 'HTML':
				curExtName = '.htm';
				break;
		}
		// 获得Ajax请求数据要插入的DOM对象
		curAjaxTarget = $(ajaxSet.target);
		// ajaxTab 请求的信息，只做展示之用，应该要缓存住，因此没有用  Math.random() 方法清除缓存
		curAjaxPath = ajaxSet.path + fileName + curExtName;
		
		// 执行 ajax 请求
		$.ajax({
			type: curAjaxType,
			url: curAjaxPath,
			success: function(data){
				var htmlContent = '';
				
				switch (curAjaxDataType) {
					case 'JSON':
					    htmlContent = data.html;
						break;
					case 'HTML':
					    htmlContent = data;
						break;
				}
				
				curAjaxTarget.html(htmlContent);
				
				tabView._afterTabSelectedChange();
			},
			dataType: curAjaxDataType
		});
		
		return this;
	},
	_getDefSelection: function(){
		var defaultSelection = [],
		    setting = this.setting,
			showAll = setting.showAll,
			ajax = setting.ajax,
			tabs = setting.tabs,
			contents = setting.contents;
		
		// 显示默认的选中项和内容
		if (showAll) {
			// 默认的上次显示项为所有 tabs
			setting.lastTab = tabs;
			setting.lastContent = contents;
		}
		else {
			// 默认的上次显示项为第一个tab
			setting.lastTab = tabs[setting.lastIndex];
			// 如果是 ajaxTab 则上次显示的 content 为 null
			// 因为 ajaxTab 都是在一个 targetContent 中显示异步加载的信息
			if (!ajax) {
				setting.lastContent = contents[setting.lastIndex];
			}
		}
		
		defaultSelection = [setting.lastTab, setting.lastContent];
		
		return defaultSelection;
	},
	_getTabHash: function(){
		var tabHash = '';
		
		// 根据URL地址参数设置默认选中的 tab 显示项
		if (location.href.indexOf('tab=') > -1) {
			tabHash = location.href.split('tab=')[1];
			
			// URL地址如：tabs.htm?tab=javascript&id=4#xml
			if (tabHash.indexOf('&') > -1) {
				tabHash = tabHash.split('&')[0];
			}
			else {
				// URL地址如：tabs.htm?tab=javascript#xml
				if (tabHash.indexOf('#') > -1) {
					tabHash = tabHash.split('#')[0];
				}
			}
		}
		else {
			// URL地址如：tabs.htm#xml
			if (location.href.indexOf('#') > -1) {
				tabHash = location.href.split('#')[1];
			}
		}
		
		return tabHash;
	},
	/**
	 * 获得 tabsView 实例的配置项信息
	 * 
	 * @method getSetting
	 * @return {Object} this.setting 
	 */
	getSetting: function(){
		return this.setting;
	},
	/**
	 * 获得 tabsView 实例的 tabs 的 NodeList 信息
	 * 
	 * @method getTabs
	 * @return {HTMLNodeList} this.setting.tabs
	 */
	getTabs: function(){
		return this.setting.tabs;
	},
	/**
	 * 获得 tabsView 实例的 contents 的 NodeList 信息
	 * 
	 * @method getContents
	 * @return {HTMLNodeList} this.setting.contents;
	 */
	getContents: function(){
		return this.setting.contents;
	},
	getDefTab: function(){
		var defaultTab = this._getDefSelection()[0];
		
		return defaultTab;
	},
	getDefContent: function(){
		var defaultContent = this._getDefSelection()[1];
		
		return defaultContent;
	},
	getCurTab: function(){
		return this.setting.lastTab;
	},
	getCurContent: function(){
		return this.setting.lastContent;
	},
	/**
	 * 获得 tabsView 实例的 lastIndex 信息 - 当前选中标签在 tabs 中的索引值
	 * 
	 * @method getContents
	 * @return {HTMLNodeList} this.setting.contents;
	 */
	getCurIndex: function(){
		return this.setting.lastIndex;
	}
};

/**
 * jQuery.tabsView 的静态方法，用来同时设置多个 tabsView
 * 
 * @method tabs
 * @static
 * @return {Object|Array} tabs - 一个或者多个 jQuery.tabsView 的实例
 */
jQuery.tabs = function(){
	var tabs = [], 
	    args = arguments, 
		i = 0, 
		len = args.length;
	
	
	for (; i < len; i += 1) {
		tabs[i] = new jQuery.tabsView(args[i]);
	}
	
	return len > 0 ? tabs : null;
};