package Persistance;
import java.sql.*;
import java.util.ArrayList;

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
		
		
		//Verifier si un gorupe existe
		public  ArrayList <String>GroupePers(Personne p1) throws SQLException{
			ArrayList <String> groupe = new ArrayList<String>();
			String req ="Select NomGroupe from Groupe where NomComptePers= ?";
			PreparedStatement ps=DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setString(1,p1.getNomComptePers());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				groupe.add(rs.getString(1));
			}
			return groupe;
			
		}
		
		
		
		//Retourner l'id du groupe a partir de la personne qui l'a creer et le nom du groupe
		public int IdGroupe(Personne p,String nomGroupe) throws SQLException{
			String req ="Select ID_Groupe from Groupe where NomGroupe= ? AND "
					+ "NomComptePers=?";
			PreparedStatement ps=DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setString(1,nomGroupe);
			ps.setString(2,p.getNomComptePers());
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);	
			
		}
		
		
		//Retourner l'id du groupe a partir du nom 
		public int FindByNomGroupe(String nomGroupe) throws SQLException{
			String req ="Select ID_Groupe from Groupe where NomGroupe= ? ";
			PreparedStatement ps=DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setString(1,nomGroupe);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);	
			
		}
		
		
		//Retourner le nom du groupe  a partir d'un id 
		public String FindById(int id) throws SQLException{
			String req ="Select NomGroupe from Groupe where ID_Groupe= ? ";
			PreparedStatement ps=DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getString(1);	
			
		}
		
		//Retourner le nom du groupe  a partir d'un id 
		public String FindModerateur(String nomGroupe) throws SQLException{
			String req ="Select NomComptePers from Groupe where NomGroupe= ? ";
			PreparedStatement ps=DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setString(1,nomGroupe);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getString(1);	
			
		}
			
	}

