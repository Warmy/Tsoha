/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkisto;

/**
 *
 * @author Keni
 */
public class Kayttaja {
    
    private String tunnus; // kayttajatunnus
    private String salasana;
    
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
