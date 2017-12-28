/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ASURacingGame;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
/**
 *
 * @author khaled hesham
 */
public class Bonus extends JPanel {
        public int x,y,w,h;
        private Image img=null;
        private String path;
        private Timer movementTimer;
        private boolean moving=false;
        public Bonus(int x , int y,int w, int h, String path){
            this.x=x;
            this.y=y;
            this.w=w;
            this.h=h;
            this.path=path;
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            this.setSize(594,990);
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

