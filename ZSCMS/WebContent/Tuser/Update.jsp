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
<title>user-update</title>
<!-- 添加表单验证 -->
<script type="text/javascript">
	//用户名：数字+字母，结束之前不能全部都是数字，6-16
	var CHKLOGINNAME = "^(?![0-9]+$)[a-zA-Z0-9]{6,16}$";
	//密码:数字+字母，结束之前不能全部都是数字和字母，6-16
	var CHKPASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{6,16}$";
	//出生日期     yyyy-MM-dd  月份1-12     日期1-31
	var CHKDATE = "^([1-9][0-9]{3})-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
	//邮箱xxxxxx@xxx.com,可以包含_      企业邮箱qwe@huewei.com.cn
	var CHKEMAIL = "^[a-zA-Z0-9_]+@[a-z0-9]{2,5}(\\.[a-z]{2,3}){1,2}$";
	//真实姓名
	var CHKREALNAME = "^[\u4e00-\u9fa5]{2,}$";

	//验证用户名
	function chkloginname() {
		//获取用户名
		var lgnEle=document.getElementById("loginname");
		var loginname=$("#loginname").val();
		//定义用户匹配的正则表达式
		var reg=new RegExp(CHKLOGINNAME);
		if(reg.test(loginname)){
			$("#resultName").html("✔");
			$("#resultName").css("color","green");
			return true;
		}else{
			$("#resultName").html("用户名必须包含数字与字母且不能低于六位");
			$("#resultName").css("color","red");
			//清空文本框
			$("#loginname").val = "";
			//重新聚焦
			$("#loginname").focus();
			return false;
		}
	}
	
	//验证真实姓名
	function chkrealname() {
		//获取真实姓名
		var realEle = document.getElementById("realname");
		var realname = $("#realname").val();
		//定义匹配真实姓名的正则表达式
		var reg = new RegExp(CHKREALNAME);
		//获取真实姓名是否输入成功的元素对象
		var spanEle = document.getElementById("resultRealName");
		if (reg.test(realname)) {//表示输入正确
			$("#resultRealName").html("✔");
			$("#resultRealName").css("color","green");
			return true;
		} else {//输入失败
			$("#resultRealName").html("姓名格式错误，并且不能低于1位");
			$("#resultRealName").css("color","red");
			//清空文本框
			$("#realname").val = "";
			//重新聚焦
			$("#realname").focus();
			return false;
		}
	}
	//验证出生日期
	function chkbirthday() {
		//获取出生日期
		var birthdayEle = document.getElementById("birthday");
		var birthday = $("#birthday").val();
		//定义匹配出生日期的正则表达式
		var reg = new RegExp(CHKDATE);
		//获取出生日期是否输入成功的元素对象
		var spanEle = document.getElementById("resultBirthdayName");
		if (reg.test(birthday)) {//表示输入正确
			$("#resultBirthdayName").html("✔");
			$("#resultBirthdayName").css("color","green");
			return true;
		} else {//输入失败
			$("#resultBirthdayName").html("日期格式错误，请重新输入");
			$("#resultBirthdayName").css("color","red");
			//清空文本框
			$("#birthday").val = "";
			//重新聚焦
			$("#birthday").focus();
			return false;
		}
	}
	//验证邮箱
	function chkemail() {
		//获取邮箱
		var emailEle = document.getElementById("email");
		var email = $("#email").val();
		//定义匹配邮箱的正则表达式
		var reg = new RegExp(CHKEMAIL);
		//获取邮箱是否输入成功的元素对象
		var spanEle = document.getElementById("resultEmailName");
		if (reg.test(email)) {//表示输入正确
			$("#resultEmailName").html("✔");
			$("#resultEmailName").css("color","green");
			return true;
		} else {//输入失败
			$("#resultEmailName").html("邮箱格式错误，请重新输入");
			$("#resultEmailName").css("color","red");
			//清空文本框
			$("#email").val = "";
			//重新聚焦
			$("#email").focus();
			return false;
		}
	}
	//验证所有
	function chkAll() {
		return chkloginname() && chkrealname() && chkbirthday () && chkemail()
	}
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 用户管理 - 修改</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='userlist.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box" style="float:right">
	<form id="jvForm" action="userupdate.do?id=${user.id}" method="post" onsubmit="return chkAll()">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired"></span>
						<span class="pn-frequired">${msg}</span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						用户名:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="loginname" id="loginname" maxlength="100" 
						value="${user.loginname}"/>
						<span id="resultName"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						真实姓名:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="realname" id="realname" maxlength="100"
						value="${user.realname}"/>
						<span id="resultRealName"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						性别:</td><td width="80%" class="pn-fcontent">
						<c:if test="${user.sex=='男'}">
						<input type="radio" name="sex" value="男" checked="checked"/>男
						<input type="radio" name="sex" value="女"/>女
						</c:if>
						<c:if test="${user.sex=='女'}">
						<input type="radio" name="sex" value="男" checked="checked"/>男
						<input type="radio" name="sex" value="女"/>女
						</c:if>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						部门:</td><td width="80%" class="pn-fcontent">
						<select name="dept">
							<c:forEach items="${depts}" var="dept">
								<!-- 显示所属部门 -->
								<c:if test="${user.dept==dept.id}">
									<option value="${dept.id}" selected="selected">${dept.deptname}</option>
								</c:if>
								<!-- 显示其他部门 -->
								<c:if test="${user.dept!=dept.id}">
									<option value="${dept.id}" >${dept.deptname}</option>
								</c:if>
								
								
							</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						邮箱:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="email" id="email" maxlength="80"
						value="${user.email}"/>
						<span id="resultEmailName" ></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						出生日期:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="Wdate"  name="birthday" id="birthday" maxlength="80"
						onclick="WdatePicker()" value="${user.birthday}"/>
						<span id="resultBirthdayName"></span>
					</td>
				</tr>
				
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">是否可用:</td>
					<td width="80%" class="pn-fcontent">
						<c:if test="${user.enabled==1 }">
							<input type="radio" class="required" name="enabled" maxlength="80"
							value="1" checked="checked"/>可用
							<input type="radio" class="required" name="enabled" maxlength="80"
							value="2" />不可用
						
						</c:if>
						<c:if test="${user.enabled==2 }">
							<input type="radio" class="required" name="enabled" maxlength="80"
							value="1" />可用
							<input type="radio" class="required" name="enabled" maxlength="80"
							value="2" checked="checked"/>不可用
						
						</c:if>
						
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