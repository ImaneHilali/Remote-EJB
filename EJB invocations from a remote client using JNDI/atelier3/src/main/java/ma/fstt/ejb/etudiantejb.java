package ma.fstt.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.fstt.model.Etudiant;
import java.util.List;

@Stateless
public class etudiantejb implements remoteinterface {
    @PersistenceContext(unitName="Etudiant")
    private EntityManager entityManager;

    @Override
    public Etudiant save(Etudiant etudiant) {
        try {
            entityManager.persist(etudiant);
            return etudiant;
        } catch (Exception e) {
            throw new EJBException("Error saving Etudiant", e);
        }
    }

    @Override
    public Etudiant findById(Long id) {
        try {
            return entityManager.find(Etudiant.class, id);
        } catch (Exception e) {
            throw new EJBException("Error finding Etudiant by id", e);
        }
    }

    @Override
    public List<Etudiant> findAll() {
        try {
            return entityManager.createQuery("SELECT e FROM Etudiant e", Etudiant.class).getResultList();
        } catch (Exception e) {
            throw new EJBException("Error finding all Etudiants", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            entityManager.remove(entityManager.find(Etudiant.class, id));
        } catch (Exception e) {
            throw new EJBException("Error deleting Etudiant by id", e);
        }
    }
}