package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BlurFilters implements ImageFilters {
    final Class<Main> mainClass = Main.class;
    final ClassLoader classLoader = mainClass.getClassLoader();


    int getBlurredPixel(BufferedImage image, int x, int y, int radius) {

        int side = radius * 2 + 1;
        int pixelCount = side * side - 1;

        float brightnessSum = 0.0f;


        for (int row = -radius; row <= radius; row++) {
            for (int col = -radius; col <= radius; col++) {
                if (row == 0 && col == 0) {
                    continue;
                }
                final int rgb = image.getRGB(x + col, y + row);
                final Color color = new Color(rgb);

                final float[] hsbColor =
                        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
                final float brightness = hsbColor[2];
                brightnessSum += brightness;

            }
        }


        final float brightnessAvg = brightnessSum / pixelCount;
        final int currentPixel = image.getRGB(x, y);
        final Color currentPxColor = new Color(currentPixel);
        final float[] currentPxHsb = Color.RGBtoHSB(currentPxColor.getRed(), currentPxColor.getGreen(),
                currentPxColor.getBlue(), null);
        currentPxHsb[2] = brightnessAvg;
        final int calcuatedPixel = Color.HSBtoRGB(currentPxHsb[0], currentPxHsb[1], currentPxHsb[2]);
        return calcuatedPixel;
    }


    @Override
    public BufferedImage filer(BufferedImage bufferedImage) {
        final int height = bufferedImage.getHeight();
        final int width = bufferedImage.getWidth();
        final int rgb = bufferedImage.getRGB(0, 0);
        int blurRadius = 2;

        final BufferedImage blurredImage = new BufferedImage(width, height, bufferedImage.getType());
        for (int x = blurRadius; x < width - blurRadius; x++) {
            for (int y = blurRadius; y < height - blurRadius; y++) {

                BlurFilters calculatedPixel = new BlurFilters();
                blurredImage.setRGB(x, y, calculatedPixel.getBlurredPixel(bufferedImage, x, y, blurRadius));
            }
        }return blurredImage;


    }
    public File saveFile(BufferedImage bufferedImage) throws IOException {
        BlurFilters blurredImage=new BlurFilters();
        final String blurPath = classLoader.getResource("").getPath();
        final File blurFile = new File(blurPath + "/blurred.jpg");
        FileWrite fileWrite=new FileWrite(blurFile);

        fileWrite.write(blurredImage.filer(bufferedImage));
        return blurFile;
    }
}

