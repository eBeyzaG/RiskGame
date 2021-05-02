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
 * @author beyza Holds the region information
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
        try {//assigns images to region
            this.blue_img = ImageIO.read(new File("src\\riskclient\\images\\" + this.name + "blue.png"));
            this.red_img = ImageIO.read(new File("src\\riskclient\\images\\" + this.name + "red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Region.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public BufferedImage getBlue_img() {
        return blue_img;
    }

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
