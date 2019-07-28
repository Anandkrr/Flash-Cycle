import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class Menu{
    
    public Rectangle playButton = new Rectangle(266, 150, 100, 50);
    public Rectangle helpButton = new Rectangle(266, 250, 100, 50);
    public  Rectangle quitButton = new Rectangle(266, 350, 100, 50);  
    
    
    public void render(Graphics g)
    {

        Graphics2D g2d = (Graphics2D) g;
               
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.black);
        g.drawString("Flash Cycle", 160, 100);
        
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("Play", playButton.x + 15, playButton.y + 35);
        g.drawString("Help", helpButton.x + 12, helpButton.y + 35);
        g.drawString("Quit", quitButton.x + 13, quitButton.y + 35);
        g2d.draw(playButton);
        g2d.draw(helpButton);
        g2d.draw(quitButton);
    }

}
