package Entity;

import Game.GamePanel;
import Game.Sound;
import Input.KeyHandler;
import Item.AbstractItem;
import Item.Rupee.AbstractRupee;
import Item.Rupee.BlueRupee;
import Item.Rupee.GreenRupee;
import Item.Rupee.RedRupee;
import Item.Weapon.WoodenSwordItem;
import Map.MapHandler;
import Map.Overworld;
import Util.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    BufferedImage north1, north2, east1, east2, south1, south2, west1, west2;
    int spriteCounter = 0;
    int spriteState = 0;
    MapHandler currentMap;
    public AbstractItem[] inventory = new AbstractItem[10];
    public int wallet = 0;

    public final int screenX, screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.direction = "down";
        setDefaultValues();
        try {
            this.spriteSheet = getPlayerImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        south1 = spriteSheet.getSubimage(1, 11, 16, 16);
        south2 = spriteSheet.getSubimage(18, 11, 16, 16);
        east1 = spriteSheet.getSubimage(35, 11, 16, 16);
        east2 = spriteSheet.getSubimage(52, 11, 16, 16);
        north1 = spriteSheet.getSubimage(69, 11, 16, 16);
        north2 = spriteSheet.getSubimage(86, 11, 16, 16);
        west1 = ImageUtil.flipImageHorizontal(east1);
        west2 = ImageUtil.flipImageHorizontal(east2);

        this.currentMap = new Overworld();

        int solidAreax = 8;
        int solidAreaY = 16;
        solidArea = new Rectangle(solidAreax, solidAreaY, 32, 32);

        this.screenX = this.gp.screenWidth/2 - gp.tileSize/2;
        this.screenY = this.gp.screenHeight/2 - gp.tileSize/2;

        this.solidAreaDefaultX = solidAreax;
        this.solidAreaDefaultY = solidAreaY;
    }

    @Override
    public void setDefaultValues() {
        this.worldX = 200;
        this.worldY = 750;
        this.speed = 3;
    }

    public void update() {
        if (keyH.upPressed) {
            this.direction = "up";
        }
        else if (keyH.downPressed) {
            this.direction = "down";
        }
        else if (keyH.rightPressed) {
            this.direction = "right";
        }
        else if (keyH.leftPressed) {
            this.direction = "left";
        }

        // IF COLLISION IS FALSE PLAYER CAN MOVE
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        int Itemidx = gp.collisionChecker.checkItem(this, true);
        pickUpItem(Itemidx);

        if (keyH.isAnyMoveKeyPressed()) {
            if (!collisionOn) {
                switch(direction) {
                    case "up": moveUp(); break;
                    case "down": moveDown(); break;
                    case "left": moveLeft(); break;
                    case "right": moveRight(); break;
                }
            }
            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteState == 1) {
                    spriteState = 0;
                } else {
                    spriteState = 1;
                }
                spriteCounter = 0;
            }
        } else {
            spriteState = 0;
            spriteCounter = 0;
        }
    }

    public void pickUpItem(int i) {
        if(i != gp.itemsList.size() + 1) {
            AbstractItem item = gp.itemsList.get(i);
            if(item instanceof AbstractRupee) {
                wallet += ((AbstractRupee) item).value;
                gp.playSoundEffect(Sound.SoundEvents.GET_RUPEE);
            } else if (item instanceof WoodenSwordItem) {
                addItemToInventory(item);
                gp.playSoundEffect(Sound.SoundEvents.GET_GENERIC_ITEM);
            } else if (item != null) {
                gp.playSoundEffect(Sound.SoundEvents.GET_GENERIC_ITEM);
            }
            gp.itemsList.remove(i);
        }
    }

    public BufferedImage getPlayerImage() throws IOException {
        return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/player/LinkSpriteSheet.png")));
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch (direction) {
            case "down":
                if (spriteState == 0) {
                    image = this.south1;
                }
                if (spriteState == 1) {
                    image = this.south2;
                }
                break;
            case "up":
                if (spriteState == 0) {
                    image = this.north1;
                }
                if (spriteState == 1) {
                    image = this.north2;
                }
                break;
            case "right":
                if (spriteState == 0) {
                    image = this.east1;
                }
                if (spriteState == 1) {
                    image = this.east2;
                }
                break;
            case "left":
                if (spriteState == 0) {
                    image = this.west1;
                }
                if (spriteState == 1) {
                    image = this.west2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, this.gp.getTileSize(), this.gp.getTileSize(), null);
    }

    public MapHandler getCurrentMap() {
        return this.currentMap;
    }

    public void addItemToInventory(AbstractItem item) {
        boolean keepLooping = true;
        for(int i = 0; keepLooping; i++) {
            if(inventory[i] == null) {
                inventory[i] = item;
                keepLooping = false;
            }
        }
    }

    public String[] InventoryToString() {
        String[] out = new String[inventory.length];
        for(int i = 0; i < this.inventory.length; i++) {
            out[i] = (this.inventory[i] == null) ? i + ": empty" : i + ": " + inventory[i].name;
        }
        return out;
    }
}
