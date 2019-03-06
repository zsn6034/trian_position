<%@ page language="java" import="java.util.*,model.*,java.util.Date,java.text.SimpleDateFormat" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Train train = (Train)request.getAttribute("train");
String station_train_code = "";
String tempdate = ""; 
ArrayList<ViaStation> viaStationList = null;
if(train!=null){
	System.out.println(train);
	station_train_code = train.getStation_train_code();
	System.out.println("station_train_code==="+station_train_code);
	tempdate = train.getDate();
	System.out.println("tempdate==="+tempdate);
	viaStationList = train.getViaStationList();
}else
	System.out.println("train===null!");
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
String currentTime = df.format(new Date());
System.out.println(currentTime);// new Date()为获取当前系统时间
%>
<!DOCTYPE html>  
<html>  
<head>  
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />  
    <style type="text/css">  
        body, html {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}  
        #allmap {position:absolute;left:0px;width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}  
    </style>  
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=E06eb9d756d0eafc722effb355657b4c"></script>  
    <title>列车定位显示界面</title>  
    <link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="style.css">
    <script src="http://c.cnzz.com/core.php"></script></head>  
    <script src="../js/bootstrap.min.js"></script> 
<body>  
<div id="container">
 <div id="allmap"
	style="position: absolute;
		margin-left:400px;
        margin-top:30px; 
       	width: 700px; 
        height: 500px; 
        top: 50; 
        border: 1px solid gray;
        overflow:hidden;">
 </div>  
 <div id="train_info" 
 style="width:300px;
 margin-left:50px; 
 height:600px; 
 overflow:scroll;">
 <h1 style="font-weight: bolder;">列车信息</h1>
 <table class="table table-striped">
 	<tr>
 		<th>车次</th>
 		<td><%=train.getStation_train_code() %></td>
 	</tr>
 	<tr>
 		<th>日期</th>
 		<td><%=train.getDate() %></td>
 	</tr>
 	<tr>
 		<th>始发站</th>
 		<td><%=train.getStart_station() %></td>
 	</tr>
 	<tr>
 		<th>终点站</th>
 		<td><%=train.getEnd_station() %></td>
 	</tr>
 	<tr>
 		<th>发车时间</th>
 		<td><%=viaStationList.get(0).getStart_time() %></td>
 	</tr>
 	<tr>
 		<th>到站时间</th>
 		<td><%=viaStationList.get(viaStationList.size()-1).getArrive_time() %></td>
 	</tr>
 	<tr>
 		<th>总站点数</th>
 		<td><%=viaStationList.size() %></td>
 	</tr>
 </table><br>
 <table class="table table-striped">
 	<tr>
 		<th>站序</th><th>站名</th><th>到达时间</th><th>出发时间</th><th>停留时间</th>
 	</tr>
 	<%for(int i=0;i<viaStationList.size();i++){	
 		%>
 		<tr>
 			<td><%=i+1 %></td>
 			<td><%=viaStationList.get(i).getStation_name() %></td>
 			<td><%=viaStationList.get(i).getArrive_time() %></td>
 			<td><%=viaStationList.get(i).getStart_time() %></td>
 			<td><%=viaStationList.get(i).getStopover_time()%></td>		
 		</tr>
 	<%
 	}
 	%>
 </table>
 </div>
 <div id="return" onclick="back()"
	 style="
		position:fixed;
		bottom:50px;
		right:30px; 
	 ">
   <img src="<%=path %>/image/return.png">
 </div>
</div>
</body>  
<script type="text/javascript">
	function back(){
		url = "<%=path%>/train_position/train_query.jsp";
		location = url;
	}
	function addDate(str){
		var date = new Array();
		date = str.split("-");
		var year = parseInt(date[0]);
		var month = parseInt(date[1]);
		var day = parseInt(date[2]);
		if((year%4==0&&year%100!=0)||year%400==0){		//该年为闰年
			switch(month){
			case 1:
				if(day < 31){
					day ++;
				}else{			//day=31
					day = 1;
					month ++;
				}
				break;
			case 2:	
				if(day < 29){
					day ++;
				}else{			//day=29
					day = 1;
					month ++;
				}
				break;
			case 3:
				if(day < 31){
					day ++;
				}else{			//day=31
					day = 1;
					month ++;
				}
				break;
			case 4:
				if(day < 30){
					day ++;
				}else{			//day=30
					day = 1;
					month ++;
				}
				break;
			case 5:
				if(day < 31){
					day ++;
				}else{			//day=31
					day = 1;
					month ++;
				}
				break;
			case 6:
				if(day < 30){
					day ++;
				}else{			//day=30
					day = 1;
					month ++;
				}
				break;
			case 7:
				if(day < 31){
					day ++;
				}else{			//day=31
					day = 1;
					month ++;
				}
				break;
			case 8:
				if(day < 31){
					day ++;
				}else{			//day=31
					day = 1;
					month ++;
				}
				break;
			case 9:
				if(day < 30){
					day ++;
				}else{			//day=30
					day = 1;
					month ++;
				}
				break;
			case 10:
				if(day < 31){
					day ++;
				}else{			//day=31
					day = 1;
					month ++;
				}
				break;
			case 11:
				if(day < 30){
					day ++;
				}else{			//day=30
					day = 1;
					month ++;
				}
				break;
			case 12:
				if(day < 31){
					day ++;
				}else{			//day=31
					day = 1;
					month = 1;
					year ++;
				}
			}
		}else{					//非闰年
			switch(month){
			case 1:
				if(day < 31){
					day ++;
				}else{			//day=31
					day = 1;
					month ++;
				}
				break;
			case 2:	
				if(day < 28){
					day ++;
				}else{			//day=28
					day = 1;
					month ++;
				}
				break;
			case 3:
				if(day < 31){
					day ++;
				}else{			//day=31
					day = 1;
					month ++;
				}
				break;
			case 4:
				if(day < 30){
					day ++;
				}else{			//day=30
					day = 1;
					month ++;
				}
				break;
			case 5:
				if(day < 31){
					day ++;
				}else{			//day=31
					day = 1;
					month ++;
				}
				break;
			case 6:
				if(day < 30){
					day ++;
				}else{			//day=30
					day = 1;
					month ++;
				}
				break;
			case 7:
				if(day < 31){
					day ++;
				}else{			//day=31
					day = 1;
					month ++;
				}
				break;
			case 8:
				if(day < 31){
					day ++;
				}else{			//day=31
					day = 1;
					month ++;
				}
				break;
			case 9:
				if(day < 30){
					day ++;
				}else{			//day=30
					day = 1;
					month ++;
				}
				break;
			case 10:
				if(day < 31){
					day ++;
				}else{			//day=31
					day = 1;
					month ++;
				}
				break;
			case 11:
				if(day < 30){
					day ++;
				}else{			//day=30
					day = 1;
					month ++;
				}
				break;
			case 12:
				if(day < 31){
					day ++;
				}else{			//day=31
					day = 1;
					month = 1;
					year ++;
				}
			}
		}
		var newDate = year.toString() + "-" + month.toString() + "-" + day.toString();
		return newDate;
	}
	var tempDate = "<%=tempdate%>";
	alert("tempDate=="+tempDate);
	var lng = new Array();
	var lat = new Array();
	var startTime = new Array();
	var arriveTime = new Array();
	var visited = new Array();			//保存是否在比较的过程中访问过，用来确定动点显示的轨迹
	var stationName = new Array();
	<%
	if(viaStationList!=null){
		for(int i=0;i<viaStationList.size();i++){%>
			lng[<%=i%>] = "<%=viaStationList.get(i).getLongitude()%>";
			lat[<%=i%>] = "<%=viaStationList.get(i).getLatitude()%>";
			startTime[<%=i%>] = "<%=viaStationList.get(i).getStart_time()%>";
			arriveTime[<%=i%>] = "<%=viaStationList.get(i).getArrive_time()%>";
			visited[<%=i%>] = 0;
			stationName[<%=i%>] = "<%=viaStationList.get(i).getStation_name()%>";
			<%
		}
	}%>
    // 百度地图API功能  
    var map = new BMap.Map("allmap");    				// 创建Map实例  
    map.addControl(new BMap.MapTypeControl());   		//添加地图类型控件  
    map.enableScrollWheelZoom(true);     				//开启鼠标滚轮缩放  
    setTimeout(drawIcon,500);  			 				//延迟执行半秒，先加载图片
    var carMk;  
    var myBeginIcon = new BMap.Icon("http://localhost:8080/train_position/image/start_point.jpg", new BMap.Size(25,37), {imageOffset: new BMap.Size(0, 0)});//人  
    var myEndIcon = new BMap.Icon("http://localhost:8080/train_position/image/end_point.jpg", new BMap.Size(25,37), {imageOffset: new BMap.Size(0, 0)});//人  
    var currentTime = "<%=currentTime%>";		//当前时间
	var currentTimeMs = (new Date(currentTime)).getTime();//当前时间对应毫秒数
	var arriveTimeMs = null;
	var startTimeMs = null;
	var nowLng = lng[lng.length-1];
	var nowLat = lat[lat.length-1];
	var lastStartTime = null;
	var lastStartTimeMs = null;
	var tempDateCopy = null;
	var lastStation = stationName[stationName.length-1];//上一站(若停到某站点则为当前站)
	var nextStation = "无";//下一站(若为终点站则无)
	var time2NextStation = 0;//距离下一站的时间
	var stationNum = 0;
	for(var i=0;i<<%=viaStationList.size()%>;i++){    //逐站开始比较，分为3种情况
		visited[i] = 1;
		if(i==0){				//当比较首发站时，只将出发时间与当前时间比较即可
			var tempStartTime = startTime[i];
			startTime[i] = tempDate + " " + tempStartTime;
			//alert("startTime[0] = "+startTime[0]);
			startTimeMs = (new Date(startTime[i])).getTime();
			//第1种情况，当前时间处于始发站发车时间之前，列车位于始发站
			if(currentTimeMs < startTimeMs){
				nowLng = lng[0];
				nowLat = lat[0];
				lastStation = stationName[0];
				nextStation = stationName[1];
				time2NextStation = (new Date(tempDate+" "+arriveTime[1])).getTime() - currentTimeMs;
				stationNum = 1;
				//alert(i);
				break;
			}
			startTime[i] = tempStartTime;
		}else{		//比较非首发站，需先判断列车到该站点的日期tempDate
			tempArriveTime = arriveTime[i];
			arriveTime[i] = tempDate + " " + tempArriveTime;
			arriveTimeMs = (new Date(arriveTime[i])).getTime();
			//lastStartTime作为第一个功能是与arriveTimeMs比较来判断是否进入第二天
			lastStartTime = tempDate + " " + startTime[i-1];
			lastStartTimeMs = (new Date(lastStartTime)).getTime();
			tempDateCopy = tempDate;
			//如果到站时间arriveTimeMs小于上一站出发时间，说明日期进入第二天，tempDate加1
			//alert("arriveTime="+arriveTime[i]+",arriveTimeMs="+arriveTimeMs+",lastStartTime="+lastStartTime+",lastStartTimeMs="+lastStartTimeMs);
			if(arriveTimeMs < lastStartTimeMs){
				tempDate = addDate(tempDate);
			}
			//lastStartTime第二个功能是作为上一站带有日期的出发时间，用来判断第2种情况
			arriveTime[i] = tempDate + " " + tempArriveTime;
			arriveTimeMs = (new Date(arriveTime[i])).getTime();
			tempStartTime = startTime[i];
			startTime[i] = tempDate + " " + tempStartTime;
			startTimeMs = (new Date(startTime[i])).getTime();
	    	//alert("currentTime="+currentTime+",lastStartTime="+lastStartTime+",arriveTime="+arriveTime[i]+",startTime="+startTime[i]+",currentTimeMs="+currentTimeMs+",lastStartTimeMs="+lastStartTimeMs+",arriveTimeMs="+arriveTimeMs+",startTimeMs="+startTimeMs);
			//第2种情况，如果当前时间大于上一站出发时间，且当前时间小于当前站到达时间，
			//即当前列车处于上一站与该站的中途
			if((currentTimeMs > lastStartTimeMs)&&(currentTimeMs < arriveTimeMs)){
				nowLng = (parseFloat(lng[i-1])+(parseFloat(lng[i])-parseFloat(lng[i-1]))*(currentTimeMs-lastStartTimeMs)/(arriveTimeMs-lastStartTimeMs)).toString();
				nowLat = (parseFloat(lat[i-1])+(parseFloat(lat[i])-parseFloat(lat[i-1]))*(currentTimeMs-lastStartTimeMs)/(arriveTimeMs-lastStartTimeMs)).toString();
				visited[i] = 0;		//说明该站点还未访问到
				lastStation = stationName[i-1];
				nextStation = stationName[i];
				time2NextStation = arriveTimeMs - currentTimeMs;
				stationNum = i;
				break;
			}else if((currentTimeMs > arriveTimeMs)&&(currentTimeMs < startTimeMs)){
				//第3种情况，如果当前时间大于该站的到达时间，且小于该站的出发时间，即列车于该站点停留
				nowLng = lng[i];
				nowLat = lat[i];
				lastStation = stationName[i];
				if(i==<%=viaStationList.size()%>-1){
					nextStation = "无";
					time2NextStation = 0;
				}else{
					nextStation = stationName[i+1];
					time2NextStation = (new Date(tempDate+" "+arriveTime[i+1])).getTime() - currentTimeMs;
				}
				stationNum = i+1;
				break;
			}
			arriveTime[i] = tempArriveTime;
			startTime[i] = tempStartTime;
		}    	
	}
	time2NextStation = parseInt(time2NextStation / (60*1000));//将毫秒转化为分钟数
	var point = new BMap.Point(nowLng, nowLat);
	map.centerAndZoom(point, 7);// 初始化地图,设置中心点坐标和地图级别  
    function drawGreenLine(i){  			 //画折线的方法
        var polyline = new BMap.Polyline([  
            new BMap.Point(lng[i],lat[i]),	 //起始点的经纬度  
            new BMap.Point(lng[i+1],lat[i+1])//终点的经纬度  
        ], {strokeColor:"green",			 //设置颜色  
            strokeWeight:4, 				 //宽度  
            strokeOpacity:1});    			 //透明度  
        map.addOverlay(polyline);  
    }  
    function drawIcon(){  
    	if(carMk){  
            map.removeOverlay(carMk);  
        } 
        carMk2 = new BMap.Marker(  
                new BMap.Point(lng[0],lat[0]),						//起始点的经纬度  
                {icon:myBeginIcon});  
        map.addOverlay(carMk2);  
        carMk = new BMap.Marker(  
                new BMap.Point(lng[lng.length-1],lat[lat.length-1]),//终点的经纬度  
                {icon:myEndIcon});  
        map.addOverlay(carMk);  
        for(var i=0;i<lng.length-1;i++){  
            drawGreenLine(i);  
        }  
    }  
    if(lng.length>0){								 //说明此时该趟列车存在
    	var myP1 = new BMap.Point(lng[0],lat[0]);    //起点  
        var myP2 = new BMap.Point(lng[lng.length-1],lat[lat.length-1]);    //终点  
        var myIcon = new BMap.Icon("http://localhost:8080/train_position/image/train_sign.jpg", new BMap.Size(32, 70), {    //小车图片  
            //offset: new BMap.Size(0, -5),    		 //相当于CSS精灵  
            imageOffset: new BMap.Size(0, 30)    	 //图片的偏移量。为了是图片底部中心对准坐标点。  
          });
        var pts = new Array();
        window.run = function (){ 
        	var pointCount = lng.length;
        	for(var i=0;i<pointCount;i++){
        		pts[i] = new BMap.Point(lng[i],lat[i]);
        	}
        	var carMk = new BMap.Marker(pts[0],{icon:myIcon});  
            map.addOverlay(carMk);  
            i=0;  
            function resetMkPoint(i){  
            	//alert("visited["+i+"]="+visited[i]);
            	if(visited[i] == 1)
            		carMk.setPosition(pts[i]);  
                if(i < pointCount && visited[i] == 1){  
                    setTimeout(function(){  
                        i++;  
                        resetMkPoint(i);  		//递归调用
                    },1000);  
                }else
                	carMk.setPosition(point);
            }  
            setTimeout(function(){  
                resetMkPoint(0);  	//显示调用 resetMkPoint 函数，并从第1个点开始移动  
            },1000);
        };
        setTimeout(function(){  
            run();  				//开始显示动点
        },3000);  					//延迟3秒显示
		var marker = new BMap.Marker(point);  // 创建标注
		map.addOverlay(marker);               // 将标注添加到地图中
		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		var station_train_code = "<%=station_train_code%>";
		var longlat = "("+nowLng.substring(0,9)+","+nowLat.substring(0,9)+")";
		var opts = {
				  width : 300,     // 信息窗口宽度
				  height: 200,     // 信息窗口高度
				  title : station_train_code, // 信息窗口标题
				  enableMessage:true,//设置允许信息窗发送短息
				  message:""
				};
		var infoWindow = new BMap.InfoWindow("<div style='line-height:1.8em;font-size:12px;'><table><tr><td>经纬度:</td><td>"+longlat+"</td></tr><tr><td>上一站</td><td>"+lastStation+"</td></tr><tr><td>下一站</td><td>"+nextStation+"</td></tr><tr><td>距离下一站时间为:</td><td>"+time2NextStation+"分钟</td></tr><tr><td>站序(所处停留站点或者上一站点)</td><td>"+stationNum+"</td></tr></table></div>", opts);  // 创建信息窗口对象 
		marker.addEventListener("click", function(){     
			map.openInfoWindow(infoWindow,point); //开启信息窗口
		});
    }else{
    	alert("该趟列车不存在！");
    }
</script> 
</html> 