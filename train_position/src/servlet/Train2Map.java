package servlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import dao.StationDao;
import dao.TrainDao;
import model.Train;
import model.ViaStation;
public class Train2Map extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static HashMap<String, String> stationMap;
	@Override
	public void init() throws ServletException {
		super.init();
		stationMap = new HashMap<String, String>();
		stationMap.put("金山北", "121.095909,30.900653");
		stationMap.put("新化南", "111.167098,27.661796");
		stationMap.put("玉山南", "118.299676,28.653256");
		stationMap.put("金华", "119.642135,29.116588");
		stationMap.put("湘潭北", "112.948852,27.970812");
		stationMap.put("凯里南", "107.893737,26.522345");
		stationMap.put("邵阳北", "111.510953,27.580108");
		stationMap.put("南京南", "118.804341,31.975024");
		stationMap.put("盘州", "104.580725,25.800105");
		stationMap.put("曲靖北", "103.79278,25.592945");
		stationMap.put("关岭", "105.535861,25.969535");
		stationMap.put("三穗", "108.657919,26.969138");
		stationMap.put("铜仁南", "108.982119,27.285157");
		stationMap.put("昆明南", "102.868997,24.876596");
		stationMap.put("嵩明", "103.143767,25.246043");
		stationMap.put("安顺西", "105.881528,26.204308");
		stationMap.put("平坝南", "103.143767,25.246043");
		stationMap.put("溆浦南", "110.599261,27.611199");
		stationMap.put("韶山南", "112.554231,27.893762");
		stationMap.put("广州北", "113.211108,23.382816");
		stationMap.put("富源北", "104.276593,25.718603");
		stationMap.put("双峰北", "112.021247,27.568837");
		stationMap.put("石门县北", "111.441186,29.606425");
		stationMap.put("于家堡", "117.691495,39.010155");
		stationMap.put("天津西", "117.169986,39.16427");
		stationMap.put("新郑机场", "113.854048,34.532259");
		stationMap.put("南湖东", "114.387326,30.473223");
		stationMap.put("孝感东", "113.954474,30.9391");
		stationMap.put("天河机场", "114.217844,30.776223");
		stationMap.put("大治北场", "115.006922,30.126793");
		stationMap.put("闵集", "114.147906,30.830114");
		stationMap.put("青城山", "103.612953,30.897546");
		stationMap.put("成都东", "104.147737,30.635028");
		stationMap.put("罗江东", "104.541034,31.310279");
		stationMap.put("德阳", "104.39646,31.172247");
		stationMap.put("百宜", "106.986275,26.835561");
		stationMap.put("万州北", "108.373843,30.869004");
		stationMap.put("梁平南", "107.776748,30.647445");
		stationMap.put("垫江", "107.386019,30.313325");
		stationMap.put("开阳", "106.980749,27.047186");
		stationMap.put("桃村北", "121.153046,37.217382");
		stationMap.put("深圳北", "114.035529,22.615107");
		stationMap.put("福田", "114.063271,22.544659");
		stationMap.put("大旺", "112.818794,23.259881");
		stationMap.put("狮山", "112.990439,23.128163");
		stationMap.put("狮山北", "112.978259,23.159538");
		stationMap.put("三水北", "112.924959,23.190401");
		stationMap.put("云东海", "112.85198,23.224043");
		stationMap.put("四会", "112.76118,23.27638");
		stationMap.put("鼎湖东", "112.666512,23.219521");
		stationMap.put("鼎湖山", "112.593992,23.18083");
		stationMap.put("端州", "112.519853,23.11611");
		stationMap.put("株洲南", "113.175508,27.775328");
		stationMap.put("大丰", "113.162262,27.885396");
		stationMap.put("先锋", "112.998324,28.080691");
		stationMap.put("板塘", "112.964094,27.860236");
		stationMap.put("深圳", "114.123611,22.53796");
		stationMap.put("顺德", "113.283154,22.881332");
		stationMap.put("明珠", "113.521396,22.275056");
		stationMap.put("樟木头东", "114.112633,22.964905");
		stationMap.put("银瓶", "114.164407,22.972377");
		stationMap.put("龙丰", "114.401358,23.068206");
		stationMap.put("沥林北", "114.236056,22.985513");
		stationMap.put("仲恺", "114.333375,23.011787");
		stationMap.put("西湖东", "114.407563,23.093564");
		stationMap.put("石家庄", "114.490825,38.016821");
		stationMap.put("泾县", "118.393395,30.665008");
		stationMap.put("武夷山东", "118.099255,27.479524");
		stationMap.put("沧州西", "116.774362,38.313274");
		stationMap.put("大连北", "121.614009,39.022493");
		stationMap.put("瑞安", "120.587514,27.773006");
		stationMap.put("缙云西", "120.061966,28.682517");
		stationMap.put("永康南", "120.027903,28.856828");
		stationMap.put("武义北", "119.855207,28.988533");
		stationMap.put("滁州", "118.326879,32.204422");
		stationMap.put("临海", "121.253319,28.885732");
		stationMap.put("台州", "121.296612,28.691911");
		stationMap.put("绩溪北", "118.57836,30.077223");
		stationMap.put("婺源", "117.881927,29.245218");
		stationMap.put("南平北", "118.279402,26.596239");
		stationMap.put("古田北", "118.672104,26.468548");
		stationMap.put("厦门北", "118.080553,24.642667");
		stationMap.put("重庆北", "106.557329,29.614066");
		stationMap.put("德兴", "117.867696,28.963838");
		stationMap.put("武夷山东", "118.099255,27.479524");
		stationMap.put("建瓯西", "118.284725,27.057147");
		stationMap.put("白洋淀", "115.880825,39.075048");
		stationMap.put("白沟", "116.099897,39.092733");
		stationMap.put("霸州西", "116.357414,39.098505");
		stationMap.put("本溪", "123.765369,41.301014");
		stationMap.put("永城北", "116.526642,34.223134");
		stationMap.put("砀山南", "116.311669,34.39322");
		stationMap.put("民权北", "115.186481,34.665914");
		stationMap.put("三门峡南", "111.166879,34.754363");
		stationMap.put("五龙背东", "124.294458,40.271148");
		stationMap.put("双吉", "126.446907,43.970541");
		stationMap.put("胜芳", "116.766125,39.145866");
		stationMap.put("闽清北", "118.878392,26.29877");
		stationMap.put("武夷山北", "118.050917,27.783469");
		stationMap.put("五府山", "118.052243,28.233786");
		stationMap.put("上海", "121.462056,31.255923");
		stationMap.put("荣昌北", "105.642882,29.439217");
		stationMap.put("资阳北", "104.689329,30.131355");
		stationMap.put("南昌", "115.925576,28.668366");
		stationMap.put("灵武", "106.374049,38.163739");
		stationMap.put("盐池", "107.35505,37.74031");
		stationMap.put("马兰", "87.388615,42.241131");
		stationMap.put("三门峡", "111.239549,34.766858");
		stationMap.put("宁东", "106.553987,38.152581");
		stationMap.put("松原北", "125.008013,45.114832");
		stationMap.put("三井子", "125.353638,45.111179");
		stationMap.put("弓棚子", "125.717604,45.07269");
		stationMap.put("眉山", "103.819088,30.070862");
		stationMap.put("达拉特西", "109.897905,40.386578");
		stationMap.put("鄂尔多斯", "109.849032,39.566899");
		stationMap.put("富源", "104.276838,25.687928");
		stationMap.put("龙市", "113.974751,26.725225");
		stationMap.put("綦江", "106.670523,29.023276");
		stationMap.put("小榆树", "124.518941,47.893498");
		stationMap.put("辛集", "115.221895,37.911424");
		stationMap.put("大兴", "123.756887,46.882845");
		stationMap.put("古田会址", "116.778437,25.190658");
		stationMap.put("邵东", "111.754375,27.280101");
		stationMap.put("磁窑", "117.112697,35.907723");
		stationMap.put("清河门", "121.426558,41.783227");
		stationMap.put("阜新南", "121.644453,41.993137");
		stationMap.put("永安", "117.384865,25.980893");
		stationMap.put("新民", "122.82476,42.007406");
		stationMap.put("羊场", "120.00776,42.645985");
		stationMap.put("治安", "121.515797,43.356006");
		stationMap.put("坪上", "119.076785,35.184513");
		stationMap.put("广通北", "101.771463,25.17029");
		stationMap.put("张家口南", "114.889807,40.758007");
		stationMap.put("二龙屯", "121.116406,45.339606");
		stationMap.put("西乌旗", "117.519381,44.625249");
		stationMap.put("白音华南", "118.459287,44.776311");
		stationMap.put("哈达铺", "104.241375,34.226408");
		stationMap.put("陇南", "104.966936,33.381267");
		stationMap.put("姚渡", "105.436081,32.785841");
		stationMap.put("三门峡西", "111.087309,34.693475");
		stationMap.put("他石海", "124.313477,45.570696");
		stationMap.put("建设", "124.18749,45.458888");
		stationMap.put("巨宝", "123.486903,44.722363");
		stationMap.put("二龙", "119.283215,41.704967");
		stationMap.put("乃林", "119.27988,41.917639");
		stationMap.put("热水", "119.28258,42.145814");
		stationMap.put("八角台", "121.24289,41.263387");
		stationMap.put("汐子", "119.270829,41.799032");
		stationMap.put("高山子", "122.056799,41.556144");
		stationMap.put("兴福", "127.127073,46.696725");
		stationMap.put("姜家", "126.105952,46.017337");
		stationMap.put("万发屯", "127.127795,46.540793");
		stationMap.put("四方台", "127.001807,46.928328");
		stationMap.put("李家", "126.763416,47.946187");
		stationMap.put("兴莲", "130.302209,46.885023");
		stationMap.put("洪河", "133.503593,47.610639");
		stationMap.put("加南", "124.16886,50.331344");
		stationMap.put("尚家", "125.883651,46.133857");
		stationMap.put("永安乡", "131.484277,45.345034");
		stationMap.put("卫星", "132.499437,45.729467");
		stationMap.put("东方红", "133.090504,46.206667");
		stationMap.put("永胜", "130.558707,46.78045");
		stationMap.put("兴凯", "132.074787,45.708527");
		stationMap.put("新松浦", "126.672105,45.846989");
		stationMap.put("团结", "124.622725,48.109362");
		stationMap.put("恒地营", "124.970224,48.567498");
		stationMap.put("老莱", "125.013627,48.656733");
		stationMap.put("石磷", "130.702796,45.166962");
		stationMap.put("乌奴耳", "121.255606,48.863209");
		stationMap.put("兴安岭", "121.678682,48.827304");
		stationMap.put("双柳", "132.038817,46.49229");
		stationMap.put("青山", "130.576761,45.454856");
		stationMap.put("姚家", "125.886805,44.785188");
		stationMap.put("松树", "122.10457,39.831855");
		stationMap.put("连山关", "123.764532,40.975334");
		stationMap.put("黄泥崴子", "127.273812,42.210846");
		stationMap.put("十里坪", "129.06176,42.625607");
		stationMap.put("柳河", "125.771319,42.295836");
		stationMap.put("氡泉", "127.182487,42.134265");
		stationMap.put("北岗", "127.598113,42.419139");
		stationMap.put("白河", "128.138838,42.459475");
		stationMap.put("松江镇", "128.345289,42.57718");
		stationMap.put("荒沟西", "128.378371,42.560833");
		stationMap.put("枕头峰", "128.600373,42.4927");
		stationMap.put("三道湖", "126.763257,42.356686");
		stationMap.put("大东", "126.782184,42.084512");
		stationMap.put("左家", "126.092576,44.076205");
		stationMap.put("双河镇", "126.189383,43.473574");
		stationMap.put("五道沟", "125.882375,42.143335");
		stationMap.put("白泉", "125.008144,42.932169");
		stationMap.put("西哲里木", "120.608948,45.558493");
		stationMap.put("蓟州", "117.404624,40.032737");
		stationMap.put("昆独仑召", "109.80302,40.709839");
		stationMap.put("包头 东", "110.03128381271408,40.576215996076485");
		stationMap.put("二道沙河", "110.006305,40.631136");
		stationMap.put("韦庄", "109.942342,34.976713");
		stationMap.put("栟茶", "120.887485,32.504279");
		stationMap.put("南山北", "109.202316,18.325366");
		stationMap.put("干塘", "104.53098,37.461402");
		stationMap.put("河口北", "103.982435,22.53458");
		stationMap.put("曲江", "102.84443,23.978071");
		stationMap.put("宾阳", "109.098573,23.222499");
		stationMap.put("钟山西", "111.33853,24.559173");
	}
	/**
	 * 根据url获取相应json字符串
	 * @param url
	 * @return
	 */
	String loadJson (String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL urlObject = new URL(url);
			URLConnection uc = urlObject.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"UTF-8"));
			String inputLine = null;
			while ( (inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json.toString();
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Train train = null;
		//获取车次station_train_code和日期date
		String station_train_code = request.getParameter("station_train_code");
		String date = request.getParameter("date");
		System.out.println("station_train_code==="+station_train_code);
		System.out.println("date==="+date);
		//根据车次station_train_code和日期date从数据库表train_list中获取列车编号train_no以及
		//列车起始站start_station和终点站end_station
		TrainDao trainDao = new TrainDao();
		String train_no = trainDao.getTrainNo(station_train_code,date);
		String start_station = trainDao.getStartStation(station_train_code,date);
		String end_station = trainDao.getEndStation(station_train_code,date);
		System.out.println("train_no==="+train_no);
		System.out.println("start_station==="+start_station);
		System.out.println("end_station==="+end_station);
		if("null".equals(train_no)||"null".equals(start_station)
				||"null".equals(end_station)){//如果找不到该趟列车，返回null
			System.out.println("train======null");
		}else{
			//根据获取到的起始站start_station和终点站end_station从数据库表station_list中获取
			//起始站电报码start_tele_code和终点站电报码end_tele_code
			StationDao stationDao = new StationDao();
			String start_tele_code = stationDao.getTeleCode(start_station).toUpperCase();
			String end_tele_code = stationDao.getTeleCode(end_station).toUpperCase();
			System.out.println("start_tele_code==="+start_tele_code);
			System.out.println("end_tele_code==="+end_tele_code);
			String url = "https://kyfw.12306.cn/otn/czxx/queryByTrainNo?train_no="+train_no+"&from_station_telecode="+start_tele_code+"&to_station_telecode="+end_tele_code+"&depart_date="+date;
			System.out.println("url="+url);
			String trainDetailStr = this.loadJson(url);
			try {
				JSONObject json = new JSONObject(trainDetailStr);
				JSONObject dataJson = json.getJSONObject("data");
				System.out.println("dataJson="+dataJson);
				JSONArray dataArray = dataJson.getJSONArray("data");
				System.out.println("dataArray="+dataArray);
				ArrayList<ViaStation> viaStationList = new ArrayList<ViaStation>();
				for(int i=0;i<dataArray.length();i++){
					ViaStation viaStation = new ViaStation(
							dataArray.getJSONObject(i).getString("station_name"),
							dataArray.getJSONObject(i).getString("arrive_time"),
							dataArray.getJSONObject(i).getString("start_time"),
							dataArray.getJSONObject(i).getString("stopover_time"),
							dataArray.getJSONObject(i).getString("station_no"),
							dataArray.getJSONObject(i).getBoolean("isEnabled")+"");
					viaStationList.add(viaStation);
				}
				for(int i=0;i<viaStationList.size();i++){
					System.out.println(viaStationList.get(i).toString());
				}
				for(int i=0;i<viaStationList.size();i++){
					url = "http://api.map.baidu.com/geocoder/v2/?callback=renderOption&output=json&address=";
					//String lastName = viaStationList.get(i).getStation_name().substring(viaStationList.get(i).getStation_name().length()-1);
					if(stationMap.get(viaStationList.get(i).getStation_name())!=null){
						//该站名属于stationMap，是已标注好经纬度的特殊站名
						String []location = stationMap.get(viaStationList.get(i).getStation_name()).split(",");
						viaStationList.get(i).setLongitude(location[0]);
						viaStationList.get(i).setLatitude(location[1]);
					}else if(viaStationList.get(i).getStation_name().equals("东海")){
						if(viaStationList.get(i+1).getStation_name().equals("鸡东")){
							viaStationList.get(i).setLongitude("131.317819");
							viaStationList.get(i).setLatitude("45.2966");
						}
					}else{
						viaStationList.get(i).setStation_name(viaStationList.get(i).getStation_name()+"站");
						url = url + viaStationList.get(i).getStation_name() + "&ak=Wplg4l74vg8SybyTWCYIZuyVDiakG1wb";
						System.out.println("queryUrl:::"+url);
						String addressJson = this.loadJson(url);	//获取具体地址的经纬度
						//字符串从第28个字符开始才是json格式到倒数第2个
						addressJson = addressJson.substring(27,addressJson.length()-1);
						json = new JSONObject(addressJson);
						json = json.getJSONObject("result");
						json = json.getJSONObject("location");
						viaStationList.get(i).setLongitude(json.getDouble("lng")+"");
						viaStationList.get(i).setLatitude(json.getDouble("lat")+"");
					}
					System.out.println(":::"+viaStationList.get(i).getStation_name()+"("+viaStationList.get(i).getLongitude()+","+viaStationList.get(i).getLatitude()+")");
				}
				train = new Train(station_train_code, date, start_station, end_station, train_no, viaStationList);
				System.out.println(train);

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("train", train);
		if(train!=null){
			request.getRequestDispatcher("/train_position/track_show.jsp").forward(request, response);
		}else{
			String path = request.getContextPath();
			response.sendRedirect(path+"/train_position/train_query.jsp?found=NO");
		}
	}
}