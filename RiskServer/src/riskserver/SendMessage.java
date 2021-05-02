/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskserver;

import java.io.Serializable;

/**
 *
 * @author beyza
 */
public class SendMessage implements Serializable{
    
    String type;
    String message;
    int toId;
    int fromId;
    int wanted_pair_id;
    int client_id_for_connection;

    public SendMessage(String type) {
        this.type = type;
    }

    public void setWanted_pair_id(int wanted_pair_id) {
        this.wanted_pair_id = wanted_pair_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setClient_id_for_connection(int client_id_for_connection) {
        this.client_id_for_connection = client_id_for_connection;
    }
    
    
    @Override
    public String toString() {//converts message to string to send to client
        String msg = "";
        
         msg += "Type: " + this.type + "\nMessage: " + this.message +
                  "\nTo: " + this.toId + "\nFrom: " + this.fromId +
                     "\nWanted_pair_id: " + this.wanted_pair_id +
                        "\nClient_id_for_connection: " + this.client_id_for_connection +
                        "\nno info\nno info ";
     
        return msg;
    }
    
}
