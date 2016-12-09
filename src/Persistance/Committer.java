package Persistance;

import java.sql.SQLException;
import Domaine.*;
import Service.Visiteur;

/*C'est le visiteur qui va se charger de realiser les updates
 (en appelant le bon DataMapper en fonction de la classe) */

public  class Committer extends Visiteur {
		    public void visiter(Groupe g) throws SQLException {
		    	GroupeMapper.getInstance().insert(g);  
		    }	
		    public void visiter (Personne p) throws SQLException{
		    	   PersonneMapper.getInstance().UpdatePersonne(p);
		    }
}



