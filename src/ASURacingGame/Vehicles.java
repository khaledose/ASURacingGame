
package ASURacingGame;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;

public class Vehicles extends JPanel{
        public int x,y,w,h;
        private Image img=null;
        private String path;
        private Timer movementTimer;
        private boolean died=false;
        private boolean bonus=false;
        private boolean fuel=false;
        public Vehicles(int x , int y,int w, int h, String path,boolean bns,boolean fuel){
            this.x=x;
            this.y=y;
            this.w=w;
            this.h=h;
            this.path=path;
            this.bonus=bns;
            this.fuel=fuel;
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            this.setSize(990,990);
            if(img==null)
                img=getImage(path);
            Graphics2D g2 =(Graphics2D)g;
            g2.drawImage(img,x,y,w,h,this);
        }

        public Image getImage(String path){
            Image temp=null;
            try{
                URL ImageURL = Vehicles.class.getResource(path);
                temp=Toolkit.getDefaultToolkit().getImage(ImageURL);
            }
            catch(Exception e){
                System.out.println("error");
            }
            return temp;
        }
        public String getImagePath(){
            return path;
        }
        public void VehicleLocation(int x,int y){
            this.x=x;
            this.y=y;
        }
        public void stopper(){
            movementTimer.stop();
        }
        
        public void moveRight(int limit){
            if(movementTimer!=null)
                movementTimer.stop();
            movementTimer = new Timer(20,new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                  if(x<limit*18){
                    x+=9;
                    repaint();
                }
                  else stopper();
                }
            });
            movementTimer.start();
        }
        public void moveLeft(int limit){
            if(movementTimer!=null)
                movementTimer.stop();
            movementTimer = new Timer(20,new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if(x>limit*18){
                    x-=9;
                    repaint();
                    }
                    else stopper();
                }
            });
            movementTimer.start();
        }
        public void moveDown(int t){
            if(movementTimer!=null)
                movementTimer.stop();
            movementTimer = new Timer(t,new ActionListener(){
                int count=0;
                @Override
                public void actionPerformed(ActionEvent ae) {
                    y+=t;
                    repaint();
                }
            });
            movementTimer.start();
        }
    public void setPath(String p){
        this.path=p;
    }
    public boolean isDied() {
        return died;
    }

    public void setDied(boolean died) {
        this.died = died;
    }
    public boolean isBonus(){
        return bonus;
    }
    public boolean isFuel(){
        return fuel;
    }

}
