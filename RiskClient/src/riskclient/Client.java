/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author beyza
 */
public class Client {

    Socket socket;
    ObjectInputStream server_input;
    ObjectOutputStream client_output;
    ClientListeningServer client_listening_server;

    String serverIP;
    int serverPort;
    boolean isConnected;
    
    int clientId;

    public Client(String serverIp, int serverPort) {

        this.serverIP = serverIp;
        this.serverPort = serverPort;
        this.isConnected = false;
        

        this.connect();
        this.start();

    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    
    void connect() {
        try {
            System.out.println("Connecting...");

            this.socket = new Socket(this.serverIP, this.serverPort);

            System.out.println("Connected");

            this.client_output = new ObjectOutputStream(this.socket.getOutputStream());
            this.server_input = new ObjectInputStream(this.socket.getInputStream());

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void start() {

        this.client_listening_server = new ClientListeningServer(this);
        this.isConnected = true;
        this.client_listening_server.start();

    }

    void close() {
        try {
            this.isConnected = false;
            this.socket.close();
            this.client_output.close();
            this.server_input.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void sendMessage(Object msg){
        
        
        if(this.socket.isConnected()){
            try {
                
                client_output.writeObject(msg.toString());
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    
    }
}

class ClientListeningServer extends Thread {

    Client client;

    public ClientListeningServer(Client client) {
        this.client = client;
    }

    @Override
    public void run() {

        Object receivedText = "";

        while (this.client.socket.isConnected()) {

            try {
                receivedText = this.client.server_input.readObject();
                ReceiveMessage msg = new ReceiveMessage(receivedText.toString());
                
                switch (msg.type){
                    
                    case "clientId":
                        this.client.setClientId(msg.client_id_for_connection);
                        System.out.println("client id: " + this.client.clientId);
                        break;
                    case "msg":
                        System.out.println(msg.message);
                        break;
                    default:
                        System.out.println("Unknown message type.");
                        break;
                
                }
                
            } catch (IOException ex) {
                this.client.close();
                Logger.getLogger(ClientListeningServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                this.client.close();
                Logger.getLogger(ClientListeningServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
