/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskserver;

import java.util.Random;

/**
 *
 * @author beyza
 */
public class Game {

    ServerClient player1;
    ServerClient player2;
    int gameID;

    public Game(ServerClient player1, ServerClient player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameID = Server.game_id_count++;

        start_game();
    }

    void start_game() {//starts the game and chooses the first player randomly

        SendMessage newMsg = new SendMessage("msg");
        newMsg.setMessage("Paired");

        player1.sendMessage(newMsg.toString());
        player2.sendMessage(newMsg.toString());
        
        player1.setGame(this);
        player2.setGame(this);
        
        Random r = new Random();
         newMsg = new SendMessage("first_part");
         newMsg.setMessage("first_turn");
        if(r.nextInt(2) == 1){
            player2.sendMessage(newMsg.toString());
        }else{
            player1.sendMessage(newMsg.toString());
        }
            
    }
    
    
    void forwardMessage(Object msg, ServerClient pair){//forwards the message from client to client
        pair.sendMessage(msg.toString()); 
    }

}
