/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkisto;

import java.io.Serializable;
import java.util.List;
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
 * @author Keni
 */

@Entity
public class Juomalaji implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column
    private String nimi;
           
    @OneToMany
    @JoinTable(joinColumns = {
        @JoinColumn(name = "laji_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "resepti_id")})
    // kullakin juomalajilla on lista juomia
    private List<Drinkkiresepti> drinks;

    public Juomalaji() {
    }
    
    public Juomalaji(String nimi) {
        this.nimi = nimi;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Drinkkiresepti> getDrinkit() {
        return drinks;
    }

    public void setDrinkit(List<Drinkkiresepti> drinks) {
        this.drinks = drinks;
    }
    
}
