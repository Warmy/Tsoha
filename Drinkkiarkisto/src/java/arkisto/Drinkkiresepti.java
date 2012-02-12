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
 * @author Keni
 */
@Entity
public class Drinkkiresepti implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    
    @Column
    private String nimi; // drinkin nimi
    
    @Column
    private String kuvaus; // drinkin mainospuhe
    
    @Column
    private String ohjeet; // reseptiohjeet
    
    @OneToMany
    @JoinColumn // viiteavain Arvosteluun
    // drinkillä voi olla monta arvostelua
    private List<Arvostelu> arvostelut;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn // kertoo, että attribuutti on viiteavain toiseen tauluun
    private Juomalaji laji;
    
    @ManyToMany
    @JoinColumn
    private List<Ainesosa> ainesosat;
    
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

    // palauttaa tiedon siitä mihin juomalajiin drinkki kuuluu
    public Juomalaji getLaji() {
        return laji;
    }

    // voidaan asettaa mihin juomalajiin drinkki kuuluu
    public void setLaji(Juomalaji laji) {
        this.laji = laji;
        if (!laji.getDrinkit().contains(this)) // samalla kun asetetaan drinkille juomalaji,
            laji.getDrinkit().add(this); // asetetaan drinkki juomalajille
    }
    
    // drinkkiin liittyvät arvostelut
    public void setArvostelut(List<Arvostelu> arvostelut) {
        this.arvostelut = arvostelut;
    }
    
    public List<Arvostelu> getArvostelut() {
        return arvostelut;
    }
    
    public void setAinesosat(List<Ainesosa> ainesosat) {
        this.ainesosat = ainesosat;
        
        for (int i=0; i < ainesosat.size(); ++i) { // kun drinkki saa listan siihen kuuluvista ainesosista
            if (!ainesosat.get(i).getDrinkit().contains(this)) { // lisätään kyseinen drinkki jokaiselle
                ainesosat.get(i).getDrinkit().add(this); // ainesosalle
            }
        }
    }
    
    public List<Ainesosa> getAinesosat() {
        return ainesosat;
    }
    
    
}
