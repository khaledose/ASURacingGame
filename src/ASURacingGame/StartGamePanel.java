package ASURacingGame;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class StartGamePanel extends JPanel{
    private Street street;
    private ImageIcon btnImg,btn2Img,btn3Img,blk;
    private JLabel scoreLbl,topScoreLbl,playerNameLbl;
    private static JLabel playerName;
    private JLabel mainMenuBtn,mainMenuLbl;
    private Font f1,f2,f3;
    private Vehicles myCar;
    private Vehicles [] vehicle;
    private int [] lanes;
    private Random rand;
    private javax.swing.Timer gameTimer,streetTimer;
    private javax.swing.Timer VehicleGenerator,FuelGenerator,BonusGenerator;
    private ArrayList movingVehicles,bonuses,fuels;
    private int easyScr,easyTopScr=0,hardScr,hardTopScr=0;
    private JLabel scrLbl,easyTopScrLbl,hardTopScrLbl,fuelLbl,fuelCap;
    private Vehicles fuel,bonus;
    private int fuelCounter=0,bonusCounter=0,scoreDoubler=1;
    private Difficulty mode=null;
    private TreeMap<Integer,String> hm=new TreeMap<Integer,String>(Collections.reverseOrder());
    private JProgressBar fuelBar;
    private JLabel black,dieLbl,dieScore,dieTopScore,replay,mainmenu,replayLbl,mainmenuLbl,dieScr,dieTopScr;
    private JPanel die;
    private double r=0,g=255,b=0;
    
    public StartGamePanel() throws IOException{
        init();
        layout();
        Bounds();
        font();
        foreground();
        add();
    }
    public void init() throws FileNotFoundException, IOException{
        dieScr=new JLabel();
        dieTopScr=new JLabel();
        black=new JLabel();
        dieLbl=new JLabel("Car Crashed!");
        dieScore=new JLabel("Score: ");
        dieTopScore=new JLabel("Top Score: ");
        replay=new JLabel();
        replayLbl=new JLabel("           Replay");
        mainmenu=new JLabel();
        mainmenuLbl=new JLabel("       Main Menu");
        die=new JPanel();
        fuelBar=new JProgressBar();
        street=new Street(0,-990,594,1980,"street.png");
        scoreLbl = new JLabel("      Score");
        topScoreLbl= new JLabel("  Top Score");
        playerNameLbl = new JLabel("  Player Name");
        playerName = new JLabel();
        mainMenuBtn = new JLabel();
        mainMenuLbl = new JLabel("       Main Menu");
        btnImg=new ImageIcon("./UI/btn1.png");
        btn2Img=new ImageIcon("./UI/btn2.png");
        btn3Img=new ImageIcon("./UI/btn3 - Copy.png");
        blk=new ImageIcon("./UI/Untitled-1.png");
        f1=new Font(scoreLbl.getText(),Font.BOLD,30);
        f2=new Font(mainMenuLbl.getText(),Font.BOLD,20);
        f3=new Font(mainMenuLbl.getText(),Font.BOLD,25);
        myCar=new Vehicles(18*14+6,18*41,84,180,"Audi.png",false,false);
        vehicle = new Vehicles[17];
        lanes=new int[5];
        movingVehicles=new ArrayList();
        fuels=new ArrayList();
        bonuses=new ArrayList();
        scrLbl=new JLabel("0");
        easyTopScrLbl=new JLabel("0");
        hardTopScrLbl=new JLabel("0");
        fuelLbl=new JLabel("Fuel");
        fuelCap= new JLabel("400");
        easyScr=1;
        easyTopScr=0;
        rand=new Random();
        lanes[0]=2*18;
        lanes[1]=8*18;
        lanes[2]=14*18;
        lanes[3]=20*18;
        lanes[4]=26*18;
        
        
        
        //##################################################################### GENERATORS ###############################################################
        
        
        streetTimer=new javax.swing.Timer(10,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                int strtSpd=10+easyScr/400*1;
                if(mode==Difficulty.HardMode)
                    strtSpd=14+easyScr/400*1;
                street.moveDown(strtSpd);
                if(street.y>=0){
                street.y=-990;
            }
            }
        });
    BonusGenerator=new javax.swing.Timer(15000,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                int vclSpd=0;
                int x=rand.nextInt(5);
                bonus=new Vehicles(lanes[x],-300,90,90,"bonus.png",true,false);
                int v=rand.nextInt(8);
                bonus.setBounds(0,0,990,990);
                bonus.setOpaque(false);
                if(mode==Difficulty.EasyMode)
                vclSpd=10+easyScr/400*1;
                else
                    vclSpd=14+hardScr/400*1;
                boolean flag=false;
                for(int i=0;i<movingVehicles.size();i++){
                   if(Collision(bonus, (Vehicles) movingVehicles.get(i)) || bonus.getImagePath().equals(((Vehicles) movingVehicles.get(i)).getImagePath()))
                       flag=true;
                       break;
                   }
                if(!flag)
                        generateVehicle(bonus,vclSpd);
            }
        });
        FuelGenerator=new javax.swing.Timer(10000,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                int vclSpd=0;
                int x=rand.nextInt(5);
                fuel=new Vehicles(lanes[x],-300,90,90,"jerry can.png",false,true);
                int v=rand.nextInt(8);
                fuel.setBounds(0,0,990,990);
                fuel.setOpaque(false);
                //vclSpd=7+scr/500*4;
                if(mode==Difficulty.HardMode)
                    vclSpd=14+hardScr/400*1;
                boolean flag=false;
                for(int i=0;i<movingVehicles.size();i++)
                   if(Collision(fuel, (Vehicles) movingVehicles.get(i)) || fuel.getImagePath().equals(((Vehicles) movingVehicles.get(i)).getImagePath())){
                       flag=true;
                       break;
                   }
                if(!flag)
                        generateVehicle(fuel,vclSpd);            
                }
        });
        VehicleGenerator=new javax.swing.Timer(700,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                int vclSpd=0;
                int x=rand.nextInt(5);
                vehicle[0]=new Vehicles(lanes[x],-300,90,198,"Black_viper.png",false,false);
                vehicle[1]=new Vehicles(lanes[x],-300,90,198,"Black_viper2.png",false,false);
                vehicle[2]=new Vehicles(lanes[x],-300,90,198,"Black_viper3.png",false,false);
                vehicle[3]=new Vehicles(lanes[x],-300,90,198,"Black_viper4.png",false,false);
                vehicle[4]=new Vehicles(lanes[x]+5,-300,77,180,"Car.png",false,false);
                vehicle[5]=new Vehicles(lanes[x]+5,-300,77,180,"Car2.png",false,false);
                vehicle[6]=new Vehicles(lanes[x]+5,-300,77,180,"Car3.png",false,false);
                vehicle[7]=new Vehicles(lanes[x]+5,-300,77,180,"Car4.png",false,false);
                vehicle[8]=new Vehicles(lanes[x],-300,90,166,"Mini_truck2.png",false,false);
                vehicle[9]=new Vehicles(lanes[x],-300,90,166,"Mini_truck3.png",false,false);
                vehicle[10]=new Vehicles(lanes[x],-300,90,166,"Mini_truck4.png",false,false);
                vehicle[11]=new Vehicles(lanes[x],-300,90,166,"Mini_truck5.png",false,false);
                vehicle[12]=new Vehicles(lanes[x],-300,90,192,"Mini_van.png",false,false);
                vehicle[13]=new Vehicles(lanes[x]+2,-300,82,180,"Police.gif",false,false);
                vehicle[14]=new Vehicles(lanes[x],-300,84,162,"taxi.png",false,false);
                vehicle[15]=new Vehicles(lanes[x],-300,90,232,"truck2.png",false,false);
                vehicle[16]=new Vehicles(lanes[x],-300,90,213,"Ambulance.gif",false,false);
                int v=rand.nextInt(17);
                vehicle[v].setBounds(0,0,990,990);
                vehicle[v].setOpaque(false);
                vclSpd=7+easyScr/400*1;
                if(mode==Difficulty.HardMode)
                    vclSpd=9+hardScr/200*1;
                boolean flag=false;
                for(int i=0;i<movingVehicles.size();i++)
                   if(Collision(vehicle[v], (Vehicles) movingVehicles.get(i)) || vehicle[v].getImagePath().equals(((Vehicles) movingVehicles.get(i)).getImagePath())){
                       flag=true;
                       break;
                   }
                if(!flag)
                        generateVehicle(vehicle[v],vclSpd);            
                }
        });
        gameTimer=new javax.swing.Timer(50,new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                
                if(mode==Difficulty.HardMode){
                    r+=0.6375;
                    g-=0.6375;
                    fuelBar.setForeground(new Color((int)r,(int)g,(int)b));
                    fuelCounter--;
                }

                //fuelCap.setText(fuelCounter+"");
                fuelBar.setValue(fuelCounter);
                if(mode==Difficulty.EasyMode){
                easyScr+=1*scoreDoubler;
                scrLbl.setText(easyScr+"");
                }
                else{
                    hardScr+=1*scoreDoubler;
                scrLbl.setText(hardScr+"");
                }
                if(bonusCounter<=0)
                    scoreDoubler=1;
                else
                    scoreDoubler=2;
                    
                if(fuelCounter<=0){
                    dieLbl.setText("  Out of Fuel!");
                    GamePanel.stopMusic();
                    playSound("./Audio/fuelLose.wav");
                           FileOutputStream fos = null;
                         try {
                            fos = new FileOutputStream("Hard.txt",true);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                                oos.writeObject(new player(playerName.getText(),hardScr));
                            }
                            catch (FileNotFoundException ex) {
                                Logger.getLogger(StartGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(StartGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                            }                         finally {
                            if (fos != null)
                             try {
                                 fos.close();
                            } catch (IOException ex) {
                                Logger.getLogger(StartGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         }
                    timeStopper();
                    myCar.setDied(true);
                    if(hardScr>hardTopScr){
                            hardTopScr=hardScr;
                            hardTopScrLbl.setText(hardScr+"");
                        }
                    black.setVisible(true);
                    dieScr.setText(hardScr+"");
                    dieTopScr.setText(hardTopScr+"");
                }
                for(int i=0;i<bonuses.size();i++)
                    if(Collision((Vehicles) bonuses.get(i),myCar)){
                        playSound("./Audio/bonus_sound.wav");
                        bonusCounter=200;
                        street.remove((Vehicles) bonuses.get(i));
                        bonuses.remove(i);
                    }
                bonusCounter--;
                for(int i=0;i<fuels.size();i++)
                    if(Collision((Vehicles) fuels.get(i),myCar)&&((Vehicles) fuels.get(i)).isFuel()&&mode==Difficulty.HardMode){
                        playSound("./Audio/fuel.wav");
                        r=0;
                        g=255;
                        fuelCounter=400;
                        street.remove((Vehicles) fuels.get(i));
                        fuels.remove(i);
                    }
                
                for(int i=0;i<movingVehicles.size();i++)
                    if(Collision((Vehicles) movingVehicles.get(i),myCar)){
                        GamePanel.stopMusic();
                       playSound("./Audio/carCrash.wav");
                        //here you die in both easy and hard if you hit a car
                        if(mode == Difficulty.EasyMode){
                         
                         FileOutputStream fos = null;
                         try {
                            fos = new FileOutputStream("Easy.txt",true);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                                oos.writeObject(new player(playerName.getText(),easyScr));
                            }
                            catch (FileNotFoundException ex) {
                                Logger.getLogger(StartGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(StartGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                            }                         finally {
                            if (fos != null)
                             try {
                                 fos.close();
                            } catch (IOException ex) {
                                Logger.getLogger(StartGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         }   
                        }
                        else
                            {
                                FileOutputStream fos = null;
                         try {
                            fos = new FileOutputStream("Hard.txt",true);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                                oos.writeObject(new player(playerName.getText(),hardScr));
                            }
                            catch (FileNotFoundException ex) {
                                Logger.getLogger(StartGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(StartGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                            }                         finally {
                            if (fos != null)
                             try {
                                 fos.close();
                            } catch (IOException ex) {
                                Logger.getLogger(StartGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         }
                            }
                        for(int j=0;j<movingVehicles.size();j++)
                            ((Vehicles) movingVehicles.get(j)).stopper();
                        timeStopper();
                        myCar.setDied(true);
                        if(easyScr>easyTopScr&&mode==Difficulty.EasyMode){
                            easyTopScr=easyScr;
                            easyTopScrLbl.setText(easyScr+"");
                        }
                        else if(hardScr>hardTopScr&&mode==Difficulty.HardMode){
                            hardTopScr=hardScr;
                            hardTopScrLbl.setText(hardScr+"");
                            
                        }
                        if(mode==Difficulty.HardMode){
                            black.setVisible(true);
                            dieScr.setText(hardScr+"");
                            dieTopScr.setText(hardTopScr+"");
                        }
                        else if(mode==Difficulty.EasyMode){
                            black.setVisible(true);
                            dieScr.setText(easyScr+"");
                            dieTopScr.setText(easyTopScr+"");
                        }
                    }
            }
        });
        
        
        //########################################################## MOUSE LISTENERS #########################################################################
        mainMenuBtn.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseEntered(MouseEvent e){
            if(!black.isVisible())
                mainMenuBtn.setIcon(btn2Img);
        }
            @Override
        public void mousePressed(MouseEvent e) {
            if(!black.isVisible()){
                mainMenuBtn.setIcon(btn3Img);
                mainMenuLbl.setForeground(Color.white);
            }
        }
            @Override
        public void mouseExited(MouseEvent e) {
            if(!black.isVisible())
        mainMenuBtn.setIcon(btnImg);
              }
        });
        
        
        mainmenu.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseEntered(MouseEvent e){
                mainmenu.setIcon(btn2Img);
        }
            @Override
        public void mousePressed(MouseEvent e) {
                mainmenu.setIcon(btn3Img);
                //mainmenuLbl.setForeground(Color.white);
        }
            @Override
        public void mouseExited(MouseEvent e) {
        mainmenu.setIcon(btnImg);
              }
        });
        replay.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseEntered(MouseEvent e){
                replay.setIcon(btn2Img);
        }
            @Override
        public void mousePressed(MouseEvent e) {
                replay.setIcon(btn3Img);
                //replayLbl.setForeground(Color.white);
        }
            @Override
        public void mouseExited(MouseEvent e) {
        replay.setIcon(btnImg);
              }
        });
        //####################################################################################################################################################
    }

    public void Bounds(){
        scrLbl.setBounds(870,100,200,50);
        easyTopScrLbl.setBounds(870,300,170,50);
        hardTopScrLbl.setBounds(870,300,170,50);
        fuelLbl.setBounds(850,400,170,50);
        fuelBar.setBounds(810,450,150,20);
        street.setBounds(203,0,594,990);
        myCar.setBounds(0,0,990,990);
        playerNameLbl.setBounds(0,50,200,50);
        playerName.setBounds(15,100,200,50);
        scoreLbl.setBounds(800,50,200,50);
        topScoreLbl.setBounds(800,250,170,50);
        mainMenuBtn.setBounds(15,900,200,50);
        black.setBounds(0,0,990,990);
        die.setBounds(245,245,500,500);
        dieLbl.setBounds(155, 20, 200, 50);
        dieScore.setBounds(20,150,200,50);
        dieTopScore.setBounds(20, 250, 200, 50);
        dieScr.setBounds(250,150,200,50);
        dieTopScr.setBounds(250, 250, 200, 50);
        replay.setBounds(275, 400, 200, 50);
        mainmenu.setBounds(25, 400, 200, 50);
    }
    public void add(){
        this.add(black);
        black.add(die);
        die.add(dieLbl);
        die.add(dieScore);
        die.add(dieTopScore);
        die.add(replay);
        die.add(mainmenu);
        die.add(dieScr);
        die.add(dieTopScr);
        replay.setLayout(new BorderLayout());
        mainmenu.setLayout(new BorderLayout());
        mainmenu.add(mainmenuLbl);
        replay.add(replayLbl);
        this.add(fuelBar);
        this.add(scrLbl);
        this.add(easyTopScrLbl);
        this.add(hardTopScrLbl);
        this.add(fuelLbl);
        this.add(fuelCap);
        this.add(playerName);
        this.add(street);
        this.add(scoreLbl);
        this.add(playerNameLbl);
        this.add(topScoreLbl);
        this.add(mainMenuBtn);
        street.add(myCar);
        mainMenuBtn.setLayout(new BorderLayout());
        mainMenuBtn.add(mainMenuLbl);
    }
    public void font(){
        scrLbl.setFont(f1);
        easyTopScrLbl.setFont(f1);
        hardTopScrLbl.setFont(f1);
        fuelLbl.setFont(f1);
        fuelCap.setFont(f1);
        playerName.setFont(f3);
        mainMenuLbl.setFont(f2);
        playerNameLbl.setFont(f1);
        scoreLbl.setFont(f1);
        topScoreLbl.setFont(f1);
        mainmenuLbl.setFont(f2);
        replayLbl.setFont(f2);
        dieLbl.setFont(f1);
        dieScore.setFont(f1);
        dieTopScore.setFont(f1);
        dieScr.setFont(f1);
        dieTopScr.setFont(f1);
    }
    public void foreground(){
        scrLbl.setForeground(Color.red);
        easyTopScrLbl.setForeground(Color.red);
        hardTopScrLbl.setForeground(Color.red);
        fuelCap.setForeground(Color.red);
        playerName.setForeground(Color.red);
        mainMenuLbl.setForeground(Color.red);
        this.setBackground(new Color(186,184,145));
        die.setBackground(new Color(186,184,145));
        mainmenuLbl.setForeground(Color.red);
        replayLbl.setForeground(Color.red);
        dieLbl.setForeground(Color.red);
        dieScr.setForeground(Color.red);
        dieTopScr.setForeground(Color.red);
    }
    public void layout(){
        black.setLayout(null);
        die.setLayout(null);
        
        this.setLayout(null);
        street.setLayout(null);
        street.setLayout(null);
        street.setOpaque(false);
        myCar.setOpaque(false);
        mainMenuBtn.setIcon(btnImg);
        black.setIcon(blk);
        fuelBar.setMinimum(0);
        fuelBar.setMaximum(400);
        mainmenu.setIcon(btnImg);
        replay.setIcon(btnImg);
    }
    public JLabel getMainMenuBtn(){
        return mainMenuBtn;
    }
    public JLabel getMainMenuLbl(){
        return mainMenuLbl;
    }
    public JLabel getmainmenu(){
        return mainmenu;
    }
    public JLabel getmainmenuLbl(){
        return mainmenuLbl;
    }
    public JLabel getReplay(){
        return replay;
    }
    public JLabel getReplayLbl(){
        return replayLbl;
    }
    public Vehicles getVehicle(){
        return myCar;
    }
    public JLabel getBlack(){
        return black;
    }
    public void generateVehicle(Vehicles moveVehicle,int spd){
        if(moveVehicle.isBonus()){
            bonuses.add(moveVehicle);
            street.add(moveVehicle);
            moveVehicle.moveDown(spd);
        }
        else if(moveVehicle.isFuel()){
            fuels.add(moveVehicle);
            street.add(moveVehicle);
            moveVehicle.moveDown(spd);
        }
        else{
            movingVehicles.add(moveVehicle);
            street.add(moveVehicle);
            moveVehicle.moveDown(spd);
        }
      for(int i=0;i<fuels.size();i++){
          Vehicles vcl=(Vehicles)fuels.get(i);
          if(vcl.y>900){
              street.remove((Vehicles)fuels.get(i));
              fuels.remove((Vehicles)fuels.get(i));
          }
      }
      for(int i=0;i<movingVehicles.size();i++){
          Vehicles vcl=(Vehicles)movingVehicles.get(i);
          if(vcl.y>900){
              street.remove((Vehicles)movingVehicles.get(i));
              movingVehicles.remove((Vehicles)movingVehicles.get(i));
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
        for(int i=0;i<movingVehicles.size();i++)
            ((Vehicles)movingVehicles.get(i)).stopper();
        for(int i=0;i<bonuses.size();i++)
            ((Vehicles)bonuses.get(i)).stopper();
        for(int i=0;i<fuels.size();i++)
            ((Vehicles)fuels.get(i)).stopper();
            gameTimer.stop();
            VehicleGenerator.stop();
            streetTimer.stop();
            if(mode==Difficulty.HardMode)
            FuelGenerator.stop();
            BonusGenerator.stop();

     }
    public void timeStarter(){
            gameTimer.start();
            VehicleGenerator.start();
            BonusGenerator.start();
            if(mode==Difficulty.HardMode)
            FuelGenerator.start();
            streetTimer.start();
    }
    public void reset() throws FileNotFoundException, IOException, ClassNotFoundException, LineUnavailableException, UnsupportedAudioFileException{
        //playSound("Alan_Walker_x_David_Whistle_-_Routine.wav");
        dieLbl.setText("Car Crashed!");
            scoreDoubler=1;
            bonusCounter=0;
            r=0;
            g=255;
            b=0;
                mode=NewGamePanel.getMode();
                black.setVisible(false);
            if(mode==Difficulty.HardMode){
                fuelLbl.setVisible(true);
                fuelCap.setVisible(true);
                hardTopScrLbl.setVisible(true);
                easyTopScrLbl.setVisible(false);
                fuelBar.setVisible(true);
            }
            else{
                hardTopScrLbl.setVisible(false);
                easyTopScrLbl.setVisible(true);
                    fuelLbl.setVisible(false);
                    fuelCap.setVisible(false);
                    fuelBar.setVisible(false);
                }
                fuelCounter=400;
                fuel=new Vehicles(0,-300,90,90,"jerry can.png",false,true);
                bonus=new Vehicles(0,-300,90,90,"bonus.png",true,false);
                vehicle[0]=new Vehicles(0,-300,90,198,"Black_viper.png",false,false);
                vehicle[1]=new Vehicles(0,-300,90,198,"Black_viper2.png",false,false);
                vehicle[2]=new Vehicles(0,-300,90,198,"Black_viper3.png",false,false);
                vehicle[3]=new Vehicles(0,-300,90,198,"Black_viper4.png",false,false);
                vehicle[4]=new Vehicles(0,-300,77,180,"Car.png",false,false);
                vehicle[5]=new Vehicles(0,-300,77,180,"Car2.png",false,false);
                vehicle[6]=new Vehicles(0,-300,77,180,"Car3.png",false,false);
                vehicle[7]=new Vehicles(0,-300,77,180,"Car4.png",false,false);
                vehicle[8]=new Vehicles(0,-300,90,166,"Mini_truck2.png",false,false);
                vehicle[9]=new Vehicles(0,-300,90,166,"Mini_truck3.png",false,false);
                vehicle[10]=new Vehicles(0,-300,90,166,"Mini_truck4.png",false,false);
                vehicle[11]=new Vehicles(0,-300,90,166,"Mini_truck5.png",false,false);
                vehicle[12]=new Vehicles(0,-300,90,192,"Mini_van.png",false,false);
                vehicle[13]=new Vehicles(0,-300,82,180,"Police.gif",false,false);
                vehicle[14]=new Vehicles(0,-300,84,162,"taxi.png",false,false);
                vehicle[15]=new Vehicles(0,-300,90,232,"truck2.png",false,false);
                vehicle[16]=new Vehicles(0,-300,90,213,"Ambulance.gif",false,false);

        for(int i=0;i<movingVehicles.size();i++){
            street.remove((Vehicles)movingVehicles.get(i));
        }
        for(int i=0;i<bonuses.size();i++){
            street.remove((Vehicles)bonuses.get(i));
        }
        for(int i=0;i<fuels.size();i++){
            street.remove((Vehicles)fuels.get(i));
        }
        movingVehicles.clear();
        bonuses.clear();
        fuels.clear();
        myCar.VehicleLocation(18*14,18*41);
        scrLbl.setText("0");
        hardScr=0;
        easyScr=0;
        //######################################################################################################
        FileInputStream fis =null;
        ArrayList players = new ArrayList();
    try {
        fis = new FileInputStream("Easy.txt");
        while (true) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            players.add(ois.readObject());
        }
    } catch (EOFException ignored) {
        // as expected
    } finally {
        if (fis != null)
            fis.close();
    }
    Collections.sort(players, new Comparator<player>() {
        @Override
        public int compare(player p1, player p2)
        {
            return ((Integer)p2.getScore()).compareTo((Integer)p1.getScore());
        }
    });
    if(players.size()>0){
    easyTopScr=((player)players.get(0)).getScore();
    easyTopScrLbl.setText(easyTopScr+"");
    }
    else{
        easyTopScr=0;
        easyTopScrLbl.setText("0");
    }
    
    
    
    //##########################################################################################################
    
    
    
     FileInputStream fis2 =null;
        ArrayList players2 = new ArrayList();
    try {
        fis2 = new FileInputStream("Hard.txt");
        while (true) {
            ObjectInputStream ois = new ObjectInputStream(fis2);
            players2.add(ois.readObject());
        }
    } catch (EOFException ignored) {
        // as expected
    } finally {
        if (fis2 != null)
            fis2.close();
    }
    Collections.sort(players2, new Comparator<player>() {
        @Override
        public int compare(player p1, player p2)
        {
            return ((Integer)p2.getScore()).compareTo((Integer)p1.getScore());
        }
    });
    if(players2.size()>0){
        hardTopScr=((player)players2.get(0)).getScore();
        hardTopScrLbl.setText(hardTopScr+"");
    }
    else{
        hardTopScr=0;
        hardTopScrLbl.setText("0");
    }
    
    //##########################################################################################################
        timeStarter();
    }
    public static void setPlayerName(String s){
            playerName.setText(s);

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