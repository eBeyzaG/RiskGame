/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskclient.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author beyza
 */
public class Region {

    String name;
    int troop_count;
    String owner;
    ArrayList<Region> neighbor_regions;
    BufferedImage red_img;
    BufferedImage blue_img;

    public Region(String name) {
        this.name = name;
        this.owner = "empty";
        try {
            this.blue_img = ImageIO.read(new File("src\\riskclient\\images\\" + this.name +"blue.png"));
               this.red_img = ImageIO.read(new File("src\\riskclient\\images\\" + this.name + "red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Region.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
    }

    public BufferedImage getBlue_img() {
        return blue_img;
    }

    void assign_img() {

        try {
            switch (this.name) {
                case "A":
                    this.blue_img = ImageIO.read(new File("src\\riskclient\\images\\Ablue.png"));
                    this.red_img = ImageIO.read(new File("src\\riskclient\\images\\Ared.png"));
                    break;
                case "B":
                    this.blue_img = ImageIO.read(new File("src\\riskclient\\images\\Bblue.png"));
                    this.red_img = ImageIO.read(new File("src\\riskclient\\images\\Bred.png"));
                    break;
                case "C":
                    this.blue_img = ImageIO.read(new File("src\\riskclient\\images\\Cblue.png"));
                    this.red_img = ImageIO.read(new File("src\\riskclient\\images\\Cred.png"));
                    break;
                case "D":
                    this.blue_img = ImageIO.read(new File("src\\riskclient\\images\\Dblue.png"));
                    this.red_img = ImageIO.read(new File("src\\riskclient\\images\\Dred.png"));
                    break;
                case "E":
                    this.blue_img = ImageIO.read(new File("src\\riskclient\\images\\Eblue.png"));
                    this.red_img = ImageIO.read(new File("src\\riskclient\\images\\Ered.png"));
                    break;
                case "F":
                    this.blue_img = ImageIO.read(new File("src\\riskclient\\images\\Fblue.png"));
                    this.red_img = ImageIO.read(new File("src\\riskclient\\images\\Fred.png"));
                    break;
                case "G":
                    this.blue_img = ImageIO.read(new File("src\\riskclient\\images\\Gblue.png"));
                    this.red_img = ImageIO.read(new File("src\\riskclient\\images\\Gred.png"));
                    break;
                case "H":
                    this.blue_img = ImageIO.read(new File("src\\riskclient\\images\\Hblue.png"));
                    this.red_img = ImageIO.read(new File("src\\riskclient\\images\\Hred.png"));
                    break;
                case "I":
                    this.blue_img = ImageIO.read(new File("src\\riskclient\\images\\Iblue.png"));
                    this.red_img = ImageIO.read(new File("src\\riskclient\\images\\Ired.png"));
                    break;
                case "J":
                    this.blue_img = ImageIO.read(new File("src\\riskclient\\images\\Jblue.png"));
                    this.red_img = ImageIO.read(new File("src\\riskclient\\images\\Jred.png"));
                    break;

            }

        } catch (IOException ex) {
            Logger.getLogger(Region.class.getName()).log(Level.SEVERE, null, ex);
        }

    } //gereksiz

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Region> getNeighbor_regions() {
        return neighbor_regions;
    }

    public String getOwner() {
        return owner;
    }

    public void setNeighbor_regions(ArrayList<Region> neighbours) {
        this.neighbor_regions = neighbours;
    }

    public int getTroop_count() {
        return troop_count;
    }

    public String getName() {
        return name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTroop_count(int troop_count) {
        this.troop_count = troop_count;
    }

}
