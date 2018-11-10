
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel implements ActionListener {

    public static final int HEIGHT = 150;
    public static final int WIDTH = 375;
    private JLabel tytul;
    private JButton PlayButton,StopButton,OpenButton,ExitButton;
    private BufferedImage tlo= null;
    private boolean lastSong = false;
    private boolean openedSong = false;
    MP3 song ;


    public ButtonPanel(){

        ImageIcon playimage = new ImageIcon("play.png");
        ImageIcon stopimage = new ImageIcon("stop.png");
        ImageIcon openimage = new ImageIcon("open.png");
        ImageIcon exitimage = new ImageIcon("exit.png");
        try {
            tlo = ImageIO.read(new File("tlo.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        PlayButton = new JButton(playimage);
        StopButton = new JButton(stopimage);
        OpenButton = new JButton(openimage);
        ExitButton= new JButton(exitimage);
        ExitButton.setLocation(100,50);
        tytul      = new JLabel("Wybierz utwór");
        tytul.setFont(new Font("Serif",Font.ITALIC,14));


        PlayButton.setUI(new StyledButtonUI());
        StopButton.setUI(new StyledButtonUI());
        OpenButton.setUI(new StyledButtonUI());




        PlayButton.setBackground(new Color(255, 255, 255));
        StopButton.setBackground(new Color(255, 255, 255));
        OpenButton.setBackground(new Color(255, 255, 255));

        OpenButton.addActionListener(this);
        PlayButton.addActionListener(this);
        StopButton.addActionListener(this);
        ExitButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.CENTER));
        tytul.setLocation(80,100);
        setPreferredSize(new Dimension(tlo.getWidth()-10,tlo.getHeight()-50));
        add(OpenButton);
        add(PlayButton);
        add(StopButton);

        add(tytul);

        song = null;


    }


    private void OpenButtonActionPerformed(ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser("C:\\Users\\krasno\\Desktop\\java\\MusicPlayer");
        int result = fileChooser.showOpenDialog(null);
        File selectedFile = fileChooser.getSelectedFile();
        System.out.println("Wybrany "+ selectedFile.getAbsolutePath());

        tytul.setText(selectedFile.getName());

        song = new MP3(selectedFile.getAbsolutePath());
        openedSong = true;
    }

    private void StopButtonActionPerformed(ActionEvent evt) {

        song.close();
        lastSong = false;
    }

    private void PlayButtonActionPerformed(ActionEvent evt) {
        if (song!=null){
            song.play();
        }
        if(openedSong ==true){
             lastSong = true;
        }
    }
    private void ExitButtonActionPerformed(ActionEvent evt) {
        System.exit(1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == PlayButton && lastSong == false)
        {
            PlayButtonActionPerformed(e);
        }else if(e.getSource() == StopButton ){
            StopButtonActionPerformed(e);
        }else if(e.getSource() == OpenButton) {
            if(lastSong == false){
                 OpenButtonActionPerformed(e);
            }
        }else if(e.getSource() == ExitButton) {
            ExitButtonActionPerformed(e);
        }

    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(tlo, 0 ,0,this);
    }


}
