/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author beyza
 */
public class ServerClient {

    Server server;
    int clientId;
    Socket socket;
    ObjectOutputStream server_client_output;
    ObjectInputStream client_input;
    ServerListeningClient server_listening_client;
    boolean isConnected;
    boolean isPaired;
    Game game;
    ServerClient pair;
    int wanted_pair_id = -1;

    public ServerClient(Socket socket, Server server) throws IOException {

        this.server = server;
        this.socket = socket;
        this.clientId = Server.client_id_count++;

        this.server_client_output = new ObjectOutputStream(this.socket.getOutputStream());
        this.client_input = new ObjectInputStream(this.socket.getInputStream());

        this.server_listening_client = new ServerListeningClient(this);
        this.isConnected = false;
        this.isPaired = false;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    void listen() {
        this.isConnected = true;
        this.server_listening_client.start();

    }

    void sendMessage(Object msg) {

        if (this.socket.isConnected()) {

            try {
                server_client_output.writeObject(msg);
            } catch (IOException ex) {
                Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    void close() {
        try {
            this.isConnected = false;

            this.socket.close();
            this.client_input.close();
            this.server_client_output.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPair(ServerClient pair) {
        this.pair = pair;
    }

    public void setIsPaired(boolean isPaired) {
        this.isPaired = isPaired;
    }

    public void setWanted_pair_id(int wanted_pair_id) {
        this.wanted_pair_id = wanted_pair_id;
    }

}

class ServerListeningClient extends Thread {

    ServerClient server_client;

    public ServerListeningClient(ServerClient server_client) {
        this.server_client = server_client;
    }

    void assign_wanted_pair(int id) {
        this.server_client.setWanted_pair_id(id);
    }

    @Override
    public void run() {
        while (this.server_client.isConnected) {//listens messages from client 
            //and determines what to do according to message type
            System.out.println("Waiting for a message from client");

            Object receivedText;
            try {
                receivedText = this.server_client.client_input.readObject();
                ReceiveMessage msg = new ReceiveMessage(receivedText.toString(), this.server_client.clientId);

                switch (msg.type) {

                    case "pair":
                        System.out.println(msg.toString());
                        assign_wanted_pair(msg.wanted_pair_id);
                        break;
                    case "first_part":
                        this.server_client.game.forwardMessage(receivedText, server_client.pair);
                        break;
                    case "locate_troops":
                        this.server_client.game.forwardMessage(receivedText, server_client.pair);
                        break;
                    case "boardInfo":
                        this.server_client.game.forwardMessage(receivedText, server_client.pair);
                        break;
                    default:
                        System.out.println("Server: " + msg.toString());
                         this.server_client.game.forwardMessage(receivedText, server_client.pair);
 
                        System.out.println("Message type not supported.");
                        break;

                }

            } catch (IOException ex) {
                this.server_client.server.removeClient(server_client);
                Logger.getLogger(ServerListeningClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                this.server_client.server.removeClient(server_client);
                Logger.getLogger(ServerListeningClient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
