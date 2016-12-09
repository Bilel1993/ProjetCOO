package Domaine;

import java.util.ArrayList;
import java.util.Iterator;


public class Groupe{
	String NomGroupe;
	Personne ModerateurGroupe;
	ArrayList<Personne> ListeGroupePers = new ArrayList<Personne>();
	
	public Groupe(String nomGroupe, Personne moderateurGroupe) {
			NomGroupe = nomGroupe;
			ModerateurGroupe = moderateurGroupe;
		ListeGroupePers.add(ModerateurGroupe);
	}
	

	public void AffecterPersGroupe(Personne p1 ){
		Iterator<Personne> it = ListeGroupePers.iterator() ;
		 while (it.hasNext()) {
			 Personne p = it.next() ; 
			 if(p.NomComptePers==p1.NomComptePers) return;
		}
		 ListeGroupePers.add(p1);
	}
	
}
