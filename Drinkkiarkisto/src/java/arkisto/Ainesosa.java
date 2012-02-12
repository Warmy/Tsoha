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
 * @author Keni
 */
@Entity
public class Ainesosa implements Serializable {
    
    @Id
    private String nimi; // ainesosan nimi, pääavain
    
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinColumn
    private List<Drinkkiresepti> reseptit;

    public Ainesosa() {
    }
    
    public Ainesosa(String nimi) {
        this.nimi = nimi;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public List<Drinkkiresepti> getDrinkit() {
        return reseptit;
    }

    public void setDrinkit(List<Drinkkiresepti> reseptit) {
        this.reseptit = reseptit;
    }
    
}
