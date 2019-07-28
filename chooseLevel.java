import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class chooseLevel {
    public Rectangle level1 = new Rectangle(266, 150, 150, 50);
    public Rectangle level2 = new Rectangle(266, 250, 150, 50);
    public Rectangle level3 = new Rectangle(266, 350, 150, 50);


    
     public void render(Graphics g)
    {
    Graphics2D g2d = (Graphics2D) g;
    
    Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.black);
        g.drawString("Choose Level:", 150, 100);
    
    Font fnt2 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt2);
        g.drawString("Level 1", level1.x + 12, level1.y + 30);
        g.drawString("Level 2", level2.x + 12, level2.y + 30);
        g.drawString("Level 3", level3.x + 12, level3.y + 30);
        
        g2d.draw(level1);
        g2d.draw(level2);
        g2d.draw(level3);
    
    
    }
}