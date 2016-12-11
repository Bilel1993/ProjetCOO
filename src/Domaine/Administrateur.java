package Domaine;


public class Administrateur extends Personne {

	public Administrateur(String nomComptePers, String nomPers,
			String prenomPers, String passwordPers) {
		super(nomComptePers, nomPers, prenomPers, passwordPers );		
	}
		
	
	public Administrateur(Personne pConnecte) {
		this.PrenomPers=pConnecte.PrenomPers;
		this.NomComptePers = pConnecte.NomComptePers;
		this.NomPers = pConnecte.NomPers;
		this.PasswordPers = pConnecte.PasswordPers;
		this.ListeAmisPers = pConnecte.ListeAmisPers;
		this.ListeCentreInteretPers = pConnecte.ListeCentreInteretPers;
		
	}


	public Personne create(String nomComptePers, String nomPers,
			String prenomPers, String passwordPers){
		Personne p =new Utilisateur (nomComptePers,nomPers,
			prenomPers,passwordPers);
		return p;
	}
	
	

}
