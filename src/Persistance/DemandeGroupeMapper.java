package Persistance;
import java.sql.*;
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



	
}
