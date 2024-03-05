package Item.Weapon;

import Item.AbstractItem;

import java.io.IOException;

public class WoodenSwordItem extends AbstractItem {
    public int damage;

    public WoodenSwordItem(int damage) {
        this.image = AbstractItem.itemSpriteSheet1.getSubimage(1, 1, 8, 16);
        this.name = "Wooden Sword";
        this.damage = damage;

        this.width = 8;
        this.height = 16;

    }
}
