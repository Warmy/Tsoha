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
    
    // lisää uuden arvostelun tietokantaan, mergen avulla päivitetään drinkin
    // tila tietokannassa, koska drinkille lisättiin uusi arvostelu
    public void lisaaArvostelu(Arvostelu uusi) {
        em = getEntityManager();
        em.getTransaction().begin();
        em.merge(uusi);
        em.getTransaction().commit();
    }
    
    
    // palauttaa listan kaikista käyttäjistä
    public List<Kayttaja> getKayttajat() {
        em = getEntityManager();
        return em.createQuery("SELECT u FROM Kayttaja u").getResultList();
    }
    
    // etsii tietokannasta käyttäjän tämän tunnuksen perusteella, joka on pääavain
    public Kayttaja haeKayttaja(String tunnus) {
        em = getEntityManager();
        return em.find(Kayttaja.class, tunnus); // etsii kayttaja-luokan ilmentymän tämän tunnuksella
    }
    
    // etsii tietokannasta drinkin tämän id:n perusteella, joka on pääavain
    public Drinkkiresepti haeDrinkki(long juomaId) {
        em = getEntityManager();
        return em.find(Drinkkiresepti.class, juomaId);
    }
    
    
    // lisää uuden drinkin tietokantaan
    public void lisaaDrinkki(Drinkkiresepti uusi) {
        em = getEntityManager();
        em.getTransaction().begin();
        em.merge(uusi);
        em.getTransaction().commit();     
    }

    // palauttaa listan kaikista juomista
    public List<Drinkkiresepti> getJuomat() {
        em = getEntityManager();
        return em.createQuery("SELECT d FROM Drinkkiresepti d ORDER BY d.nimi asc").getResultList();
    }
    
    // järjestetty lista juomista
    public List<Drinkkiresepti> getOrderedJuomat() {
        em = getEntityManager();
        return em.createQuery("SELECT d FROM Drinkkiresepti d ORDER BY d.nimi desc").getResultList();
    }
    
    // lisää uuden juomalajin tietokantaan
    public void lisaaJuomalaji(Juomalaji uusi) {
        em = getEntityManager();
        em.getTransaction().begin();
        em.persist(uusi);
        em.getTransaction().commit();   
    }
    
    // lista drinkeistä, järjestetty nousevasti juomalajin perusteella
    public List<Drinkkiresepti> sortJuomatByLajitAsc() {
        em = getEntityManager();
        return em.createQuery("SELECT d FROM Drinkkiresepti d ORDER BY d.laji.nimi asc").getResultList(); 
    }
    
    // lista drinkeistä, järjestetty laskevasti juomalajin perusteella
    public List<Drinkkiresepti> sortJuomatByLajitDesc() {
        em = getEntityManager();
        return em.createQuery("SELECT d FROM Drinkkiresepti d ORDER BY d.laji.nimi desc").getResultList();
    }
    
    public List<Juomalaji> getJuomaLajit() {
        em = getEntityManager();
        return em.createQuery("SELECT j FROM Juomalaji j").getResultList();
    }
    
    // etsii pääavaimen perusteella haettavan juomalajin
    public Juomalaji haeJuomalaji(long lajinId) {
        EntityManager em = getEntityManager();
        return em.find(Juomalaji.class, lajinId);
    }
    
}
