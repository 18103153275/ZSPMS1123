<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
<!-- 引入jQuery -->
<script type="text/javascript" language="javascript"
	src="js/jquery-1.11.0.min.js"></script>
<!-- 引入日期控件 -->
<script type="text/javascript" language="javascript"
	src="js/DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="res/css/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广告修改</title>
<style type="">
.h2_ch a:hover,.h2_ch a.here {
	color: #FFF;
	font-weight: bold;
	background-position: 0px -32px;
}

.h2_ch a {
	float: left;
	height: 32px;
	margin-right: -1px;
	padding: 0px 16px;
	line-height: 32px;
	font-size: 14px;
	font-weight: normal;
	border: 1px solid #C5C5C5;
	background: url('res/lecheng/img/admin/bg_ch.gif') repeat-x scroll 0% 0%
		transparent;
}

a {
	color: #06C;
	text-decoration: none;
}
</style>
<!-- 添加表单验证 -->
<script type="text/javascript">
	//日期     yyyy-MM-dd  月份1-12     日期1-31
	var CHKCRTIME = "^([1-9][0-9]{3})-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
	//公告人
	var CHKCRMAN = "^[\u4e00-\u9fa5]{2,}$";
	//内容
	var CHKCONTENT = "^.{4,}$";
	//标题
	var CHKTITLE = "^(?![0-9a-zA-Z]+$)[a-z|A-Z|0-9|\u4e00-\u9fa5]{6,16}$";
	//验证标题
	function chktitle() {
		//验证标题
		function chktitle() {
			//获取标题
			var title =$("#title").val();
			//定义匹配标题的正则表达式
			var reg = new RegExp(CHKTITLE);
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
			var content =$("#content").val();
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
		//验证公告人
		function chkcrman() {
			//获取公告人
			var crman =$("#crman").val();
			//定义匹配公告人的正则表达式
			var reg = new RegExp(CHKCRMAN);
			if (reg.test(crman)) {//表示输入正确
				$("#resultCrman").html("✔");
				$("#resultCrman").css("color","green");
				return true;
			} else {//输入失败
				$("#resultCrman").html("姓名格式错误，并且不能低于1位");
				$("#resultCrman").css("color","red");
				//清空文本框
				$("#crman").val = "";
				//重新聚焦
				$("#crman").focus();
				return false;
			}
		}
		//验证公告时间
		function chkcrtime() {
			//获取公告时间
			var crtime =$("#crtime").val();
			//定义匹配公告时间的正则表达式
			var reg = new RegExp(CHKCRTIME);
			if (reg.test(crtime)) {//表示输入正确
				$("#resultCrtime").html("✔");
				$("#resultCrtime").css("color","green");
				return true;
			} else {//输入失败
				$("#resultCrtime").html("日期格式错误，请重新输");
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
		return chktitle() && chkcontent() && chkcrman() && chkcrtime() 
	}
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 广告管理 - 添加</div>
		<form class="ropt">
			<input type="submit" onclick="this.form.action='Tadvertlist.do';"
				value="返回列表" class="return-button" />
		</form>
		<div class="clear"></div>
	</div>

	<div class="body-box" style="float: right">
		<form id="jvForm" action="Tadvertupdate.do?id=${tad.id}" method="post"
			onsubmit="return chkAll()">
			<table cellspacing="1" cellpadding="2" width="100%" border="0"
				class="pn-ftable">
				<tbody id="tab_1">
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired"></span> <span class="pn-frequired">${msg}</span>
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> <span class="pn-frequired"></span>
							标题:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							name="title" id="title" value="${tad.title}" /><br /> <span
							id="resultTitle"></span></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span>内容:</td>
						<td width="80%" class="pn-fcontent"><textarea name="content"
								id="content" onblur="chkcontent()" rows="20" cols="40">${tad.content}</textarea>
							<span id="resultContent"></span></td>
					</tr>

					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">公告时间:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							name="crtime" id="crtime" maxlength="80" class="Wdate"
							onclick="WdatePicker()" readonly="readonly"
							onclick="WdatePicker()" value="${tad.crtime}" /> <span
							id="resultCrtime"></span></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired"></span> 公告人:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							name="crman" id="crman" value="${tad.crman}" /><br /> <span
							id="resultCrman"></span></td>
					</tr>

				</tbody>
				<tbody id="tab_2" style="display: none">
					<tr>
						<td><textarea rows="10" cols="10" id="productdesc"
								name="description"></textarea></td>
					</tr>
				</tbody>
				<tbody id="tab_3" style="display: none">
					<tr>
						<td><textarea rows="15" cols="136" id="productList"
								name="packageList"></textarea></td>
					</tr>
				</tbody>
				<tbody>
					<tr>
						<td class="pn-fbutton" colspan="2"><input type="submit"
							class="submit" value="提交" onclick="return confirm('是否确定提交');" />
							&nbsp; <input type="reset" class="reset" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>