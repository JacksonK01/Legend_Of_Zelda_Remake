package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public int worldX, worldY;
    public int speed;
    public String direction;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = true;

    public BufferedImage spriteSheet;

    public Entity() {

    }

    public abstract void setDefaultValues();

    public int getWorldX() {
        return this.worldX;
    }

    public int getWorldY() {
        return this.worldY;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void moveUp() {
        this.worldY -= this.speed;
    }

    public void moveDown() {
        this.worldY += this.speed;
    }

    public void moveRight() {
        this.worldX += this.speed;
    }

    public void moveLeft() {
        this.worldX -= this.speed;
    }


}
