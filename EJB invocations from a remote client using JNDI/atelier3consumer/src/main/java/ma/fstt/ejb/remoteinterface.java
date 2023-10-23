package ma.fstt.ejb;

import jakarta.ejb.Remote;
import ma.fstt.model.Etudiant;

import java.util.List;

@Remote
public interface remoteinterface {
    Etudiant save(Etudiant etudiant);

    Etudiant findById(Long id);

    List<Etudiant> findAll();

    void deleteById(Long id);

}
