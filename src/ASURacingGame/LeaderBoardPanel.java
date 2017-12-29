
package ASURacingGame;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
public class LeaderBoardPanel extends JPanel {
    private JLabel easyPnl,hardPnl;
    private JLabel easyLbl,hardLbl;
    private JLabel hardName,hardScore,easyName,easyScore;
    private JLabel background;
    private JLabel backBtn,backLbl;
    private static JLabel [] easyPlayers;
    private static JLabel [] easyScores;
    private static JLabel [] hardPlayers;
    private static JLabel [] hardScores;
    private ImageIcon bgImg,btnImg,btn2Img,btn3Img,pnlImg;
    private Font f1,f2;
    
    public LeaderBoardPanel(){
        init();
    }
    public void init(){
        
            
        easyPnl=new JLabel();
        hardPnl=new JLabel();
        easyLbl=new JLabel("Easy");
        hardLbl=new JLabel("Hard");
        background=new JLabel();
        hardName=new JLabel("Name ");
        hardScore=new JLabel("Score ");
        easyName=new JLabel("Name ");
        easyScore=new JLabel("Score ");
        btnImg=new ImageIcon("./UI/btn1.png");
        bgImg=new ImageIcon("./UI/BG.png");
        btn2Img=new ImageIcon("./UI/btn2.png");
        btn3Img=new ImageIcon("./UI/btn3.png");
        pnlImg=new ImageIcon("./UI/score panel.png");
        backBtn=new JLabel();
        backLbl=new JLabel("            Back");
        f2=new Font(backLbl.getText(),Font.BOLD,20);
        f1=new Font(easyLbl.getText(),Font.BOLD,30);
        easyPlayers=new JLabel[10];
        easyScores=new JLabel[10];
        hardPlayers=new JLabel[10];
        hardScores=new JLabel[10];
        for(int i=0;i<10;i++){
            easyPlayers[i]=new JLabel("Player Name");
            easyScores[i]=new JLabel("0");
            hardPlayers[i]=new JLabel("Player Name");
            hardScores[i]=new JLabel("0");
        }
        for(int i=0;i<10;i++){
            easyPlayers[i].setBounds(20,100+50*i,200,50);
            easyScores[i].setBounds(250,100+50*i,200,50);
            hardPlayers[i].setBounds(20,100+50*i,200,50);
            hardScores[i].setBounds(250,100+50*i,200,50);
            
            easyPlayers[i].setFont(f2);
            easyScores[i].setFont(f2);
            hardPlayers[i].setFont(f2);
            hardScores[i].setFont(f2);
            
            easyPlayers[i].setForeground(Color.GRAY);
            easyScores[i].setForeground(Color.GRAY);
            hardPlayers[i].setForeground(Color.GRAY);
            hardScores[i].setForeground(Color.GRAY);
        }
        
        backBtn.setBounds(380,860,200,50);
        backBtn.setIcon(btnImg);
        backBtn.setLayout(new BorderLayout());
        backBtn.add(backLbl);
        backLbl.setFont(f2);
        backLbl.setForeground(Color.red);
        background.add(backBtn);
        background.setIcon(bgImg);
        easyPnl.setIcon(pnlImg);
        hardPnl.setIcon(pnlImg);
        this.setLayout(null);
        easyPnl.setLayout(null);
        hardPnl.setLayout(null);
        background.setLayout(null);
        background.setBounds(0, 0, 990, 990);
        this.add(background);
        background.add(easyPnl);
        background.add(hardPnl);
        easyPnl.add(easyLbl);
        hardPnl.add(hardLbl);
        easyLbl.setBounds(165,5,100,50);
        hardLbl.setBounds(165,5,100,50);
        easyPnl.setBounds(50,150,400,700);
        hardPnl.setBounds(530,150,400,700);
        hardName.setBounds(20,50,100,50);
        hardScore.setBounds(250,50,100,50);
        easyName.setBounds(20,50,100,50);
        easyScore.setBounds(250,50,100,50);
        easyPnl.add(easyName);
        easyPnl.add(easyScore);
        hardPnl.add(hardName);
        hardPnl.add(hardScore);
        easyLbl.setForeground(Color.red);
        hardLbl.setForeground(Color.red);
        hardLbl.setFont(f1);
        easyLbl.setFont(f1);
        hardName.setFont(f1);
        hardScore.setFont(f1);
        easyName.setFont(f1);
        easyScore.setFont(f1);
        for(int i=0;i<10;i++){
            hardPnl.add(hardPlayers[i]);
            hardPnl.add(hardScores[i]);
            easyPnl.add(easyPlayers[i]);
            easyPnl.add(easyScores[i]);
        }
        backBtn.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseEntered(MouseEvent e){
                backBtn.setIcon(btn2Img);
        }
            @Override
        public void mousePressed(MouseEvent e) {
                backBtn.setIcon(btn3Img);
                backLbl.setForeground(Color.white);
        }
            @Override
        public void mouseExited(MouseEvent e) {
        backBtn.setIcon(btnImg);
              }
        });
        
        }
      public static void Listeasy() throws IOException, ClassNotFoundException{
        ArrayList players = new ArrayList();
        //FileInputStream fis = new FileInputStream("Easy.txt");
        //ObjectInputStream ois = new ObjectInputStream(fis);
            /*player plr = (player)ois.readObject();
            System.out.println(plr.getName());
            players.add(plr);*/
        FileInputStream fis =null;
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
        for(int i = 0;i<players.size();i++)
        {
            if(i>=10)
                break;
            easyPlayers[i].setText((i+1)+""+". "+((player)players.get(i)).getName());
            easyScores[i].setText(""+((player)players.get(i)).getScore());
        }
    }
    public static void Listhard() throws FileNotFoundException, IOException, ClassNotFoundException{
        ArrayList players = new ArrayList();
        //FileInputStream fis = new FileInputStream("Hard.txt");
        //ObjectInputStream ois = new ObjectInputStream(fis);
            //player plr = (player)ois.readObject();
            //players.add(plr);
        FileInputStream fis =null;
    try {
        fis = new FileInputStream("Hard.txt");
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
        for(int i = 0;i<players.size();i++)
        {
            if(i>=10)
                break;
            hardPlayers[i].setText((i+1)+""+". "+((player)players.get(i)).getName());
            hardScores[i].setText(""+((player)players.get(i)).getScore());
        }
    }

/*public static int countLines(String filename) throws IOException {
    InputStream is = new BufferedInputStream(new FileInputStream(filename));
    try {
        byte[] c = new byte[1024];
        int count = 0;
        int readChars = 0;
        boolean empty = true;
        while ((readChars = is.read(c)) != -1) {
            empty = false;
            for (int i = 0; i < readChars; ++i) {
                if (c[i] == '\n') {
                    ++count;
                }
            }
        }
        return (count == 0 && !empty) ? 1 : count;
    } finally {
        is.close();
    }
}
*/
    public JLabel getBackBtn(){
        return backBtn;
    }
    public JLabel getBackLbl(){
        return backLbl;
    }
}
