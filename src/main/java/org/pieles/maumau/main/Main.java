/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pieles.maumau.main;

import org.pieles.maumau.logic.Game;
import org.pieles.maumau.tui.TUI;
import java.util.Scanner;

/**
 *
 * @author Julian Pieles
 */
public class Main {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        TUI.showTitleScreen();
        TUI.showRules();
        
        do {
            System.out.println("Um zu spielen, musst du y drücken");
        }while(!scan.next().equals("y"));
        
        TUI.clearScreen();
        
        do {
            Game game = new Game();
            System.out.println("Willst du nochmal spielen? (y/n)");
        }while(!scan.next().equals("n"));
    }
    
}
