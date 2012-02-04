/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkisto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Keni
 */
@Entity
public class Drinkkiresepti implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String nimi; // drinkin nimi
    
    @Column
    private String kuvaus; // drinkin mainospuhe
    
    @Column
    private String ohjeet; // reseptiohjeet
    
    @Column
    private int arvosana; // drinkin arvosana

    public Drinkkiresepti() {
    }

    public Drinkkiresepti(String nimi) {
        this.nimi = nimi;
    }

    public Drinkkiresepti(String nimi, String kuvaus, String ohjeet) {
        this.nimi = nimi;
        this.kuvaus = kuvaus;
        this.ohjeet = ohjeet;
    }
    

    public int getArvosana() {
        return arvosana;
    }

    public void setArvosana(int arvosana) {
        this.arvosana = arvosana;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getOhjeet() {
        return ohjeet;
    }

    public void setOhjeet(String ohjeet) {
        this.ohjeet = ohjeet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
