/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskclient.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author beyza
 */
public class GamePanel extends JPanel {
    
    Canvas canvas;
    int width, height;

    public GamePanel(int width, int height) {
        
        
        this.width = width;
        this.height = height;
        setBackground(Color.red);
        this.setVisible(true);
        
        
      //  this.init();
    }
    
    public void init(){
        canvas = new Canvas();
        canvas.setBackground(Color.yellow);
        canvas.setPreferredSize(new Dimension(width, height));
        
        this.add(canvas);
      
    }
}
