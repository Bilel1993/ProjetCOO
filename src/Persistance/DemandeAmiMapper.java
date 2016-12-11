package Persistance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Domaine.Personne;

public class DemandeAmiMapper{
		static DemandeAmiMapper inst;
		
		static public DemandeAmiMapper getInstance() {
			if (inst == null)
				inst = new DemandeAmiMapper();
			return inst;
		}

		// Inserer Demande dans la Base de donnees
		public void insert(Personne Send,Personne Receive) throws SQLException {
			String req = "INSERT INTO DemandeAmis VALUES(?,?)";
			PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setString(1, Send.getNomComptePers());
			ps.setString(2, Receive.getNomComptePers());
			ps.executeUpdate();
		}
		
		
		//Verifier si une demande n'existe pas : 0 si Non , 1 si Oui
		public int IsDemande(Personne p,Personne p1) throws SQLException{
			String req ="Select count(*) from DemandeAmis where Pers_Demande = ? "
					+ "AND Pers_Receive =?";
			PreparedStatement ps=DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setString(1,p.getNomComptePers());
			ps.setString(2,p1.getNomComptePers());
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);	
			}




}
