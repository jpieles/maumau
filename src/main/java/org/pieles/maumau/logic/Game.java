/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pieles.maumau.logic;

import org.pieles.maumau.tui.TUI;
import org.pieles.maumau.util.ListUtil;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Julian Pieles
 */
public class Game {

    Random rand;
    Scanner scanner;
    CardManager manager;
    String color = "";
    int toDraw = 0;

    public Game() {
        rand = new Random();
        scanner = new Scanner(System.in);
        manager = new CardManager();
        gameLoop();
    }

    private void gameLoop() {
        boolean gewonnen = false;
        do {

            TUI.showPlayerCards(manager.getPlayerCards());
            TUI.showLayingCard(manager.getDeskCards());
            System.out.println("Welche Karte spielst Du [0 für nachziehen] ?");

            playerTurn(Integer.parseInt(scanner.nextLine()));
            computerTurn();

            if (manager.getPlayerCards().size() == 0 || manager.getComputerCards().size() == 0) {
                gewonnen = true;
            }

        } while (!gewonnen);

        TUI.showWinningText(manager.getPlayerCards());

    }

    private void playerTurn(int input) {
        if (input == 0) {
            manager.drawCard(manager.getCards(), manager.getPlayerCards());
            System.out.println("Du ziehst " + ListUtil.getLast(manager.getPlayerCards()));
        }
        if (input > 0 && input <= manager.getPlayerCards().size()) {
            if (check(manager.getPlayerCards().get(input - 1), ListUtil.getLast(manager.getDeskCards()))) {
                manager.moveCard(input - 1, manager.getPlayerCards(), manager.getDeskCards());
                System.out.println("Du legst " + ListUtil.getLast(manager.getDeskCards()));
                if(ListUtil.getLast(manager.getDeskCards()).contains("Bube")) {
                    System.out.println("Du darfst dir eine Farbe wünschen [Kreuz] [Pik] [Herz] [Karo]");
                    cardWish(scanner.nextLine());
                    
                }
            } else {
                System.out.println("Du kannst diese Karte nicht legen, nimm eine andere");
            }

        }

    }

    private void computerTurn() {
        int index = ComputerPlayer.computerSpieltKarte(manager.getComputerCards(), manager.getDeskCards(), color);
        if(index == -1) {
            manager.drawCard(manager.getCards(), manager.getComputerCards());
            System.out.println(" >> Computer zieht " + ListUtil.getLast(manager.getComputerCards()));
        } else {
            System.out.println(" >> Computer legt " + manager.getComputerCards().get(index));
            manager.moveCard(index, manager.getComputerCards(), manager.getDeskCards());
            color = "";
            if(manager.getDeskCards().equals("Bube")) {
                this.color = ComputerPlayer.computerWuenschtFarbe();
            }
        }
    }
    
    private void cardWish(String color) {
        this.color = color;
        System.out.println("Der nächste Spieler muss eine Karte der Farbe [" + color + "] legen");
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
    private boolean check(String playerCard, String deskCard) {
        String[] cardValues = playerCard.split(" ");
        String[] card2Values = deskCard.split(" ");
        if(cardValues[0].equals("Bube") && !card2Values[0].equals("Bube")) {
            return true;
        }
        
        boolean equal = (cardValues[0].equals(card2Values[0]) || cardValues[1].equals(card2Values[1]));
        
        if(!color.equals("")) {
            equal =  equal && cardValues[0].equals(color);
            this.color = "";
        }
        
        return equal;
    }

}
