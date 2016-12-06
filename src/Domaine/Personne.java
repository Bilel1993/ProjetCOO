package Domaine;
import java.util.*;




public abstract class Personne {
	String NomComptePers;
	String NomPers;
	String PrenomPers;
	String PasswordPers;
	ArrayList<CentreInteret> ListeCentreInteretPers = new ArrayList<CentreInteret>();
	ArrayList<Personne> ListeAmisPers = new ArrayList<Personne>();
	Boolean isModerateur= false;
	
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

	public void setPasswordPers(String passwordPers) {
		PasswordPers = passwordPers;
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
	
	
	
	
	
	
	

}
