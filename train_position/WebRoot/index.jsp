<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<title>批量地址</title>
	<style type="text/css">
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#l-map{height:300px;width:100%;}
		#r-result{width:100%; font-size:14px;line-height:20px;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=Wplg4l74vg8SybyTWCYIZuyVDiakG1wb"></script>
</head>
<body>
	<div id="l-map"></div>
	<div id="r-result">
		<input type="button" value="批量地址解析" onclick="bdGEO()" />
		<div id="result"></div>
	</div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("l-map");
	map.centerAndZoom(new BMap.Point(117.269945,31.86713), 13);
	map.enableScrollWheelZoom(true);
	var index = 0;
	var myGeo = new BMap.Geocoder();
	var adds = [
		"长春站",
		"吉林站",
		
	];
	function bdGEO(){
		var add = adds[index];
		geocodeSearch(add);
		index++;
	}
	function geocodeSearch(add){
		if(index < adds.length){
			setTimeout(window.bdGEO,400);
		} 
		myGeo.getPoint(add, function(point){
			if (point) {
				document.getElementById("result").innerHTML +=  index + "、" + add + ":" + point.lng + "," + point.lat + "</br>";
				var address = new BMap.Point(point.lng, point.lat);
				addMarker(address,new BMap.Label(index+":"+add,{offset:new BMap.Size(20,-10)}));
			}
		},"长春市");
	}
	// 编写自定义函数,创建标注
	function addMarker(point,label){
		var marker = new BMap.Marker(point);
		map.addOverlay(marker);
		marker.setLabel(label);
	}
</script>