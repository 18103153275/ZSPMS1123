<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="res/lecheng/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<!-- <script src="/thirdparty/ckeditor/ckeditor.js" type="text/javascript"></script> -->
<!-- <script src="/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script> -->
<script type="text/javascript" src="res/fckeditor/fckeditor.js"></script>
<script src="res/common/js/jquery.js" type="text/javascript"></script>
<script src="res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="res/common/js/lecheng.js" type="text/javascript"></script>
<script src="res/lecheng/js/admin.js" type="text/javascript"></script>
<!-- 引入jQuery -->
<script type="text/javascript" language="javascript" src="js/jquery-1.11.0.min.js"></script>
<!-- 引入日期控件 -->
<script type="text/javascript" language="javascript" src="js/DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="res/css/style.css" />
<title>article-add</title>
<!-- 添加表单验证 -->
<script type="text/javascript">
	//日期     yyyy-MM-dd  月份1-12     日期1-31
	var CHKDATE = "^([1-9][0-9]{3})-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
	//作者姓名
	var CHKAUTHOR= "^[\u4e00-\u9fa5]{2,}$";
	//内容
	var CHKCONTENT ="^.{4,}$";
	//标题
	var CHKTITLE ="^(?![0-9a-zA-Z]+$)[a-z|A-Z|0-9|\u4e00-\u9fa5]{6,16}$";
	//验证标题
	function chktitle() {
		//获取标题
		var titleEle = document.getElementById("title");
		var title = $("#title").val();
		//定义匹配标题的正则表达式
		var reg = new RegExp(CHKTITLE);
		//获取标题是否输入成功的元素对象
		var spanEle = document.getElementById("resultTitle");
		if (reg.test(title)) {//表示输入正确
			$("#resultTitle").html("✔");
			$("#resultTitle").css("color","green");
			return true;
		} else {//输入失败
			$("#resultTitle").html("标题不能为空");
			$("#resultTitle").css("color","red");
			//清空文本框
			$("#title").val = "";
			//重新聚焦
			$("#title").focus();
			return false;
		}
	}
	//验证内容
	function chkcontent() {
		//获取内容
		var content = $("#content").val();
		//定义匹配内容的正则表达式
		var reg = new RegExp(CHKCONTENT);
		if (reg.test(content)) {//表示输入正确
			$("#resultContent").html("✔");
			$("#resultContent").css("color","green");
			return true;
		} else {//输入失败
			$("#resultContent").html("内容不能为空");
			$("#resultContent").css("color","red");
			//重新聚焦
			$("#content").focus();
			return false;
		}
	}
	//验证作者
	function chkauthor() {
		//获取作者
		var author =$("#author").val();
		//定义匹配作者的正则表达式
		var reg = new RegExp(CHKAUTHOR);
		if (reg.test(author)) {//表示输入正确
			$("#resultAuthor").html("✔");
			$("#resultAuthor").css("color","green");
			return true;
		} else {//输入失败
			$("#resultAuthor").html("姓名格式错误，并且不能低于1位");
			$("#resultAuthor").css("color","red");
			//清空文本框
			$("#author").val = "";
			//重新聚焦
			$("#author").focus();
			return false;
		}
	}
	//验证创建日期
	function chkcrtime() {
		//获取创建日期
		var crtime =$("#crtime").val();
		//定义匹配创建日期的正则表达式
		var reg = new RegExp(CHKDATE);
		if (reg.test(crtime)) {//表示输入正确
			$("#resultCrtime").html("✔");
			$("#resultCrtime").css("color","green");
			return true;
		} else {//输入失败
			$("#resultCrtime").html("日期格式错误，请重新输入");
			$("#resultCrtime").css("color","red");
			//清空文本框
			$("#crtime").val = "";
			//重新聚焦
			$("#crtime").focus();
			return false;
		}
	}
	//验证所有
	function chkAll() {
		return chktitle() && chkcontent() && chkauthor() && chkcrtime() 
	}
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 文章管理 - 添加</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='Tarticlelist.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box" style="float:right">
	<form id="jvForm" action="Tarticleadd.do" method="post" onsubmit="return chkAll()">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired"></span>
						<span class="pn-frequired"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						标题:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="title" id="title" onblur="chktitle()" maxlength="100"/>
						<span id="resultTitle"></span>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						内容:</td><td width="80%" class="pn-fcontent">
						<textarea name="content" id="content"  onblur="chkcontent()" rows="20" cols="40" ></textarea>
						<span id="resultContent"></span>
						
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						作者:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="author" id="author" onblur="chkauthor()" maxlength="100"/>
						<span id="resultAuthor"></span>
					</td>
				</tr>
				
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						创建日期:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="Wdate" name="crtime" id="crtime" onblur="chkcrtime()"
						onclick="WdatePicker()" maxlength="80"/>
						<span id="resultCrtime"></span>
					</td>
				</tr>
				
			
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						所属栏目:</td><td width="80%" class="pn-fcontent">
						<select name="channel">
							<c:forEach items="${ channes}" var="tar">
								<option value="${tar.id}" name="id">${tar.cname}</option>
							</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						是否推荐:</td><td width="80%" class="pn-fcontent">
						<input type="radio" name="isremod" value="1" checked="checked"/>推荐
						<input type="radio" name="isremod" value="2"/>不推荐
					</td>
				</tr>
			<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						是否热点:</td><td width="80%" class="pn-fcontent">
						<input type="radio" name="ishot" value="1" checked="checked"/>是热点
						<input type="radio" name="ishot" value="2"/>不是热点
					</td>
				</tr>
			</tbody>
			<tbody>
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="submit" class="submit" value="提交" onclick="return confirm('是否确定提交');"/> &nbsp; <input type="reset" class="reset" value="重置"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>