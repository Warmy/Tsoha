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
    
    private EntityManager em; // tallennetaan, poistetaan ja haetaan entiteettejä
    
    private EntityManagerFactory emf = null;

    public Rekisteri() {
        // käytetään "DrinkkiarkistoPU"-konfiguraatiota
        emf = Persistence.createEntityManagerFactory("DrinkkiarkistoPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    // lisää uuden käyttäjän tietokantaan
    public void lisaaKayttaja(Kayttaja uusi) {
        em = getEntityManager();
        em.getTransaction().begin();
        em.persist(uusi);
        em.getTransaction().commit();     
    }
    
    
    // palauttaa listan kaikista käyttäjistä
    public List<Kayttaja> getKayttajat() {
        em = getEntityManager();
        return em.createQuery("SELECT u FROM Kayttaja u").getResultList();
    }
    
    // etsii tietokannasta käyttäjän tämän tunnuksen perusteella
    public Kayttaja haeKayttaja(String tunnus) {
        em = getEntityManager();
        return em.find(Kayttaja.class, tunnus); // etsii kayttaja-luokan ilmentymän tämän tunnuksella
    }
    
    
    // lisää uuden drinkin tietokantaan
    public void lisaaDrinkki(Drinkkiresepti uusi) {
        em = getEntityManager();
        em.getTransaction().begin();
        em.persist(uusi);
        em.getTransaction().commit();     
    }

    // palauttaa listan kaikista juomista
    public List<Drinkkiresepti> getJuomat() {
        em = getEntityManager();
        return em.createQuery("SELECT d FROM Drinkkiresepti d").getResultList();
    }
    
}
