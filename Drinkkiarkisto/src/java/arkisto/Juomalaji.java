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
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 *
 * @author Kenny Heinonen
 */

/**
 * 
 * Drinkkireseptin juomalaji.
 */
@Entity
public class Juomalaji implements Serializable {
    
    /**
     * Juomalajin yksilöivä pääavain.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    /**
     * Juomalajin nimi.
     */
    @Column
    private String nimi;
        
    /**
     * Lista drinkkiresepteistä, jotka kuuluvat tähän juomalajiin.
     * 
     * Muuttuja on viiteavain tauluun Drinkkiresepti.
     */
    @OneToMany
    @JoinTable(joinColumns = {
        @JoinColumn(name = "laji_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "resepti_id")})
    // kullakin juomalajilla on lista juomia
    private List<Drinkkiresepti> drinks;

    public Juomalaji() {
    }
    
    /**
     * Luo uuden juomalajin.
     * @param nimi Juomalajille annettu nimi.
     */
    public Juomalaji(String nimi) {
        this.nimi = nimi;
    }

    /**
     * 
     * @return Palauttaa juomalajin nimen.
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * Asettaa juomalajille uuden nimen.
     * @param nimi Uusi nimi.
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    /**
     * @return Palauttaa juomalajin pääavaimen.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asettaa juomalajille uuden pääavaimen.
     * @param id Uusi tunnus.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return Palauttaa juomalajiin kuuluvat drinkit.
     */
    public List<Drinkkiresepti> getDrinkit() {
        return drinks;
    }

    /**
     * Asettaa juomalajiin kuuluvat drinkit.
     * @param drinks Juomalajiin kuuluvat drinkit.
     */
    public void setDrinkit(List<Drinkkiresepti> drinks) {
        this.drinks = drinks;
    }
    
}
