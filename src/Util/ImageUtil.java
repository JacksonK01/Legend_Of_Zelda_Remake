package Util;

import java.awt.image.BufferedImage;

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
}
