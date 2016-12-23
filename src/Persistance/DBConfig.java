package Persistance;

import java.sql.Connection;
import java.sql.DriverManager;


class DBConfig{
    private static DBConfig instance = null;
    private static Connection conn= null;
   
    //constructeur vide
    public DBConfig(){};
    
    //Get Instance
    public static DBConfig getInstance(){
    	 if (instance == null)
             instance = new DBConfig(); 
         return instance;
     }
    
	//GetConnection
	public Connection getConn(){
		if(conn == null){
			try{
				/*METTRE VOS IDENTIFIANTS ICI */
				String url = "";
				String user= "";
				String passwd="";
				conn = DriverManager.getConnection(url,user,passwd);
				return conn;
			} catch (Exception e){
				System.out.println("Une erreur s'est produite");
				e.printStackTrace();
				System.exit(1);
			}
		}
		return conn;
	}
    
}
