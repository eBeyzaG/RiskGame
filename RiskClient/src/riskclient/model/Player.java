/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskclient.model;

import java.util.ArrayList;
import riskclient.Client;

/**
 *
 * @author beyza
 */
public class Player{
    
    String username;
    ArrayList<Region> owned_regions;
    int total_troop_count;
    boolean players_turn;

    public Player(String serverIp, int serverPort, String username) {
        
       
        this.username = username;
        
    }
    
    
    
    void attack(Region r){}
    
    void defend(Region r){}
    
    
}
