package Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Sound {
    Clip clip;
    FloatControl volumeControl;
    HashMap<SoundEvents, URL> soundURL = new HashMap<>();

    public Sound() {
        soundURL.put(SoundEvents.OVERWORLD_THEME, getClass().getResource("/sounds/music/overworldTheme.wav"));

        soundURL.put(SoundEvents.GET_GENERIC_ITEM, getClass().getResource("/sounds/player/getItem.wav"));
        soundURL.put(SoundEvents.GET_RUPEE, getClass().getResource("/sounds/player/getRupee.wav"));
    }

    public void setFile(SoundEvents soundEvents) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL.get(soundEvents));
            clip = AudioSystem.getClip();
            clip.open(ais);

            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }

    public void changeVolume(float volume) {
        volumeControl.setValue(volume);
    }

    public enum SoundEvents {
        OVERWORLD_THEME,
        GET_GENERIC_ITEM,
        GET_RUPEE;
    }
}
