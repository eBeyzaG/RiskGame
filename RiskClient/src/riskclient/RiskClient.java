/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskclient;

import javax.swing.JFrame;
import riskclient.view.ClientMainFrame;

/**
 *
 * @author beyza
 */
public class RiskClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //JFrame fr = new ClientMainFrame("Risk", 1200, 900);
        /*     java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientMainFrame("Risk", 1200, 900).setVisible(true);
            }
        });*/

        Client cli = new Client("127.0.0.1", 5000);
    }

}
