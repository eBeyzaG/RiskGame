/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskclient;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author beyza
 */
public class ReceiveMessage  implements Serializable{

    String type;
    String message;
    int toId;
    int fromId;
    int wanted_pair_id;
    int client_id_for_connection;
    String board_info;
    String chosen_region_name;

    
    public void break_board_message(){
    
    }
    
    public ReceiveMessage(String text) {
        String[] lines = text.split("\n");
        this.type = lines[0].split(" ")[1];
        this.message = lines[1].split(" ")[1];
        this.toId = Integer.parseInt(lines[2].split(" ")[1]);
        this.fromId = Integer.parseInt(lines[3].split(" ")[1]);
        this.wanted_pair_id = Integer.parseInt(lines[4].split(" ")[1]);
        this.client_id_for_connection = Integer.parseInt(lines[5].split(" ")[1]);
        this.board_info = lines[6].split(" ")[1];
        this.chosen_region_name = lines[7].split(" ")[1];

    }

    @Override
    public String toString() {
        String msg = "";

        msg += "Type: " + this.type + "\nMessage: " + this.message
                + "\nTo: " + this.toId + "\nFrom: " + this.fromId
                + "\nWanted_pair_id: " + this.wanted_pair_id
                + "\nClient_id_for_connection: " + this.client_id_for_connection
                + "\nBoard_info: " + this.board_info
                + "\nChosen_region_name: " + this.chosen_region_name;
        return msg;
    }

}
