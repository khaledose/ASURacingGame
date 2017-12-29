
package ASURacingGame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class MultiplayerPanel extends JPanel{
    private Street street;
    private Font f1,f2;
    private Vehicles redCar,blueCar;
    private Vehicles [][] vehicle;
    private int [] lanes;
    private Random rand;
    private javax.swing.Timer gameTimer,streetTimer;
    private javax.swing.Timer VehicleGenerator;
    private ArrayList redVehicles,blueVehicles;
    private int scr=0;
    private JPanel window;
    private JLabel replay,mainmenu,winner,black;
    private ImageIcon blackImg,btnImg,btn2Img,btn3Img;
    private JLabel replayLbl,mainmenuLbl;
    public MultiplayerPanel(){
        init();
        layout();
        Bounds();
        foreground();
        add();
    }
    public void init(){
        replayLbl=new JLabel("           Replay");
        mainmenuLbl=new JLabel("       Main Menu");
        window=new JPanel();
        black=new JLabel();
        replay=new JLabel();
        mainmenu=new JLabel();
        winner=new JLabel("The Blue Won!");
        street=new Street(0,-990,990,1980,"street2.png");
        f1=new Font("",Font.BOLD,40);
        f2=new Font("",Font.BOLD,20);
        redCar=new Vehicles(18*14+6,18*41,84,180,"Audi.png",false,false);
        blueCar=new Vehicles(18*36,18*41,84,180,"Audi2.png",false,false);
        vehicle = new Vehicles[2][17];
        lanes=new int[8];
        redVehicles=new ArrayList();
        blueVehicles=new ArrayList();
        rand=new Random();
        blackImg=new ImageIcon("./UI/Untitled-1.png");
        btnImg=new ImageIcon("./UI/btn1.png");
        btn2Img=new ImageIcon("./UI/btn2.png");
        btn3Img=new ImageIcon("./UI/btn3.png");
        lanes[0]=2*18;
        lanes[1]=8*18;
        lanes[2]=14*18;
        lanes[3]=20*18;
        lanes[4]=30*18;
        lanes[5]=36*18;
        lanes[6]=42*18;
        lanes[7]=48*18;
        
        
        //##################################################################### GENERATORS ###############################################################
        
        
        streetTimer=new javax.swing.Timer(10,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                int strtSpd=10+scr/500*4;
                street.moveDown(strtSpd);
                if(street.y>=0){
                street.y=-990;
            }
            }
        });
        VehicleGenerator=new javax.swing.Timer(1000,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                int vclSpd=0;
                int x1=rand.nextInt(4);
                int x2=rand.nextInt(4)+4;
                vehicle[0][0]=new Vehicles(lanes[x1],-300,90,198,"Black_viper.png",false,false);
                vehicle[0][1]=new Vehicles(lanes[x1],-300,90,198,"Black_viper2.png",false,false);
                vehicle[0][2]=new Vehicles(lanes[x1],-300,90,198,"Black_viper3.png",false,false);
                vehicle[0][3]=new Vehicles(lanes[x1],-300,90,198,"Black_viper4.png",false,false);
                vehicle[0][4]=new Vehicles(lanes[x1]+5,-300,77,180,"Car.png",false,false);
                vehicle[0][5]=new Vehicles(lanes[x1]+5,-300,77,180,"Car2.png",false,false);
                vehicle[0][6]=new Vehicles(lanes[x1]+5,-300,77,180,"Car3.png",false,false);
                vehicle[0][7]=new Vehicles(lanes[x1]+5,-300,77,180,"Car4.png",false,false);
                vehicle[0][8]=new Vehicles(lanes[x1],-300,90,166,"Mini_truck2.png",false,false);
                vehicle[0][9]=new Vehicles(lanes[x1],-300,90,166,"Mini_truck3.png",false,false);
                vehicle[0][10]=new Vehicles(lanes[x1],-300,90,166,"Mini_truck4.png",false,false);
                vehicle[0][11]=new Vehicles(lanes[x1],-300,90,166,"Mini_truck5.png",false,false);
                vehicle[0][12]=new Vehicles(lanes[x1],-300,90,192,"Mini_van.png",false,false);
                vehicle[0][13]=new Vehicles(lanes[x1]+2,-300,82,180,"Police.gif",false,false);
                vehicle[0][14]=new Vehicles(lanes[x1],-300,84,162,"taxi.png",false,false);
                vehicle[0][15]=new Vehicles(lanes[x1],-300,90,232,"truck2.png",false,false);
                vehicle[0][16]=new Vehicles(lanes[x1],-300,90,213,"Ambulance.gif",false,false);
                
                vehicle[1][0]=new Vehicles(lanes[x2],-300,90,198,"Black_viper.png",false,false);
                vehicle[1][1]=new Vehicles(lanes[x2],-300,90,198,"Black_viper2.png",false,false);
                vehicle[1][2]=new Vehicles(lanes[x2],-300,90,198,"Black_viper3.png",false,false);
                vehicle[1][3]=new Vehicles(lanes[x2],-300,90,198,"Black_viper4.png",false,false);
                vehicle[1][4]=new Vehicles(lanes[x2]+5,-300,77,180,"Car.png",false,false);
                vehicle[1][5]=new Vehicles(lanes[x2]+5,-300,77,180,"Car2.png",false,false);
                vehicle[1][6]=new Vehicles(lanes[x2]+5,-300,77,180,"Car3.png",false,false);
                vehicle[1][7]=new Vehicles(lanes[x2]+5,-300,77,180,"Car4.png",false,false);
                vehicle[1][8]=new Vehicles(lanes[x2],-300,90,166,"Mini_truck2.png",false,false);
                vehicle[1][9]=new Vehicles(lanes[x2],-300,90,166,"Mini_truck3.png",false,false);
                vehicle[1][10]=new Vehicles(lanes[x2],-300,90,166,"Mini_truck4.png",false,false);
                vehicle[1][11]=new Vehicles(lanes[x2],-300,90,166,"Mini_truck5.png",false,false);
                vehicle[1][12]=new Vehicles(lanes[x2],-300,90,192,"Mini_van.png",false,false);
                vehicle[1][13]=new Vehicles(lanes[x2]+2,-300,82,180,"Police.gif",false,false);
                vehicle[1][14]=new Vehicles(lanes[x2],-300,84,162,"taxi.png",false,false);
                vehicle[1][15]=new Vehicles(lanes[x2],-300,90,232,"truck2.png",false,false);
                vehicle[1][16]=new Vehicles(lanes[x2],-300,90,213,"Ambulance.gif",false,false);
                int v1=rand.nextInt(17);
                int v2=rand.nextInt(17);
                vehicle[0][v1].setBounds(0,0,990,990);
                vehicle[0][v1].setOpaque(false);
                vehicle[1][v2].setBounds(0,0,990,990);
                vehicle[1][v2].setOpaque(false);
                vclSpd=4+scr/200*1;
                
                boolean flag1=false;
                for(int i=0;i<redVehicles.size();i++)
                   if(Collision(vehicle[0][v1], (Vehicles) redVehicles.get(i)) || vehicle[0][v1].getImagePath().equals(((Vehicles) redVehicles.get(i)).getImagePath())){
                       flag1=true;
                       break;
                   }
                if(!flag1)
                        generateRedVehicle(vehicle[0][v1],vclSpd);   
                
                boolean flag2=false;
                for(int i=0;i<blueVehicles.size();i++)
                   if(Collision(vehicle[1][v2], (Vehicles) blueVehicles.get(i)) || vehicle[1][v2].getImagePath().equals(((Vehicles) blueVehicles.get(i)).getImagePath())){
                       flag2=true;
                       break;
                   }
                if(!flag2)
                        generateBlueVehicle(vehicle[1][v2],vclSpd);
                }
            
        });
        gameTimer=new javax.swing.Timer(50,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                scr++;
                for(int i=0;i<redVehicles.size();i++)
                    if(Collision((Vehicles) redVehicles.get(i),redCar)){
                        GamePanel.stopMusic();
                        playSound("./Audio/carCrash.wav");
                        //here you die in both easy and hard if you hit a car
                        for(int j=0;j<redVehicles.size();j++)
                            ((Vehicles) redVehicles.get(j)).stopper();
                        timeStopper();
                         winner.setText("The Blue Wins!");
                        winner.setForeground(Color.blue);
                        black.setVisible(true);
                        redCar.setDied(true);
                        blueCar.setDied(true);
                    }
                for(int i=0;i<blueVehicles.size();i++)
                    if(Collision((Vehicles) blueVehicles.get(i),blueCar)){
                        GamePanel.stopMusic();
                        playSound("./Audio/carCrash.wav");
                        //here you die in both easy and hard if you hit a car
                        for(int j=0;j<blueVehicles.size();j++)
                            ((Vehicles) blueVehicles.get(j)).stopper();
                        timeStopper();
                        winner.setText("The Red Wins!");
                        winner.setForeground(Color.red);
                       
                        black.setVisible(true);
                        blueCar.setDied(true);
                        redCar.setDied(true);
                    }
            }
        });
        replay.addMouseListener(new MouseAdapter(){
                @Override
        public void mouseEntered(MouseEvent e){
                replay.setIcon(btn2Img);
        }
            @Override
        public void mousePressed(MouseEvent e) {
                //replayLbl.setForeground(Color.white);
                mainmenu.setIcon(btn3Img);
        }
            @Override
        public void mouseExited(MouseEvent e) {
        replay.setIcon(btnImg);
              }
        });
        mainmenu.addMouseListener(new MouseAdapter(){
                @Override
        public void mouseEntered(MouseEvent e){
                mainmenu.setIcon(btn2Img);
        }
            @Override
        public void mousePressed(MouseEvent e) {
                //mainmenuLbl.setForeground(Color.white);
                mainmenu.setIcon(btn3Img);
        }
            @Override
        public void mouseExited(MouseEvent e) {
        mainmenu.setIcon(btnImg);
              }
        });
    }
    public JLabel getMainMenuBtn(){
        return mainmenu;
    }
    public JLabel getReplayBtn(){
        return replay;
    }
    public JLabel getMainMenuLbl(){
        return mainmenuLbl;
    }
    public JLabel getReplayLbl(){
        return replayLbl;
    }
    public void Bounds(){
        street.setBounds(0,0,990,990);
        redCar.setBounds(0,0,990,990);
        blueCar.setBounds(0,0,990,990);
        window.setBounds(245,245,500,500);
        black.setBounds(0,0,990,990);
        replay.setBounds(275, 400, 200, 50);
        mainmenu.setBounds(50, 400, 200, 50);
        winner.setBounds(120, 100, 400, 50);
        
    }
    public void add(){
        this.add(street);
        street.add(black);
        black.add(window);
        window.add(replay);
        window.add(mainmenu);
        window.add(winner);
        street.add(blueCar);
        street.add(redCar);
        
        
        
    }
    public void foreground(){
        this.setBackground(new Color(186,184,145));
        window.setBackground(new Color(186,184,145));
        mainmenuLbl.setForeground(Color.red);
        replayLbl.setForeground(Color.red);
    }
    public void layout(){
        mainmenuLbl.setFont(f2);
        replayLbl.setFont(f2);
        this.setLayout(null);
        street.setLayout(null);
        street.setOpaque(false);
        redCar.setOpaque(false);
        blueCar.setOpaque(false);
        replay.setLayout(new BorderLayout());
        mainmenu.setLayout(new BorderLayout());
        replay.add(replayLbl);
        mainmenu.add(mainmenuLbl);
        black.setLayout(null);
        window.setLayout(null);
        black.setIcon(blackImg);
        replay.setIcon(btnImg);
        mainmenu.setIcon(btnImg);
        black.setOpaque(false);
        winner.setFont(f1);
    }
    public Vehicles getRedCar(){
        return redCar;
    }
    public Vehicles getBlueCar(){
        return blueCar;
    }
    public void generateRedVehicle(Vehicles moveVehicle,int spd){
            redVehicles.add(moveVehicle);
            street.add(moveVehicle);
            moveVehicle.moveDown(spd);
      for(int i=0;i<redVehicles.size();i++){
          Vehicles vcl=(Vehicles)redVehicles.get(i);
          if(vcl.y>900){
              street.remove((Vehicles)redVehicles.get(i));
              redVehicles.remove((Vehicles)redVehicles.get(i));
          }
      }
    }
    public void generateBlueVehicle(Vehicles moveVehicle,int spd){
            blueVehicles.add(moveVehicle);
            street.add(moveVehicle);
            moveVehicle.moveDown(spd);
      for(int i=0;i<blueVehicles.size();i++){
          Vehicles vcl=(Vehicles)blueVehicles.get(i);
          if(vcl.y>900){
              street.remove((Vehicles)blueVehicles.get(i));
              blueVehicles.remove((Vehicles)blueVehicles.get(i));
          }
      }
    }
    public boolean Collision(Vehicles vcl1,Vehicles vcl2){
        int vx = vcl1.x , vy = vcl1.y , vh = vcl1.h , vw = vcl1.w;
        int mx = vcl2.x , my = vcl2.y , mh = vcl2.h , mw = vcl2.w;
           if(( vy + vh >= my && vy <= my + mh)&&(( vx >= mx && vx <= mx + mw )||( vx + vw >= mx && vx + vw <= mx + mw )))
               return true;
            else
                return false;
    }
    public void timeStopper(){
        for(int i=0;i<redVehicles.size();i++)
            ((Vehicles)redVehicles.get(i)).stopper();
        for(int i=0;i<blueVehicles.size();i++)
            ((Vehicles)blueVehicles.get(i)).stopper();
            gameTimer.stop();
            VehicleGenerator.stop();
            streetTimer.stop();
     }
    public void timeStarter(){
            gameTimer.start();
            VehicleGenerator.start();
            streetTimer.start();
    }
    public void reset(){
                vehicle[0][0]=new Vehicles(0,-300,90,198,"Black_viper.png",false,false);
                vehicle[0][1]=new Vehicles(0,-300,90,198,"Black_viper2.png",false,false);
                vehicle[0][2]=new Vehicles(0,-300,90,198,"Black_viper3.png",false,false);
                vehicle[0][3]=new Vehicles(0,-300,90,198,"Black_viper4.png",false,false);
                vehicle[0][4]=new Vehicles(0,-300,77,180,"Car.png",false,false);
                vehicle[0][5]=new Vehicles(0,-300,77,180,"Car2.png",false,false);
                vehicle[0][6]=new Vehicles(0,-300,77,180,"Car3.png",false,false);
                vehicle[0][7]=new Vehicles(0,-300,77,180,"Car4.png",false,false);
                vehicle[0][8]=new Vehicles(0,-300,90,166,"Mini_truck2.png",false,false);
                vehicle[0][9]=new Vehicles(0,-300,90,166,"Mini_truck3.png",false,false);
                vehicle[0][10]=new Vehicles(0,-300,90,166,"Mini_truck4.png",false,false);
                vehicle[0][11]=new Vehicles(0,-300,90,166,"Mini_truck5.png",false,false);
                vehicle[0][12]=new Vehicles(0,-300,90,192,"Mini_van.png",false,false);
                vehicle[0][13]=new Vehicles(0,-300,82,180,"Police.gif",false,false);
                vehicle[0][14]=new Vehicles(0,-300,84,162,"taxi.png",false,false);
                vehicle[0][15]=new Vehicles(0,-300,90,232,"truck2.png",false,false);
                vehicle[0][16]=new Vehicles(0,-300,90,213,"Ambulance.gif",false,false);
                
                vehicle[1][0]=new Vehicles(0,-300,90,198,"Black_viper.png",false,false);
                vehicle[1][1]=new Vehicles(0,-300,90,198,"Black_viper2.png",false,false);
                vehicle[1][2]=new Vehicles(0,-300,90,198,"Black_viper3.png",false,false);
                vehicle[1][3]=new Vehicles(0,-300,90,198,"Black_viper4.png",false,false);
                vehicle[1][4]=new Vehicles(0,-300,77,180,"Car.png",false,false);
                vehicle[1][5]=new Vehicles(0,-300,77,180,"Car2.png",false,false);
                vehicle[1][6]=new Vehicles(0,-300,77,180,"Car3.png",false,false);
                vehicle[1][7]=new Vehicles(0,-300,77,180,"Car4.png",false,false);
                vehicle[1][8]=new Vehicles(0,-300,90,166,"Mini_truck2.png",false,false);
                vehicle[1][9]=new Vehicles(0,-300,90,166,"Mini_truck3.png",false,false);
                vehicle[1][10]=new Vehicles(0,-300,90,166,"Mini_truck4.png",false,false);
                vehicle[1][11]=new Vehicles(0,-300,90,166,"Mini_truck5.png",false,false);
                vehicle[1][12]=new Vehicles(0,-300,90,192,"Mini_van.png",false,false);
                vehicle[1][13]=new Vehicles(0,-300,82,180,"Police.gif",false,false);
                vehicle[1][14]=new Vehicles(0,-300,84,162,"taxi.png",false,false);
                vehicle[1][15]=new Vehicles(0,-300,90,232,"truck2.png",false,false);
                vehicle[1][16]=new Vehicles(0,-300,90,213,"Ambulance.gif",false,false);
                scr=0;
                redCar.setDied(false);
                blueCar.setDied(false);
                black.setVisible(false);
        for(int i=0;i<redVehicles.size();i++){
            street.remove((Vehicles)redVehicles.get(i));
        }
        redVehicles.clear();
        for(int i=0;i<blueVehicles.size();i++){
            street.remove((Vehicles)blueVehicles.get(i));
        }
        blueVehicles.clear();
        redCar.VehicleLocation(18*14,18*41);
        blueCar.VehicleLocation(18*36,18*41);
        timeStarter();
    }
    public void playSound(String path){
                        String bip = path;
                        File soundFile = new File(bip);
                        AudioInputStream audioStream = null;
                    try {
                        audioStream = AudioSystem.getAudioInputStream(soundFile);
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(StartGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(StartGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                        AudioFormat format = audioStream.getFormat();
                        DataLine.Info info = new DataLine.Info(Clip.class, format);
                        Clip audioClip = null;
                    try {
                        audioClip = (Clip) AudioSystem.getLine(info);
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(StartGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        audioClip.open(audioStream);
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(StartGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(StartGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        audioClip.start();
   }
}

