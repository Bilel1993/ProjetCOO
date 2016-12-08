package Persistance;

import java.util.HashSet;
import java.util.Set;

import Service.IDomainObject;
import Service.Observateur;
import Service.Visiteur;

// But: memoriser la liste des objets ayant besoin d'un update en BD


public class UnitOfWork implements Observateur {
    // Set c'est comme une liste, sauf que si l'objet existe déjà dedans, ça n'ajoute pas !
    Set<IDomainObject> dirty;
    static UnitOfWork inst = null;
    public UnitOfWork() {
        dirty = new HashSet<IDomainObject>();
    }
    public static UnitOfWork getInstance() {
        if (inst == null)
            inst = new UnitOfWork();
        return inst;
    }

    //But: enregister l'objet dans la liste des objets modifies
    
    public void action(IDomainObject o) {
        System.out.println("UOW.action(): Enregistrer l'objet dans la liste des objets modifiés");
        dirty.add(o);
    }
    public void commit() {
        Visiteur v = new Committer();
        for (IDomainObject o : dirty) {
            v.visiter(o);
        }
        dirty.clear();
        System.out.println("UOW.commit(): On a effectué le commit & vidé la liste des objets modifiés");
    }
}
