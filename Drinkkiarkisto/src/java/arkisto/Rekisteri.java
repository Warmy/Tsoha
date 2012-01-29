/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkisto;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author Keni
 */
public class Rekisteri {
    
    private EntityManagerFactory emf = null;
    
    public Rekisteri() {
        // käytetään "DrinkkiarkistoPU"-konfiguraatiota
        emf = Persistence.createEntityManagerFactory("DrinkkiarkistoPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    // lisää käyttäjän tietokantaan
    public void lisaaKayttaja(Kayttaja uusi) {
        EntityManager em = getEntityManager();
        
        em.getTransaction().begin();
        em.persist(uusi);
        em.getTransaction().commit();
    }
    
    // palauttaa listan käyttäjistä
    public List<Kayttaja> getKayttajat() {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT k FROM Kayttaja k").getResultList();
    }
    
    // etsii käyttäjän kyseisellä nimellä
    public Kayttaja haeTunnus(long tunnus) {
        EntityManager em = getEntityManager();
        return em.find(Kayttaja.class, tunnus);
    }
    
}
