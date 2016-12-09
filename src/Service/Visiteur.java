package Service;

import java.sql.SQLException;

import Domaine.Personne;


public abstract class Visiteur {
    public void visiter(IDomainObject o) {
        o.accepter(this);
    }
    abstract public void visiter(Personne p) throws SQLException;
}
