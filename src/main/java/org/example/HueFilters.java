package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;

public class HueFilters implements ImageFilters {
    final Class<Main> mainClass = Main.class;
    final ClassLoader classLoader = mainClass.getClassLoader();
    @Override
    public BufferedImage filer(BufferedImage bufferedImage) {
        final int height = bufferedImage.getHeight();
        final int width = bufferedImage.getWidth();
        int x=1;
        int y=1;

        Graphics2D g2 = bufferedImage.createGraphics();
        int srcWidth = bufferedImage.getHeight();
        int srcHeight = bufferedImage.getWidth();
        if (srcWidth > 0 && srcHeight > 0) {
            java.awt.image.ImageFilter cropFilter =  new CropImageFilter(x, y, width, height);
            Image img = Toolkit.getDefaultToolkit().createImage(
                    new FilteredImageSource(bufferedImage.getSource(), (java.awt.image.ImageFilter) cropFilter));

            g2.drawImage(img, 0, 0, width-50, height-50, null);
            g2.dispose();
        }
        return bufferedImage;

    } public File saveFile(BufferedImage bufferedImage) throws IOException {
        final String bothFiltersPath = classLoader.getResource("").getPath();
        final File bothFiltersFile = new File(bothFiltersPath + "/crop.jpg");
        HueFilters hueImage=new HueFilters();
        hueImage.filer(bufferedImage);
        FileWrite hueFilterWrite=new FileWrite(bothFiltersFile);
        hueFilterWrite.write(hueImage.filer(bufferedImage));
        return bothFiltersFile;

    }

}

