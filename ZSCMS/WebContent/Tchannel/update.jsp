<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="res/lecheng/css/admin.css" rel="stylesheet" type="text/css" />
<link href="res/common/css/theme.css" rel="stylesheet" type="text/css" />
<link href="res/common/css/jquery.validate.css" rel="stylesheet"
	type="text/css" />
<link href="res/common/css/jquery.treeview.css" rel="stylesheet"
	type="text/css" />
<link href="res/common/css/jquery.ui.css" rel="stylesheet"
	type="text/css" />

<!-- <script src="/thirdparty/ckeditor/ckeditor.js" type="text/javascript"></script> -->
<!-- <script src="/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script> -->
<script type="text/javascript" src="res/fckeditor/fckeditor.js"></script>
<script src="res/common/js/jquery.js" type="text/javascript"></script>
<script src="res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="res/common/js/lecheng.js" type="text/javascript"></script>
<script src="res/lecheng/js/admin.js" type="text/javascript"></script>

<link rel="stylesheet" href="res/css/style.css" />

<title>channel-update</title>
<title>channel-add</title>
<!-- 添加表单验证 -->
<script type="text/javascript">
	//频道名称
	var CHKCNAME = "^[\u4e00-\u9fa5]{1,}$";
	//频道名称
	var CHKSORT = "^[1-9]{1,2}$";
	//验证频道名称
	function chkcname() {
		//获取频道名称
		var cnameEle = document.getElementById("cname");
		var cname = $("#cname").val();
		//定义匹配标题的正则表达式
		var reg = new RegExp(CHKCNAME);
		//获取标题是否输入成功的元素对象
		var spanEle = document.getElementById("resultCname");
		if (reg.test(cname)) {//表示输入正确
			$("#resultCname").html("✔");
			$("#resultCname").css("color","green");
			return true;
		} else {//输入失败
			$("#resultCname").html("标题不能为空");
			$("#resultCname").css("color","red");
			//清空文本框
			$("#cname").val = "";
			//重新聚焦
			$("#cname").focus();
			return false;
		}
	}
	//验证频道序号
	function chksort() {
		//获取频道序号
		var sortEle = document.getElementById("sort");
		var sort = $("#sort").val();
		//定义匹配频道序号的正则表达式
		var reg = new RegExp(CHKSORT);
		//获取频道序号是否输入成功的元素对象
		var spanEle = document.getElementById("resultSort");
		if (reg.test(sort)) {//表示输入正确
			$("#resultSort").html("✔");
			$("#resultSort").css("color","green");
			return true;
		} else {//输入失败
			$("#resultSort").html("频道序号不能为空");
			$("#resultSort").css("color","red");
			//清空文本框
			$("#sort").val = "";
			//重新聚焦
			$("#sort").focus();
			return false;
		}
	}
	//验证所有
	function chkAll() {
		return chkcname() && chksort() 
	}
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 频道管理 - 修改</div>
		<form class="ropt">
			<input type="submit" onclick="this.form.action='channelist.do';"
				value="返回列表" class="return-button" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box" style="float: right">
		<form id="jvForm" action="channelupdate.do?id=${channel.id}"
			method="post" onsubmit="return chkAll()">
			<table cellspacing="1" cellpadding="2" width="100%" border="0"
				class="pn-ftable">
				<tbody>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">${msg}</span></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 频道名称:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="cname" id="cname" value="${channel.cname }"
							maxlength="100" />
							<span id="resultCname"></span>
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 上级栏目id:</td>
						<td width="80%" class="pn-fcontent"><select name="pid">
								<c:forEach items="${channels}" var="channels">
									<c:if test="${channels.id==channel.pid }">
										<!-- 显示所属频道 -->
										<option value="${channels.id}" selected="selected">${channels.cname}</option>
									</c:if>
									<c:if test="${channels.id!=channel.pid }">
										<!-- 显示其他频道 -->
										<option value="${channels.id}">${channels.cname}</option>
									</c:if>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 频道级别:</td>
						<td width="80%" class="pn-fcontent"><c:if
								test="${channel.lev==1 }">
								<input type="radio" class="required" name="lev" value="1"
									checked="checked" />一级
						<input type="radio" class="required" name="lev" value="2" />二级
						</c:if> <c:if test="${channel.lev==2 }">
								<input type="radio" class="required" name="lev" value="1" />一级
						<input type="radio" class="required" name="lev" value="2"
									checked="checked" />二级
						</c:if></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 是否叶子:</td>
						<td width="80%" class="pn-fcontent"><c:if
								test="${channel.isleaf==1 }">
								<input type="radio" class="required" name="isleaf" value="1"
									checked="checked" />是
						<input type="radio" class="required" name="isleaf" value="2" />否
						</c:if> <c:if test="${channel.isleaf==2 }">
								<input type="radio" class="required" name="isleaf" value="1" />是
						<input type="radio" class="required" name="isleaf" value="2"
									checked="checked" />否
						</c:if></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">频道序号:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" value="${channel.sort }" name="sort" id="sort" 
							maxlength="80" />
							<span id="resultSort"></span>
						</td>
					</tr>
				</tbody>
				<tbody>
					<tr>
						<td class="pn-fbutton" colspan="2"><input type="submit"
							class="submit" value="提交" onclick="return confirm('是否确定提交');"/> &nbsp; <input type="reset"
							class="reset" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>