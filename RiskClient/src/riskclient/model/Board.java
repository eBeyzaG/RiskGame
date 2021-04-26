/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskclient.model;

import java.util.ArrayList;

/**
 *
 * @author beyza
 */
public class Board {
    
    ArrayList<Region> regions;

    public Board() {
        
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
        
        
        
        
    }
    
    
    
}
