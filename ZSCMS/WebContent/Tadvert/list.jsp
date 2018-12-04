<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link href="res/lecheng/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<!-- <script src="/thirdparty/ckeditor/ckeditor.js" type="text/javascript"></script> -->
<!-- <script src="/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script> -->
<script type="text/javascript" src="../res/fckeditor/fckeditor.js"></script>
<script src="res/common/js/jquery.js" type="text/javascript"></script>
<script src="res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="res/common/js/lecheng.js" type="text/javascript"></script>
<script src="res/lecheng/js/admin.js" type="text/javascript"></script>

<link rel="stylesheet" href="../res/css/style.css" />






<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广告列表</title>
<meta charset="UTF-8">

</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 广告管理 - 列表</div>
	<form class="ropt">
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='Tadvert/add.jsp'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form method="post" id="tableForm">

<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" /></th>
			<th>标题编号</th>
			<th>标题</th>
			<th>内容</th>
			<th>公告时间</th>
			<th>公告人</th>
			<th width="12%">操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach items="${tads}" var="tad">
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
			<td><input type="checkbox" name="ids" value="297"/></td>
			<td align="center">${tad.id }</td>
			<td align="center">${tad.title }</td>
			<td align="center">${tad.content }</td>
			<td align="center">${tad.crtime }</td>
			<td align="center">${tad.crman }</td>
			<td align="center">
			<a href="Tadvertget.do?id=${tad.id}" class="pn-opt">修改</a> | 
			<a href="Tadvertdelete.do?id=${tad.id}" onclick="if(!confirm('您确定删除吗？')) {return false;}" class="pn-opt">删除</a> | <a href="sku.html" class="pn-opt"></a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<div class="page pb15" style="float:right;">
	<span class="r inb_a page_b">
		<!-- [当前页/尾页] -->
		[${requestScope.currentPage }/${requestScope.pageCount }]
		<a href="Tadvertlist.do?currentPage=1">首页</a>
		<c:if test="${requestScope.currentPage-1>0 }">
			<a href="Tadvertlist.do?currentPage=${requestScope.currentPage-1 }">上一页</a>
		</c:if>
		<c:if test="${requestScope.currentPage+1<=requestScope.pageCount }">
			<a href="Tadvertlist.do?currentPage=${requestScope.currentPage+1 }">下一页</a>
		</c:if>
		<a href="Tadvertlist.do?currentPage=${requestScope.pageCount }">尾页</a>
	</span>
</div>
<div style="margin-top:15px;">
<!-- 	<input class="del-button" type="button" value="取消" onclick="optCancel();"/>
	<input class="submit" type="button" value="通过" onclick="optPass();"/> -->
</div>
</form>
</div>
</body>
</html>