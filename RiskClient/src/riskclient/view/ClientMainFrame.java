/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskclient.view;

import java.awt.CardLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author beyza
 */
public class ClientMainFrame extends JFrame implements Runnable {

    public static CardLayout cardLayout;
    public static JPanel mainPanel;
    int width, height;

    public ClientMainFrame(String title, int width, int height) throws HeadlessException {

        this.width = width;
        this.height = height;

        this.setTitle(title);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.init_components();
        this.showWindow();

    }

    public void showWindow() {
        this.setLocationRelativeTo(null);
        this.setSize(width, height);
        this.setVisible(true);
    }

    public void init_components() {

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        add(mainPanel);

        GamePanel gamePanel = new GamePanel(width, height);
        MenuPanel menuPanel = new MenuPanel();

        mainPanel.add(gamePanel, "game");
        mainPanel.add(menuPanel, "menu");
        
        cardLayout.show(mainPanel, "menu");
        
    }

    public static void show_game() {
        cardLayout.show(mainPanel, "game");
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
