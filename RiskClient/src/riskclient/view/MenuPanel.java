/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskclient.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author beyza
 */
public class MenuPanel extends JPanel {

    public MenuPanel() {
        init();

    }

    void init() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.setBackground(Color.PINK);

        JButton start_but = new JButton();
        start_but.setText("Start");
        start_but.setPreferredSize(new Dimension(200,300));
        start_but.setBackground(Color.GRAY);
        start_but.setForeground(Color.white);
      
        start_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ClientMainFrame.show_game();

            }
        });
        this.add(start_but);

    }

}
