import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public MyFrame(){

        super("MusicPlayer");
        JPanel buttonPanel = new ButtonPanel();
        ImageIcon music = new ImageIcon("music.jpg");
        setIconImage(music.getImage());
        add(buttonPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setVisible(true);
        setResizable(false);

    }
}
