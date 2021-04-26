/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskserver;

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

    void start_game() {

        SendMessage newMsg = new SendMessage("msg");
        newMsg.setMessage("Paired");

        player1.sendMessage(newMsg.toString());
        player2.sendMessage(newMsg.toString());
        
        player1.setGame(this);
        player2.setGame(this);

    }
    
    void forwardMessage(){
        
        
        
    }

}
