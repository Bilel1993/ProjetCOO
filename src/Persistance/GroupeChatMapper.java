package Persistance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Domaine.Personne;

public class GroupeChatMapper {
	static GroupeChatMapper inst;
	
	static public GroupeChatMapper getInstance() {
		if (inst == null)
			inst = new GroupeChatMapper();
		return inst;
	}

	// Inserer Demande dans la Base de donnees
	public void insert(String nomGroupe,Personne Receive) throws SQLException {
		String req = "INSERT INTO GroupeChat VALUES(?,?,?,?)";
		PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
		ps.setString(1,nomGroupe);
		ps.setString(2, Receive.getNomComptePers());
		ps.setString(3,null);
		ps.setString(4,null);
		ps.executeUpdate();
		
		// si la conversation a deja commencé 
		String reqSelect = "SELECT Message,origine_Message FROM  GroupeChat "
				+ "WHERE NomGroupe = ?"; 
		PreparedStatement psSelect = DBConfig.getInstance().getConn().prepareStatement(reqSelect);
		psSelect.setString(1,nomGroupe);
		ResultSet rs = psSelect.executeQuery();
		rs.next();
		// on récupere le dernier message envoyé 
		if (!rs.wasNull()) {
			String reqUpdate = "UPDATE GroupeChat SET Message= ? ,origine_Message = ?"
					+ "WHERE NomGroupe = ?  AND Pers_Receive = ? ";
			PreparedStatement psUpdate = DBConfig.getInstance().getConn().prepareStatement(reqUpdate);
			psUpdate.setString(1,rs.getString(1));
			psUpdate.setString(2,rs.getString(2));
			psUpdate.setString(3,nomGroupe);
			psUpdate.setString(4,Receive.getNomComptePers());
			psUpdate.executeUpdate();
		}
		
	}
	
	//Renvoyer le dernier message 
	public String FindMessage(String nomGroupe,Personne p) throws SQLException {
		String req = "SELECT Message,origine_Message FROM  GroupeChat "
				+ "WHERE NomGroupe = ? AND Pers_Receive = ? "; 
		PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
		ps.setString(1,nomGroupe);
		ps.setString(2, p.getNomComptePers());
		ResultSet rs = ps.executeQuery();
		rs.next();
		String message = "aucun message envoyé";
		if (rs.wasNull()) return message;
		else return rs.getString(2)+" : "+rs.getString(1);

	}
	
	
	//mettre a jour le champ message
	public void UpdateMessage(String nomGroupe,Personne p,String message)  throws SQLException {
		String req = "UPDATE GroupeChat SET Message= ? ,origine_Message = ?"
				+ "WHERE NomGroupe = ? ";
		PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
		ps.setString(1, message);
		ps.setString(2, p.getNomComptePers());
		ps.setString(3,nomGroupe);
		ps.executeUpdate();
	}
	
	//Renvoyer groupe de l'utilisateur passé en parametre 
	public ArrayList <String> FindGroupe(Personne p) throws SQLException {
		GroupeMapper GP = new GroupeMapper();
		ArrayList <String> groupe= new ArrayList<String>();
		String req = "SELECT NomGroupe FROM  GroupeChat WHERE Pers_Receive= ? " ;
		PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
		ps.setString(1, p.getNomComptePers());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			groupe.add(rs.getString(1));
		}
		return groupe;
	}		

	//quitter un groupe 
	public void Quit(String nomGroupe,Personne p) throws SQLException{
		String req  = "DELETE FROM GroupeChat WHERE NomGroupe = ? AND Pers_Receive = ? ";  
		PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
		ps.setString(1,nomGroupe);
		ps.setString(2,p.getNomComptePers());
		ps.executeUpdate();
		System.out.println("Supprimé !");
	}
	
	
}
