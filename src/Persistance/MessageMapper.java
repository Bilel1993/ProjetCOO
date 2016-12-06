package Persistence;


import java.sql.*;

import Domaine.*;
import Persistence.DBConfig;

	public class MessageMapper {
		static PersonneMapper inst;
		
		static public PersonneMapper getInstance() {
			if (inst == null)
				inst = new PersonneMapper();
			return inst;
		}
		
	// Inserer une personne dans la Base de donnees
		public void insert(Message m) throws SQLException {
			String req = "INSERT INTO Message VALUES(?,?,?)";
			PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setInt(1, m.getIdMessage());
			ps.setInt(2, m.getIdPersonne());
			ps.setString(3,  m.getMessageReçu());
			ps.executeUpdate();
		}
		
	
		
		
}
