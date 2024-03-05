package Tile;

import Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TileManager {

    GamePanel gp;

    public static HashMap<Integer, Tile> spriteTilesMap = new HashMap<>();
    //Sprite sheets for each section, named them after seasons because I wanted to
    public static BufferedImage basicSheet, springSheet, summerSheet, fallSheet, winterSheet;

    static {
        try {
            basicSheet = ImageIO.read(TileManager.class.getResourceAsStream("/overworld/BasicOverworldTiles.png"));
            springSheet = ImageIO.read(TileManager.class.getResourceAsStream("/overworld/SpringOverworldTiles.png"));
            summerSheet = ImageIO.read(TileManager.class.getResourceAsStream("/overworld/SummerOverworldTiles.png"));
            //Note: this is one pixel less than the others. This one is not my fault.
            fallSheet = ImageIO.read(TileManager.class.getResourceAsStream("/overworld/FallOverworldTiles.png"));
            winterSheet = ImageIO.read(TileManager.class.getResourceAsStream("/overworld/WinterOverworldTiles.png"));
            getTileImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public TileManager(GamePanel gp) {
        this.gp = gp;
    }

    public static void getTileImage() {
        int imageWidth = basicSheet.getWidth()/16;
        int imageHeight = basicSheet.getHeight()/16;
        int i = 0;
        //These offsets are only needed for this sprite sheet
        int yOffSet = 1;
        for(int y = 0; y < imageHeight && i <= 41; y++) {
            int xOffSet = 1;
            for(int x = 0; x < imageWidth && i <= 41; x++) {
                spriteTilesMap.put(i, new Tile(basicSheet.getSubimage((x * 16) + xOffSet, (y * 16) + yOffSet, 16, 16)));
                //It's easier to find the tiles that don't have collision and just add them to this condition
                spriteTilesMap.get(i).collision = !(i == 0 || i == 7 || i == 8 || i == 9 || i == 16 || i == 17 || i == 33 || i == 34 || i == 35 || i == 37);
                spriteTilesMap.get(i).isEntrance = (i == 34 || i == 35);
                i++;
                xOffSet++;
            }
            yOffSet++;
        }

        imageWidth = springSheet.getWidth()/16;
        imageHeight = springSheet.getHeight()/16;
        int previousSize = spriteTilesMap.size();
        i = 0;
        for(int y = 0; y < imageHeight; y++) {
            for(int x = 0; x < imageWidth; x++) {
                int j = i + previousSize;
                spriteTilesMap.put(j, new Tile(springSheet.getSubimage(x * 16, y * 16, 16, 16)));
                spriteTilesMap.get(j).collision = !(i == 4 || i == 6 || i == 7 || i == 8 || i == 9 || i == 17 || i == 34 || i == 35 || i == 37);
                i++;
            }
        }

        imageWidth = summerSheet.getWidth()/16;
        imageHeight = summerSheet.getHeight()/16;
        previousSize = spriteTilesMap.size();
        i = 0;
        for(int y = 0; y < imageHeight; y++) {
            for(int x = 0; x < imageWidth; x++) {
                int j = i + previousSize;
                spriteTilesMap.put(j, new Tile(summerSheet.getSubimage(x * 16, y * 16, 16, 16)));
                spriteTilesMap.get(j).collision = !(i == 4 || i == 6 || i == 7 || i == 8 || i == 9 || i == 17 || i == 34 || i == 35 || i == 37);
                i++;
            }
        }

        imageWidth = winterSheet.getWidth()/16;
        imageHeight = winterSheet.getHeight()/16;
        previousSize = spriteTilesMap.size();
        i = 0;
        for(int y = 0; y < imageHeight; y++) {
            for(int x = 0; x < imageWidth; x++) {
                int j = i + previousSize;
                spriteTilesMap.put(j, new Tile(winterSheet.getSubimage(x * 16, y * 16, 16, 16)));
                spriteTilesMap.get(j).collision = !(i == 4 || i == 6 || i == 7 || i == 8 || i == 9 || i == 17 || i == 34 || i == 35 || i == 37);
                i++;
            }
        }

        imageWidth = fallSheet.getWidth()/16;
        imageHeight = fallSheet.getHeight()/16;
        previousSize = spriteTilesMap.size();
        i = 0;
        for(int y = 0; y < imageHeight; y++) {
            for(int x = 0; x < imageWidth; x++) {
                int j = i + previousSize;
                spriteTilesMap.put(j, new Tile(fallSheet.getSubimage(x * 16, y * 16, 16, 16)));
                spriteTilesMap.get(j).collision = !(i == 4 || i == 6 || i == 7 || i == 8 || i == 9 || i == 17 || i == 34 || i == 35 || i == 37);
                i++;
            }
        }

    }

    public void draw(Graphics2D g2) {
        for (int y = 0; y < gp.player.getCurrentMap().getMapHeight(); y++) {
            for (int x = 0; x < gp.player.getCurrentMap().getMapLength(); x++) {
                //Study up on this math for the camera
                int screenX = (x * gp.tileSize) - gp.player.worldX + gp.player.screenX;
                int screenY = (y * gp.tileSize) - gp.player.worldY + gp.player.screenY;
                if ((0 <= screenX + gp.tileSize && screenX <= gp.screenWidth) && (0 <= screenY + gp.tileSize && screenY <= gp.screenHeight)) {
                    g2.drawImage(spriteTilesMap.get(gp.player.getCurrentMap().getSpot(x, y)).image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
                }
            }
        }
    }

    public enum TileType  {
        //All the basic tiles
        SAND(0),
        LEFT_TO_RIGHT_SLANTED_ROCK_UPWARD(1),
        SHORT_ROCK_TILE(2),
        RIGHT_TO_LEFT_SLANTED_ROCK_UPWARD(3),
        TOP_LEFT_POND(4),
        TOP_MIDDLE_POND(5),
        TOP_RIGHT_POND(6),
        TOP_LEFT_ISLAND(7),
        TOP_RIGHT_ISLAND(8),
        SAND_WITH_ROCKS(9),
        LEFT_TO_RIGHT_SLANTED_ROCK_DOWNWARD(10),
        ROCK_WALL(11),
        RIGHT_TO_LEFT_SLANTED_ROCK_DOWNWARD(12),
        MIDDLE_LEFT_POND(13),
        MIDDLE_CENTER_POND(14),
        MIDDLE_RIGHT_POND(15),
        BOTTOM_LEFT_ISLAND(16),
        BOTTOM_RIGHT_ISLAND(17),
        ROCK_FORMATION(18),
        BASIC_TREE(19),
        BASIC_STATUE(20),
        BASIC_GRAVESTONE(21),
        BOTTOM_LEFT_POND(22),
        BOTTOM_MIDDLE_POND(23),
        BOTTOM_RIGHT_POND(24),
        SANDY_WATER1(25),
        SANDY_WATER2(26),
        TOP_LEFT_TREE(27),
        TOP_MIDDLE_TREE(28);

        private final int value;

        TileType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static TileType getByValue(int value) {
            for (TileType type : TileType.values()) {
                if (type.getValue() == value) {
                    return type;
                }
            }
            throw new IllegalArgumentException("No enum constant with value " + value);
        }
    }
//public void draw(Graphics2D g2) {
//    int mapWidth = gp.player.getCurrentMap().getMapLength() * gp.tileSize;
//    int mapHeight = gp.player.getCurrentMap().getMapHeight() * gp.tileSize;
//
//    for (int y = 0; y < gp.player.getCurrentMap().getMapHeight(); y++) {
//        for (int x = 0; x < gp.player.getCurrentMap().getMapLength(); x++) {
//            // Calculate screen coordinates for the tile
//            int screenX = (x * gp.tileSize) - gp.player.worldX + gp.player.screenX;
//            int screenY = (y * gp.tileSize) - gp.player.worldY + gp.player.screenY;
//
//            // Check if the tile is within the bounds of the screen and map
//            if (screenX + gp.tileSize > 0 && screenX < gp.screenWidth &&
//                    screenY + gp.tileSize > 0 && screenY < gp.screenHeight &&
//                    x >= 0 && x < gp.player.getCurrentMap().getMapLength() &&
//                    y >= 0 && y < gp.player.getCurrentMap().getMapHeight()) {
//                // Draw the tile if it's within bounds
//                g2.drawImage(this.tiles.get(gp.player.getCurrentMap().getSpot(x, y)).image,
//                        screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
//            }
//        }
//    }
// }

}
