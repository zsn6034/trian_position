package model;
import java.util.ArrayList;
public class Train {
	String station_train_code;			//列车车次
	String date;						//出发日期
	String start_station;				//起始站
	String end_station;					//终点站
	String train_no;					//列车编号
	ArrayList<ViaStation> viaStationList;	//途经站点
	/**
	 * 构造函数1
	 * @param station_train_code
	 * @param date
	 * @param start_station
	 * @param end_station
	 * @param train_no
	 * @param viaStationList
	 */
	public Train(String station_train_code, String date, String start_station,
			String end_station, String train_no,
			ArrayList<ViaStation> viaStationList) {
		super();
		this.station_train_code = station_train_code;
		this.date = date;
		this.start_station = start_station;
		this.end_station = end_station;
		this.train_no = train_no;
		this.viaStationList = viaStationList;
	}
	/**
	 * 构造函数2
	 * @param station_train_code
	 * @param date
	 * @param start_station
	 * @param end_station
	 * @param train_no
	 */
	public Train(String station_train_code, String date, String start_station,
			String end_station, String train_no) {
		super();
		this.station_train_code = station_train_code;
		this.date = date;
		this.start_station = start_station;
		this.end_station = end_station;
		this.train_no = train_no;
	}
	/**
	 * toString方法
	 */
	public String toString(){
		return this.station_train_code+","+
				this.date+","+
				this.start_station+","+
				this.end_station+","+
				this.train_no+","+
				"viaStationList.size==="+this.viaStationList.size();
	}
	/**
	 * 获取该趟列车途经站点数量
	 * @return viaStationList.size()
	 */
	public int getViaStationCount(){
		return this.viaStationList.size();
	}
	/**
	 * 获取该趟列车途经站点列表ArrayList<ViaStation>
	 * @return viaStationList
	 */
	public ArrayList<ViaStation> getViaStationList() {
		return viaStationList;
	}
	/**
	 * 获取该趟列车出发日期
	 * @return
	 */
	public String getDate() {
		return date;
	}
	/**
	 * 获取列车车次
	 * @return station_train_code
	 */
	public String getStation_train_code() {
		return station_train_code;
	}
	/**
	 * 设置列车车次
	 * @param station_train_code
	 */
	public void setStation_train_code(String station_train_code) {
		this.station_train_code = station_train_code;
	}
	/**
	 * 获取列车始发站
	 * @return start_station
	 */
	public String getStart_station() {
		return start_station;
	}
	/**
	 * 设置列车始发站
	 * @param start_station
	 */
	public void setStart_station(String start_station) {
		this.start_station = start_station;
	}
	/**
	 * 获取列车终点站
	 * @return end_station
	 */
	public String getEnd_station() {
		return end_station;
	}
	/**
	 * 设置列车终点站
	 * @param end_station
	 */
	public void setEnd_station(String end_station) {
		this.end_station = end_station;
	}
	/**
	 * 获取trian_no(非列车车次)
	 * @return train_no
	 */
	public String getTrain_no() {
		return train_no;
	}
	/**
	 * 设置trian_no(非列车车次)
	 * @param train_no
	 */
	public void setTrain_no(String train_no) {
		this.train_no = train_no;
	}
	/**
	 * 设置列车出发日期
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * 设置途经站点
	 * @param viaStationList
	 */
	public void setViaStationList(ArrayList<ViaStation> viaStationList) {
		this.viaStationList = viaStationList;
	}
}