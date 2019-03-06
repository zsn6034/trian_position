<%@ page language="java" import="java.util.*,model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<Train> listTrain = (LinkedList<Train>)request.getAttribute("listTrain");
String found = (request.getParameter("found")!=null)?request.getParameter("found"):"YES";
System.out.println("found==="+found);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
<script type="text/javascript">
if("NO"=="<%=found%>")
	alert("未找到该趟列车信息！");
$(function() {
	getDate();
});
function getDate() {
	$.ajax({
		url:"<%=path %>/servlet/QueryDate",
		type:"post",
		success:function(json){
			var select = $(".date");
			$(json).each(function() {
				select.append("<option value='"+ this.DATE +"'>"+ this.DATE +"</option>");
			});
		},
		dataType:"json",
		async:false
	});
}
</script>
<style type="text/css">  
#all{
	position: absolute;
	margin-left: 20px;
	margin-top: 20px;
	margin-right: 450px;
	overflow:hidden;
}
#title_image{
	margin-top: 20px;
	margin-left: 900px;
	position: fixed;
}
.input_label{
	font-size: 20px;
}
#train_title{
	font-size: 50px;
	color:orange;
	font-weight:bolder;
}
</style>
  <head>
    <title>列车查询</title>
  </head>
  <link rel="stylesheet" href="../css/bootstrap.min.css">
  <link rel="stylesheet" href="style.css">
  <script src="http://c.cnzz.com/core.php"></script></head>  
  <script src="../js/bootstrap.min.js"></script> 
  <body>
  <div id="container">
  <div id="all">
  <form  class="form-search" action="<%=path%>/servlet/Train2Map" method="post">
    <b class="input_label">请输入查询车次:</b>
    <input class="input-medium search-query" type="text" name="station_train_code" placeholder="如:D2284">
    <b class="input_label">请输入查询日期:</b>
    <select class="date" name="date"></select>
    <input type="submit" value="确定"> 
  </form>
  <form action="<%=path%>/servlet/QueryTrains" method="post">
  	<b class="input_label">始发站:</b>
  	<input type="text" placeholder="请输入起始站" name="start_station">
  	<b class="input_label">终点站:</b>
  	<input type="text" placeholder="请输入终点站" name="end_station">
  	<input type="submit" value="查询"> 
  	<b>(未输入起始站和终点站则随机显示前20条列车信息)</b>
  </form>
  <table class="table table-striped">
<%	
if(listTrain!=null){
	%>
	<tr>
		<th>车次</th>
		<th>起始站</th>
		<th>终点站</th>
		<th>列车编号</th>
		<th>日期</th>
	</tr>
	<%
	for(int i=0;i<listTrain.size();i++){
	%>
	<tr>
		<td><%=listTrain.get(i).getStation_train_code()%></td>
		<td><%=listTrain.get(i).getStart_station()%></td>
		<td><%=listTrain.get(i).getEnd_station()%></td>
		<td><%=listTrain.get(i).getTrain_no()%></td>
		<td>
			<form action="<%=path%>/servlet/Train2Map" method="post">
				<select class="date" name="date"></select>
				<input type="hidden" name="station_train_code" value="<%=listTrain.get(i).getStation_train_code()%>">
    			<input type="submit" value="查询"> 
    		</form>
		</td>
	</tr>
	<%	
	}	
}
%>
  </table>
  </div>
  <div id="title_image">
  	<img alt="" src="../image/train1.jpg" width="450px" height="380px">
  	<h1 id="train_title">列车定位查询系统</h1>
  	<a href="https://kyfw.12306.cn/otn/leftTicket/init" target="_blank">点击此处可查询12306官网列车车次</a>
  	<br><br><br><br><br><br>
  	<h6 style="right:10px;">Copyright © 2017 All Rights Reserved Powered By ZSN</h6>
  </div>
  </div>
  </body>
</html>