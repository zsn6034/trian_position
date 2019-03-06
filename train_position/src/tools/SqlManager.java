package tools;
/*
 * 数据库操作类，进行数据库底层操作
 */
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.PropertyResourceBundle;
public class SqlManager
{
	private static SqlManager manager = null; // 静态成员变量，支持单态模式
	private PropertyResourceBundle bundle; // 配置资源文件
	private static String jdbcDrive = null; // JDBC驱动类型
	private String DBhost = ""; // 数据库主机地址
	private String DBname = ""; // 数据库名
	private String DBprot = ""; // 数据库端口
	private String DBuser = ""; // 数据库用户名
	private String DBpasswd = ""; // 数据库密码
	private String strcon = null; // 连接字符

	private Connection conn = null; // 连接对象
	private PreparedStatement pstm = null;
	private CallableStatement cstm = null;

	/**
	 * 私有构造函数,不可实例化
	 * @throws IOException
	 */
	private SqlManager() throws IOException
	{
		// 读取配置文件
		bundle = new PropertyResourceBundle(SqlManager.class
				.getResourceAsStream("Config.properties"));
		this.DBhost = getString("DBhost"); // 读取主机地址
		this.DBname = getString("DBname"); // 读取用户名
		this.DBprot = getString("DBport"); // 读取端口
		this.DBuser = getString("DBuser"); // 读取用户
		this.DBpasswd = getString("DBpassword"); // 读取密码
		// 设置mysql数据库的驱动程序和连接字
		jdbcDrive = "com.mysql.jdbc.Driver";
		strcon = "jdbc:mysql://" + DBhost + ":" + DBprot + "/" + DBname;
	}
	/**
	 * 读取配置文件中的参数
	 * @param key
	 * @return key对应的参数
	 */
	private String getString(String key)
	{
		return this.bundle.getString(key);
	}

	/**
	 * 单例模式获取实例
	 * @return SqlManager对象
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public static SqlManager createInstance() throws IOException, ClassNotFoundException
	{
		if (manager == null)
		{
			manager = new SqlManager();
			manager.initDB();
		}
		return manager;
	}
	/**
	 * 初始化连接参数，由指定的DBType生成
	 * @throws ClassNotFoundException
	 */
	public void initDB() throws ClassNotFoundException
	{
		Class.forName(jdbcDrive);
	}
	/**
	 * 连接数据库
	 * @throws SQLException
	 */
	public void connectDB() throws SQLException
	{
		conn = DriverManager.getConnection(strcon, DBuser, DBpasswd); // 获取连接
		conn.setAutoCommit(false); // 设置自动提交为false
	}
	/**
	 * 断开数据库
	 * @throws SQLException
	 */
	public void closeDB() throws SQLException
	{
		if (pstm != null)
		{
			pstm.close();
		}
		if (cstm != null)
		{
			cstm.close();
		}
		if (conn != null)
		{
			conn.close();
		}
	}
	/**
	 * 设置PrepareStatement对象中Sql语句中的参数
	 * @param sql
	 * @param params
	 * @throws SQLException
	 */
	private void setPrepareStatementParams(String sql, Object[] params)
			throws SQLException
	{
		pstm = conn.prepareStatement(sql); // 获取对象
		if (params != null)
		{
			for (int i = 0; i < params.length; i++) // 遍历参数列表填充参数
			{
				pstm.setObject(i + 1, params[i]);
			}
		}
	}
	/**
	 * 执行查询
	 * @param sql
	 * @param params
	 * @return 返回ResultSet类型的查询结果
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String sql, Object[] params)
			throws SQLException
	{
		ResultSet rs = null;
		manager.setPrepareStatementParams(sql, params); // 填充参数
		rs = pstm.executeQuery(); // 执行查询操作
		return rs;
	}

	/**
	 * 更新数据库操作
	 * @param sql
	 * @param params
	 * @return 执行操作的结果
	 * @throws SQLException
	 */
	public boolean executeUpdate(String sql, Object[] params)
			throws SQLException // 执行无返回数据的数据查询，返回的是被改变的数据库项
	{
		boolean result = false;
		manager.setPrepareStatementParams(sql, params); // 填充参数
		pstm.executeUpdate(); // 执行更新
		manager.commitChange();
		result = true;
		return result;
	}
	/**
	 * 提交信息到数据库
	 * @throws SQLException
	 */
	public void commitChange() throws SQLException
	{
		conn.commit();
	}
	/**
	 * 数据库事务回滚
	 * @throws SQLException
	 */
	public void rollback() throws SQLException
	{
		System.out.println("00");
		conn.rollback();
		System.out.println("11");
	}
	/**
	 * 事务处理的uptate方法，不包含commit
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean transUpdate(String sql, Object[] params) 
			throws SQLException 
	{
		boolean result = false;
		manager.setPrepareStatementParams(sql, params); // 填充参数
		pstm.executeUpdate(); // 执行更新
		result = true;
		return result;
	}
}