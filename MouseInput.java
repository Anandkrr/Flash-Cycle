import java.awt.event.*;
public class MouseInput implements MouseListener{
    
    public void mouseClicked(MouseEvent e) {
        //nothing
    }
    
    public void mousePressed(MouseEvent e) {
        
        int mx = e.getX();
        int my = e.getY();
        
    if (mx >= 266 && mx <= 366)
    {
        if (my >= 150 && my <= 200)
        World.State = World.STATE.LEVEL;
        else if (my >= 350 && my <= 400)
        System.exit(1);
        else if (my >= 250 && my <= 300)
        World.State = World.STATE.HELP;
    }
 
    }
    
    public void mouseReleased(MouseEvent arg0) {
        
    }
    
    public void mouseEntered(MouseEvent e) {
        
    }
    
    public void mouseExited(MouseEvent e) {
        
    }
    
    
    
}
