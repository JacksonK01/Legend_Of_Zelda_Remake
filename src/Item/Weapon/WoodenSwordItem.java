package Item.Weapon;

import Item.AbstractItem;
import Util.ImageUtil;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class WoodenSwordItem extends AbstractItem {
    public int damage;
    //The upSprite is redundant but makes it easier to read
    public BufferedImage upSprite, downSprite, leftSprite, rightSprite;

    public WoodenSwordItem(int damage) {
        this.worldSprite = AbstractItem.itemSpriteSheet1.getSubimage(1, 1, 8, 16);
        this.name = "Wooden Sword";
        this.damage = damage;

        this.upSprite = worldSprite;
        this.downSprite = ImageUtil.flipImageVertical(upSprite);
        this.rightSprite = AbstractItem.itemSpriteSheet1.getSubimage(10, 1, 8, 16);
        this.leftSprite = ImageUtil.flipImageHorizontal(rightSprite);

        this.width = 8;
        this.height = 16;

    }
}
