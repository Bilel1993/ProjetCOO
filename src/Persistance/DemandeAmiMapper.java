package Persistance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			String req = "INSERT INTO DemandeAmis VALUES(?,?,?)";
			PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setString(1, Send.getNomComptePers());
			ps.setString(2, Receive.getNomComptePers());
			ps.setInt(3,0);
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

		//Renvoyer les demandes recu par l'utilisateur passé en parametre 
		public ArrayList <Personne> FindPersSend(Personne p) throws SQLException {
			PersonneMapper PM = new PersonneMapper();
			ArrayList <Personne> Send = new ArrayList<Personne>();
			String req = "SELECT Pers_Demande FROM  DemandeAmis WHERE Pers_Receive= ? "
					+ "AND isAmis=0";
			PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setString(1, p.getNomComptePers());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Send.add(PM.FindByComptePers(rs.getString(1)));
			}
			return Send;
		}		

		//Supprimer une demande si la personne refuse 
		public void delete(Personne Send ,Personne Receive) throws SQLException {
			String req = "DELETE FROM DemandeAmis WHERE Pers_Demande = ? "
					+ "AND Pers_Receive =?";
			PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setString(1, Send.getNomComptePers());
			ps.setString(2, Receive.getNomComptePers());
			ps.executeUpdate();
		}

		
		//mettre a jour le champ isAmis si l'utilisateur accepte la demande 
		public void UpdateAmis(Personne Send,Personne Receive)throws SQLException {
			String req = "UPDATE DemandeAmis SET IsAmis= ? WHERE  Pers_Demande = ? "
					+ "AND Pers_Receive =?";
			PreparedStatement ps = DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setInt(1,1);
			ps.setString(2,Send.getNomComptePers());
			ps.setString(3,Receive.getNomComptePers());
			ps.executeUpdate();
		}

		
		//savoir si les deux utilisateur sont amis ou pas : 0 si Non , 1 si Oui
		public int IsAmis(Personne Send,Personne Receive) throws SQLException{
			String req ="Select isAmis from DemandeAmis where Pers_Demande = ? "
					+ "AND Pers_Receive =?";
			PreparedStatement ps=DBConfig.getInstance().getConn().prepareStatement(req);
			ps.setString(1,Send.getNomComptePers());
			ps.setString(2,Receive.getNomComptePers());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) return rs.getInt(1);
			else return 99; // 99 = la table elle est encore vide rs est empty 
			}
		
}
