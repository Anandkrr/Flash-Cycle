public class LightCycleTest extends LightCycle  
        

{   

  public LightCycleTest (int x, int y, 
                 ModelDisplay modelDisplayer) 
  {
    // let the parent constructor handle it
    super(x,y,modelDisplayer);
  }
  
  /** Constructor that takes the model display
   * @param modelDisplay the thing that displays the model
   */
   
  
  public static void main(String[] args) throws InterruptedException
  {      
   final World w = new World();
   final LightCycleTest lc = new LightCycleTest(213, 240, w);
   final LightCycleTest lc1 = new LightCycleTest(423, 240, w);
   boolean ran;
   new Thread()
   {
       public void run()
       {
           if (World.State == World.STATE.LEVEL1)
           {
               lc.oForward(50);
           }
           else if (World.State == World.STATE.LEVEL2)
           {
               lc.oForward(25);
           }
           else if (World.State == World.STATE.LEVEL3)
           {
               lc.oForward(12);
           }
           else
           {
               lc.oForward(25);
           }

       }
   }.start();
    new Thread()
   {
       public void run()
       {
           if (World.State == World.STATE.LEVEL1)
           {
               lc1.oForward(50);
           }
           else if (World.State == World.STATE.LEVEL2)
           {
               lc1.oForward(25);
           }
           else if (World.State == World.STATE.LEVEL3)
           {
               lc1.oForward(12);
           }
           else
           {
               lc1.oForward(25);
           }
     }
   }.start();
    new Thread()
   {
       public void run()
       {
           while (true)
           {
               if (w.checkScore() != null)
               w.updateScore(w.checkScore());
               if (w.getScore1() == 11 || w.getScore2() == 10)
               {
                   if (w.getScore1() == 11)
                   w.setWinner(1);
                   else if (w.getScore2() == 10)
                   w.setWinner(2);
               World.State = World.STATE.DONE;
               }

           }

       }
   }.start();



}

  
  }