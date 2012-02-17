/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkisto;

/**
 *
 * @author Kenny Heinonen
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 
 * Web-sovelluksen rekisteröitynyt käyttäjä.
 */
@Entity
public class Kayttaja implements Serializable {
    
    /**
     * Käyttäjän tunnus, joka on myös käyttäjän yksilöivä pääavain.
     */
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private String tunnus;
    
    /**
     * Käyttäjän salasana.
     */
    @Column
    private String salasana;

    public Kayttaja() {
    }
    
    /**
     * Luo uuden käyttäjän.
     * @param tunnus Käyttäjän tunnus.
     * @param salasana Käyttäjän salasana.
     */
    public Kayttaja(String tunnus, String salasana) {
        this.tunnus = tunnus;
        this.salasana = salasana;
    }

    /**
     * Palauttaa käyttäjän salasanan.
     * @return Käyttäjän salasana.
     */
    public String getSalasana() {
        return salasana;
    }

    /**
     * Asettaa käyttäjälle uuden salasanan.
     * @param salasana Uusi salasana.
     */
    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    /**
     * Palauttaa käyttäjän tunnuksen.
     * @return Käyttäjän tunnus.
     */
    public String getTunnus() {
        return tunnus;
    }

    /**
     * Asettaa käyttäjän tunnuksen.
     * @param tunnus Käyttäjän tunnus.
     */
    public void setTunnus(String tunnus) {
        this.tunnus = tunnus;
    }
}
