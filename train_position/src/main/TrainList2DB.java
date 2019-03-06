package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tools.DAOUtil;
/**
 * 下载train_list到数据库
 * @author zsn
 *
 */
public class TrainList2DB extends DAOUtil{
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
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
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
	public static void main(String[] args) {
		System.out.println("start...");
		TrainList2DB trainList2DB = new TrainList2DB();
		//https://kyfw.12306.cn/otn/resources/js/query/train_list.js
		String url = "https://kyfw.12306.cn/otn/resources/js/query/train_list.js";
		String jsonString = trainList2DB.loadJson(url);
		jsonString = jsonString.substring(16);		//从第17个字符开始为json串
		//JSON串长度为：20402990
		System.out.println("JSON串长度为："+jsonString.length());
		//jsonString = jsonString.substring(0,jsonString.length()-17);
		//		File file = new File("train_data.txt");
		//		try {
		//			FileOutputStream fos = new FileOutputStream(file);
		//			BufferedOutputStream bos = new BufferedOutputStream(fos);
		//			bos.write(jsonString.getBytes());
		//			bos.flush();
		//			bos.close();
		//		} catch (FileNotFoundException ex) {
		//			ex.printStackTrace();
		//		} catch (IOException ex) {
		//			ex.printStackTrace();
		//		}
		//System.out.println("over...");
		try {
			//System.out.println("jsonString="+jsonString);
			JSONObject json = new JSONObject(jsonString);
			trainList2DB.json2db(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			trainList2DB.closeDB();
		}
		System.out.println("over...");

	}
	/**
	 * 将列车车次JSON串解析后写入数据库
	 * @param json
	 * @throws SQLException
	 */
	private void json2db(JSONObject json) throws SQLException {
		String sql = "null";
		String key = "null";
		JSONObject jKeyIsDate = null;
		Iterator it = json.keys();
		JSONArray jaKeyIsC = null;
		JSONArray jaKeyIsD = null;
		JSONArray jaKeyIsG = null;
		JSONArray jaKeyIsZ = null;
		JSONArray jaKeyIsT = null;
		JSONArray jaKeyIsK = null;
		JSONObject jTrainInfo = null;
		String station_train_code = "null";
		String train_no = "null";
		String[] stc = null;
		while(it.hasNext()){
			key = (String)it.next();			//key是日期，如"2017-03-29"
			try {
				jKeyIsDate = json.getJSONObject(key);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			try{
				jaKeyIsC = jKeyIsDate.getJSONArray("C");
				for(int i=0;i<jaKeyIsC.length();i++){
					jTrainInfo = jaKeyIsC.getJSONObject(i);
					station_train_code = jTrainInfo.getString("station_train_code");
					train_no = jTrainInfo.getString("train_no");
					stc = station_train_code.split("(\\(|-|\\))");
					station_train_code = stc[0];		//车次
					String start_station = stc[1];		//起始站
					String end_station = stc[2];		//终点站
					sql = "INSERT INTO train_list(DATE,station_train_code,start_station,end_station,train_no) VALUES(?,?,?,?,?)";
					this.executeUpdate(sql, new Object[]{key,station_train_code,start_station,end_station,train_no});
				}
			}catch (JSONException e) {
				//e.printStackTrace();
			}
			try{
				jaKeyIsD = jKeyIsDate.getJSONArray("D");
				System.out.println("jaKeyIsD.length()="+jaKeyIsD.length());
				for(int i=0;i<jaKeyIsD.length();i++){
					jTrainInfo = jaKeyIsD.getJSONObject(i);
					station_train_code = jTrainInfo.getString("station_train_code");
					train_no = jTrainInfo.getString("train_no");
					stc = station_train_code.split("(\\(|-|\\))");
					station_train_code = stc[0];		//车次
					String start_station = stc[1];		//起始站
					String end_station = stc[2];		//终点站
					sql = "INSERT INTO train_list(DATE,station_train_code,start_station,end_station,train_no) VALUES(?,?,?,?,?)";
					this.executeUpdate(sql, new Object[]{key,station_train_code,start_station,end_station,train_no});
				}
			}catch (JSONException e) {
				//e.printStackTrace();
			}
			try{
				jaKeyIsG = jKeyIsDate.getJSONArray("G");
				for(int i=0;i<jaKeyIsG.length();i++){
					jTrainInfo = jaKeyIsG.getJSONObject(i);
					station_train_code = jTrainInfo.getString("station_train_code");
					train_no = jTrainInfo.getString("train_no");
					stc = station_train_code.split("(\\(|-|\\))");
					station_train_code = stc[0];		//车次
					String start_station = stc[1];		//起始站
					String end_station = stc[2];		//终点站
					sql = "INSERT INTO train_list(DATE,station_train_code,start_station,end_station,train_no) VALUES(?,?,?,?,?)";
					this.executeUpdate(sql, new Object[]{key,station_train_code,start_station,end_station,train_no});
				}
			}catch (JSONException e) {
				//e.printStackTrace();
			}
			try{
				jaKeyIsZ = jKeyIsDate.getJSONArray("Z");
				for(int i=0;i<jaKeyIsZ.length();i++){
					jTrainInfo = jaKeyIsZ.getJSONObject(i);
					station_train_code = jTrainInfo.getString("station_train_code");
					train_no = jTrainInfo.getString("train_no");
					stc = station_train_code.split("(\\(|-|\\))");
					station_train_code = stc[0];		//车次
					String start_station = stc[1];		//起始站
					String end_station = stc[2];		//终点站
					sql = "INSERT INTO train_list(DATE,station_train_code,start_station,end_station,train_no) VALUES(?,?,?,?,?)";
					this.executeUpdate(sql, new Object[]{key,station_train_code,start_station,end_station,train_no});
				}
			}catch (JSONException e) {
				//e.printStackTrace();
			}
			try{
				jaKeyIsT = jKeyIsDate.getJSONArray("T");
				for(int i=0;i<jaKeyIsT.length();i++){
					jTrainInfo = jaKeyIsT.getJSONObject(i);
					station_train_code = jTrainInfo.getString("station_train_code");
					train_no = jTrainInfo.getString("train_no");
					stc = station_train_code.split("(\\(|-|\\))");
					station_train_code = stc[0];		//车次
					String start_station = stc[1];		//起始站
					String end_station = stc[2];		//终点站
					sql = "INSERT INTO train_list(DATE,station_train_code,start_station,end_station,train_no) VALUES(?,?,?,?,?)";
					this.executeUpdate(sql, new Object[]{key,station_train_code,start_station,end_station,train_no});
				}
			}catch (JSONException e) {
				//e.printStackTrace();
			}
			try{
				jaKeyIsK = jKeyIsDate.getJSONArray("K");
				for(int i=0;i<jaKeyIsK.length();i++){
					jTrainInfo = jaKeyIsK.getJSONObject(i);
					station_train_code = jTrainInfo.getString("station_train_code");
					train_no = jTrainInfo.getString("train_no");
					stc = station_train_code.split("(\\(|-|\\))");
					station_train_code = stc[0];		//车次
					String start_station = stc[1];		//起始站
					String end_station = stc[2];		//终点站
					sql = "INSERT INTO train_list(DATE,station_train_code,start_station,end_station,train_no) VALUES(?,?,?,?,?)";
					this.executeUpdate(sql, new Object[]{key,station_train_code,start_station,end_station,train_no});
				}
			}catch (JSONException e) {
				//e.printStackTrace();
			}
		}
	}
}