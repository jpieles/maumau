package org.pieles.maumau.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Julian Pieles
 */
public class ComputerPlayer {
    
    private static ArrayList<String> possibleCards;
    
    /**
     * Liefert den Index der Karte, die der Computer spielen will oder -1 wenn er zieht<br />
     * gelegte Karten in der Stapel mit den abgelegten Karten - da der Computer ein gutes<br />
     * Gedächtnis hat, kennt er nicht nur die oberste sondern hat sich alle gemerkt
     * @param kartenComputer
     * @param gelegteKarten
     * @param gewuenschteFarbe
     * @return index
     */
    public static int computerSpieltKarte(ArrayList<String> kartenComputer, ArrayList<String> gelegteKarten, String gewuenschteFarbe) {
        Random rand = new Random();
        possibleCards = checkForMatch(gelegteKarten.get(gelegteKarten.size() - 1), kartenComputer, gewuenschteFarbe);
        if (possibleCards.size() > 0) {
            int index = 0;
            if (possibleCards.size() == 1) {
                index = 0;
            }
            if (possibleCards.size() > 1) {
                index = rand.nextInt(possibleCards.size() - 1);
            }
            if(possibleCards.get(index).contains("Bube")) {
                computerWuenschtFarbe();
            }
            return kartenComputer.indexOf(possibleCards.get(index));
            
        } 
        return -1;
    }
    
    /**
     * Wird aufgerufen, wenn der Computer einen Buben gespielt hat, liefert die Farbe<br />
     * die er sich wünscht
     * @return farbe
     */
    public static String computerWuenschtFarbe() {
        String farbe = getMostFrequentCard(possibleCards);
        System.out.println("Der Computer wünscht sich eine Karte der Farbe [" + farbe + "].");
        return farbe;
    }
    
    private static String getMostFrequentCard(ArrayList<String> possibleCards) {
        int[] freq = new int[4];
        
        for (String card : possibleCards) {
            if(card.contains("Kreuz"))
                freq[0]++;
            if(card.contains("Pik"))
                freq[1]++;
            if(card.contains("Herz"))
                freq[2]++;
            if(card.contains("Karo"))
                freq[3]++;
        }
        if(freq[0] > freq[1] && freq[0] > freq[2] && freq[0] > freq[3])
            return "Kreuz";
        if(freq[1] > freq[0] && freq[1] > freq[2] && freq[1] > freq[3])
            return "Pik";
        if(freq[2] > freq[0] && freq[2] > freq[1] && freq[2] > freq[3])
            return "Herz";
        if(freq[3] > freq[0] && freq[3] > freq[1] && freq[3] > freq[2])
            return "Karo";
        
        return "Kreuz";
    }
    
    
    private static ArrayList<String> checkForMatch(String card, ArrayList<String> computerCards, String color) {
        ArrayList<String> possibleCards = new ArrayList<>();
        for (String computerCard : computerCards) {
            if (check(card, computerCard, color)) {
                possibleCards.add(computerCard);
            }
        }
        return possibleCards;
    }

    /**
     * Wenn bereits ein Bube liegt, darf kein Bube gelegt werden -> nur true, wenn die gelegte Karte kein Bube ist<br />
     * Ein Bube, kann auf jede Karte gelegt werden -> return true<br /><br />
     * 
     * Wenn es sich nicht um einen Buben handelt wird überprüft,<br />
     * ob die Karten gleich sind, und der gewünschten Farbe entsprechen
     * 
     * @param card
     * @param card2
     * @return gibt zurück, ob die ausgewählte Karte gelegt werden darf
     */
    private static boolean check(String playerCard, String deskCard, String color) {
        String[] cardValues = playerCard.split(" ");
        String[] card2Values = deskCard.split(" ");
        if(cardValues[0].equals("Bube") && !card2Values[0].equals("Bube")) {
            return true;
        }
        
        boolean equal = (cardValues[0].equals(card2Values[0]) || cardValues[1].equals(card2Values[1]));
        
        if(!color.equals("")) {
            equal =  equal && cardValues[0].equals(color);
        }
        
        return equal;
    }
}
