import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import javax.swing.Timer;
public class LightCycle
{


    ///////////////// fields ////////////////////////
  int score1, score2;
  /** count of the number of light cycle created */
  private static int numCycles = 0;
  /** array of colors to use for the light cycle */
  private static Color[] colorArray = { new Color(226, 116, 12), new Color(111,195,223)};
  private static Timer myTimer;
  /** who to notify about changes to this light cycle */
  private ModelDisplay modelDisplay = null;
  /** picture to draw this light cycle on */
  private Picture picture = null;
  //10-8-4-3
  //15-12-6-5
  /** width of light cycle in pixels */
  private int width = 15;
  private int oldX, oldY;
  
  /** height of light cycle in pixels */
  private int height = 12;
  
  /** current location in x (center) */
  private int xPos = 0;
  
  /** current location in y (center) */
  private int yPos = 0;
  
  /** heading angle */
  private double heading = 0;  // default is facing north
  
  /** pen to use for this light cycle */
  private Pen pen = new Pen();
  
  /** color to draw the body in */
  private Color bodyColor = null;
  
  /** color of information string */
  private Color infoColor = Color.black;
  private ActionListener gameTimer;
  /** flag to say if this light cycle is visible */
  private boolean visible = true;
  /** flag to say if should show light cycle info */
  private boolean showInfo = false;
  /** the name of this light cycle */
  private ArrayList<String> positions;
  ////////////////// constructors ///////////////////
  
  /**
   * Constructor that takes the x and y position for the
   * light cycle
   * @param x the x pos
   * @param y the y pos
   */
  public LightCycle(int x, int y)
  {
    xPos = x;
    yPos = y;
    oldX = x;
    oldY = y;
    bodyColor = colorArray[numCycles % colorArray.length];
    setPenColor(bodyColor);
    setPenWidth(6);
    numCycles++;
    positions = new ArrayList<String>();
    positions.add(x + " " + y);
    gameTimer = new ActionListener()
    {
        public void actionPerformed(ActionEvent theEvent)
        {
            forward(5);
        }
    };
  }
  
  /**
   * Constructor that takes the x and y position and the
   * model displayer
   * @param x the x pos
   * @param y the y pos
   * @param display the model display
   */
  public LightCycle(int x, int y, ModelDisplay display)
  {
    this(x,y); // invoke constructor that takes x and y
    modelDisplay = display;
    display.addModel(this);
  }
  
  public ArrayList<String> getList()
  {
      return positions;
  }

  public void setXYPos()
  {
      xPos = oldX;
      yPos = oldY;
  }

  public Color getBodyColor() { return this.bodyColor; }
  
  
  public int getXPos() { return this.xPos; }
  
  
  /**
   * Method to get the current y position
   * @return the y position (in pixels)
   */
  public int getYPos() { return this.yPos; }
  
 
  public void setPenColor(Color color) { this.pen.setColor(color); }
  
  /**
   * Method to set the pen width
   * @param width the width to use in pixels
   */
  public void setPenWidth(int width) { this.pen.setWidth(width); }
  
  public void clearPath()
  {
    this.pen.clearPath();
  }
  
  /**
   * Method to get the current heading
   * @return the heading in degrees
   */
  public double getHeading() { return this.heading; }
  
  
  public boolean isVisible() { return this.visible;}
  
  /**
   * Method to hide the light cycle (stop showing it)
   * This doesn't affect the pen status
   */
  public void hide() { this.setVisible(false); }
  
  /**
   * Method to show the light cycle (doesn't affect
   * the pen status
   */
  public void show() { this.setVisible(true); }
  
  /**
   * Method to set the visible flag 
   * @param value the value to set it to
   */
  public void setVisible(boolean value) 
  { 
    // if the light cycle wasn't visible and now is
    if (visible == false && value == true)
    {
      // update the display
      this.updateDisplay();
    }
    
    // set the visibile flag to the passed value
    this.visible = value;
  }
    

  
  
  
  
  /**
   * Method to update the display of this light cycle and
   * also check that the light cycle is in the bounds
   */
  public synchronized void updateDisplay()
  {
    // check that x and y are at least 0
    if (xPos < 0)
      xPos = 0;
    if (yPos < 0)
      yPos = 0;
    
    // if picture
    if (picture != null)
    {
      if (xPos >= picture.getWidth())
        xPos = picture.getWidth() - 1;
      if (yPos >= picture.getHeight())
        yPos = picture.getHeight() - 1;
      Graphics g = picture.getGraphics();
      paintComponent(g);
    }
    else if (modelDisplay != null)
    {
      if (xPos >= modelDisplay.getWidth())
        xPos = modelDisplay.getWidth() - 1;
      if (yPos >= modelDisplay.getHeight())
        yPos = modelDisplay.getHeight() - 1;
      modelDisplay.modelChanged();
    }
  }
  public void forward()
  {
      forward(5);
  }
   public void oForward(int milliseconds)
   {
       myTimer = new Timer(milliseconds,gameTimer);
       myTimer.start();
       if(modelDisplay.checkScore() !=null)
       myTimer.stop();
   }
  public void forward(int pixels)
  {
      
      
      
    int oldX = xPos;
    int oldY = yPos;
    // change the current position
    xPos = oldX + (int) (pixels * Math.sin(Math.toRadians(heading)));
    yPos = oldY + (int) (pixels * -Math.cos(Math.toRadians(heading)));
        for (int i = oldX + 1; i <= xPos; i++)
             positions.add(i + " " + yPos);
        for (int j = oldX - 1; j >=xPos; j--)
            positions.add(j + " " + yPos);    
        for (int l = oldY + 1; l<=yPos; l++)
            positions.add(xPos + " " + l);
        for (int k = oldY - 1; k >=yPos; k--)
            positions.add(xPos + " " + k);

    pen.addMove(oldX,oldY,xPos,yPos);
    
    // update the display to show the new line
    updateDisplay();
    
    //keep track of score 


  }
  
  
  
  /** 
   * Method to turn left 
   */
  public void turnLeft()
  {
      if (heading != 0)
   this.turn(-90);
   else
   this.turn(270);
  }
  
  /**
   * Method to turn right 
   */
  public void turnRight()
  {
    this.turn(90);
  }
  
  /**
   * Method to turn the light cycle the passed degrees
   * use negative to turn left and pos to turn right
   * @param degrees the amount to turn in degrees
   */
  public void turn(double degrees) 
  {
    this.heading = (heading + degrees) % 360;
    this.updateDisplay();
  }
  
  /**
   * Method to draw a passed picture at the current light cycle
   * location and rotation in a picture or model display
   * @param dropPicture the picture to drop
   */
  public synchronized void drop(Picture dropPicture) 
  {
    Graphics2D g2 = null;
    
    // only do this if drawing on a picture
    if (picture != null)
      g2 = (Graphics2D) picture.getGraphics();
    else if (modelDisplay != null)
      g2 = (Graphics2D) modelDisplay.getGraphics();
    
    // if g2 isn't null
    if (g2 != null)
    {
      
      // save the current tranform
      AffineTransform oldTransform = g2.getTransform();
      
      // rotate to light cycle heading and translate to xPos and yPos
      g2.rotate(Math.toRadians(heading),xPos,yPos);
      
      // draw the passed picture
      g2.drawImage(dropPicture.getImage(),xPos,yPos,null);
      
      // reset the tranformation matrix
      g2.setTransform(oldTransform);
      
      //  draw the pen
      pen.paintComponent(g2);
    }
  }
  
  /**
   * Method to paint the light cycle 
   * @param g the graphics context to paint on
   */
  public synchronized void paintComponent(Graphics g)
  {
      
    // cast to 2d object
    Graphics2D g2 = (Graphics2D) g;
    
    // if the light cycle is visible
    if (visible)
    {
      // save the current tranform
      AffineTransform oldTransform = g2.getTransform();
      
      // rotate the light cycle and translate to xPos and yPos
      g2.rotate(Math.toRadians(heading),xPos,yPos);
      
      // draw the body parts (head)
      g2.setColor(bodyColor);
      g2.fillRoundRect(xPos - (height / 2), yPos, height, width,5, width);

      
      // draw the info string if the flag is true
      if (showInfo)
        drawInfoString(g2);
      
      // reset the tranformation matrix
      g2.setTransform(oldTransform);
    }
    
    //  draw the pen
    pen.paintComponent(g);


  }
  
  /**
   * Method to draw the information string
   * @param g the graphics context
   */
  public synchronized void drawInfoString(Graphics g) 
  {
    g.setColor(infoColor);
    g.drawString(this.toString(),xPos + (int) (width/2),yPos);
  }
  public String toString()
  {
      return "" + getXPos() + " " + getYPos();
  }
  
  /**
   * Method to return a string with informaiton 
   * about this light cycle
   * @return a string with information about this object
   */
}