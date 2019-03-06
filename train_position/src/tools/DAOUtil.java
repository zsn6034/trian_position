package tools;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import com.mysql.jdbc.ResultSetMetaData;
import tools.SqlManager;
public abstract class DAOUtil{
	protected Logger log = Logger.getLogger("DaoUtil");
	private SqlManager manager = null;
	public DAOUtil()  {
			try {
				manager=SqlManager.createInstance();
				try {
					manager.connectDB();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public ResultSet executeQuery(String sql, Object[] params)  throws SQLException{
			log.info("sql:"+sql);
			ResultSet rs = manager.executeQuery(sql, params);	
			return rs;
	}
	public boolean executeUpdate(String sql, Object[] params) throws SQLException {
		log.info("sql==="+sql);
			boolean tag=manager.executeUpdate(sql, params);
		log.info("tag==="+tag);
			return tag;
	}
	public void commit()
	{
		try {
			manager.commitChange();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void rollback()  {
		try {
			manager.rollback();
			closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void closeDB()
	{
		try {
			manager.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Map<String, String>> queryByList(String sql, Object[] params)
			throws SQLException{
		return query2List(executeQuery(sql, params));
	}
	public List<Map<String, String>> query2List(ResultSet rs) 
			throws SQLException{
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
		while( rs.next() ) {
			Map<String,String> map = new HashMap<String,String>();
			
			for( int i = 0; i < rsmd.getColumnCount(); i++ ) {
				String v = rs.getString( rsmd.getColumnName( i+1 ) );
				map.put( rsmd.getColumnName( i+1 ).toUpperCase(), v );
			}
			
			list.add( map );
		}
		rs.close();
		return list;
	}
}