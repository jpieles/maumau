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

            playerTurn(scanner.nextInt());
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
            } else {
                System.out.println("Du kannst diese Karte nicht legen, nimm eine andere");
            }

        }
    }

    private void computerTurn() {
        ArrayList<String> possibleCards = checkForMatch(ListUtil.getLast(manager.getDeskCards()), manager.getComputerCards());
        if (possibleCards.size() > 0) {
            int index = 0;
            if (possibleCards.size() == 1) {
                index = 0;
            }
            if (possibleCards.size() > 1) {
                index = rand.nextInt(possibleCards.size() - 1);
            }

            System.out.println(" >> Computer legt " + possibleCards.get(index));
            manager.getComputerCards().remove(possibleCards.get(index));
            manager.moveCard(index, possibleCards, manager.getDeskCards());
        } else {
            manager.drawCard(manager.getCards(), manager.getComputerCards());
            System.out.println(" >> Computer zieht " + ListUtil.getLast(manager.getComputerCards()));
        }
    }

    private ArrayList<String> checkForMatch(String card, ArrayList<String> computerCards) {
        ArrayList<String> possibleCards = new ArrayList<>();
        for (String computerCard : computerCards) {
            if (check(card, computerCard)) {
                possibleCards.add(computerCard);
            }
        }
        return possibleCards;
    }

    private boolean check(String card, String card2) {
        String[] cardValues = card.split(" ");
        String[] card2Values = card2.split(" ");
        return cardValues[0].equals(card2Values[0]) || cardValues[1].equals(card2Values[1]);
    }

}
