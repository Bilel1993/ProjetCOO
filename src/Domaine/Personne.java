package Domaine;
import java.sql.SQLException;
import java.util.*;

import Service.IDomainObject;
import Service.Observateur;
import Service.Visiteur;




public abstract class Personne implements IDomainObject{
	 private List<Observateur> obs;
	String NomComptePers;
	String NomPers;
	String PrenomPers;
	String PasswordPers;
	ArrayList<CentreInteret> ListeCentreInteretPers = new ArrayList<CentreInteret>();
	ArrayList<Personne> ListeAmisPers = new ArrayList<Personne>();
	Boolean isModerateur= false;
	
	 public void accepter(Visiteur v) {
	        try {
				v.visiter(this);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	
	public Personne(String nomComptePers, String nomPers, String prenomPers,
			String passwordPers) {
		NomComptePers = nomComptePers;
		NomPers = nomPers;
		PrenomPers = prenomPers;
		PasswordPers = passwordPers;
	}

	public String getNomComptePers() {
		return NomComptePers;
	}

	public void setNomComptePers(String nomComptePers) {
		NomComptePers = nomComptePers;
	}

	public String getNomPers() {
		return NomPers;
	}

	public void setNomPers(String nomPers) {
		NomPers = nomPers;
	}

	public String getPrenomPers() {
		return PrenomPers;
	}

	public void setPrenomPers(String prenomPers) {
		PrenomPers = prenomPers;
	}

	public String getPasswordPers() {
		return PasswordPers;
	}

	public void setPasswordPers(char[] cs) {
		String mdp =new String(cs);
		PasswordPers = mdp;
	}

	public ArrayList<CentreInteret> getListeCentreInteretPers() {
		return ListeCentreInteretPers;
	}

	public void setListeCentreInteretPers(
			ArrayList<CentreInteret> listeCentreInteretPers) {
		ListeCentreInteretPers = listeCentreInteretPers;
	}

	public ArrayList<Personne> getListeAmisPers() {
		return ListeAmisPers;
	}

	public void setListeAmisPers(ArrayList<Personne> listeAmisPers) {
		ListeAmisPers = listeAmisPers;
	}

	@Override
	public String toString() {
		return "Personne [NomComptePers=" + NomComptePers + ", NomPers="
				+ NomPers + ", PrenomPers=" + PrenomPers
				+ ", ListeCentreInteretPers=" + ListeCentreInteretPers
				+ ", getNomComptePers()=" + getNomComptePers()
				+ ", getNomPers()=" + getNomPers() + ", getPrenomPers()="
				+ getPrenomPers() + ", getPasswordPers()=" + getPasswordPers()
				+ ", getListeCentreInteretPers()="
				+ getListeCentreInteretPers() + ", getListeAmisPers()="
				+ getListeAmisPers() + "]";
	}
	
	

	public void add(Observateur o) {
	        obs.add(o);
	    }

	    /**
	     * On notifie l'observateur que l'on à fait un changement
	     */
	    public void notifier() {
	        for (Observateur o : obs)
	            o.action(this);
	    }
	
}
