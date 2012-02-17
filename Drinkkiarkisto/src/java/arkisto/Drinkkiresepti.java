/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkisto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Kenny Heinonen
 */

/**
 * Tietokantaan lisättävä drinkkiresepti.
 */
@Entity
public class Drinkkiresepti implements Serializable {

    /**
     * Drinkkireseptin yksilöivä pääavain.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    
    /**
     * Drinkin nimi.
     */
    @Column
    private String nimi;
    
    /**
     * Drinkkiä kuvaava "mainospuhe".
     */
    @Column
    private String kuvaus;
    
    /**
     * Drinkkireseptin teko-ohjeet.
     */
    @Column
    private String ohjeet; // reseptiohjeet
    
    /**
     * Lista drinkille tehdyistä arvosteluista.
     * 
     * Muuttuja on viiteavain tauluun Arvostelu. Kun drinkille kirjoitetaan
     * arvostelu eli luodaan uusi Arvostelu-olio, sillä on viite arvosteltuun
     * drinkkiin ja drinkillä on viite siihen liittyviin arvosteluihin.
     */
    @OneToMany
    @JoinColumn // viiteavain Arvosteluun
    // drinkillä voi olla monta arvostelua
    private List<Arvostelu> arvostelut;
    
    /**
     * Kertoo mihin juomalajiin drinkki kuuluu.
     * 
     * Viiteavain tauluun Juomalaji. Kun drinkki luodaan ja sille määritellään
     * sen juomalaji, Juomalaji-oliolle päivitetään tieto siitä, että sillä
     * on yksi drinkki lisää, joka kuuluu tälle Juomalaji-oliolle.
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn // kertoo, että attribuutti on viiteavain toiseen tauluun
    private Juomalaji laji;
    
    /**
     * Lista kaikista drinkin aineosista.
     * 
     * Viiteavain tauluun Ainesosa. Kun drinkki luodaan, sille valitut ainesosat
     * ovat Ainesosa-olioita, joille päivitetään tieto siitä, että nämä oliot
     * kuuluvat tähän drinkkireseptiin.
     */
    @ManyToMany
    @JoinColumn
    private List<Ainesosa> ainesosat;
    
    public Drinkkiresepti() {
    }

    /**
     * Luo uuden drinkkireseptin.
     * @param nimi Drinkille annettu nimi.
     */
    public Drinkkiresepti(String nimi) {
        this.nimi = nimi;
    }

    /**
     * Luo uuden drinkkireseptin.
     * @param nimi Drinkille annettu nimi.
     * @param kuvaus Drinkkiä kuvaava teksti.
     * @param ohjeet Drinkin teko-ohjeet.
     */
    public Drinkkiresepti(String nimi, String kuvaus, String ohjeet) {
        this.nimi = nimi;
        this.kuvaus = kuvaus;
        this.ohjeet = ohjeet;
    }

    /**
     * 
     * @return Palauttaa drinkkiä kuvailevan tekstin.
     */
    public String getKuvaus() {
        return kuvaus;
    }

    /**
     * Asettaa drinkille drinkkiä kuvaavan tekstin.
     * @param kuvaus Kuvaava teksti.
     */
    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    /**
     * 
     * @return Palauttaa drinkin nimen.
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * Asettaa drinkille uuden nimen.
     * @param nimi Uusi nimi.
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    /**
     * 
     * @return Palauttaa drinkin teko-ohjeet.
     */
    public String getOhjeet() {
        return ohjeet;
    }

    /**
     * Asettaa drinkille uudet teko-ohjeet.
     * @param ohjeet Uudet ohjeet.
     */
    public void setOhjeet(String ohjeet) {
        this.ohjeet = ohjeet;
    }

    /**
     * 
     * @return Palauttaa drinkin yksilöivän pääavaimen.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asettaa drinkille uuden pääavaimen.
     * @param id Uusi pääavain.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return Palauttaa tiedon siitä mihin juomalajiin drinkki kuuluu.
     */
    public Juomalaji getLaji() {
        return laji;
    }

    /**
     * Asetetaan drinkille uusi juomalaji, johon drinkki kuuluu.
     * 
     * Samalla kun asetetaan drinkille uusi juomalaji, johon drinkki kuuluu, niin
     * kutsutaan Juomalaji-olion metodia getDrinkit(). Metodi palauttaa listan drinkeistä,
     * jotka kuuluvat Juomalaji-olioon. Jos kyseinen drinkki ei vielä ole Juomalaji-olion
     * listassa, lisätään se sinne.
     * 
     * @param laji Drinkille asetettava juomalaji.
     * @see arkisto.Juomalaji#getDrinkit() 
     */
    public void setLaji(Juomalaji laji) {
        this.laji = laji;
        if (!laji.getDrinkit().contains(this)) // samalla kun asetetaan drinkille juomalaji,
            laji.getDrinkit().add(this); // asetetaan drinkki juomalajille
    }
    
    /**
     * Drinkille asetettavat arvostelut.
     * @param arvostelut Drinkin arvostelut.
     */
    public void setArvostelut(List<Arvostelu> arvostelut) {
        this.arvostelut = arvostelut;
    }
    
    /**
     * 
     * @return Palauttaa listan drinkille kirjoitetuista arvosteluista.
     */
    public List<Arvostelu> getArvostelut() {
        return arvostelut;
    }
    
    /**
     * Asetetaan drinkille siihen kuuluvat ainesosat.
     * 
     * Kun drinkille lisätään siihen kuuluvat ainesosat, käydään samalla parametrina
     * annettua ainesosa-listaa läpi ja kutsutaan kunkin Ainesosa-olion metodia
     * getDrinkit(). Tämä metodi palauttaa listan drinkeistä, jotka "kuuluvat" tähän
     * ainesosaan. Jos kyseinen drinkki ei ole vielä Ainesosa-olion listassa, lisätäään
     * se sinne.
     * 
     * @param ainesosat Drinkkiin kuuluvat ainesosat.
     * @see arkisto.Ainesosa#getDrinkit() 
     */
    public void setAinesosat(List<Ainesosa> ainesosat) {
        this.ainesosat = ainesosat;
        
        for (int i=0; i < ainesosat.size(); ++i) { // kun drinkki saa listan siihen kuuluvista ainesosista
            if (!ainesosat.get(i).getDrinkit().contains(this)) { // lisätään kyseinen drinkki jokaiselle
                ainesosat.get(i).getDrinkit().add(this); // ainesosalle
            }
        }
    }
    
    /**
     * 
     * @return Palauttaa drinkkiin kuuluvat ainesosat.
     */
    public List<Ainesosa> getAinesosat() {
        return ainesosat;
    }
    
    
}
