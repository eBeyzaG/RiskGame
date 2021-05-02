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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import riskclient.model.Board;
import riskclient.model.Region;

/**
 *
 * @author beyza
 */
public class Client {

    static int turn_count = 0;
    static String game_part = "choose_regions";

    Socket socket;
    ObjectInputStream server_input;
    ObjectOutputStream client_output;
    ClientListeningServer client_listening_server;

    int clientId = -1;
    String username;
    ArrayList<Region> owned_regions;
    int total_troop_count = 20;
    Board board;
    ClientFrame cf;

    String serverIP;
    int serverPort;
    boolean isConnected;

    public Client(String serverIp, int serverPort) {

        this.serverIP = serverIp;
        this.serverPort = serverPort;
        this.isConnected = false;
        this.board = new Board();

        this.connect();
        this.start();

    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    void startFrame() {

        java.awt.EventQueue.invokeLater(new Runnable() {
            Client cli;

            public void run() {
                cli.cf = new ClientFrame(cli);
                cli.cf.setVisible(true);
            }

            public Runnable init(Client cl) {
                this.cli = cl;
                return this;
            }
        }.init(this));
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

    void sendMessage(Object msg) {

        if (this.socket.isConnected()) {
            try {

                client_output.writeObject(msg.toString());
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    void attack(String info) {

    }

    void defend(Region r) {
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
        this.client.startFrame();

        while (this.client.socket.isConnected()) {

            try {
                receivedText = this.client.server_input.readObject();
                ReceiveMessage msg = new ReceiveMessage(receivedText.toString());

                switch (msg.type) {
                    case "clientId":
                        this.client.setClientId(msg.client_id_for_connection);
                        System.out.println("client id: " + this.client.clientId);
                        break;
                    case "msg":
                        this.client.cf.setMessageBox(msg.message);
                        System.out.println("Message: " + msg.message);
                        break;
                    case "first_part":
                        this.client.cf.setMessageBox("Your turn to choose a region.");
                        this.client.cf.chooseButton.setEnabled(true);
                        this.client.board.updateBoard(msg.chosen_region_name, 1, "rival");
                        this.client.cf.load_region_list();
                        this.client.cf.update_images();
                        System.out.println(msg.toString());
                        if (this.client.board.is_all_full()) {//all regions are occupied
                            this.client.cf.start_second_part();
                            System.out.println("BİTTİ Mİ");
                        }
                        break;
                    case "boardInfo":
                        this.client.board.receive_board_info(msg.board_info, this.client.clientId);
                        this.client.cf.start_attack_part();
                        break;
                    case "attack":
                        this.client.cf.defend(this.client.board.receive_board_info(msg.board_info, this.client.clientId));
                        break;
                    case "defend":
                        this.client.board.receive_board_info(msg.board_info, this.client.clientId);
                        this.client.cf.start_attack_part();
                        break;
                    case "start_turn":
                        this.client.board.receive_board_info(msg.board_info, this.client.clientId);
                        this.client.cf.second_turn();
                        break;
                    case "locate_troops": {
                        if (Client.game_part.equals("locate_troops")) {
                            this.client.board.receive_board_info(msg.board_info, this.client.clientId);
                            this.client.cf.start_attack_part();
                        } else {
                            this.client.board.receive_board_info(msg.board_info, this.client.clientId);
                            this.client.cf.start_second_part();
                        }
                        break;
                    }
                    case "game_over":
                        this.client.cf.setMessageBox("You " + msg.message);
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
