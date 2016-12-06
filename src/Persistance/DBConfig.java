package Persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



class DBConfig {
	

	private static Connection conn=null;;
	
	private static DBConfig inst=null;
	public DBConfig(){};
	 
	/** Point d'accès pour l'instance unique du singleton */
	
	public static DBConfig getInstance()
	{	
		if (inst == null)
			inst = new DBConfig();
			return inst;
	}
	
	public static Connection getConn() throws SQLException{
		if(conn==null) {
			String url = "jdbc:mysql://webtp.fil.univ-lille1.fr/aloui";
			String user= "aloui";
			String passwd="qz6fnwhy";
			conn = DriverManager.getConnection(url,user,passwd);
			return conn;
		}
					
		return conn;
	}
	
	
}
