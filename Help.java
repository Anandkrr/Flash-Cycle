import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;


public class Help {
    
    public Rectangle backButton = new Rectangle(10, 20, 100, 50);
    
    public void render(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.black);
        g.drawString("Help", 250, 50);
        
        Font fnt3 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt3);
        g.drawString("Play", backButton.x + 12, backButton.y + 35);
        g2d.draw(backButton);
        
        
        Font fnt1 = new Font("Arial", Font.BOLD, 12);
        g.setFont(fnt1);
        g.setColor(Color.black);
        g.drawString("Flash Cycle is made by Anand and Dheeraj." ,10,100);
        g.drawString(" You use the WASD  and arrow keys to move the " + 
        "seperate players." ,10,150);
        g.drawString("The opponent wins a point when you hit the boundary" ,10,200);
        g.drawString(" or you hit the other player." ,10,250);
         g.drawString("  First player to 10 wins the game." ,10,300);
        
        
    
        
    }
    
    
    
}
