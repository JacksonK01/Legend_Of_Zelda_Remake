package Item.Rupee;

import Item.AbstractItem;

public class BlueRupee extends AbstractRupee{
    public BlueRupee() {
        this.value = 5;
        this.worldSprite = AbstractItem.itemSpriteSheet2.getSubimage(70, 16, 10, 16);
        this.setCustomTextureSize(7, 10);
    }
}
