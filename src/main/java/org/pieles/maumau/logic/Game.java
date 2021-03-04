/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pieles.maumau.logic;

import org.pieles.maumau.tui.TUI;
import org.pieles.maumau.util.Util;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Julian Pieles
 */
public class Game {

    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);

    private ArrayList<String> cards;
    private ArrayList<String> playerCards;
    private ArrayList<String> computerCards;
    private ArrayList<String> deskCards;

    public Game() {
        init();
        gameLoop();
    }

    private void gameLoop() {
        boolean gewonnen = false;
        do {

            TUI.showPlayerCards(playerCards);
            TUI.showLayingCard(deskCards);
            System.out.println("Welche Karte spielst Du [0 für nachziehen] ?");
            int input = scanner.nextInt();

            playerTurn(input);
            computerTurn();

            if (playerCards.size() == 0 || computerCards.size() == 0) {
                gewonnen = true;
            }


        } while (!gewonnen);

        TUI.showWinningText(playerCards);

    }

    private void playerTurn(int input) {
        if (input == 0) {
            drawCard(cards, playerCards);
            System.out.println("Du ziehst " + playerCards.get(playerCards.size() - 1));
        }
        if (input > 0 && input <= playerCards.size()) {
            if (check(playerCards.get(input - 1), Util.getLast(deskCards))) {
                moveCard(input - 1, playerCards, deskCards);
                System.out.println("Du legst " + deskCards.get(deskCards.size() - 1));
            } else {
                System.out.println("Du kannst diese Karte nicht legen, nimm eine andere");
            }

        }
    }

    private void computerTurn() {
        ArrayList<String> possibleCards = checkForMatch(deskCards.get(deskCards.size() - 1), computerCards);
        if (possibleCards.size() > 0) {
            int index = 0;
            if (possibleCards.size() == 1) {
                index = 0;
            }
            if (possibleCards.size() > 1) {
                index = rand.nextInt(possibleCards.size() - 1);
            }

            System.out.println(" >> Computer legt " + possibleCards.get(index));
            computerCards.remove(possibleCards.get(index));
            moveCard(index, possibleCards, deskCards);
        } else {
            drawCard(cards, computerCards);
            System.out.println(" >> Computer zieht " + Util.getLast(computerCards));
        }
    }

    private void init() {
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

    private void moveCard(int index, ArrayList<String> list1, ArrayList<String> list2) {
        list2.add(list1.get(index));
        list1.remove(index);
    }

    private void drawCard(ArrayList<String> list1, ArrayList<String> list2) {
        if (list1.size() > 1) {
            int index = rand.nextInt(list1.size() - 1);
            moveCard(index, list1, list2);
        } else {
            System.out.println("Du kannst keine Karte mehr ziehen, der Stapel ist leer");
        }

    }

    private ArrayList<String> checkForMatch(String card, ArrayList<String> computerCards) {
        ArrayList<String> possibleCards = new ArrayList<String>();
        for (int i = 0; i < computerCards.size() - 1; i++) {
            String[] cardV = card.split(" ");
            String[] cardAv = computerCards.get(i).split(" ");
            if (cardV[0].equals(cardAv[0]) || cardV[1].equals(cardAv[1])) {
                possibleCards.add(computerCards.get(i));
            }
        }
        return possibleCards;
    }

    private boolean check(String card, String card2) {
        String[] cardV = card.split(" ");
        String[] cardAv = card2.split(" ");
        if (cardV[0].equals(cardAv[0]) || cardV[1].equals(cardAv[1])) {
            return true;
        }
        return false;
    }

}
