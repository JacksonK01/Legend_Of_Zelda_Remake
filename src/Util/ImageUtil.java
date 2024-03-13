package Util;

import java.awt.image.BufferedImage;

//Most sprite sheets only include one orientation of a sprite, so it's nice to have these to flip around images
public class ImageUtil {
    public static BufferedImage flipImageHorizontal(BufferedImage image) {
        BufferedImage copyOfImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        //r = y, c = x
        for(int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth(); c++) {
                copyOfImage.setRGB(c, r, image.getRGB(image.getWidth() - 1 - c, r));
            }
        }
        return copyOfImage;
    }

    public static BufferedImage flipImageVertical(BufferedImage image) {
        BufferedImage copyOfImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for(int y = 0; y < image.getHeight(); y++) {
            for(int x = 0; x < image.getWidth(); x++) {
                copyOfImage.setRGB(x, y, image.getRGB(x, image.getHeight() - y - 1));
            }
        }
        return copyOfImage;
    }
}
