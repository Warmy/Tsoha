/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkisto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

/**
 *
 * @author Kenny Heinonen
 */

/**
 * 
 * Drinkkireseptiin kuuluva ainesosa.
 */
@Entity
public class Ainesosa implements Serializable {
    
    /**
     * Ainesosan nimi.
     */
    @Id
    private String nimi; // ainesosan nimi, pääavain
    
    /**
     * Lista drinkkiresepteistä, joihin ainesosa kuuluu.
     */
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn
    private List<Drinkkiresepti> reseptit;

    public Ainesosa() {
    }
    
    /**
     * Luo uuden ainesosan ja asettaa sille nimen.
     * @param nimi Ainesosalle annettava nimi.
     */
    
    public Ainesosa(String nimi) {
        this.nimi = nimi;
    }

    /**
     * @return Palauttaa ainesosan nimen.
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * Asettaa ainesosalle uuden nimen.
     * @param nimi Uusi nimi ainesosalle.
     */
    
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    /**
     * 
     * @return Palauttaa drinkkireseptit, joihin ainesosa kuuluu.
     */
    
    public List<Drinkkiresepti> getDrinkit() {
        return reseptit;
    }

    /**
     * Asettaa ainesosalle listan drinkeistä, johon ainesosa kuuluu.
     * @param reseptit Lista drinkeistä, johon ainesosa kuuluu.
     */
    
    public void setDrinkit(List<Drinkkiresepti> reseptit) {
        this.reseptit = reseptit;
    }
    
}
