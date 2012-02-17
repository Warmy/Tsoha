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
    
    // lisää uuden drinkin tietokantaan
    public void lisaaDrinkki(Drinkkiresepti uusi) {
        em = getEntityManager();
        em.getTransaction().begin();
        em.merge(uusi);
        em.getTransaction().commit();     
    }
    
    // lisää uuden juomalajin tietokantaan
    public void lisaaJuomalaji(Juomalaji uusi) {
        em = getEntityManager();
        em.getTransaction().begin();
        em.persist(uusi);
        em.getTransaction().commit();   
    }
    
    // lisää uuden ainesosan tietokantaan
    public void lisaaAinesosa(Ainesosa uusi) {
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
    
    // palauttaa listan kaikista käyttäjistä
    public List<Kayttaja> getKayttajat() {
        em = getEntityManager();
        return em.createQuery("SELECT u FROM Kayttaja u").getResultList();
    }
    
    public List<Juomalaji> getJuomaLajit() {
        em = getEntityManager();
        return em.createQuery("SELECT j FROM Juomalaji j").getResultList();
    }
    
    public List<Ainesosa> getAinesOsat() {
        em = getEntityManager();
        return em.createQuery("SELECT a FROM Ainesosa a").getResultList();
    }
    
    // palauttaa listan kaikista juomista
    public List<Drinkkiresepti> getJuomat() {
        em = getEntityManager();
        return em.createQuery("SELECT d FROM Drinkkiresepti d ORDER BY d.nimi asc").getResultList();
    }
    
    // laskevasti järjestetty lista juomista
    public List<Drinkkiresepti> getOrderedJuomat() {
        em = getEntityManager();
        return em.createQuery("SELECT d FROM Drinkkiresepti d ORDER BY d.nimi desc").getResultList();
    }
    
    // etsii pääavaimen perusteella haettavan juomalajin
    public Juomalaji haeJuomalaji(long lajinId) {
        em = getEntityManager();
        return em.find(Juomalaji.class, lajinId);
    }
    
    // etsii pääavaimen perusteella haettavan ainesosan
    public Ainesosa haeAinesosa(String tunnus) {
        em = getEntityManager();
        return em.find(Ainesosa.class, tunnus);
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
    
    // poistaa tietyn drinkin
    public void poistaDrinkki(long drinkinId) {
        em = getEntityManager();
        em.getTransaction().begin();
        
        Drinkkiresepti poistettava = em.find(Drinkkiresepti.class, drinkinId); // etsitään poistettava
        Juomalaji laji = poistettava.getLaji(); // poistettavan drinkin juomalaji
        laji.getDrinkit().remove(poistettava); // tuhotaan juomalajilta viitteet poistettavaan drinkkiin
        poistettava.setLaji(null); // ja drinkilta poistetaan viitteet juomalajiin
        List<Ainesosa> osat = poistettava.getAinesosat();
        
        for (int i=0; i < osat.size(); ++i) { // poistetaan ainesosilta viitteet poistettavaan drinkkiin
            osat.get(i).getDrinkit().remove(poistettava);
        }
        
        poistettava.setAinesosat(null); // ja drinkiltä viitteet ainesosiin
        
        em.remove(poistettava); // haetaan drinkki tietokannasta ja poistetaan
        em.getTransaction().commit();  
    }
    
    public void poistaArvostelu(long arvosteluId) {
        em = getEntityManager();
        em.getTransaction().begin();
        
        Arvostelu poistettava = em.find(Arvostelu.class, arvosteluId); // etsitään poistettava arvostelu
        Drinkkiresepti drinkki = poistettava.getResepti(); // etsitään drinkki, jolle arvostelu tehty
        drinkki.getArvostelut().remove(poistettava); // poistetaan drinkiltä viitteet arvosteluun
        poistettava.setResepti(null); // ja arvostelulta viitteet drinkkiin
        
        em.remove(poistettava);
        em.getTransaction().commit();
    }
    
    public void paivitaDrinkki(long drinkinId, String kuvaus, String ohjeet) {
        em = getEntityManager();
        em.getTransaction().begin();
        Drinkkiresepti muokattava = em.find(Drinkkiresepti.class, drinkinId);
        muokattava.setKuvaus(kuvaus);
        muokattava.setOhjeet(ohjeet);
        em.merge(muokattava);
        em.getTransaction().commit();
    }
    
}
