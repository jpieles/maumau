/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pieles.maumau.tui;

import java.util.ArrayList;

/**
 *
 * @author Julian Pieles
 */
public class TUI {
    
    public static void showTitleScreen() {
        System.out.println(" __   __  _______  __   __    __   __  _______  __   __ \n"
                + "|  |_|  ||   _   ||  | |  |  |  |_|  ||   _   ||  | |  |\n"
                + "|       ||  |_|  ||  | |  |  |       ||  |_|  ||  | |  |\n"
                + "|       ||       ||  |_|  |  |       ||       ||  |_|  |\n"
                + "|       ||       ||       |  |       ||       ||       |\n"
                + "| ||_|| ||   _   ||       |  | ||_|| ||   _   ||       |\n"
                + "|_|   |_||__| |__||_______|  |_|   |_||__| |__||_______|");
    }
    
    public static void showRules() {
        System.out.println("Jeder Spieler erhält 5 Karten. Eine Karte wird in die Mitte gelegt\n"
                + "der Rest kommt auf den Nachziehstapel");
        System.out.println("Jeder Spieler legt abwechselnd eine Karte von der Hand in die Mitte\n"
                + "Die Karte muss, entweder die gleiche Farbe oder den gleichen Wert haben");
        System.out.println("Hat man keine passende Karte, wird eine vom Nachziehstapel gezogen\n"
                + "das ganze geht so lang, bis der erste Spieler keine Karten mehr hat\n"
                + "und damit gewinnt :)");
    }
    
    public static void showPlayerCards(ArrayList<String> cards) {
        System.out.println("Deine Karten:");
        for(int i = 0; i < cards.size(); i++) {
            System.out.println("[" + (i+1) + "] " + cards.get(i));
        }
        
    }
    
    public static void showLayingCard(ArrayList<String> cards) {
        System.out.println("================================================================================");
        System.out.print("Es liegt: ");
        System.out.println(cards.get(cards.size() - 1));
        System.out.println("================================================================================");
    }
    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void showWinningText(ArrayList<String> cards) {
        if(cards.size() == 0) {
            System.out.println("Herzlichen Glückwunsch, du hast gewonnen!");
        }else {
            System.out.println("Schade, du hast leider verloren");
        }
    }
    
}
