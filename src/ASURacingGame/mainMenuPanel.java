package ASURacingGame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class mainMenuPanel extends JPanel{
        private JLabel newgameBtn,multiplayerBtn,scoresBtn,exitBtn,backgroundPnl,blackPnl;
        private JLabel newgameLbl,multiplayerLbl,scoresLbl,exitLbl;
        private ImageIcon btnImg,BGImg,btn2Img,btn3Img,blackImg,logoImg;
        private Font f;
        private JLabel logo;
        
        public mainMenuPanel(){
            init();
        }
        public void init(){
            logoImg=new ImageIcon("./UI/logo.png");
            logo=new JLabel();
            btnImg=new ImageIcon("./UI/btn1.png");
        BGImg=new ImageIcon("./UI/BG.png");
        btn2Img=new ImageIcon("./UI/btn2.png");
        btn3Img=new ImageIcon("./UI/btn3.png");
        blackImg=new ImageIcon("./UI/black.png");
        blackPnl=new JLabel();
        backgroundPnl=new JLabel();
        newgameBtn =new JLabel();
        multiplayerBtn =new JLabel();
        scoresBtn =new JLabel();
        exitBtn =new JLabel();
        
        newgameLbl = new JLabel("      Singleplayer");
        multiplayerLbl = new JLabel("        Multiplayer");
        scoresLbl = new JLabel("      Leaderboard");
        exitLbl = new JLabel("             Exit");
        
        f=new Font(newgameLbl.getText(),Font.BOLD,20);
        
        this.setLayout(null);
        this.add(backgroundPnl);
        
        newgameBtn.setLayout(new BorderLayout());
        multiplayerBtn.setLayout(new BorderLayout());
        scoresBtn.setLayout(new BorderLayout());
        exitBtn.setLayout(new BorderLayout());
        backgroundPnl.setLayout(null);
        blackPnl.setLayout(null);
        
        newgameLbl.setForeground(Color.red);
        multiplayerLbl.setForeground(Color.red);
        scoresLbl.setForeground(Color.red);
        exitLbl.setForeground(Color.red);
        
        newgameLbl.setFont(f);
        multiplayerLbl.setFont(f);
        scoresLbl.setFont(f);
        exitLbl.setFont(f);
        
        newgameBtn.add(newgameLbl);
        multiplayerBtn.add(multiplayerLbl);
        scoresBtn.add(scoresLbl);
        exitBtn.add(exitLbl);
        backgroundPnl.add(blackPnl);
        blackPnl.add(newgameBtn);
        blackPnl.add(multiplayerBtn);
        blackPnl.add(scoresBtn);
        blackPnl.add(exitBtn);
        blackPnl.add(logo);
        
        backgroundPnl.setIcon(BGImg);
        newgameBtn.setIcon(btnImg);
        multiplayerBtn.setIcon(btnImg);
        scoresBtn.setIcon(btnImg);
        exitBtn.setIcon(btnImg);
        blackPnl.setIcon(blackImg);
        logo.setIcon(logoImg);
        
       blackPnl.setBounds(195,195,600,600);
        backgroundPnl.setSize(990,990);
        newgameBtn.setSize(190,50);
        multiplayerBtn.setSize(190,50);
        scoresBtn.setSize(190,50);
        exitBtn.setSize(190,50);
        logo.setBounds(50,0,500,200);
        
        backgroundPnl.setLocation(0,0);
        newgameBtn.setLocation(205,200);
        multiplayerBtn.setLocation(205,300);
        scoresBtn.setLocation(205,400);
        exitBtn.setLocation(205,500);
        
    newgameBtn.addMouseListener(new MouseAdapter(){
             @Override
        public void mouseEntered(MouseEvent e){
                newgameBtn.setIcon(btn2Img);
        }
            @Override
        public void mousePressed(MouseEvent e) {
                newgameBtn.setIcon(btn3Img);
                newgameLbl.setForeground(Color.white);
        }
            @Override
        public void mouseExited(MouseEvent e) {
        newgameBtn.setIcon(btnImg);
              }
        });
    multiplayerBtn.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseEntered(MouseEvent e){
                multiplayerBtn.setIcon(btn2Img);
        }
            @Override
        public void mousePressed(MouseEvent e) {
                multiplayerBtn.setIcon(btn3Img);
                multiplayerLbl.setForeground(Color.white);
        }
            @Override
        public void mouseReleased(MouseEvent e) {
              multiplayerBtn.setIcon(btnImg);
              multiplayerLbl.setForeground(Color.red);
        }
            @Override
        public void mouseExited(MouseEvent e) {
        multiplayerBtn.setIcon(btnImg);
              }
        });
    scoresBtn.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseEntered(MouseEvent e){
                scoresBtn.setIcon(btn2Img);
        }
            @Override
        public void mousePressed(MouseEvent e) {
                scoresBtn.setIcon(btn3Img);
                scoresLbl.setForeground(Color.white);
        }
            @Override
        public void mouseExited(MouseEvent e) {
        scoresBtn.setIcon(btnImg);
              }
        });
    exitBtn.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseEntered(MouseEvent e){
                exitBtn.setIcon(btn2Img);
        }
            @Override
        public void mousePressed(MouseEvent e) {
                exitBtn.setIcon(btn3Img);
                exitLbl.setForeground(Color.white);
        }
            @Override
        public void mouseReleased(MouseEvent e) {
              exitBtn.setIcon(btnImg);
              exitLbl.setForeground(Color.red);
              System.exit(0);
        }
            @Override
        public void mouseExited(MouseEvent e) {
        exitBtn.setIcon(btnImg);
              }
        });
        }
        public JLabel getNewGameBtn(){
            return newgameBtn;
        }
        public JLabel getNewGameLbl(){
            return newgameLbl;
        }
        public JLabel getLeaderBoardBtn(){
            return scoresBtn;
        }
        public JLabel getLeaderBoardLbl(){
            return scoresLbl;
        }
        public JLabel getMPBtn(){
            return multiplayerBtn;
        }
        public JLabel getMPLbl(){
            return multiplayerLbl;
        }
}