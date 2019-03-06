package model;
/**
 * 列车途经站点信息
 * @author zsn
 *
 */
public class ViaStation {
	String station_name;			//途经站名
	String arrive_time;				//到站时间
	String start_time;				//出发时间
	String stopover_time;			//停留时间
	String station_no;				//站次
	String isEnabled;				//是否开通
	String longitude;				//经度
	String latitude;				//纬度
	/**
	 * 获取该站点经度
	 * @return longitude
	 */
	public String getLongitude() {
		return this.longitude;
	}
	/**
	 * 设置该站点经度
	 * @param longitude
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * 获取该站点纬度
	 * @return latitude
	 */
	public String getLatitude() {
		return this.latitude;
	}
	/**
	 * 设置该站点纬度
	 * @param latitude
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * 构造方法
	 * @param station_name
	 * @param arrive_time
	 * @param start_time
	 * @param stopover_time
	 * @param station_no
	 * @param isEnabled
	 */
	public ViaStation(String station_name, String arrive_time,
			String start_time, String stopover_time, String station_no,
			String isEnabled) {
		super();
		this.station_name = station_name;
		this.arrive_time = arrive_time;
		this.start_time = start_time;
		this.stopover_time = stopover_time;
		this.station_no = station_no;
		this.isEnabled = isEnabled;
	}
	/**
	 * toString方法
	 */
	@Override
	public String toString(){
		return this.station_name+"|"+
				this.arrive_time+"|"+
				this.start_time+"|"+
				this.stopover_time+"|"+
				this.station_no+"|"+
				this.isEnabled;
	}
	/**
	 * 获取站名
	 * @return station_name
	 */
	public String getStation_name() {
		return this.station_name;
	}

	/**
	 * 设置站名
	 * @param station_name
	 */
	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}
	/**
	 * 获取到站时间
	 * @return arrive_time
	 */
	public String getArrive_time() {
		return this.arrive_time;
	}
	/**
	 * 设置到站时间
	 * @param arrive_time
	 */
	public void setArrive_time(String arrive_time) {
		this.arrive_time = arrive_time;
	}
	/**
	 * 获取出发时间
	 * @return start_time
	 */
	public String getStart_time() {
		return this.start_time;
	}
	/**
	 * 设置出发时间
	 * @param start_time
	 */
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	/**
	 * 返回该站停留时间
	 * @return stopover_time
	 */
	public String getStopover_time() {
		return this.stopover_time;
	}
	/**
	 * 设置该站停留时间
	 * @param stopover_time
	 */
	public void setStopover_time(String stopover_time) {
		this.stopover_time = stopover_time;
	}
	/**
	 * 返回站序
	 * @return station_no
	 */
	public String getStation_no() {
		return this.station_no;
	}
	/**
	 * 设置站序
	 * @param station_no
	 */
	public void setStation_no(String station_no) {
		this.station_no = station_no;
	}
}