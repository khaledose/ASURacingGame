package ASURacingGame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
final class LengthRestrictedDocument extends PlainDocument {

  private final int limit;

  public LengthRestrictedDocument(int limit) {
    this.limit = limit;
  }

  @Override
  public void insertString(int offs, String str, AttributeSet a)
      throws BadLocationException {
    if (str == null)
      return;

    if ((getLength() + str.length()) <= limit) {
      super.insertString(offs, str, a);
    }
  }
}
public class NewGamePanel extends JPanel{
    private JLabel backgroundPnl,startBtn,backBtn,easy,hard;
    private JLabel startLbl,backLbl,blackPnl,logo;
    private static JTextField name;
    private ImageIcon bgImg,btnImg,btn2Img,btn3Img,easych,easynch,hardch,hardnch,blackImg,blk,logoImg;
    private Font f,f2;
    boolean checked1,checked2;
    private static Difficulty diff=null; 
    private JPanel msg;
    private JLabel msgLbl,msgLbl2,okBtn,okLbl,black;
    public NewGamePanel(){
        init();
    }
    public void init(){
        logoImg=new ImageIcon("logo.png");
        black=new JLabel();
        blk=new ImageIcon("Untitled-1.png");
        msg=new JPanel();
        msgLbl=new JLabel("Please, Enter name");
        msgLbl2=new JLabel("and difficulty.");
        okBtn=new JLabel();
        okLbl=new JLabel("             Ok");
    backgroundPnl=new JLabel();
    logo=new JLabel();
    name = new JTextField("PlayerName");
    easy = new JLabel();
    hard = new JLabel();
    btnImg=new ImageIcon("btn1.png");
    bgImg=new ImageIcon("BG.png");
    btn2Img=new ImageIcon("btn2.png");
    btn3Img=new ImageIcon("btn3.png");
    easych=new ImageIcon("easych.png");
    easynch=new ImageIcon("easy.png");
    hardch=new ImageIcon("hardch.png");
    hardnch=new ImageIcon("hard.png");
    blackImg=new ImageIcon("black.png");
    startBtn = new JLabel();
    backBtn = new JLabel();
    startLbl = new JLabel("            Start");
    backLbl = new JLabel("            Back");
    blackPnl=new JLabel();
    f=new Font(startLbl.getText(),Font.BOLD,20);
    f2=new Font(startLbl.getText(),Font.BOLD,30);
    checked1=false;
    checked2=false;
    
    this.setLayout(null);
    backgroundPnl.setLayout(null);
    startBtn.setLayout(new BorderLayout());
    backBtn.setLayout(new BorderLayout());
    this.add(backgroundPnl);
    blackPnl.setLayout(null);
    msg.setLayout(null);
    okBtn.setLayout(new BorderLayout());
    msg.setBackground(new Color(186,184,145));
    
    backgroundPnl.setIcon(bgImg);
    startBtn.setIcon(btnImg);
    backBtn.setIcon(btnImg);
    easy.setIcon(easynch);
    hard.setIcon(hardnch);
    blackPnl.setIcon(blackImg);
    okBtn.setIcon(btnImg);
    black.setIcon(blk);
    logo.setIcon(logoImg);
    
    startLbl.setFont(f);
    backLbl.setFont(f);
    name.setFont(f);
    okLbl.setFont(f);
    msgLbl.setFont(f2);
    msgLbl2.setFont(f2);
    
    
    
    startLbl.setForeground(Color.red);
    backLbl.setForeground(Color.red);
    name.setForeground(Color.LIGHT_GRAY); 
    okLbl.setForeground(Color.red);
    
    backgroundPnl.add(black);
    black.add(msg);
    blackPnl.add(logo);
    backgroundPnl.add(blackPnl);
    blackPnl.add(name);
    blackPnl.add(easy);
    blackPnl.add(hard);
    blackPnl.add(startBtn);
    blackPnl.add(backBtn);
    startBtn.add(startLbl);
    backBtn.add(backLbl);
    msg.add(msgLbl);
    msg.add(okBtn);
    msg.add(msgLbl2);
    okBtn.add(okLbl);
    black.setVisible(false);
    
    blackPnl.setBounds(195,195,600,600);
    backgroundPnl.setBounds(0,0,990,990);
    name.setBounds(150,200,300,40);
    easy.setBounds(200,300,200,40);
    hard.setBounds(200,400,200,40);
    startBtn.setBounds(90,500,200,50);
    backBtn.setBounds(310,500,200,50);
    msg.setBounds(295,295,400,400);
    okBtn.setBounds(100,300,200,50);
    msgLbl.setBounds(50,50,400,50);
    msgLbl2.setBounds(100,100,400,50);
    black.setBounds(0,0,990,990);
    logo.setBounds(50,0,500,200);
    this.addMouseListener(new MouseAdapter(){
        @Override
      public void mouseClicked(MouseEvent e){
          if(name.getText().equals("")){
                  name.setText("PlayerName");
                  name.setForeground(Color.LIGHT_GRAY); 
          }
      }
    });
    name.addMouseListener(new MouseAdapter(){
      @Override
      public void mouseClicked(MouseEvent e){
          
          if(name.getText().equals("PlayerName")){
              name.setDocument(new LengthRestrictedDocument(10));
          name.setText("");
          name.setForeground(Color.red);
          }
      }
  });
    startBtn.addMouseListener(new MouseAdapter(){
             @Override
        public void mouseEntered(MouseEvent e){
            if(!black.isVisible())
                startBtn.setIcon(btn2Img);
        }
            @Override
        public void mousePressed(MouseEvent e) {
            if(!black.isVisible()){
            StartGamePanel.setPlayerName(name.getText());
                startBtn.setIcon(btn3Img);
                startLbl.setForeground(Color.white);
            }
        }
            @Override
        public void mouseExited(MouseEvent e) {
            if(!black.isVisible())
        startBtn.setIcon(btnImg);
              }
        @Override
        public void mouseClicked(MouseEvent e){
            if(name.getText().equals("")){
                  name.setText("PlayerName");
                  name.setForeground(Color.LIGHT_GRAY); 
          }
        }
        });
    backBtn.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseEntered(MouseEvent e){
            if(!black.isVisible())
                backBtn.setIcon(btn2Img);
        }
            @Override
        public void mousePressed(MouseEvent e) {
            if(name.getText().equals("")){
                  name.setText("PlayerName");
                  name.setForeground(Color.LIGHT_GRAY); 
          }
            if(!black.isVisible()){
                backBtn.setIcon(btn3Img);
                backLbl.setForeground(Color.white);
            }
        }
            @Override
        public void mouseExited(MouseEvent e) {
            if(!black.isVisible()){
        backBtn.setIcon(btnImg);
            }
              }
        @Override
        public void mouseClicked(MouseEvent e){
            if(name.getText().equals("")){
                  name.setText("PlayerName");
                  name.setForeground(Color.LIGHT_GRAY); 
          }
        }
        });
    okBtn.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
            if(name.getText().equals("")){
                  name.setText("PlayerName");
                  name.setForeground(Color.LIGHT_GRAY); 
          }
        }
            @Override
        public void mouseEntered(MouseEvent e){
                okBtn.setIcon(btn2Img);
        }
            @Override
        public void mousePressed(MouseEvent e) {
                okBtn.setIcon(btn3Img);
                okLbl.setForeground(Color.white);
        }
            @Override
        public void mouseExited(MouseEvent e) {
        okBtn.setIcon(btnImg);
              }
        });
    easy.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
            if(name.getText().equals("")){
                  name.setText("PlayerName");
                  name.setForeground(Color.LIGHT_GRAY); 
          }
            if(diff==null&&!black.isVisible()){
                easy.setIcon(easych);
                diff=Difficulty.EasyMode;
            }
            else if(diff==Difficulty.HardMode&&!black.isVisible()){
                easy.setIcon(easych);
                hard.setIcon(hardnch);
                diff=Difficulty.EasyMode;
            }
        }
    });
    hard.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
            if(name.getText().equals("")){
                  name.setText("PlayerName");
                  name.setForeground(Color.LIGHT_GRAY); 
          }
            if(diff==null&&!black.isVisible()){
                hard.setIcon(hardch);
                diff=Difficulty.HardMode;
            }
            else if(diff==Difficulty.EasyMode&&!black.isVisible()){
                hard.setIcon(hardch);
                easy.setIcon(easynch);
                diff=Difficulty.HardMode;
            }
        }
    });
        }
        public JLabel getBackBtn(){
                return backBtn;
        }
        public JLabel getBackLbl(){
            return backLbl;
        }
        public JLabel getStartBtn(){
            return startBtn;
        }
        public JLabel getStartLbl(){
            return startLbl;
        }
        public JLabel getEasy(){
            return easy;
        }
        public JLabel getHard(){
            return hard;
        }
        public static String getPlayerName(){
            return name.getText();
        }
        public static Difficulty getMode(){
            return diff;
        }
        public static void setMode(Difficulty mode){
            diff=mode;
        }
        public JLabel getOkBtn(){
            return okBtn;
        }
        public JLabel getOkLbl(){
            return okLbl;
        }
        public JLabel getMsg(){
            return black;
        }
        public JTextField getTextField(){
            return name;
        }
}