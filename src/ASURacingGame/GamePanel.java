package ASURacingGame;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import javax.sound.sampled.*;
import javax.swing.*;
enum Difficulty{ EasyMode , HardMode };
enum GameMode{Singleplayer,Multiplayer};
public class GamePanel extends JPanel{
    private mainMenuPanel pnl1;
    private NewGamePanel pnl2;
    private StartGamePanel pnl3;
    private LeaderBoardPanel pnl4;
    private MultiplayerPanel pnl5;
    private ImageIcon bgImg,btnImg,btn2Img,btn3Img,easynch,hardnch,inf,logo;
    private Vehicles myCar,redCar,blueCar;
    private Timer t;
    private JLabel easy,hard;
    private int end=0;
    private GameMode GM=null;
    private JLabel team,bigLogo,cont,intro;
    private Font f;
    File soundFile;
    AudioInputStream audioStream;
    AudioFormat format;
    DataLine.Info info;
    static Clip audioClip;
   
    public GamePanel() throws IOException, LineUnavailableException, UnsupportedAudioFileException{
        init();
    }
    public void init() throws IOException, LineUnavailableException, UnsupportedAudioFileException{
        bigLogo=new JLabel();
        intro=new JLabel();
        f=new Font("",Font.BOLD,30);
        logo=new ImageIcon("./UI/big iogo.png");
        cont=new JLabel("Click to Continue");
        team=new JLabel();
        inf = new ImageIcon("./UI/inf.gif");
        pnl1=new mainMenuPanel();
        pnl2=new NewGamePanel();
        pnl3=new StartGamePanel();
        pnl4=new LeaderBoardPanel();
        pnl5=new MultiplayerPanel();
        btnImg=new ImageIcon("UI/btn1.png");
        bgImg=new ImageIcon("./UI/BG.png");
        btn2Img=new ImageIcon("./UI/btn2.png");
        btn3Img=new ImageIcon("./UI/btn3.png");
        easynch=new ImageIcon("./UI/easy.png");
        hardnch=new ImageIcon("./UI/hard.png");
        easy=pnl2.getEasy();
        hard=pnl2.getHard();
        team.setIcon(inf);
        team.setBounds(0,0,990,990);
        bigLogo.setIcon(logo);
        bigLogo.setBounds(0,0,990,990);
        cont.setFont(f);
        cont.setForeground(Color.white);
        cont.setBounds(360,720,400,50);
        intro.setLayout(null);
        intro.setBounds(0, 0, 990, 990);
        intro.setIcon(bgImg);
        this.add(team);
        this.add(intro);
        intro.add(bigLogo);
        intro.add(cont);
        intro.setVisible(false);

        pnl1.setVisible(false);
        t=new Timer(100,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                end++;
                if(end==55){
                    t.stop();
                    try {
                    playMusic("./Audio/mainMenu.wav");
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                    //team.setVisible(false);
                    remove(team);
                    intro.setVisible(true);
                    
                }
            }
        });
        t.start();
        
        JLabel newgameBtn=pnl1.getNewGameBtn();
        JLabel newgameLbl=pnl1.getNewGameLbl();
        JLabel backLbl=pnl2.getBackLbl();
        JLabel backBtn=pnl2.getBackBtn();
        JLabel startBtn = pnl2.getStartBtn();
        JLabel startLbl = pnl2.getStartLbl();
        JLabel mainMenuBtn = pnl3.getMainMenuBtn();
        JLabel mainMenuLbl = pnl3.getMainMenuLbl();
        JLabel scoresBtn=pnl1.getLeaderBoardBtn();
        JLabel backBtn2=pnl4.getBackBtn();
        JLabel backLbl2=pnl4.getBackLbl();
        JLabel scoresLbl=pnl1.getLeaderBoardLbl();
        JLabel multiplayerBtn=pnl1.getMPBtn();
        JLabel multiplayerLbl=pnl1.getMPLbl();
        JLabel mainmenu=pnl5.getMainMenuBtn();
        JLabel mainmenuLbl=pnl5.getMainMenuLbl();
        JLabel replay=pnl5.getReplayBtn();
        JLabel replayLbl=pnl5.getReplayLbl();
        JLabel msg=pnl2.getMsg();
        JTextField name=pnl2.getTextField();
        JLabel okBtn=pnl2.getOkBtn();
        JLabel okLbl=pnl2.getOkLbl();
        JLabel mainmenu2=pnl3.getmainmenu();
        JLabel mainmenu2Lbl=pnl3.getmainmenuLbl();
        JLabel replay2=pnl3.getReplay();
        JLabel replay2Lbl=pnl3.getReplayLbl();
        JLabel black=pnl3.getBlack();
        myCar=pnl3.getVehicle();
        redCar=pnl5.getRedCar();
        blueCar=pnl5.getBlueCar();
        intro.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                playSound("./Audio/buttons.wav");
                remove(intro);
                pnl1.setVisible(true);
            }
        });
        team.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                t.stop();
                remove(team);
                intro.setVisible(true);
                try {
                    playMusic("./Audio/mainMenu.wav");
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                int key=e.getKeyCode();                
                if(key==KeyEvent.VK_RIGHT&&myCar.x<24*18&&!myCar.isDied()&&GM==GameMode.Singleplayer){
                           myCar.moveRight(26);
                    }
                else if(key==KeyEvent.VK_LEFT&&myCar.x>2*18&&!myCar.isDied()&&GM==GameMode.Singleplayer){
                myCar.moveLeft(2);
                }
                
                if(key==KeyEvent.VK_LEFT&&blueCar.x>30*18&&!blueCar.isDied()&&GM==GameMode.Multiplayer){
                    blueCar.moveLeft(30);
                }
                else if(key==KeyEvent.VK_RIGHT&&blueCar.x<48*18&&!blueCar.isDied()&&GM==GameMode.Multiplayer){
                    blueCar.moveRight(48);
                }
                
                if(key==KeyEvent.VK_A&&redCar.x>2*18&&!redCar.isDied()&&GM==GameMode.Multiplayer){
                    redCar.moveLeft(2);
                }
                else if(key==KeyEvent.VK_D&&redCar.x<18*18&&!redCar.isDied()&&GM==GameMode.Multiplayer){
                    redCar.moveRight(20);
                }
                if(key==KeyEvent.VK_ESCAPE&&GM==GameMode.Multiplayer){
                    audioClip.stop();
                    try {
                    playMusic("./Audio/mainMenu.wav");
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                    GM=null;
                    pnl5.timeStopper();
                    remove(pnl5);
                    add(pnl1);
                    pnl5.setBounds(0,0,0,0);
                    pnl1.setBounds(0,0,990,990);
                }
                    
            }
            public void keyReleased(KeyEvent e){
                int key=e.getKeyCode();    
                if(GM==GameMode.Singleplayer&&(key==KeyEvent.VK_LEFT||key==KeyEvent.VK_RIGHT))
                myCar.stopper();
                if(GM==GameMode.Multiplayer&&(key==KeyEvent.VK_LEFT||key==KeyEvent.VK_RIGHT))
                    blueCar.stopper();
                if(GM==GameMode.Multiplayer&&(key==KeyEvent.VK_A||key==KeyEvent.VK_D))
                        redCar.stopper();
            }
        
        });

        this.setFocusable(true);
        this.add(pnl1);
        this.setLayout(null);
        pnl1.setBounds(0,0,990,990);
        scoresBtn.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e){
                playSound("./Audio/buttons.wav");
                try {
                    LeaderBoardPanel.Listeasy();
                } catch (IOException ex) {
                    //Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    //Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    LeaderBoardPanel.Listhard();
                } catch (IOException ex) {
                    //Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    //Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                scoresBtn.setIcon(btnImg);
                scoresLbl.setForeground(Color.red);
                remove(pnl1);
                add(pnl4);
                pnl1.setBounds(0,0,0,0);
                pnl4.setBounds(0,0,990,990);
            }
        });
        newgameBtn.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseReleased(MouseEvent e) {
            playSound("./Audio/buttons.wav");
              msg.setVisible(false);
            name.setVisible(true);
              easy.setIcon(easynch);
              hard.setIcon(hardnch);
              NewGamePanel.setMode(null);
              newgameBtn.setIcon(btnImg);
              newgameLbl.setForeground(Color.red);
              remove(pnl1);
              add(pnl2);
              pnl1.setBounds(0,0,0,0);
              pnl2.setBounds(0,0,990,990);
            }
        });
    backBtn.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseReleased(MouseEvent e) {
            playSound("./Audio/buttons.wav");
            if(!msg.isVisible()){
              msg.setVisible(false);
              name.setVisible(true);
              backBtn.setIcon(btnImg);
              backLbl.setForeground(Color.red);
              remove(pnl2);
              add(pnl1);
              pnl2.setBounds(0,0,0,0);
              pnl1.setBounds(0,0,990,990);
            }
        }
        });
    mainmenu.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseReleased(MouseEvent e) {
            audioClip.stop();
            playSound("./Audio/buttons.wav");
            try {
                    playMusic("./Audio/mainMenu.wav");
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            GM=null;
            NewGamePanel.setMode(null);
            msg.setVisible(false);
            name.setVisible(true);
              mainmenu.setIcon(btnImg);
              mainmenuLbl.setForeground(Color.red);
              remove(pnl5);
              add(pnl1);
              pnl5.setBounds(0,0,0,0);
              pnl1.setBounds(0,0,990,990);
            }
        });
    mainmenu2.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseReleased(MouseEvent e) {
            audioClip.stop();
            playSound("./Audio/buttons.wav");
            try {
                    playMusic("./Audio/mainMenu.wav");
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            GM=null;
            NewGamePanel.setMode(null);
            black.setVisible(false);
              mainmenu2.setIcon(btnImg);
              mainmenu2Lbl.setForeground(Color.red);
              remove(pnl3);
              add(pnl1);
              pnl3.setBounds(0,0,0,0);
              pnl1.setBounds(0,0,990,990);
            }
        });
    replay.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseReleased(MouseEvent e) {
            audioClip.stop();
            playSound("./Audio/buttons.wav");
            try {
                    playMusic("./Audio/game.wav");
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
              replay.setIcon(btnImg);
              replayLbl.setForeground(Color.red);
              pnl5.reset();
            }
        });
    replay2.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseReleased(MouseEvent e) {
            audioClip.stop();
            playSound("./Audio/buttons.wav");
            try {
                    playMusic("./Audio/game.wav");
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            black.setVisible(false);
              replay2.setIcon(btnImg);
              replay2Lbl.setForeground(Color.red);
                try {
                    pnl3.reset();
                } catch (IOException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
              myCar.setDied(false);
            }
        });
    backBtn2.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseReleased(MouseEvent e) {
            playSound("./Audio/buttons.wav");
              backBtn2.setIcon(btnImg);
              backLbl2.setForeground(Color.red);
              remove(pnl4);
              add(pnl1);
              pnl4.setBounds(0,0,0,0);
              pnl1.setBounds(0,0,990,990);
            }
        });
    okBtn.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseReleased(MouseEvent e) {
            playSound("./Audio/buttons.wav");
              okBtn.setIcon(btnImg);
              okLbl.setForeground(Color.red);
              msg.setVisible(false);
              name.setVisible(true);
            }
        });
    
    startBtn.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseReleased(MouseEvent e) {
            playSound("./Audio/buttons.wav");
            GM=GameMode.Singleplayer;
            if(NewGamePanel.getMode()!=null&&!NewGamePanel.getPlayerName().equals("PlayerName")&&!NewGamePanel.getPlayerName().equals("")&&!msg.isVisible()){
                 audioClip.stop();
                try {
                    playMusic("./Audio/game.wav");
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                name.setVisible(true);
                msg.setVisible(false);
              startBtn.setIcon(btnImg);
              startLbl.setForeground(Color.red);
              remove(pnl2);

                try {
                    pnl3.reset();
                } catch (IOException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
              add(pnl3);
              pnl2.setBounds(0,0,0,0);
              pnl3.setBounds(0,0,990,990);
              myCar.setDied(false);
            }
            else{
                msg.setVisible(true);
                name.setVisible(false);
                startBtn.setIcon(btnImg);
                startLbl.setForeground(Color.red);
                myCar.setDied(false);
            }
            }
        });
    multiplayerBtn.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseReleased(MouseEvent e){
            playSound("./Audio/buttons.wav");
            audioClip.stop();
            try {
                playMusic("./Audio/game.wav");
            } catch (LineUnavailableException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            GM=GameMode.Multiplayer;
            pnl5.reset();
            multiplayerBtn.setIcon(btnImg);
              multiplayerLbl.setForeground(Color.red);
              remove(pnl1);
              add(pnl5);
              pnl1.setBounds(0,0,0,0);
              pnl5.setBounds(0,0,990,990);
        }
    });
    mainMenuBtn.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseReleased(MouseEvent e) {
            
            if(!black.isVisible()){
                audioClip.stop();
            
                try {
                    playMusic("./Audio/mainMenu.wav");
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                playSound("./Audio/buttons.wav");
            GM=null;
              mainMenuBtn.setIcon(btnImg);
              mainMenuLbl.setForeground(Color.red);
              pnl3.timeStopper();
              remove(pnl3);
              add(pnl1);
              pnl3.setBounds(0,0,0,0);
              pnl1.setBounds(0,0,990,990);
            }
            }
        });
    }
    public void playMusic(String path) throws LineUnavailableException, IOException, UnsupportedAudioFileException{
        String bip = path;
        soundFile = new File(bip);
        audioStream = AudioSystem.getAudioInputStream(soundFile);

        format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        audioClip = (Clip) AudioSystem.getLine(info);

        audioClip.open(audioStream);
        audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        audioClip.start();
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
    public static void stopMusic(){
        audioClip.stop();
    }
    public void stopTimer(){
        t.stop();
    }

}