package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import model.Train;
import tools.DAOUtil;
/**
 * 进行关于列车train的数据库操作
 * @author zsn
 *
 */
public class TrainDao extends DAOUtil {
	String sql = "";
	ResultSet rs = null;
	/**
	 * 根据车次station_train_code和日期date从数据库表train_list中获取列车编号train_no
	 * @param station_train_code
	 * @param date
	 * @return train_no
	 */
	public String getTrainNo(String station_train_code, String date) {
		String train_no = "null";
		sql = "SELECT train_no FROM train_list WHERE station_train_code=? AND date=?";
		try {
			rs = executeQuery(sql, new Object[]{station_train_code,date});
			if(rs.next())
				train_no = rs.getString("train_no");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return train_no;
	}
	/**
	 * 根据车次station_train_code和日期date从数据库表train_list中获取列车起始站start_station
	 * @param station_train_code
	 * @param date
	 * @return start_station
	 */
	public String getStartStation(String station_train_code, String date) {
		String start_station = "null";
		sql = "SELECT start_station FROM train_list WHERE station_train_code=? AND DATE=? ";
		try {
			rs = executeQuery(sql, new Object[]{station_train_code,date});
			if(rs.next())
				start_station = rs.getString("start_station");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return start_station;
	}
	/**
	 * 根据车次station_train_code和日期date从数据库表train_list中获取列车终点站end_station
	 * @param station_train_code
	 * @param date
	 * @return end_station
	 */
	public String getEndStation(String station_train_code, String date) {
		String end_station = "null";
		sql = "SELECT end_station FROM train_list WHERE station_train_code=? AND DATE=? ";
		try {
			rs = executeQuery(sql, new Object[]{station_train_code,date});
			if(rs.next())
				end_station = rs.getString("end_station");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return end_station;
	}
	/**
	 * 获取数据库当前所有列车的出发日期列表
	 * @return listDate
	 */
	public List<Map<String, String>> getDates() {
		List<Map<String, String>> listDate = null;
		sql = "SELECT DATE FROM train_list GROUP BY date";
		try {
			listDate = queryByList(sql, new Object[]{});
		} catch (SQLException e) {
			e.printStackTrace();
		}
System.out.println("size==="+listDate.size());
		return listDate;
	}
	/**
	 * 根据始发站和终点站获取所有符合的列车
	 * @param end_station 
	 * @param start_station 
	 * @return listTrain
	 */
	public List<Train> getTrainNameList(String start_station, String end_station) {
		List<Train> listTrain = new LinkedList<Train>();
		String station_train_code = "";
		String date = "";
		String train_no = "";
		if("".equals(start_station)&&"".equals(end_station)){	
			//若无起始站和终点站输入，则随机显示前20条列车信息
			sql = "SELECT * FROM train_list ORDER BY RAND() LIMIT 20";
			try {
				rs = executeQuery(sql, new Object[]{});
				while(rs.next()){
					station_train_code = rs.getString("station_train_code");
					date = rs.getString("date");
					start_station = rs.getString("start_station");
					end_station = rs.getString("end_station");
					train_no = rs.getString("train_no");
					Train train = new Train(station_train_code, date, start_station, end_station, train_no);
					listTrain.add(train);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			sql = "SELECT * FROM train_List WHERE start_station LIKE ? AND end_station LIKE ? GROUP BY station_train_code";
			start_station = "%" + start_station + "%";
			end_station = "%" + end_station + "%";
			try {
				rs = executeQuery(sql, new Object[]{start_station,end_station});
				while(rs.next()){
					station_train_code = rs.getString("station_train_code");
					date = rs.getString("date");
					start_station = rs.getString("start_station");
					end_station = rs.getString("end_station");
					train_no = rs.getString("train_no");
					Train train = new Train(station_train_code, date, start_station, end_station, train_no);
					listTrain.add(train);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listTrain;
	}
}