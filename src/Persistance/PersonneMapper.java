package Persistance;

import java.sql.*;

import Persistance.DBConfig;

import Domaine.*;

public class PersonneMapper {

	static PersonneMapper inst;

	static public PersonneMapper getInstance() {
		if (inst == null)
			inst = new PersonneMapper();
		return inst;
	}

	//Verifier qu un pseudo n est pas utiliser : 0 si Non , 1 si Oui
	public int IsExistNomComptePers(String NomComptePers) throws SQLException{
		String req ="Select count(*) from Personne where NomComptePers = ?";
		PreparedStatement ps=DBConfig.getInstance().getConn().prepareStatement(req);
		ps.setString(1,NomComptePers);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt(1);	


	}


	// Inserer une personne dans la Base de donnees
	public void insert(Personne p) throws SQLException {
		String req = "INSERT INTO Personne VALUES(?,?,?,?,?,?)";
		PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
		ps.setString(1, p.getNomComptePers());
		ps.setString(2, p.getNomPers());
		ps.setString(3,p.getPrenomPers());
		ps.setString(4,p.getPasswordPers());
		ps.setBoolean(5,false);
		ps.setBoolean(6,false);
		ps.executeUpdate();
	}

	// Supprimer une personne de la BDD
	public void delete(Personne p) throws SQLException {
		String req = "DELETE FROM Personne WHERE NomComptePers = ?";
		PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
		ps.setString(1, p.getNomComptePers());
		ps.executeUpdate();
	}

	// Verifie si une personne se connecte avec des identifiants présent en BDD
	public int Exists(String NomCompte , char[] cs) throws SQLException {
		String req = "SELECT count(*) FROM Personne WHERE NomComptePers =? and PasswordPers=?";
		DBConfig.getInstance();
		String mdp = new String(cs);
		PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
		ps.setString(1,NomCompte);
		ps.setString(2,mdp);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt(1);	
	}

	//Verifie si l'administrateur est admin ou non ; Renvoie 0 si non , 1 si oui
	public int IsAdmin(String NomCompte , char[] cs) throws SQLException {
		String req = "SELECT isAdmin FROM Personne WHERE NomComptePers =? and PasswordPers=?";
		DBConfig.getInstance();
		String mdp = new String(cs);
		PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
		ps.setString(1,NomCompte);
		ps.setString(2,mdp);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt(1);	
	}



	//Retourner une personne a partir de son compte Personnel 
	public Personne FindByComptePers(String ComptePers) throws SQLException {
		String req = "SELECT NomPers,PrenomPers,PasswordPers FROM Personne WHERE "
				+ "NomComptePers=?";
		PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
		ps.setString(1,ComptePers);
		ResultSet rs = ps.executeQuery();
		rs.next();
		Personne p =new Utilisateur(ComptePers,rs.getString(1),rs.getString(2),rs.getString(3));
		return p;
	}


	//modifier le nom , Prenom et mot de passe d'une personne 
	public void UpdatePersonne(Personne p)  throws SQLException {
		String req = "UPDATE Personne SET NomPers= ? , PrenomPers =? ,PasswordPers =? "
				+ "WHERE NomComptePers=?";
		PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
		ps.setString(1,p.getNomPers());
		ps.setString(2,p.getPrenomPers());
		ps.setString(3,p.getPasswordPers());
		ps.setString(4,p.getNomComptePers());
		ps.executeUpdate();
	}

	//modifier le nom , Prenom et mot de passe d'une personne 
	public void UpdateAdmin(Personne p, String pseudo)  throws SQLException {
		String req = "UPDATE Personne SET NomComptePers=?, NomPers= ? , PrenomPers =? ,PasswordPers =? "
				+ "WHERE NomComptePers=?";
		PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
		ps.setString(1,p.getNomComptePers());
		ps.setString(2,p.getNomPers());
		ps.setString(3,p.getPrenomPers());
		ps.setString(4,p.getPasswordPers());
		ps.setString(5,pseudo);
		ps.executeUpdate();
	}

	//mettre le champ moderateur a 1 
	public void isModerateur(Personne p)  throws SQLException {
		String req = "UPDATE Personne SET isModerateur= ? WHERE NomComptePers=?";
		PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
		ps.setInt(1,1);
		ps.setString(2,p.getNomComptePers());
		ps.executeUpdate();
	}


}


