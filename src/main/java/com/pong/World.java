package com.pong;
import java.awt.Color;
import java.awt.event.*;


import javax.swing.*;
public class World {
 
 

//make a PongGame and then add it to the JFrame
public static void main(String[] args) {
	   JFrame f = new JFrame("Pong");
	  
	  
	   
		//make it so program exits on close button click
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		//the size of the game will be 480x640, the size of the JFrame needs to be slightly larger
        f.setSize(650,495);

		//show the window
        f.setVisible(true);
        
        
        PongGame game = new PongGame();
        f.add(game);
        f.setVisible(true);
        
        Timer timer = new Timer(33, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.gameLogic();
                game.repaint();

            }
        });

        //start the timer after it's been created
        timer.start();

    }
       
        
        
	}