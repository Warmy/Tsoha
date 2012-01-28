/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkisto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Keni
 */
public class Rekisteri {
    
    private static List<Kayttaja> lista = new ArrayList();
    
    public void lisaaKayttaja(Kayttaja kayttaja) {
        lista.add(kayttaja);
    }
    
    public List<Kayttaja> getKayttajat() {
        return lista;
    }
    
}
