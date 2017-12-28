
package ASURacingGame;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.sound.sampled.*;


class RaceFrame extends JFrame{
   GamePanel MNPanel;
   ImageIcon frameIcon;
   
    public RaceFrame() throws FileNotFoundException, IOException, LineUnavailableException, UnsupportedAudioFileException{
        init();
    }
    public void init() throws FileNotFoundException, IOException, LineUnavailableException, UnsupportedAudioFileException{
        frameIcon= new ImageIcon("icon.png");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("ASU Racing");
        this.setSize(990,990);
        Container c=this.getContentPane();
        MNPanel=new GamePanel();
        c.add(MNPanel);
        c.setLayout(null);
        MNPanel.setLocation(0,0);
        MNPanel.setSize(990,990);
        setIconImage(frameIcon.getImage());
    }
}
public class JavaApplication29 {

    public static void main(String[] args) throws FileNotFoundException, IOException, LineUnavailableException, UnsupportedAudioFileException {
        RaceFrame f = new RaceFrame();
        f.setVisible(true);
    }
}