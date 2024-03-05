package Item.Rupee;

import Item.AbstractItem;

public class RedRupee extends AbstractRupee{
    public RedRupee() {
        this.value = 20;
        this.image = AbstractItem.itemSpriteSheet2.getSubimage(70, 32, 10, 16);
        this.setCustomTextureSize(7, 10);
    }
}
