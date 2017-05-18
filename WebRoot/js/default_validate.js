$.validator.prototype.showLabel = function(element, message) {
	
	if (this.settings.success) {
		$(element).removeAttr("title");
		$(element).removeClass("validatebox-invalid");
	} else {
		$(element).attr("title", message);
		$(element).addClass("validatebox-invalid");
	}
};

$.validator.prototype.showSuccess = function(element, message) {
	if(element) {
		$(element).removeAttr("title");
		$(element).removeClass("validatebox-invalid");
	}
	if(this.successList) {
		for ( var i = 0; this.successList[i]; i++ ) {
			$(this.successList[i]).removeAttr("title");
			$(this.successList[i]).removeClass("validatebox-invalid");
		}
	}
};

jQuery.extend(jQuery.validator.messages, {
	required : "必填字段",
	remote : "请修正该字段",
	email : "请输入正确格式的电子邮件",
	url : "请输入合法的网址",
	date : "请输入合法的日期",
	dateISO : "请输入合法的日期 (ISO).",
	number : "请输入合法的数字",
	digits : "只能输入整数",
	creditcard : "请输入合法的信用卡号",
	equalTo : "请再次输入相同的值",
	accept : "请输入拥有合法后缀名的字符串",
	maxlength : jQuery.validator.format("允许的最大长度为 {0} 个字符"),
	minlength : jQuery.validator.format("允许的最小长度为 {0} 个字符"),
	rangelength : jQuery.validator.format("允许的长度为{0}和{1}之间"),
	range : jQuery.validator.format("请输入介于 {0} 和 {1} 之间的值"),
	max : jQuery.validator.format("请输入一个最大为 {0} 的值"),
	min : jQuery.validator.format("请输入一个最小为 {0} 的值"),
	stringMinLength : jQuery.validator.format("请输入一个小于{0} 的字符串, 一个中文字符长度为2"),
	stringMaxLength : jQuery.validator.format("请输入一个大于} 的字符串, 一个中文字符长度为2"),
	string : "含特殊符号!",
	byteRangeLength : "请确保输入的值在3-15个字节之间(一个中文字算2个字节)",
	stringCH : "只能输入汉字,一个汉字占两具字节",
	stringEN : "只能输入字母",
	idCardNo :"请输入正确的身份证号码"
});

jQuery.validator.addMethod("idCardNo", function(value, element, parameter) {
	return this.optional(element) || isIdCardNo(value);
}, jQuery.validator.format("请输入正确的身份证号码"));

// 手机号码验证 
jQuery.validator.addMethod("isMobile", function(value, element) { 
  var length = value.length; 
  var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
  return this.optional(element) || (length == 11 && mobile.test(value)); 
}, "请正确填写您的手机号码"); 

//-------------------------------Add IdCardNo START		
//身份证号码验证 
function isIdCardNo(num) {
  var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
  var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
  var varArray = new Array();
  //var intValue;
  var lngProduct = 0;
  var intCheckDigit;
  var intStrLen = num.length;
  var idNumber = num;
  // initialize
  if ((intStrLen != 15) && (intStrLen != 18)) {
      return false;
  }
  // check and set value
  for (i = 0; i < intStrLen; i++) {
      varArray[i] = idNumber.charAt(i);
      if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
          return false;
      } else if (i < 17) {
          varArray[i] = varArray[i] * factorArr[i];
      }
  }

  if (intStrLen == 18) {
      //check date
      var date8 = idNumber.substring(6, 14);
      if (isDate8(date8) == false) {
          return false;
      }
      // calculate the sum of the products
      for (i = 0; i < 17; i++) {
          lngProduct = lngProduct + varArray[i];
      }
      // calculate the check digit
      intCheckDigit = parityBit[lngProduct % 11];
      // check last digit
      if (varArray[17] != intCheckDigit) {
          return false;
      }
  }else {//length is 15
      //check date
      var date6 = idNumber.substring(6, 12);
      if (isDate6(date6) == false) {
          return false;
      }
  }
  return true;
}
/**
* 判断是否为“YYYYMM”式的时期
*
*/
function isDate6(sDate) {
  if (!/^[0-9]{6}$/.test(sDate)) {
      return false;
  }
  var year, month, day;
  year = sDate.substring(0, 4);
  month = sDate.substring(4, 6);
  if (year < 1700 || year > 2500) return false
  if (month < 1 || month > 12) return false
  return true
}
/**
* 判断是否为“YYYYMMDD”式的时期
*
*/
function isDate8(sDate) {
  if (!/^[0-9]{8}$/.test(sDate)) {
      return false;
  }
  var year, month, day;
  year = sDate.substring(0, 4);
  month = sDate.substring(4, 6);
  day = sDate.substring(6, 8);
  var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
  if (year < 1700 || year > 2500) return false
  if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
  if (month < 1 || month > 12) return false
  if (day < 1 || day > iaMonthDays[month - 1]) return false
  return true
}
//-------------------------------Add IdCardNo End
