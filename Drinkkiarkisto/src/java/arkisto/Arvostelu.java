/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkisto;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Kenny Heinonen
 */

/**
 * 
 * Drinkkireseptille kirjoitettu arvostelu.
 */
@Entity
public class Arvostelu implements Serializable {
    
    /**
     * 
     * Drinkkireseptin yksilöivä pääavain relaatiotietokannassa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    
    /**
     * Arvostelussa drinkille annettu arvosana.
     */
    @Column
    private int arvosana;
    
    /**
     * Arvostelun sisältö.
     */
    @Column
    private String teksti;
    
    /**
     * Arvostelijan nimimerkki.
     */
    @Column
    private String nimimerkki;
    
    /**
     * Drinkkiresepti, jolle arvostelu on kirjoitettu.
     * 
     * Muuttuja on viiteavain tauluun Drinkkiresepti. Kun arvostelu
     * kirjoitetaan, drinkillä on viite sille kirjoitettuihin arvosteluihin ja arvostelulla
     * on viite drinkkeihin, joihin arvostelut ovat kohdistuneet.
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn // viiteavain tauluun Drinkkiresepti
    private Drinkkiresepti resepti;

    public Arvostelu() {
    }
    
    /**
     * Luo uuden arvostelun teksteineen.
     * @param teksti Arvostelussa kirjoitettu teksti.
     */
    public Arvostelu(String teksti) {
        this.teksti = teksti;
    }
    /**
     * Luo uuden arvostelun.
     * @param arvosana Drinkille annettu arvosana.
     * @param teksti Arvostelussa kirjoitettu teksti.
     * @param nimimerkki Arvostelijan nimimerkki.
     */
    public Arvostelu(int arvosana, String teksti, String nimimerkki) {
        this.arvosana = arvosana;
        this.teksti = teksti;
        this.nimimerkki = nimimerkki;
    }

    /**
     * 
     * @return Palauttaa arvostelussa drinkille annetun arvosanan.
     */
    public int getArvosana() {
        return arvosana;
    }

    /**
     * Asettaa arvostelulle uuden arvosanan.
     * @param arvosana Uusi arvosana.
     */
    public void setArvosana(int arvosana) {
        this.arvosana = arvosana;
    }

    /**
     * 
     * @return Palauttaa arvostelun tekstuaalisen sisällön.
     */
    public String getTeksti() {
        return teksti;
    }

    /**
     * Muuttaa arvostelussa kirjoitettua tekstiä.
     * @param teksti Uusi teksti.
     */
    public void setTeksti(String teksti) {
        this.teksti = teksti;
    }

    /**
     * 
     * @return Palauttaa arvostelun pääavaimen.
     */
    public Long getId() {
        return id;
    }

    /**
     * Muuttaa arvostelun pääavainta.
     * @param id Uusi pääavain.
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Asetetaan mille drinkille arvostelu on osoitettu.
     * @param resepti Arvosteltu drinkki.
     * 
     * Metodi asettaa mille drinkille arvostelu on tehty ja koska Drinkkiresepti-oliolla
     * on lista siihen kuuluvista arvosteluista, niin metodi tarkistaa samalla onko
     * tämä uusi arvostelu vielä kyseisellä drinkillä muistissa. Jos ei ole, lisätään
     * tämä arvostelu drinkin arvostelujen listaan.
     * 
     * @see arkisto.Drinkkiresepti#getArvostelut() 
     */
    public void setResepti(Drinkkiresepti resepti) {
        if (resepti == null) {
            this.resepti = resepti;
            return;
        }
        this.resepti = resepti;
        
        if (!resepti.getArvostelut().contains(this)) // samalla kun asetetaan arvostelulle drinkki
            resepti.getArvostelut().add(this); // asetetaan drinkille arvostelu
    }
    
    /**
     * 
     * @return Palauttaa tiedon siitä mille drinkille arvostelu on osoitettu.
     */
    public Drinkkiresepti getResepti() {
        return resepti;
    }

    /**
     * 
     * @return Palauttaa arvostelijan nimimerkin.
     */
    public String getNimimerkki() {
        return nimimerkki;
    }

    /**
     * Asettaa arvostelijalle uuden nimimerkin.
     * @param nimimerkki Arvostelijan uusi nimimerkki.
     */
    public void setNimimerkki(String nimimerkki) {
        this.nimimerkki = nimimerkki;
    }
}
