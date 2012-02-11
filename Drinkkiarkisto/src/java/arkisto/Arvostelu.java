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
 * @author Keni
 * Jollekin drinkille kirjoitettu arvostelu. Sisältää arvosanan
 * ja kirjoitetun tekstin.
 */

@Entity
public class Arvostelu implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    
    @Column
    private int arvosana;
    
    @Column
    private String teksti;
    
    @Column
    private String nimimerkki;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn // viiteavain tauluun Drinkkiresepti
    private Drinkkiresepti resepti;

    public Arvostelu() {
    }
    
    public Arvostelu(String teksti) {
        this.teksti = teksti;
    }
    
    public Arvostelu(int arvosana, String teksti, String nimimerkki) {
        this.arvosana = arvosana;
        this.teksti = teksti;
        this.nimimerkki = nimimerkki;
    }

    public int getArvosana() {
        return arvosana;
    }

    public void setArvosana(int arvosana) {
        this.arvosana = arvosana;
    }

    public String getTeksti() {
        return teksti;
    }

    public void setTeksti(String teksti) {
        this.teksti = teksti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    // asetetaan mihin drinkkiin arvostelu kuuluu
    public void setResepti(Drinkkiresepti resepti) {
        this.resepti = resepti;
        
        if (!resepti.getArvostelut().contains(this)) // samalla kun asetetaan arvostelulle drinkki
            resepti.getArvostelut().add(this); // asetetaan arvostelu drinkille
    }
    
    // palauttaa tiedon siitä mille drinkille arvostelu on osoitettu
    public Drinkkiresepti getResepti() {
        return resepti;
    }

    public String getNimimerkki() {
        return nimimerkki;
    }

    public void setNimimerkki(String nimimerkki) {
        this.nimimerkki = nimimerkki;
    }
}
