/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author beyza
 */
public class Server {

    public static int client_id_count = 0;
    public static int game_id_count = 0;
    ServerSocket serverSocket;

    ArrayList<ServerClient> all_clients;
    ArrayList<ServerClient> non_paired_clients;
    ArrayList<Game> games;
    
    
    ClientAcceptThread client_accept_thread;
    PairThread pair_thread;

    public Semaphore pair = new Semaphore(1, true);
    public Semaphore pair2 = new Semaphore(1, true);
    
    
    int port;

    public Server(int port) throws IOException {

        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.client_accept_thread = new ClientAcceptThread(this);
        this.all_clients = new ArrayList<>();
        
        this.games = new ArrayList<>();

        this.non_paired_clients = new ArrayList<>();
        this.pair_thread = new PairThread(this);

        this.listen_for_connection();
        this.pair();
    }

    void listen_for_connection() {
        this.client_accept_thread.start();
    }

    void pair() {
        this.pair_thread.start();
    }

    synchronized void removeClient(ServerClient client) {
        for (int i = 0; i < this.all_clients.size(); i++) {

            if (this.all_clients.get(i).clientId == client.clientId) {

                this.all_clients.get(i).close();
                this.all_clients.remove(i);
                break;
            }

        }

    }

    void sendClientMessage(Object msg, int cliId) {

        for (ServerClient sc : this.all_clients) {
            if (sc.clientId == cliId) {
                sc.sendMessage(msg);
                break;
            }

        }

    }

}

class ClientAcceptThread extends Thread {

    Server server;

    public ClientAcceptThread(Server server) {

        this.server = server;

    }

    @Override
    public void run() {

        while (!this.server.serverSocket.isClosed()) {

            System.out.println("Waiting for clients...");
            try {
                Socket socket_for_client = this.server.serverSocket.accept(); //wait till a client connects 

                System.out.println("A client has connected.");

                ServerClient newClient = new ServerClient(socket_for_client, this.server);
                newClient.listen();
                SendMessage msg = new SendMessage("clientId");
                msg.setClient_id_for_connection(newClient.clientId);
                newClient.sendMessage(msg.toString());
                this.server.all_clients.add(newClient);
                try {
                    this.server.pair.acquire(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClientAcceptThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.server.non_paired_clients.add(newClient);
                 this.server.pair.release(1);

            } catch (IOException ex) {
                Logger.getLogger(ClientAcceptThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}

class PairThread extends Thread {

    Server server;

    public PairThread(Server server) {

        this.server = server;
    }

    @Override
    public void run() {
        
        
        while (!this.server.serverSocket.isClosed()) {

            for (int i = 0; i < this.server.non_paired_clients.size(); i++) {
                
                if(!this.server.all_clients.contains(this.server.non_paired_clients.get(i))){
                    this.server.non_paired_clients.remove(i);
                    continue;
                }
                
                
                System.out.println(this.server.non_paired_clients.get(i).clientId + " : " + this.server.non_paired_clients.get(i).wanted_pair_id);
                
                for (int j = 0; j < this.server.non_paired_clients.size(); j++) {
                    
                    ServerClient sc = this.server.non_paired_clients.get(i);
                    ServerClient pair_sc = this.server.non_paired_clients.get(j);

                    
                    if (sc.wanted_pair_id == pair_sc.clientId
                            && pair_sc.wanted_pair_id == sc.clientId) {
                        
                        sc.setPair(pair_sc);
                        sc.setIsPaired(true);
                        pair_sc.setPair(sc);
                        pair_sc.setIsPaired(true);
                        
                        System.out.println(sc.clientId  + " is paired with " + pair_sc.clientId);
                        
                        
                        Game newGame = new Game(sc, pair_sc);
                        this.server.games.add(newGame);
                        
                       
                        this.server.non_paired_clients.remove(sc);
                        this.server.non_paired_clients.remove(pair_sc);
                        break;
                    }

                }
                
                try {
                    sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PairThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

}
