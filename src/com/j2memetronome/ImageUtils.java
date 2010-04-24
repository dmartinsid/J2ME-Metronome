/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2memetronome;

import javax.microedition.lcdui.Image;

/**
 *
 * @author dmartins
 */
public class ImageUtils {

    public static Image rotateImage(Image image, int angle) {
        if (angle == 0) {
            return image;
        }

        int width = image.getWidth();
        int height = image.getHeight();

        int[] rowData = new int[width];
        int[] rotatedData = new int[width * height];

        int rotatedIndex = 0;

        for (int i = 0; i < height; i++) {
            image.getRGB(rowData, 0, width, 0, i, width, 1);

            for (int j = 0; j < width; j++) {
                rotatedIndex =
                        angle == 90 ? (height - i - 1) + j * height : (angle == 270 ? i + height * (width - j - 1) : width * height - (i * width + j) - 1);

                rotatedData[rotatedIndex] = rowData[j];
            }
        }

        if (angle == 90 || angle == 270) {
            return Image.createRGBImage(rotatedData, height, width, true);
        } else {
            return Image.createRGBImage(rotatedData, width, height, true);
        }
    }
}
