package Persistance;
import java.sql.*;
import Domaine.*;

	public class GroupeMapper{
		static GroupeMapper inst;
		
		static public GroupeMapper getInstance() {
			if (inst == null)
				inst = new GroupeMapper();
			return inst;
		}
		
	// Inserer une personne dans la Base de donnees
		public void insert(Groupe g) throws SQLException {
			//chercher combien y'a de lignes dans la table Groupe pour construire l'id 
			String req ="Select count(*) from Groupe";
			PreparedStatement ps=DBConfig.getInstance().getConn().prepareStatement(req);
			ResultSet rs = ps.executeQuery();
			rs.next();
			// incrementer l'id du groupe 
			int id =rs.getInt(1) + 1;	
			
			
			String req1 = "INSERT INTO Groupe VALUES(?,?,?)";
			PreparedStatement ps1 = DBConfig.getInstance().getConn().prepareStatement(req1);
			ps1.setInt(1,id);
			ps1.setString(2,g.getNomGroupe());
			ps1.setString(3,g.getModerateurGroupe().getNomComptePers());
			ps1.executeUpdate();
		}
			
		//Verifier si un gorupe existe
		public int IsExistNomComptePers(String nomGroupe) throws SQLException{
			String req ="Select count(*) from Groupe where NomGroupe= ?";
			PreparedStatement ps=DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setString(1,nomGroupe);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);	
			
		}
		
		
		
		
		
		
		
		
			
	}

