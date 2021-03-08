/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pieles.maumau.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Julian Pieles
 */
public class CardManager {
    
    private ArrayList<String> cards;
    private ArrayList<String> playerCards;
    private ArrayList<String> computerCards;
    private ArrayList<String> deskCards;
    
    Random rand;
    
    public CardManager() {
        rand = new Random();
        initializeCards();
    }
    
    private void initializeCards() {
        this.cards = generateCards();
        this.playerCards = generatePlayerCards();
        this.computerCards = generatePlayerCards();
        this.deskCards = generateDeskCards();
    }
    
    private ArrayList<String> generateCards() {
        ArrayList<String> cards = new ArrayList<>();
        String[] colors = {"Kreuz", "Pik", "Herz", "Karo"};
        String[] values = {"7", "8", "9", "10", "Bube", "Dame", "König", "Ass"};
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < values.length; j++) {
                cards.add(colors[i] + " " + values[j]);
            }
        }
        return cards;
    }

    private ArrayList<String> generatePlayerCards() {
        ArrayList<String> playerCards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int index = rand.nextInt(cards.size() - 1);
            moveCard(index, cards, playerCards);
        }
        return playerCards;
    }

    private ArrayList<String> generateDeskCards() {
        ArrayList<String> deskCards = new ArrayList<>();
        int index = rand.nextInt(cards.size() - 1);
        moveCard(index, cards, deskCards);
        return deskCards;
    }
    
    public void moveCard(int index, ArrayList<String> list1, ArrayList<String> list2) {
        list2.add(list1.get(index));
        list1.remove(index);
    }

    public void drawCard(ArrayList<String> list1, ArrayList<String> list2) {
        if (list1.size() > 0) {
            int index = rand.nextInt(list1.size() - 1);
            moveCard(index, list1, list2);
        } else {
            System.out.println("Du kannst keine Karte mehr ziehen, der Stapel ist leer");
        }

    }
    
    

    public ArrayList<String> getCards() {
        return cards;
    }

    public void setCards(ArrayList<String> cards) {
        this.cards = cards;
    }

    public ArrayList<String> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(ArrayList<String> playerCards) {
        this.playerCards = playerCards;
    }

    public ArrayList<String> getComputerCards() {
        return computerCards;
    }

    public void setComputerCards(ArrayList<String> computerCards) {
        this.computerCards = computerCards;
    }

    public ArrayList<String> getDeskCards() {
        return deskCards;
    }

    public void setDeskCards(ArrayList<String> deskCards) {
        this.deskCards = deskCards;
    }
}
