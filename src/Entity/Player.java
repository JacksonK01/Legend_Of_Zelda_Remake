package Entity;

import Entity.Animation.Animation;
import Entity.Animation.AnimationHandler;
import Game.GamePanel;
import Game.Sound;
import Input.KeyHandler;
import Item.AbstractItem;
import Item.Rupee.AbstractRupee;
import Item.Weapon.WoodenSwordItem;
import Map.MapHandler;
import Map.Overworld;
import Util.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    public GamePanel gp;
    KeyHandler keyH;
    MapHandler currentMap;
    public AbstractItem[] inventory = new AbstractItem[10];
    public int wallet = 0;

    //This is used at the end of the draw method
    Animation currentAnimation;

    //I load in the animations for the walk cycle using this walkcycle variable.
    //I'd rather convert this into a factory method that can return a array of an instance
    Animation walkCycle;
    Animation walkSouth, walkEast, walkNorth, walkWest;

    Animation useWeapon;
    Animation useWeaponUp, useWeaponDown, useWeaponLeft, useWeaponRight;


    public final int screenX, screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.direction = "down";
        setDefaultValues();

        this.currentMap = new Overworld();

        int solidAreax = 8;
        int solidAreaY = 16;
        solidArea = new Rectangle(solidAreax, solidAreaY, 32, 32);

        this.screenX = this.gp.screenWidth/2 - gp.tileSize/2;
        this.screenY = this.gp.screenHeight/2 - gp.tileSize/2;

        this.solidAreaDefaultX = solidAreax;
        this.solidAreaDefaultY = solidAreaY;

        try {
            walkCycle = new Animation(6, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/player/playerWalkCycle.png"))),
                    0, false, 0, 0);
            useWeapon = new Animation(3, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/player/playerAttack.png"))),
                    12, false, screenX, screenY);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        walkSouth = new Animation(2, null, 12, true, screenX, screenY);
        walkSouth.setFrame(0, walkCycle.getFrame(0));
        walkSouth.setFrame(1, walkCycle.getFrame(1));

        walkEast = new Animation(2, null,12, true, screenX, screenY);
        walkEast.setFrame(0, walkCycle.getFrame(2));
        walkEast.setFrame(1, walkCycle.getFrame(3));

        walkWest = new Animation(2, null, 12, true, screenX, screenY);
        walkWest.setFrame(0, ImageUtil.flipImageHorizontal(walkCycle.getFrame(2)));
        walkWest.setFrame(1, ImageUtil.flipImageHorizontal(walkCycle.getFrame(3)));

        walkNorth = new Animation(2, null, 12, true, screenX, screenY);
        walkNorth.setFrame(0, walkCycle.getFrame(4));
        walkNorth.setFrame(1, walkCycle.getFrame(5));

        useWeaponDown = new Animation(1, null, 0, false, screenX, screenY);
        useWeaponDown.setFrame(0, useWeapon.getFrame(0));

        useWeaponRight = new Animation(1, null, 0, false, screenX, screenY);
        useWeaponRight.setFrame(0, useWeapon.getFrame(1));

        useWeaponUp = new Animation(1, null, 0, false, screenX, screenY);
        useWeaponUp.setFrame(0, useWeapon.getFrame(2));

        useWeaponLeft = new Animation(1, null, 0, false, screenX, screenY);
        useWeaponLeft.setFrame(0, ImageUtil.flipImageHorizontal(useWeapon.getFrame(1)));

        this.currentAnimation = walkSouth;
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
            this.currentAnimation = walkNorth;
        }
        else if (keyH.downPressed) {
            this.direction = "down";
            this.currentAnimation = walkSouth;
        }
        else if (keyH.rightPressed) {
            this.direction = "right";
            this.currentAnimation = walkEast;
        }
        else if (keyH.leftPressed) {
            this.direction = "left";
            this.currentAnimation = walkWest;
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

        } else {
            currentAnimation.setCurrentFrame(0);
            currentAnimation.setSpriteCounter(0);
        }
    }

    public void pickUpItem(int i) {
        if(i < gp.itemsList.size()) {
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

    public void draw(Graphics2D g2) {
        if(keyH.interactPressed) {
            currentAnimation = useWeapon;
        }
        currentAnimation.playAnimation(g2);
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
