package Entity.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Animation implements AnimationHandler {
    private BufferedImage[] frames;
    private int spriteCounter = 0;
    private int currentFrame = 0;
    private int speed;
    private boolean loop;
    private int x, y;

    // Constructor to load frames from a single image
    public Animation(int frameCount, BufferedImage spriteSheet, int speed, boolean loop, int x, int y) {
        this.frames = new BufferedImage[frameCount];
        if (spriteSheet != null) {
            int xOffSet = 1;
            for (int i = 0; i < this.frames.length; i++) {
                frames[i] = spriteSheet.getSubimage((i * 16) + xOffSet, 1, 16, 16);
                xOffSet++;
            }
        }
        this.speed = speed;
        this.loop = loop;
        this.x = x;
        this.y = y;
    }

    public void playAnimation(Graphics2D g2) {
        spriteCounter++;
        if (spriteCounter > speed) {
            currentFrame++;
            if (loop && currentFrame >= frames.length) {
                currentFrame = 0;
            }
            spriteCounter = 0;
        }
        if (currentFrame < frames.length) {
            g2.drawImage(this.frames[currentFrame], x, y, 48, 48, null);
        } else {
            spriteCounter = 0;
            currentFrame = 0;
        }
    }

    public BufferedImage getFrame(int i) {
        return frames[i];
    }

    public void setFrame(int i, BufferedImage image) {
        if (i >= 0 && i < frames.length) {
            this.frames[i] = image;
        }
    }

    public void setCurrentFrame(int i) {
        if (i >= 0 && i < frames.length) {
            this.currentFrame = i;
        }
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSpriteCounter(int num) {
        this.spriteCounter = num;
    }

    public void setPosAndPlayAnimation(Graphics2D g2, int x, int y) {
        setPos(x, y);
        playAnimation(g2);
    }
}
