import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;

import javazoom.jl.player.Player;
public class MP3 {
    private String filename;
    private Player player;

    public MP3(String filename){

        this.filename = filename;
    }

    public void close(){if (player != null) player.close();}

    public void play(){
        try {
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (FileNotFoundException e) {
            System.out.println("Problems");
            System.out.println(e);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
        new Thread(){
            public void run(){
                try {player.play();}
                catch(Exception e){System.out.println(e);}
            }
        }.start();

    }
}
