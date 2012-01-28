/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkisto;

/**
 *
 * @author Keni
 */
public class Arvostelu {
    
    private String teksti; // arvostelun sisältö
    private int arvosana; // arvostelussa annettu arvosana
    
    public Arvostelu(String teksti) {
        this.teksti = teksti;
    }

    public int getArvosana() {
        return arvosana;
    }

    public void setArvosana(int arvosana) {
        this.arvosana = arvosana;
    }

    public String getTeksti() {
        return teksti;
    }

    public void setTeksti(String teksti) {
        this.teksti = teksti;
    }
    
    
    
}
