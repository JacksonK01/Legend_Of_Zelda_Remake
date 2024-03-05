package Item.Entrance;

import Item.AbstractItem;
import Tile.TileManager;

public class CaveOpening extends AbstractItem {
    public CaveOpening() {
        this.image = TileManager.spriteTilesMap.get(34).image;
        this.collision = true;
    }
}
