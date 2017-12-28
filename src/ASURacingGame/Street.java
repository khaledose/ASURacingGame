
package ASURacingGame;
import java.awt.*;
import java.net.*;
import javax.swing.*;

public class Street extends JPanel {
    public int x,y,w,h;
        private Image img=null;
        private String path;
        private Timer movementTimer;
        private boolean moving=false;
        public Street(int x , int y,int w, int h, String path){
            this.x=x;
            this.y=y;
            this.w=w;
            this.h=h;
            this.path=path;
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            this.setSize(w,h);
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
        public void VehicleLocation(int x,int y){
            this.x=x;
            this.y=y;
        }
        public void stopper(){
            movementTimer.stop();
        }
        public void moveDown(int t){
                    y+=t;
                    repaint();
        }
}
