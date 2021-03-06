/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskserver;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author beyza
 */
public class ReceiveMessage implements Serializable{

    String type;
    String message;
    int toId;
    int fromId;
    int wanted_pair_id;
    int client_id_for_connection;
    

    public ReceiveMessage(String text, int from) { //converts string to message to read
        String[] lines = text.split("\n");
        this.type = lines[0].split(" ")[1];
        this.message = lines[1].split(" ")[1];
        this.toId = Integer.parseInt(lines[2].split(" ")[1]);
        this.fromId =  Integer.parseInt(lines[3].split(" ")[1]);
        this.wanted_pair_id =  Integer.parseInt(lines[4].split(" ")[1]);
        this.client_id_for_connection = Integer.parseInt(lines[5].split(" ")[1]);
        this.fromId = from;
    }


    @Override
    public String toString() {
        String msg = "";

         msg += "Type: " + this.type + "\nMessage: " + this.message +
                  "\nTo: " + this.toId + "\nFrom: " + this.fromId +
                     "\nWanted_pair_id: " + this.wanted_pair_id +
                        "\nClient_id_for_connection: " + this.client_id_for_connection;
        return msg;
    }

}
