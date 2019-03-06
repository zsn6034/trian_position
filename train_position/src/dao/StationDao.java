package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import tools.DAOUtil;
/**
 * 进行关于车站station的数据库操作
 * @author zsn
 *
 */
public class StationDao extends DAOUtil {
	String sql = "";
	ResultSet rs = null;
	/**
	 * 根据站名stationName获取对应车站的电报码
	 * @param station_name
	 * @return telegraph_code
	 */
	public String getTeleCode(String station_name) {
		String telegraph_code = "null";
		sql = "SELECT telegraph_code FROM station_list WHERE station_name=?";
		try {
			rs = executeQuery(sql, new Object[]{station_name});
			if(rs.next())
				telegraph_code = rs.getString("telegraph_code");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return telegraph_code;
	}
}