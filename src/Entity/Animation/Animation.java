package Entity.Animation;

import Entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

//Don't use this, yet.
public class Animation {
    private BufferedImage[] frames;
    Entity entity;

    public Animation(int amountOfFrames, Entity entity) {
        this.frames = new BufferedImage[amountOfFrames];
        this.entity = entity;
    }

    public void playAnimation(Graphics2D g2) {
        for(int i = 0; i < frames.length; i++) {
            g2.drawImage(this.frames[i], entity.worldX, entity.worldY, 48, 48, null);
        }
    }
}
