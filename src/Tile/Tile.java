package Tile;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean collision = true;
    public boolean isEntrance = false;

    public Tile() {}

    public Tile(BufferedImage image) {
        this.image = image;
    }
}
