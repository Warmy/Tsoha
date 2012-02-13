/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkisto;

/**
 *
 * @author Keni
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kayttaja implements Serializable {
    
    
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private String tunnus;
    
    @Column
    private String salasana;

    public Kayttaja() {
    }
    
    public Kayttaja(String tunnus, String salasana) {
        this.tunnus = tunnus;
        this.salasana = salasana;
    }

    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    public String getTunnus() {
        return tunnus;
    }

    public void setTunnus(String tunnus) {
        this.tunnus = tunnus;
    }
}
