package Domaine;


public class Administrateur extends Personne {

	public Administrateur(String nomComptePers, String nomPers,
			String prenomPers, String passwordPers) {
		super(nomComptePers, nomPers, prenomPers, passwordPers );		
	}
		
	
	public Personne create(String nomComptePers, String nomPers,
			String prenomPers, String passwordPers){
		Personne p =new Utilisateur (nomComptePers,nomPers,
			prenomPers,passwordPers);
		return p;
	}
	
	

}
