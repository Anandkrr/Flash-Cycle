import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.*;

/**
 * Class to represent a 2d world that can hold light cycles and
 * display them
 * 
 * Copyright Georgia Institute of Technology 2004
 * @author Barb Ericson ericson@cc.gatech.edu
 */
public class World extends JComponent implements ModelDisplay, KeyListener {
    ////////////////// fields ///////////////////////
    /** should automatically repaint when model changed */
    private boolean autoRepaint = true;

    private int winner = 0;
    /** the width of the world ee*/
    private int width = 640;

    private int score2 = 0;

    private int score1 = 0;
    private Menu                 menu        = new Menu();

    private Help                 help        = new Help();

    private chooseLevel          level       = new chooseLevel();

    /** the height of the world */
    private int                  height      = 480;

    /** the list of light cycles in the world */
    private List<LightCycleTest> lctList     = new ArrayList<LightCycleTest>();

    /** the JFrame to show this world in */
    private JFrame               frame       = new JFrame("World");

    /** background picture */
    private Picture              picture     = null;

    private int                  length, length1;
    private MouseInput           mi;
    private LevelMouseListener   lml;

    ////////////////// the constructors ///////////////

    /**
     * Constructor that takes no arguments
     */
    public World() {
        addKeyListener(this);
        setFocusable(true);
        // set up the world and make it visible
        initWorld(true);
    }

    /**
     * Constructor that takes a boolean to
     * say if this world should be visible
     * or not
     * @param visibleFlag if true will be visible
     * else if false will not be visible
     */
    public World(boolean visibleFlag) {
        initWorld(visibleFlag);
    }

    /**
     * Constructor that takes a width and height for this
     * world
     * @param w the width for the world
     * @param h the height for the world
     */
    public World(int w, int h) {
        width = w;
        height = h;
        addKeyListener(this);
        // set up the world and make it visible
        initWorld(true);
    }

    ///////////////// methods ///////////////////////////

    public static enum STATE {

        MENU,
        HELP,
        DONE,
        LEVEL,
        LEVEL1,
        LEVEL3,
        LEVEL2;

    };

    public static STATE State = STATE.MENU;


    public int getScore1() {

        return score1;

    }

    public int getScore2() {

        return score2;

    }


    /**
     * Method to initialize the world
     * @param visibleFlag the flag to make the world
     * visible or not
     */
    private void initWorld(boolean visibleFlag) {
        // set the preferred size
        this.setPreferredSize(new Dimension(width, height));
        mi = new MouseInput();
        lml = new LevelMouseListener();
        this.addMouseListener(mi);
        // create the background picture
        picture = new Picture(width, height);
        // add this panel to the frame
        frame.getContentPane().add(this);
        // pack the frame
        frame.pack();
        // show this world
        frame.setVisible(visibleFlag);
    }


    public void updateScore(LightCycle a) {
        if (a == null)
            ;
        else if (a.equals(lctList.get(0))) {

            score1++;

        }

        else {
            score2++;
        }
        paintComponent(getGraphics());
    }


    public void keyPressed(KeyEvent e) {
        if (State == STATE.LEVEL1 || State == STATE.LEVEL2 || State == STATE.LEVEL3) {

            if (e.getKeyCode() == KeyEvent.VK_W) {
                if (lctList.get(0).getHeading() == 90)
                    lctList.get(0).turnLeft();
                else if (lctList.get(0).getHeading() == 270)
                    lctList.get(0).turnRight();
            } else if (e.getKeyCode() == KeyEvent.VK_A) {
                if (lctList.get(0).getHeading() == 0)
                    lctList.get(0).turnLeft();
                else if (lctList.get(0).getHeading() == 180)
                    lctList.get(0).turnRight();
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                if (lctList.get(0).getHeading() == 90)
                    lctList.get(0).turnRight();
                else if (lctList.get(0).getHeading() == 270)
                    lctList.get(0).turnLeft();
            } else if (e.getKeyCode() == KeyEvent.VK_D) {
                if (lctList.get(0).getHeading() == 0)
                    lctList.get(0).turnRight();
                else if (lctList.get(0).getHeading() == 180)
                    lctList.get(0).turnLeft();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (lctList.get(1).getHeading() == 90)
                    lctList.get(1).turnLeft();
                else if (lctList.get(1).getHeading() == 270)
                    lctList.get(1).turnRight();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (lctList.get(1).getHeading() == 0)
                    lctList.get(1).turnLeft();
                else if (lctList.get(1).getHeading() == 180)
                    lctList.get(1).turnRight();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (lctList.get(1).getHeading() == 0)
                    lctList.get(1).turnRight();
                else if (lctList.get(1).getHeading() == 180)
                    lctList.get(1).turnLeft();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (lctList.get(1).getHeading() == 90)
                    lctList.get(1).turnRight();
                else if (lctList.get(1).getHeading() == 270)
                    lctList.get(1).turnLeft();
            }

        }

    }

    public void keyReleased(KeyEvent e) {

    }
    public void setWinner(int x)
    {
        winner = x;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void resetScore() {
        score1 = 0;
        score2 = 0;
    }

    /** 
     * Method to get the graphics context for drawing on
     * @return the graphics context of the background picture
     */
    public Graphics getGraphics() {
        return picture.getGraphics();
    }


    public synchronized void paintComponent(Graphics g) {
        if (State == STATE.LEVEL) {
            resetScore();
            this.removeMouseListener(mi);
            this.addMouseListener(lml);
            level.render(g);
        } else if (State == STATE.HELP) {
            resetScore();
            this.removeMouseListener(mi);
            this.addMouseListener(lml);
            help.render(g);
        } else if (State == STATE.LEVEL1 || State == STATE.LEVEL2 || State == STATE.LEVEL3) {
            for (int i = 0; i <= width - 15; i += 15) {
                for (int j = 0; j <= height - 45; j += 15) {
                    g.setColor(Color.black);
                    g.drawLine(i, j, i, j + 15);
                    g.drawLine(i, j, i + 15, j);
                }
            }
            // loop drawing each light cycle on the background image
            lctList.get(0).paintComponent(g);
            lctList.get(1).paintComponent(g);
            g.draw3DRect(10, height - 30, 200, 85, true);
            g.drawString("Player 1 Score:", 15, height - 15);
            g.drawString("" + (score1 - 1), 115, height - 15);

            g.setColor(Color.red);
            g.draw3DRect(220, height - 30, 200, 85, true);
            g.drawString("Player 2 Score:", 225, height - 15);
            g.drawString("" + score2, 325, height - 15);
            if (checkScore() != null) {
                g.dispose();

                lctList.get(0).clearPath();
                lctList.get(1).clearPath();
                length = lctList.get(0).getList().size();
                length1 = lctList.get(1).getList().size();
                lctList.get(0).setXYPos();
                lctList.get(1).setXYPos();
            }
        } else if (State == STATE.MENU) {
            resetScore();
            menu.render(g);
        } else if (State == STATE.DONE) {
            Font f1 = new Font("Arial", Font.BOLD, 72);
            Font f2 = new Font("Arial", Font.PLAIN, 36);
            g.setFont(f1);
            g.setColor(Color.red);
            g.drawString("Game Over!", 106, 240);
            g.setFont(f2);
            if (winner == 2) {
                g.setColor(lctList.get(0).getBodyColor());
                g.drawString("Player 2 Wins!", 212, 360);
            } 
            else if (winner == 1)
            {
                g.setColor(lctList.get(1).getBodyColor());
                g.drawString("Player 1 Wins!", 212, 360);
            }

            }

        }


    /**
     * Method to add a model to this model displayer
     * @param model the model object to add
     */
    public void addModel(Object model) {
        lctList.add((LightCycleTest)model);
        if (autoRepaint)
            repaint();
    }

    /**
     * Method to check if this world contains the passed
     * light cycle
     * @return true if there else false
     */
    public LightCycle checkScore() {
        Integer s = 0;
        Integer s1 = 0;
        String s2, s3 = "";
        for (int i = length; i < lctList.get(0).getList().size() - 10; i++) {
            s2 = lctList.get(0).getList().get(i).toString();
            if (s2.substring(s2.length() - 3, s2.length()).indexOf(' ') == -1)
                s = Integer.parseInt(s2.substring(s2.length() - 3, s2.length()));
            if (s >= 450)
                return lctList.get(1);
        }
        for (int j = length; j < lctList.get(1).getList().size() - 10; j++) {
            s3 = lctList.get(1).getList().get(j).toString();
            if (s3.substring(s3.length() - 3, s3.length()).indexOf(' ') == -1)
                s1 = Integer.parseInt(s3.substring(s3.length() - 3, s3.length()));
            if (s1 >= 450)
                return lctList.get(0);
        }
        for (int i = length; i < lctList.get(0).getList().size() - 10; i++) {
            for (int j = i + 1; j < lctList.get(0).getList().size() - 10; j++) {
                if (lctList.get(0).getList().get(i).equals(lctList.get(0).getList().get(j))) {
                    return lctList.get(0);
                }


            }
        }
        for (int i = length; i < lctList.get(0).getList().size() - 10; i++) {
            for (int k = length1; k < lctList.get(1).getList().size() - 10; k++) {
                if (lctList.get(0).getList().get(i).equals(lctList.get(1).getList().get(k))) {
                    if (i < k) {
                        return lctList.get(1);
                    } else if (k < i) {
                        return lctList.get(0);
                    }
                }

            }

        }
        for (int a = length1; a < lctList.get(1).getList().size() - 10; a++) {
            for (int b = a + 1; b < lctList.get(1).getList().size() - 10; b++) {
                if (lctList.get(1).getList().get(a).equals(lctList.get(1).getList().get(b))) {
                    return lctList.get(1);
                }

            }
        }


        return null;
    }

    public void modelChanged() {
        if (autoRepaint)
            repaint();
    }


} // end of World class
