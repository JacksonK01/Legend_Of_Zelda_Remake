package Item.Rupee;

import Item.AbstractItem;

public class GreenRupee extends AbstractRupee{
    public GreenRupee() {
        this.value = 1;
        this.worldSprite = AbstractItem.itemSpriteSheet2.getSubimage(70, 0, 10, 16);
        this.setCustomTextureSize(7, 10);
    }
}
