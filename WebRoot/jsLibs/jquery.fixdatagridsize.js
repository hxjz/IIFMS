// JavaScript Document
/**
 * JQuery扩展方法，用户对JQuery EasyUI的DataGrid控件进行操作。
 */
$.fn.extend({
 /**
  * 修改DataGrid对象的默认大小，以适应页面宽度。
  * 
  * @param heightMargin
  *            高度对页内边距的距离。
  * @param widthMargin
  *            宽度对页内边距的距离。
  * @param minHeight
  *            最小高度。
  * @param minWidth
  *            最小宽度。
  * 
  */
 resizeDataGrid : function(heightMargin, widthMargin, minHeight, minWidth) {
  var height = $(document.body).height() - heightMargin;
  var width = $(document.body).width() - widthMargin;
  height = height < minHeight ? minHeight : height;
  width = width < minWidth ? minWidth : width;
  $(this).datagrid('resize', {
   height : height,
   width : width
  });
 }
});