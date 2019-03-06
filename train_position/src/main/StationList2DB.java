package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import tools.DAOUtil;
/**
 * 下载station_name到数据库
 * @author zsn
 *
 */
public class StationList2DB extends DAOUtil{
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
	        int n = 0 ;
	        while ( (inputLine = in.readLine()) != null) {  
	            json.append(inputLine);  
	            n ++;
	        }  
	        System.out.println("n==="+n);
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
		StationList2DB stationList2DB = new StationList2DB();
        String url = "https://kyfw.12306.cn/otn/resources/js/framework/station_name.js";
		String stationStr = stationList2DB.loadJson(url);  
		//从第20个字符开始到倒数第3个字符截得的串为所要字符串
		stationStr = stationStr.substring(20, stationStr.length()-2);
        System.out.println(stationStr.length());
        System.out.println(stationStr);
        try {
			stationList2DB.str2db(stationStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			stationList2DB.closeDB();
		}
        System.out.println("over...");
	}
	/**
	 * 将站名字符串解析后写入数据库
	 * @param stationStr
	 * @return
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	private void str2db(String stationStr) throws NumberFormatException, SQLException {
		String sql = "null";
		String[] stationArray = stationStr.split("@");
		System.out.println(stationArray.length);
		System.out.println(stationArray[1]);
		System.out.println(stationArray[stationArray.length-1]);
		for(int i=1;i<stationArray.length;i++){
			//bjb|北京北|VAP|beijingbei|bjb|0
			String[] stationAttr = stationArray[i].split("\\|");
			sql = "INSERT INTO station_list(id,spell_code,station_name,telegraph_code,spell,initial) VALUES(?,?,?,?,?,?)";
			executeUpdate(sql, new Object[]{Integer.parseInt(stationAttr[5]),
					stationAttr[0],stationAttr[1],stationAttr[2],
					stationAttr[3],stationAttr[4]});
		}
	}  
}