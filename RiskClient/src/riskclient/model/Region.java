/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskclient.model;

import java.util.ArrayList;

/**
 *
 * @author beyza
 */
public class Region {
    
    String name;
    int troop_count;
    Player owner;
    ArrayList<Region> neighbor_regions;

    public Region(String name) {
        this.name = name;
        
    }

    public void setNeighbor_regions(ArrayList<Region> neighbours) {
        this.neighbor_regions = neighbours;
    }
    
    
    
    
    
    
}
