    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskserver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author beyza
 */
public class RiskServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Server myServer = new Server(5000);
        } catch (IOException ex) {
            Logger.getLogger(RiskServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
