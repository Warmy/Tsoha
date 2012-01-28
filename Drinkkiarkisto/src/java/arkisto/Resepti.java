/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkisto;

/**
 *
 * @author Keni
 */
public class Resepti {
    
    private String nimi; // drinkin nimi
    private String ohjeet; // reseptin teko-ohjeet
    private String kuvaus; // mahdollisimman hieno mainospuhe drinkistä
    private int arvosana; // drinkin tämänhetkinen arvosana
    
    public Resepti(String nimi) {
        this.nimi = nimi;
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
    
}
