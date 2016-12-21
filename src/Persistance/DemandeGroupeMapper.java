package Persistance;
import java.sql.*;
import java.util.ArrayList;

import Domaine.*;


	public class DemandeGroupeMapper{
		static DemandeGroupeMapper inst;
		
		static public DemandeGroupeMapper getInstance() {
			if (inst == null)
				inst = new DemandeGroupeMapper();
			return inst;
		}

	// Inserer une personne dans la Base de donnees
		public void insert(int id ,Personne p) throws SQLException {
			String req1 = "INSERT INTO DemandeGroupe VALUES(?,?,?)";
			PreparedStatement ps1 = DBConfig.getInstance().getConn().prepareStatement(req1);
			ps1.setInt(1,id);
			ps1.setString(2,p.getNomComptePers());
			ps1.setInt(3,0);
			ps1.executeUpdate();
		}
		
		//Verifier si une demande n'existe pas : 0 si Non , 1 si Oui
		public int IsDemande(int id ,Personne p1) throws SQLException{
			String req ="Select count(*) from DemandeGroupe where ID_Groupe= ? "
					+ "AND Pers_Receive =?";
			PreparedStatement ps=DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setInt(1,id);
			ps.setString(2,p1.getNomComptePers());
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);	
			}

		//Renvoyer les demandes recu par l'utilisateur passé en parametre 
		public ArrayList <String> FindDemandeGroupe(Personne p) throws SQLException {
			GroupeMapper GP = new GroupeMapper();
			ArrayList <String> groupe= new ArrayList<String>();
			String req = "SELECT ID_Groupe FROM  DemandeGroupe WHERE Pers_Receive= ? "
					+ "AND Decision=0";
			PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setString(1, p.getNomComptePers());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String g = GP.FindById(rs.getInt(1));
				groupe.add(g);
			}
			return groupe;
		}		

		//Supprimer une demande si la personne refuse 
		public void delete(int id,Personne Receive) throws SQLException {
			String req = "DELETE FROM DemandeGroupe WHERE ID_Groupe = ? "
					+ "AND Pers_Receive =?";
			PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setInt(1,id);
			ps.setString(2, Receive.getNomComptePers());
			ps.executeUpdate();
		}
		
		
		//mettre a jour le champ Decision si l'utilisateur accepte la demande 
		public void UpdateDecison(int id,Personne Receive)throws SQLException {
			String req = "UPDATE DemandeGroupe SET Decision= ? WHERE  ID_Groupe= ? "
					+ "AND Pers_Receive =?";
			PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setInt(1,1);
			ps.setInt(2,id);
			ps.setString(3,Receive.getNomComptePers());
			ps.executeUpdate();
		}

		//Renvoyer les groupes de l'utilisateur passé en parametre 
		public ArrayList <String> FindGroupe(Personne p) throws SQLException {
			GroupeMapper GP = new GroupeMapper();
			ArrayList <String> groupe= new ArrayList<String>();
			String req = "SELECT ID_Groupe FROM  DemandeGroupe WHERE Pers_Receive= ? "
					+ "AND Decision=1";
			PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setString(1, p.getNomComptePers());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String g = GP.FindById(rs.getInt(1));
				groupe.add(g);
			}
			return groupe;
		}	
}
