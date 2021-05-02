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
public class GameMessage implements Serializable{
    
    static enum Message_Type {msg, pair, clientId, boardInfo, first_part}
    
    Message_Type type;
    Object content;

    public GameMessage(Message_Type type) {
        this.type = type;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }
    
    
    
    
}
