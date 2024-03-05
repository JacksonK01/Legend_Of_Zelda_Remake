package Item;

import Game.GamePanel;
import Item.Rupee.BlueRupee;
import Item.Rupee.GreenRupee;
import Item.Rupee.RedRupee;
import Item.Weapon.WoodenSwordItem;

//Used for setting items in the world
public class ItemSetter {
    GamePanel gp;

    public ItemSetter(GamePanel gp) {
        this.gp = gp;
        setupItems();
    }

    public void setupItems() {
        gp.itemsList.add(new WoodenSwordItem(4));
        gp.itemsList.get(0).setPos(3, 3, gp);

        gp.itemsList.add(new GreenRupee());
        gp.itemsList.get(1).setPos(3, 5, gp);

        gp.itemsList.add(new BlueRupee());
        gp.itemsList.get(2).setPos(5, 5, gp);

        gp.itemsList.add(new RedRupee());
        gp.itemsList.get(3).setPos(7, 5, gp);
    }
}
