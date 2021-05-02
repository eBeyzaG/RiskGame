/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskclient;

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
    String board_info;
    String chosen_region_name;

    public SendMessage(String type) {
        this.type = type;
    }

    public void setWanted_pair_id(int wanted_pair_id) {
        this.wanted_pair_id = wanted_pair_id;
    }

    public void setBoard_info(String board_info) {
        this.board_info = board_info;
    }

    public void setChosen_region_name(String chosen_region_name) {
        this.chosen_region_name = chosen_region_name;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public void setMessage(String message) {
        this.message = message;
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
