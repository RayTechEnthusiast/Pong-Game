//
//  Class author: King
//  Date created: 12/10/2025
//  General description: This class contains all game logic for a functional Pong game,
//  including paddle control, ball movement, collision detection, scoring,
//  and visual rendering of all game components.
//

package com.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PongGame extends JPanel implements MouseMotionListener {

    static int width = 640;
    static int height = 480;

    private int userMouseY;
    private Paddle aiPaddle;
    private Paddle playerPaddle;
    private Ball ball;
    private int playerScore;
    private int aiScore;

    // Required game elements:
    private SlowDown slowArea1;
    private Speedup speedArea1;
    private Wall wall1;

    public PongGame() {

        aiPaddle = new Paddle(610, 240, 50, 9, Color.WHITE);
        playerPaddle = new Paddle(10, 240, 50, 9, Color.WHITE);

        userMouseY = 0;
        addMouseMotionListener(this);

        ball = new Ball(200, 200, 10, 3, Color.RED, 10);

        slowArea1 = new SlowDown(300, 275, 75, 50);
        speedArea1 = new Speedup(300, 200, 75, 50);
        wall1 = new Wall(320, 75, 150, 10, Color.WHITE);
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getAiScore() {
        return aiScore;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        g.drawString("The Score is User:" + playerScore + " vs Ai:" + aiScore, 240, 20);

        ball.draw(g);
        aiPaddle.draw(g);
        playerPaddle.draw(g);
        slowArea1.draw(g);
        speedArea1.draw(g);
        wall1.draw(g);
    }

    public void gameLogic() {

        ball.moveBall();
        ball.bounceOffwalls(470, 0);

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
            ball.setChangeX(ball.getChangeX() / 1.2);
        }

        if (speedArea1.isTouching(ball)) {
            ball.setChangeX(ball.getChangeX() * 1.2);
        }

        pointScored();
    }

    //
    //  Pre-condition: ball has moved this frame
    //  Post-condition: if ball passes left/right edges, scores update and ball resets
    //
    public void pointScored() {

        // AI scores if ball leaves left side
        if (ball.getX() <= 0) {
            aiScore++;
            ball.setX(200);
            ball.sety(200);
            // reset ball direction toward player
            ball.setChangeX(Math.abs(ball.getChangeX()));
        }

        // Player scores if ball leaves right side
        if (ball.getX() >= 640) {
            playerScore++;
            ball.setX(200);
            ball.sety(200);
            // reset ball direction toward AI
            ball.setChangeX(-Math.abs(ball.getChangeX()));
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // intentionally left blank
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        userMouseY = e.getY();
    }
}
