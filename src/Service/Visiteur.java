package Service;

import java.sql.SQLException;

import Domaine.Groupe;
import Domaine.Personne;


public abstract class Visiteur {
    public void visiter(IDomainObject o) {
        o.accepter(this);
    }
    abstract public void visiter(Personne p) throws SQLException;
    abstract public void visiter(Groupe g) throws SQLException;
}
