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
 * @author Kenny Heinonen
 */

/**
 * Tietokantaoperaatioita hoitava rekisteri.
 *
 */
public class Rekisteri {
    
    /**
     * Käsittelee entiteettejä eli lisää olioita tietokantaan, hakee
     * olioita tietokannasta, poistaa olioita tietokannasta yms.
     */
    private EntityManager em;
    
    private EntityManagerFactory emf = null;

    /**
     * Luo uuden rekisterin.
     */
    public Rekisteri() {
        // käytetään "DrinkkiarkistoPU"-konfiguraatiota
        emf = Persistence.createEntityManagerFactory("DrinkkiarkistoPU");
    }
    
    /**
     * 
     * @return Palauttaa tietokantaa käsittelevan EntityManager-olion. 
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    /**
     * Lisää uuden käyttäjän tietokantaan.
     * @param uusi Uusi käyttäjä.
     */
    public void lisaaKayttaja(Kayttaja uusi) {
        em = getEntityManager();
        em.getTransaction().begin();
        em.persist(uusi);
        em.getTransaction().commit();     
    }
    
    /**
     * Lisää uuden arvostelun tietokantaan.
     * 
     * Mergen avulla päivitetään myös drinkkireseptin tila tietokannassa,
     * koska drinkille lisättiin uusi arvostelu (eli arvostelulla on viite
     * drinkkireseptiin ja toisinpäin).
     * @param uusi Uusi arvostelu.
     */
    public void lisaaArvostelu(Arvostelu uusi) {
        em = getEntityManager();
        em.getTransaction().begin();
        em.merge(uusi);
        em.getTransaction().commit();
    }
    
    /**
     * Lisää uuden drinkkireseptin tietokantaan.
     * 
     * Mergen avulla päivitetään myös drinkkiin kuuluvien ainesosien
     * ja juomalajien tila tietokannassa, koska drinkillä on viitteet
     * sille lisättyihin ainesosiin ja juomalajeihin.
     * @param uusi Uusi drinkkiresepti.
     */
    public void lisaaDrinkki(Drinkkiresepti uusi) {
        em = getEntityManager();
        em.getTransaction().begin();
        em.merge(uusi);
        em.getTransaction().commit();     
    }
    
    /**
     * Lisää uuden juomalajin tietokantaan.
     * @param uusi Uusi juomalaji.
     */
    public void lisaaJuomalaji(Juomalaji uusi) {
        em = getEntityManager();
        em.getTransaction().begin();
        em.persist(uusi);
        em.getTransaction().commit();   
    }
    
    /**
     * Lisää uuden ainesosan tietokantaan.
     * @param uusi Uusi ainesosa.
     */
    public void lisaaAinesosa(Ainesosa uusi) {
        em = getEntityManager();
        em.getTransaction().begin();
        em.persist(uusi);
        em.getTransaction().commit();
    }
    
    /**
     * Lista drinkeistä, jotka on järjestetty nousevasti drinkkien juomalajien nimen perusteella.
     * @return Järjestetty lista drinkkiresepteistä.
     */
    public List<Drinkkiresepti> sortJuomatByLajitAsc() {
        em = getEntityManager();
        return em.createQuery("SELECT d FROM Drinkkiresepti d ORDER BY d.laji.nimi asc").getResultList(); 
    }
    
    /**
     * Lista drinkeistä, jotka on järjestetty laskevasti drinkkien juomalajien nimen perusteella.
     * @return Järjestetty lista drinkkiresepteistä.
     */
    public List<Drinkkiresepti> sortJuomatByLajitDesc() {
        em = getEntityManager();
        return em.createQuery("SELECT d FROM Drinkkiresepti d ORDER BY d.laji.nimi desc").getResultList();
    }
    
    /**
     * Palauttaa listan kaikista käyttäjistä.
     * @return Lista kaikista käyttäjistä.
     */
    public List<Kayttaja> getKayttajat() {
        em = getEntityManager();
        return em.createQuery("SELECT u FROM Kayttaja u").getResultList();
    }
    
    /**
     * Palauttaa listan kaikista juomalajeista.
     * @return Lista kaikista juomalajeista.
     */
    public List<Juomalaji> getJuomaLajit() {
        em = getEntityManager();
        return em.createQuery("SELECT j FROM Juomalaji j").getResultList();
    }
    
    /**
     * Palauttaa listan kaikista ainesosista.
     * @return Lista kaikista ainesosista.
     */
    public List<Ainesosa> getAinesOsat() {
        em = getEntityManager();
        return em.createQuery("SELECT a FROM Ainesosa a").getResultList();
    }
    
    /**
     * Palauttaa listan kaikista drinkkiresepteistä, jotka on järjestetty nousevasti
     * drinkin nimen mukaan.
     * @return Nousevasti järjestetty lista kaikista drinkeistä.
     */
    public List<Drinkkiresepti> getJuomat() {
        em = getEntityManager();
        return em.createQuery("SELECT d FROM Drinkkiresepti d ORDER BY d.nimi asc").getResultList();
    }
    
    /**
     * Palauttaa listan kaikista drinkkiresepteistä, jotka on järjestetty laskevasti
     * drinkin nimen mukaan.
     * @return Laskevasti järjestetty lista kaikista drinkeistä.
     */
    public List<Drinkkiresepti> getOrderedJuomat() {
        em = getEntityManager();
        return em.createQuery("SELECT d FROM Drinkkiresepti d ORDER BY d.nimi desc").getResultList();
    }
    
    /**
     * Hakee juomalajin tietokannasta annetun pääavaimen perusteella.
     * @param lajinId Etsittävän juomalajin pääavain.
     * @return Haettu juomalaji.
     */
    public Juomalaji haeJuomalaji(long lajinId) {
        em = getEntityManager();
        return em.find(Juomalaji.class, lajinId);
    }
    
    /**
     * Hakee ainesosan tietokannasta annetun pääavaimen perusteella.
     * @param tunnus Etsittävän ainesosan pääavain (ainesosan nimi).
     * @return Haettu ainesosa.
     */
    public Ainesosa haeAinesosa(String tunnus) {
        em = getEntityManager();
        return em.find(Ainesosa.class, tunnus);
    }
    
    /**
     * Hakee käyttäjän tietokannasta annetun pääavaimen perusteella.
     * @param tunnus Etsittävän käyttäjän pääavain (käyttäjätunnus).
     * @return Haettu käyttäjä.
     */
    public Kayttaja haeKayttaja(String tunnus) {
        em = getEntityManager();
        return em.find(Kayttaja.class, tunnus); // etsii kayttaja-luokan ilmentymän tämän tunnuksella
    }
    
    /**
     * Hakee drinkkireseptin tietokannasta annetun pääavaimen perusteella.
     * @param juomaId Etsittävän drinkkireseptin pääavain.
     * @return Haettu drinkkiresepti.
     */
    public Drinkkiresepti haeDrinkki(long juomaId) {
        em = getEntityManager();
        return em.find(Drinkkiresepti.class, juomaId);
    }
    
    /**
     * Hakee listan drinkkiresepteistä annetun hakusanan ja juomalajin perusteella.
     * 
     * Metodi hakee tietokannasta kaikki drinkkireseptit, jotka alkavat annetulla
     * hakusanalla tai vastaavat annettua hakusanaa. Jos parametrina annettiin myös
     * juomalaji, johon haettava drinkki kuuluu, se rajaa haun tulosta etsien hakusanalla
     * vain niitä drinkkejä, jotka kuuluvat annettuun juomalajiin.
     * @param sanaa Hakusana.
     * @param lajinId Haettavien drinkkien juomalaji.
     * @return Lista drinkeistä, jotka haulla löydettiin.
     */
    public List<Drinkkiresepti> haeDrinkkiHaunTuloksena(String sana, long lajinId) {
        em = getEntityManager();
        Juomalaji laji = em.find(Juomalaji.class, lajinId);
        
        Query q = null;
        
        if (laji != null) {
            q = em.createQuery("SELECT d FROM Drinkkiresepti d WHERE d.nimi LIKE :nameParam and d.laji.nimi = :lajiParam");
            q.setParameter("nameParam", sana+"%").setParameter("lajiParam", laji.getNimi());
        } else {
            q = em.createQuery("SELECT d FROM Drinkkiresepti d WHERE d.nimi LIKE :nameParam");
            q.setParameter("nameParam", sana+"%"); // etsii juomat, jotka alkavat kyseisellä mjonolla
        }
        return q.getResultList();
    }
    
    /**
     * Poistaa drinkkireseptin tietokannasta.
     * 
     * Metodille annetaan jonkin Drinkkiresepti-olion pääavain, jolla olio löydetään
     * tietokannasta. Jotta poisto onnistuisi varmasti, tulee nollata kaikki viitteet
     * drinkkiin siltä juomalajilta, johon drinkki kuuluu ja niiltä ainesosilta, jotka
     * drinkkiin kuuluvat. Itse drinkkireseptiltä tulee myös nollata viitteet juomalajiinsa
     * ja ainesosiinsa. Lopuksi drinkki poistetaan tietokannasta.
     * @param drinkinId Poistettavan drinkkireseptin pääavain.
     */
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
    
    /**
     * Poistaa tiettyyn drinkkireseptiin liittyvän arvostelun tietokannasta.
     * 
     * Metodille annetaan parametrina arvostelun pääavain, jonka avulla arvostelu
     * löydetään tietokannasta. Tämän jälkeen poistetaan drinkkireseptiltä, jolle
     * kyseinen arvostelu on tehty, viitteet itse arvosteluun. Arvostelulta myös
     * poistetaan viitteet drinkkiin, jolle arvostelu on tehty. Lopuksi
     * arvostelu poistetaan tietokannasta.
     * @param arvosteluId Poistettavan arvostelun pääavain.
     */
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
    
    /**
     * Poistaa juomalajin tietokannasta.
     * 
     * Metodille annetaan juomalajin pääavain, jolla poistettava juomalaji
     * löydetään tietokannasta. Koska juomalajin voi poistaa vain, jos sillä
     * ei ole mitään viitteitä mihinkään drinkkiresepteihin, sen voi huoletta
     * poistaa suoraan tietokannasta. (PoistaJuomalajiServlet.java on huolehtinut,
     * että tätä metodia voidaan kutsua vain, jos juomalajiin ei kuulu mitään
     * drinkkejä)
     * @param lajinId Poistettavan juomalajin pääavain.
     */
    public void poistaJuomalaji(long lajinId) {
        em = getEntityManager();
        em.getTransaction().begin();
        
        Juomalaji poistettava = em.find(Juomalaji.class, lajinId); // etsitään poistettava juomalaji    
        em.remove(poistettava); // poistetaan
        em.getTransaction().commit();
    }
    
    /**
     * Poistaa ainesosan tietokannasta.
     * 
     * Metodille annetaan ainesosan pääavain (ainesosan nimi), jolla poistettava
     * ainesosa löydetään tietokannasta. Koska ainesosan voi poistaa vain, jos sillä
     * ei ole mitään viitteitä mihinkään drinkkiresepteihin, sen voi huoletta poistaa
     * suoraan tietokannasta. (PoistaAinesosaServlet.java on huolehtinut,
     * että tätä metodia voidaan kutsua vain, jos ainesosa ei kuulu mihinkään
     * drinkkiin)
     * @param ainesosanNimi Poistettavan ainesosan pääavain.
     */
    public void poistaAinesosa(String ainesosanNimi) {
        em = getEntityManager();
        em.getTransaction().begin();
        Ainesosa poistettava = em.find(Ainesosa.class, ainesosanNimi);
        em.remove(poistettava); // poistetaan
        em.getTransaction().commit();
    }
    
    /**
     * Päivittää drinkin tiedot annetuilla parametreilla.
     * 
     * Metodille annetaan drinkkireseptin pääavain, jolla drinkki löydetään
     * tietokannasta. Tämän jälkeen sille asetetaan uudet arvot tiettyihin
     * drinkin attribuutteihin ja lisätään päivitettynä tietokantaan.
     * @param drinkinId Päivitettävän drinkkireseptin pääavain.
     * @param kuvaus Drinkkireseptin uusi kuvaus.
     * @param ohjeet Drinkkireseptin uudet teko-ohjeet.
     */
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
