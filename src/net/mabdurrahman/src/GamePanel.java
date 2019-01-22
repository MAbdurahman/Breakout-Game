package net.mabdurrahman.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The GamePanel Class is a simulation of the Breakout Game developed by Atari, Inc.  However, in
 * this game, the player is given one ball per game.  A layer of bricks line the top of the screen.
 * The ball travels across the screen, bouncing of the top and sides of the screen. The player has
 * a horizontal movable paddle to bounce the ball upward to keep it in play.  If the player misses
 * the ball or if all the bricks are removed, the game is over.
 * @author:  MAbdurrahman
 * @date:  19 April 2017
 * @version:  1.0.0
 */
public class GamePanel extends JPanel {
    //Instance Variables
    private static final Font GAME_FONT = new Font("Montserrat Alternates", Font.PLAIN, 40);
    private static final Color GAME_COLOR = new Color(255, 255, 212);
    private static final Color COLOR = new Color(54, 54, 52);
    private static final int ROWS = 5;
    private static final int COLS = 18;
    private final int delay;
    private Timer timer;
    protected Ball ball;
    protected Paddle paddle;
    protected BrickMaker bricks;
    protected boolean isPlaying;
    protected int score;
    protected int totalBricks;

    /**
     * GamePanel Constructor - Creates an instance of GamePanel
     */
    public GamePanel() {
        ball = new Ball(this);
        paddle = new Paddle(this);
        bricks = new BrickMaker(ROWS, COLS);
        score = 0;
        totalBricks = (ROWS * COLS);
        isPlaying = true;
        delay = 8;

        ActionListener actionListener = new ActionListener() {
            /**
             * actionPerformed Method - Redefines the actionPerformed Method for the ActionListener
             * Interface, and responds to the action take each time the timer fires.
             */
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (this != null) {
                    move();
                }
                repaint();
            }//end of the actionPerformed Method for the actionListener
        };//end of the ActionListener Class

        timer = new Timer(delay, actionListener);

        /**
         * Anonymous MouseListener
         */
        addMouseListener(new MouseAdapter() {
            /**
             * mousePressed Method - Redefines the mousePressed method of the MouseListener
             * Interface, and responds to the mouse pressed in the panel at the beginning of
             * the game.
             */
            @Override
            public void mousePressed(MouseEvent me) {
                requestFocus();

            }//end of the mousePressed Method of the Anonymous MouseListener
        });//end of the Anonymous MouseListener
        /**
         * Anonymous FocusListener
         */
        addFocusListener(new FocusListener() {
            /**
             * focusGained Method - Redefines the focusGained method of the FocusListener
             * Interface, and responds to the mousePressed method of requesting focus which
             * starts the timer and repaints the panel.
             */
            @Override
            public void focusGained(FocusEvent fe) {
                timer.start();
                isPlaying = true;
                repaint();

            }//end of the focusGained Method of the Anonymous FocusListener
            /**
             * focusLost Method - Redefines the focusLost method of the FocusListener Interface,
             * and respond to stopping the timer at the end of the game.
             */
            @Override
            public void focusLost(FocusEvent fe) {
                timer.stop();
                isPlaying = false;
                repaint();

            }//end of the focusLost Method of the Anonymous FocusListener
        });//end of the Anonymous FocusListener
        /**
         * Anonymous KeyListener
         */
        addKeyListener(new KeyListener() {
            /**
             * keyPressed Method - Redefines the keyPressed method of the KeyListener Interface,
             * and responds to the pressing of the left or right arrow keys.
             */
            @Override
            public void keyPressed(KeyEvent ke) {
                paddle.keyPressed(ke);

            }//end of the keyPressed Method of the Anonymous KeyListener
            /**
             * keyReleased Method - Redefines the keyReleased method of the KeyListener Interface,
             * and responds to the releasing of the left or right arrow keys.

             */
            @Override
            public void keyReleased(KeyEvent ke) {
                paddle.keyReleased(ke);

            }//end of the keyReleased Method of the Anonymous KeyListener
            /**
             * keyTyped Method - the method is not used in the application
             */
            @Override
            public void keyTyped(KeyEvent ke) {}
        });//end of the Anonymous KeyListener
    }//end of the GamePanel Constructor
    /**
     * move Method - Moves the ball and paddle by calling the move methods of the Ball and Paddle
     * Classes; and when the games is over it calls the appropriate method;  doWinner or doGameOver.
     */
    private void move() {
        if (isPlaying) {
            paddle.move();
            ball.move();

        } else {
            if (totalBricks == 0) {
                doWinner();

            } else {
                doGameOver();
            }
        }
    }//end of the move Method
    /**
     * paint Method - Overrides the paint method of Abstract JPanel Class, and paints the ball,
     * paddle, bricks, and score
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        if (hasFocus()) {
            ball.paint(g2d);
            paddle.paint(g2d);
            bricks.paint(g2d);
            g2d.setColor(GAME_COLOR);
            g2d.setFont(GAME_FONT);
            g2d.drawString("score: " +String.valueOf(getScore()), 10, 30);

        } else if (ball.getBounds().y > paddle.getBounds().y) {
            ball.paint(g2d);
            paddle.paint(g2d);
            bricks.paint(g2d);
            g2d.setColor(GAME_COLOR);
            g2d.setFont(GAME_FONT);
            g2d.drawString("score: " +String.valueOf(getScore()), 10, 30);

        } else if (ball.getBounds().y < paddle.getBounds().y && isPlaying == false) {
            ball.paint(g2d);
            paddle.paint(g2d);
            bricks.paint(g2d);
            g2d.setColor(GAME_COLOR);
            g2d.setFont(GAME_FONT);
            g2d.drawString("score: " +String.valueOf(getScore()), 10, 30);

        } else {
            ball.paint(g2d);
            paddle.paint(g2d);
            bricks.paint(g2d);
            g2d.setColor(GAME_COLOR);
            g2d.setFont(GAME_FONT);
            g2d.drawString("score: " +String.valueOf(getScore()), 10, 30);
            g2d.drawString("CLICK TO BEGIN", ((getWidth() / 2) - 140), (getHeight() / 2));
        }
    }//end of the paint Method
    /**
     * getScore Method - Keeps score the number times ball hits a brick
     * @return Int - Returns an integer for the specified value of the brick that the ball hits
     */
    public int getScore() {
        return score;
    }//end of the getScore Method
    /**
     * getTotalBricks Method - Keeps count of the number of total bricks
     * @return Int - Returns an integer for the total number of brick remaining
     */
    public int getTotalBricks() {
        return totalBricks;
    }//end of the getTotalBricks Method
    /**
     * doNewGame Method - Redraws the screen for a new game
     */
    public void doNewGame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        JFrame frame = new JFrame("BREAK OUT");
        frame.setBounds(0, 0, screenWidth, screenHeight);
        GamePanel game = new GamePanel();
        game.setBackground(COLOR);
        frame.add(game);
        frame.setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage(GamePanel.class.
              getResource("../img/breakout-image.png"));
        frame.setIconImage(icon);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }//end of the doNewGame Method
    /**
     * doGameOver Method - Displays the loser dialog, when the game stops.  Also, it gives the player the
     * option of playing again
     */
    public void doGameOver() {
        JDialog.setDefaultLookAndFeelDecorated(false);
        JOptionPane.showMessageDialog(this, "                        Your score is " +getScore(),
                "                            Y O U   L O O S E !", JOptionPane.PLAIN_MESSAGE);
        String message = "Do you want to play again?";
        int response = JOptionPane.showConfirmDialog(null, message, null, JOptionPane.YES_NO_OPTION);

        switch(response) {
            case JOptionPane.YES_OPTION:
                doNewGame();
                break;
            case JOptionPane.NO_OPTION:
                System.exit(0);
                break;
            default:
                System.exit(0);
        }
    }//end of the doEndGame Method
    /**
     * doWinner Method - Displays the winner dialog, when the game stops.  Also, it gives the player the
     * option of playing again.
     */
    public void doWinner() {
        JDialog.setDefaultLookAndFeelDecorated(false);
        JOptionPane.showMessageDialog(this, "                    Your score is " +getScore(),
                "                             Y O U   W O N !", JOptionPane.PLAIN_MESSAGE);
        String message = "Do you want to play again?";
        int response = JOptionPane.showConfirmDialog(null, message, null, JOptionPane.YES_NO_OPTION);

        switch(response) {
            case JOptionPane.YES_OPTION:
                doNewGame();
                break;
            case JOptionPane.NO_OPTION:
                System.exit(0);
                break;
            default:
                System.exit(0);
        }
    }//end of the doWinner Method
    /**
     * main Method - Creates the frame for the game; sets the background color; add the gamePanel;
     * sets the full size of the frame; and performs the game loop.
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GamePanel.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /** Create and display the Game */
        java.awt.EventQueue.invokeLater(() -> {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = (int) screenSize.getWidth();
            int screenHeight = (int) screenSize.getHeight();
            JFrame frame = new JFrame("BREAK OUT");
            frame.setBounds(0, 0, screenWidth, screenHeight);
            GamePanel game = new GamePanel();
            game.setBackground(COLOR);
            frame.add(game);
            frame.setResizable(false);
            Image icon = Toolkit.getDefaultToolkit().getImage(GamePanel.class.
                  getResource("../img/breakout-image.png"));
            frame.setIconImage(icon);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }//end of the main Method
}//end of the GamePanel Class


