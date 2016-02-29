/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertyparser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.feelit.propertyparser.entities.Parser;

/**
 *
 * @author vlad.butnaru
 */
public class PropertyParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Parser parser = new Parser();
        try {
            parser.getPropertyLinksForManhattan();
            parser. parsePropertyData();
        } catch (IOException ex) {
            Logger.getLogger(PropertyParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
