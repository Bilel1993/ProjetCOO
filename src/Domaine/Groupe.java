package Domaine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Service.IDomainObject;
import Service.Observateur;
import Service.Visiteur;


public class Groupe implements IDomainObject{
	 private List<Observateur> obs;
	String NomGroupe;
	Personne ModerateurGroupe;
	ArrayList<Personne> ListeGroupePers = new ArrayList<Personne>();
	 public void accepter(Visiteur v) {
	        try {
				v.visiter(this);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	
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


	public String getNomGroupe() {
		return NomGroupe;
	}


	public void setNomGroupe(String nomGroupe) {
		NomGroupe = nomGroupe;
	}


	public Personne getModerateurGroupe() {
		return ModerateurGroupe;
	}


	public void setModerateurGroupe(Personne moderateurGroupe) {
		ModerateurGroupe = moderateurGroupe;
	}


	public ArrayList<Personne> getListeGroupePers() {
		return ListeGroupePers;
	}


	public void setListeGroupePers(ArrayList<Personne> listeGroupePers) {
		ListeGroupePers = listeGroupePers;
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
