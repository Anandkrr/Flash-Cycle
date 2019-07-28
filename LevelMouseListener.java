import java.awt.event.*;

public class LevelMouseListener implements MouseListener {
    public void mouseClicked(MouseEvent e)
    {             
    }
    public void mousePressed(MouseEvent e) 
    {
    int my = e.getY();
    int mx = e.getX();
    if (World.State == World.STATE.LEVEL)
    {
        if(mx >= 266 && mx <= 366) 
            if(my >= 150 && my <= 200) 
                World.State = World.STATE.LEVEL1;

                if(mx >= 266 && mx <= 366) 
             if(my >= 250 && my <= 300) 
                World.State = World.STATE.LEVEL2;

            if(mx >= 266 && mx <= 366) 
             if(my >= 350 && my <= 400) 
             World.State = World.STATE.LEVEL3;
         }
         else if (World.State == World.STATE.HELP)
         {
               if (mx >= 10 && mx <= 110 && my >= 20 && my <= 70)
               World.State = World.STATE.LEVEL1;
         }




  
    }
    public void mouseReleased(MouseEvent arg0) {
        
    }
    
    public void mouseEntered(MouseEvent e) {
        
    }
    
    public void mouseExited(MouseEvent e) {
        
    }
}
