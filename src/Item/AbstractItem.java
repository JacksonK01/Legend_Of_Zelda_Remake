package Item;

import Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class AbstractItem {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    //Override these variables if the item has a custom texture size
    public int width = 16;
    public int height = 16;

    public static BufferedImage itemSpriteSheet1, itemSpriteSheet2;

    static {
        try {
            itemSpriteSheet1 = ImageIO.read(AbstractItem.class.getResourceAsStream("/items/ItemSpriteSheet1.png"));
            itemSpriteSheet2 = ImageIO.read(AbstractItem.class.getResourceAsStream("/items/ItemSpriteSheet2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if ((0 <= screenX + gp.tileSize && screenX <= gp.screenWidth) && (0 <= screenY + gp.tileSize && screenY <= gp.screenHeight)) {
            g2.drawImage(this.image, screenX, screenY, gp.scale * this.width, gp.scale * this.height, null);
        }
    }

    //Calculates pos with tile size
    public void setPos(int x, int y, GamePanel gp) {
        this.worldX = x * gp.tileSize;
        this.worldY = y * gp.tileSize;
    }

    //Custom Item Size
    public void setCustomTextureSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
