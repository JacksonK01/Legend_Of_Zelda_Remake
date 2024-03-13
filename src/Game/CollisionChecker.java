package Game;

import Entity.Entity;
import Tile.TileManager;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entityLeftWorldX + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.x;
        int entityBottomWorldY = entityTopWorldY + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.player.getCurrentMap().getSpot(entityLeftCol, entityTopRow);
                tileNum2 = gp.player.getCurrentMap().getSpot(entityRightCol, entityTopRow);
                if(TileManager.spriteTilesMap.get(tileNum1).collision || TileManager.spriteTilesMap.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.player.getCurrentMap().getSpot(entityLeftCol, entityBottomRow);
                tileNum2 = gp.player.getCurrentMap().getSpot(entityRightCol, entityBottomRow);
                if(TileManager.spriteTilesMap.get(tileNum1).collision || TileManager.spriteTilesMap.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.player.getCurrentMap().getSpot(entityLeftCol, entityTopRow);
                tileNum2 = gp.player.getCurrentMap().getSpot(entityLeftCol, entityBottomRow);
                if(TileManager.spriteTilesMap.get(tileNum1).collision || TileManager.spriteTilesMap.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.player.getCurrentMap().getSpot(entityRightCol, entityTopRow);
                tileNum2 = gp.player.getCurrentMap().getSpot(entityRightCol, entityBottomRow);
                if(TileManager.spriteTilesMap.get(tileNum1).collision || TileManager.spriteTilesMap.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
    public <T extends Entity> int checkItem(T entity, boolean isPlayer) {
        //Will throw an error if the item doesn't exist
        int idx = gp.itemsList.size() + 1;

        for(int i = 0; i < gp.itemsList.size(); i++) {
            if (gp.itemsList.get(i) != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.itemsList.get(i).solidArea.x = gp.itemsList.get(i).worldX + gp.itemsList.get(i).solidArea.x;
                gp.itemsList.get(i).solidArea.y = gp.itemsList.get(i).worldY + gp.itemsList.get(i).solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.itemsList.get(i).solidArea)) {
                            if (gp.itemsList.get(i).collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                idx = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.itemsList.get(i).solidArea)) {
                            if (gp.itemsList.get(i).collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                idx = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.itemsList.get(i).solidArea)) {
                            if (gp.itemsList.get(i).collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                idx = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.itemsList.get(i).solidArea)) {
                            if (gp.itemsList.get(i).collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                idx = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.itemsList.get(i).solidArea.x = gp.itemsList.get(i).solidAreaDefaultX;
                gp.itemsList.get(i).solidArea.y = gp.itemsList.get(i).solidAreaDefaultY;
            }
        }
        return idx;
    }
}
