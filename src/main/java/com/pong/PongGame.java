
//  Class author:  ernest pedregosa delserieys
//  Date created:  12/03/2025
//  General description: This class contains the main game logic for a simple Pong game.
package com.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PongGame extends JPanel implements MouseMotionListener {
    static int width = 640; // this is the amount of pixels to the right side of the screen
    static int height = 480; // this is the amount of pixels to the top of the screen.
    private int userMouseY;
    private Paddle aiPaddle;
    private int playerScore;
    private int aiScore;
    private Ball ball;
    // step 1 add any other private variables you may need to play the game.
    private SlowDown slowArea1;
     private Paddle playerPaddle;
      private Speedup speedArea1;
      private Wall wall1;

    public PongGame() {

        aiPaddle = new Paddle(610, 240, 50, 9, Color.WHITE);
        JLabel pScore = new JLabel("0");
        JLabel aiScore = new JLabel("0");
        pScore.setBounds(280, 440, 20, 20);
        aiScore.setBounds(360, 440, 20, 20);
        pScore.setVisible(true);
        aiScore.setVisible(true);
        userMouseY = 0;
        addMouseMotionListener(this);
        ball = new Ball(200, 200, 10, 3, Color.RED, 10);

        //create any other objects necessary to play the game.
        //create a slow down area
        slowArea1 = new SlowDown(300, 275, 75, 50);
        //create the player paddle
        playerPaddle = new Paddle(10, 240, 50, 9, Color.WHITE);
        //create a speed up area
        speedArea1 = new Speedup(300, 200, 75, 50);
        //create a wall
        wall1 = new Wall (320,75,150,10, Color.WHITE);


    } 
   

    // precondition: None
    // postcondition: returns playerScore
    public int getPlayerScore() {
        return playerScore;
    }

    // precondition: None
    // postcondition: returns aiScore
    public int getAiScore() {
        return aiScore;
    }

    //precondition: All visual components are initialized, non-null, objects 
    //postcondition: A frame of the game is drawn onto the screen.
    public void paintComponent(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        //draw the score at the top of the screen
        g.drawString("The Score is User:" + playerScore + " vs Ai:" + aiScore, 240, 20);
      
        //call the "draw" function of any visual component you'd like to show up on the screen.
         //draw the ball
        ball.draw(g);
        //draw the ai paddle
        aiPaddle.draw(g);
        //draw the slow down area
        slowArea1.draw(g);
        //draw the player paddle
        playerPaddle.draw(g);
        //draw the speed up area
        speedArea1.draw(g);
        //draw the wall
        wall1.draw(g);
    }

    // precondition: all required visual components are intialized to non-null
    // values
    // postcondition: one frame of the game is "played"
    public void gameLogic() {
        //add commands here to make the game play propperly
        ball.moveBall();
        ball.bounceOffwalls(470,0);
        playerPaddle.moveY(userMouseY);
        aiPaddle.moveY(ball.getY());

        if (aiPaddle.isTouching(ball)) {
           ball.reverseX();
           
        }
         if (playerPaddle.isTouching(ball)) {
           ball.reverseX();
           
        }
         if (wall1.isTouching(ball)) {
           ball.reverseX();
        }
        if (slowArea1.isTouching(ball)) {
            ball.setChangeX(ball.getChangeX()/1.2);
        }
        if (speedArea1.isTouching(ball)) {
            ball.setChangeX(ball.getChangeX()*1.2);
        }

        if (ball.getX() <= 0) {
            aiScore++;
            ball.setX(200);
            ball.sety(200);
            ball.moveBall();
        }
        if (ball.getX() >= 650) {
            playerScore++;
            ball.setX(200);
            ball.sety(200);
            ball.moveBall();
        }

 
        pointScored();

    }

    // precondition: ball is a non-null object that exists in the world
    // postcondition: determines if either ai or the player score needs to be
    // updated and re-sets the ball
    // the player scores if the ball moves off the right edge of the screen (640
    // pixels) and the ai scores
    // if the ball goes off the left edge (0)
    public void pointScored() {

    }

    // you do not need to edit the below methods, but please do not remove them as
    // they are required for the program to run.
    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        userMouseY = e.getY();
    }

}