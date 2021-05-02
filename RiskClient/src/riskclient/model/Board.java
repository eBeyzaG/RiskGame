/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskclient.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author beyza
 * Game board class to hold board information and methods
 */
public class Board {

    ArrayList<Region> regions;
    static ArrayList<BufferedImage> region_images;

    public Board() {

        this.regions = new ArrayList<>();
        this.region_images = new ArrayList<>();
        init_board();
    }

    void init_board() { //initializes board regions

        Region rA = new Region("A");
        Region rB = new Region("B");
        Region rC = new Region("C");
        Region rD = new Region("D");
        Region rE = new Region("E");
        Region rF = new Region("F");
        Region rG = new Region("G");
        Region rH = new Region("H");
        Region rI = new Region("I");
        Region rJ = new Region("J");

        ArrayList<Region> aNeighbors = new ArrayList<>();
        aNeighbors.add(rB);
        aNeighbors.add(rC);
        rA.setNeighbor_regions(aNeighbors);

        ArrayList<Region> bNeighbors = new ArrayList<>();
        bNeighbors.add(rA);
        bNeighbors.add(rC);
        bNeighbors.add(rD);
        rB.setNeighbor_regions(bNeighbors);

        ArrayList<Region> cNeighbors = new ArrayList<>();
        cNeighbors.add(rA);
        cNeighbors.add(rB);
        cNeighbors.add(rD);
        cNeighbors.add(rG);
        cNeighbors.add(rH);
        rC.setNeighbor_regions(cNeighbors);

        ArrayList<Region> dNeighbors = new ArrayList<>(); //B C E F
        dNeighbors.add(rB);
        dNeighbors.add(rC);
        dNeighbors.add(rE);
        dNeighbors.add(rF);
        rD.setNeighbor_regions(dNeighbors);

        ArrayList<Region> eNeighbors = new ArrayList<>(); // D I F
        eNeighbors.add(rD);
        eNeighbors.add(rI);
        eNeighbors.add(rF);
        rE.setNeighbor_regions(eNeighbors);

        ArrayList<Region> fNeighbors = new ArrayList<>(); // E I D
        fNeighbors.add(rE);
        fNeighbors.add(rI);
        fNeighbors.add(rD);
        rF.setNeighbor_regions(fNeighbors);

        ArrayList<Region> gNeighbors = new ArrayList<>(); //H C J
        gNeighbors.add(rH);
        gNeighbors.add(rC);
        gNeighbors.add(rJ);
        rG.setNeighbor_regions(gNeighbors);

        ArrayList<Region> hNeighbors = new ArrayList<>(); //G I J C
        hNeighbors.add(rG);
        hNeighbors.add(rI);
        hNeighbors.add(rJ);
        hNeighbors.add(rC);
        rH.setNeighbor_regions(hNeighbors);

        ArrayList<Region> iNeighbors = new ArrayList<>(); // H J E F
        iNeighbors.add(rH);
        iNeighbors.add(rJ);
        iNeighbors.add(rE);
        iNeighbors.add(rF);
        rI.setNeighbor_regions(iNeighbors);

        ArrayList<Region> jNeighbors = new ArrayList<>(); //G I H
        jNeighbors.add(rG);
        jNeighbors.add(rH);
        jNeighbors.add(rI);
        rJ.setNeighbor_regions(jNeighbors);

        this.regions.add(rA);
        this.regions.add(rB);
        this.regions.add(rC);
        this.regions.add(rD);
        this.regions.add(rE);
        this.regions.add(rF);
        this.regions.add(rG);
        this.regions.add(rH);
        this.regions.add(rI);
        this.regions.add(rJ);

    }

    public ArrayList<Region> getRegions() {
        return regions;
    }

    public void updateBoard(String regionName, int newTroopCount, String owner) {
        //updates the board information with given parameters

        for (Region region : this.regions) {
            if (region.name.equals(regionName)) {
                region.setTroop_count(newTroopCount);
                region.setOwner(owner);
                if (owner.equals("rival")) {
                    this.region_images.add(region.blue_img);
                } else if (owner.equals("player")) {
                    this.region_images.add(region.red_img);
                }
            }
        }

    }

    public ArrayList<BufferedImage> getRegion_images() {
        return region_images;
    }

    public String receive_board_info(String boardInfo, int clientId) {
        //converts string to board information and updates the board
        //returns attack info 

        String[] board = boardInfo.split("/");
        String[] regs = board[0].split("-");

        for (String info : regs) {
            String[] parts = info.split(",");
            if (parts[1].equals("senders")) {
                updateBoard(parts[0], Integer.parseInt(parts[2]), "rival");
            } else {
                updateBoard(parts[0], Integer.parseInt(parts[2]), "player");
            }

        }
        return board[1];
    }

    public String send_board_info(int clientId, String attacked_region, int attacked_count, String attacking_region) {
        //prepares the info about the board as a string
        String info = "";

        for (Region r : this.regions) {

            if (r.getOwner().equals("player")) {
                info += r.name + "," + "senders" + "," + r.troop_count + "-";
            } else {
                info += r.name + "," + "players" + "," + r.troop_count + "-";
            }

        }
        info += "/" + attacked_region + "," + attacked_count + "," + attacking_region;

        return info;
    }

    public boolean is_all_full() {//checks whether all the regions are occupied

        for (Region r : regions) {
            if (r.getTroop_count() == 0) {
                return false;
            }
        }

        return true;
    }

    public String fight(Region attacking, int attacking_count, Region attacked, int defendCount) {
        //when a region is attacked calculates the result of the fight
        //least number of common troop count is the comparison count
        //largest amounts out of the random numbers are compared
        String result = "";
        Random r = new Random();

        List<Integer> attacker = new ArrayList<>();

        for (int i = 0; i < attacking_count; i++) {
            attacker.add(r.nextInt(6) + 1);
        }

        List<Integer> defender = new ArrayList<>();

        for (int i = 0; i < defendCount; i++) {
            defender.add(r.nextInt(6) + 1);

        }

        Collections.sort(attacker);
        Collections.reverse(attacker);
        Collections.sort(defender);
        Collections.reverse(defender);

        int compCount = defendCount < attacking_count ? defendCount : attacking_count;

        for (int i = 1; i <= compCount; i++) {
            if (attacker.get(i - 1) > defender.get(i - 1)) {
                attacked.setTroop_count(attacked.getTroop_count() - 1);
            } else {
                attacking.setTroop_count(attacking.getTroop_count() - 1);
                attacking_count--;
            }

            if (attacked.getTroop_count() == 0) {
                attacked.setOwner("rival");
                attacked.setTroop_count(attacking_count);
                Board.region_images.add(attacked.blue_img);

                break;
            }
        }
        result = "Attack finished.";

        return result;

    }

    public String check_finish() {
        //checks wheter all regions are occupied by one person meaning the game ended
        String winner = "";
        if (regions.get(0).getOwner().equals("player")) {
            winner = "player";
            for (Region r : regions) {
                if (!r.getOwner().equals("player")) {
                    winner = "";
                }
            }
        } else if (regions.get(0).getOwner().equals("rival")) {
            winner = "rival";
            for (Region r : regions) {
                if (!r.getOwner().equals("rival")) {
                    winner = "";
                }
            }
        }
        return winner;
    }
    
    public void restart_board(){
        //restarts board info
    
        for(Region r: regions){
            r.setOwner("");
            r.setTroop_count(0);
        }
        region_images.clear();
        
    }
}
