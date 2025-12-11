package com.pong;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class World {

    public static void main(String[] args) {
        // Create the JFrame
        JFrame f = new JFrame("Pong");

        // Make program exit when close button clicked
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set the size slightly larger than game area
        f.setSize(650, 495);

        // Create PongGame object
        PongGame game = new PongGame();

        // Add PongGame to the JFrame
        f.add(game);

        // Show the window after adding the game
        f.setVisible(true);

        // Timer to update game logic and repaint every ~33ms (~30 FPS)
        Timer timer = new Timer(33, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.gameLogic(); // call the game logic
                game.repaint();   // redraw the game
            }
        });

        // Start the game loop
        timer.start();
    }
}
