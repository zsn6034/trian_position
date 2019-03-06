# trian_position
This is a project about train positioning, using the application to view real-time information on train positioning on the web.

本科毕业设计做的一个使用百度地图进行列车定位的web系统。
设计流程如下：

1.获取全国火车站站点信息： 
从12306官网提供的API入口url——https://kyfw.12306.cn/otn/resources/js/framework/station_name.js， 获取全国火车站站点信息，保存到数据库为表station_list

2.获取列车车次信息：
从12306官网提供的API入口url——https://kyfw.12306.cn/otn/resources/js/query/train_list.js， 获取列车车次信息，保存到数据库为表train_list

3.列车途经站点下载：
根据用户输入查询的车次station_train_code和日期date，结合表station_list、表train_list和12306官网API入口url——https://kyfw.12306.cn/otn/czxx/queryByTrainNo, 拼接成一个新的url地址，格式如下：
  https://kyfw.12306.cn/otn/czxx/queryByTrainNo?train_no=车次编号&from_station_telecode=起始站代码&to_station_telecode=终点站代码&depart_date=日期
通过访问该新的url从而查询该趟车次途经站点信息，得到的是一个列表list，包含该趟车次的所有途径站点信息

4.列车行进路线绘制：
调用百度地图API，将途经站点在地图上的位置点按照途经次序一一连接，形成路线

5.最终定位显示
在百度地图上，将绘制好的路线及根据当前时间计算出来的定位进行显示反馈给用户

注:程序中虽然调用了百度地图api将每个位置的文字地理信息与经纬度转换从而可以在地图上标注出位置点，但是由于百度地图API在处理某些地址时转换的经纬度有误，所以在其中加入了一部分手工检查工作，筛选出一些转换的经纬度与真实经纬度差距较大的地址，将其放入一个HashMap中进行特殊处理。
附程序截图：
![main](https://github.com/zsn6034/trian_position/blob/master/main.png)
![result](https://github.com/zsn6034/trian_position/blob/master/result.png)
![detail](https://github.com/zsn6034/trian_position/blob/master/detail.png)





  
