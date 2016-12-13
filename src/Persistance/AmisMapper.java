package Persistance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Domaine.Personne;

public class AmisMapper{
		static AmisMapper inst;
		
		static public AmisMapper getInstance() {
			if (inst == null)
				inst = new AmisMapper();
			return inst;
		}

		// Inserer Demande dans la Base de donnees
		public void insert(Personne Send,Personne Receive) throws SQLException {
			String req = "INSERT INTO Amis VALUES(?,?,?,?)";
			PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setString(1, Send.getNomComptePers());
			ps.setString(2, Receive.getNomComptePers());
			ps.setString(3,null);
			ps.setString(4,null);
			ps.executeUpdate();
		}
		
		//Renvoyer les amis de l'utilisateur passé en parametre 
		public ArrayList <Personne> FindPersFriend(Personne p) throws SQLException {
			PersonneMapper PM = new PersonneMapper();
			ArrayList <Personne> Friend = new ArrayList<Personne>();
			String req = "SELECT Pers_Send,Pers_Receive FROM  Amis "
					+ "WHERE Pers_Send = ? OR Pers_Receive= ? ";
			PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setString(1, p.getNomComptePers());
			ps.setString(2, p.getNomComptePers());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				//si le premier champ = au nom de l'utilisateur alors on l'insere pas 
				if (rs.getString(1).equals(p.getNomComptePers()))
					Friend.add(PM.FindByComptePers(rs.getString(2)));
				//si le 2e champ = au nom de l'utilisateur alors on l'insere pas 
				if (rs.getString(2).equals(p.getNomComptePers()))
					Friend.add(PM.FindByComptePers(rs.getString(1)));
				
			}
			return Friend;
			
		}		
		
		
		//Renvoyer le dernier message 
		public String FindMessage(Personne p,Personne p1) throws SQLException {
			String req = "SELECT Message,origine_Message FROM  Amis "
					+ "WHERE ((Pers_Send = ? AND Pers_Receive= ?) "
					+ "OR (Pers_Receive= ?  AND Pers_Send = ?  ))";
			PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setString(1, p.getNomComptePers());
			ps.setString(2, p1.getNomComptePers());
			ps.setString(3, p.getNomComptePers());
			ps.setString(4, p1.getNomComptePers());
			ResultSet rs = ps.executeQuery();
			rs.next();
			String message = "aucun message envoyé";
			if (rs.wasNull()) return message;
			else return rs.getString(2)+" : "+rs.getString(1);

		}
		
		
		//mettre a jour le champ message
		public void UpdateMessage(Personne p,Personne p1,String message)  throws SQLException {
			String req = "UPDATE Amis SET Message= ? ,origine_Message = ?"
					+ "WHERE ((Pers_Send = ? AND Pers_Receive= ?) "
					+ "OR (Pers_Receive= ?  AND Pers_Send = ?  ))";
			PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setString(1, message);
			ps.setString(2, p.getNomComptePers());
			ps.setString(3, p.getNomComptePers());
			ps.setString(4, p1.getNomComptePers());
			ps.setString(5, p.getNomComptePers());
			ps.setString(6, p1.getNomComptePers());
			ps.executeUpdate();
		}


}