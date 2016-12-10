package Domaine;

public class Utilisateur extends Personne  {

	public Utilisateur(String nomComptePers, String nomPers, String prenomPers,
			String passwordPers) {
		super(nomComptePers, nomPers, prenomPers, passwordPers);
	}

	public Utilisateur() {
		super();
	}

	public Utilisateur(String nomComptePers, String nomPers, String prenomPers,
			char[] passwordPers) {
		super(nomComptePers, nomPers, prenomPers, passwordPers);
	}

	
}