package org.example;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BothFilters implements ImageFilters{
    String typeOfColor;
    final Class<Main> mainClass = Main.class;
    final ClassLoader classLoader = mainClass.getClassLoader();
    @Override
    public BufferedImage filer(BufferedImage bufferedImage) {
        BlurFilters blurredForBothFilters=new BlurFilters();
        ColorFilters colorForBothFilters=new ColorFilters();
        colorForBothFilters.typeOfColor(typeOfColor);
        blurredForBothFilters.filer(bufferedImage);
        colorForBothFilters.filer(bufferedImage);


        return bufferedImage;
    }
    public File saveFile(BufferedImage bufferedImage) throws IOException {
        BlurFilters blurredForBothFilters=new BlurFilters();
        final String bothFiltersPath = classLoader.getResource("").getPath();
        final File bothFiltersFile = new File(bothFiltersPath + "/bothFilters.jpg");
        FileWrite bothFiltersWrite=new FileWrite(bothFiltersFile);
        bothFiltersWrite.write(blurredForBothFilters.filer(bufferedImage));
        return bothFiltersFile;

    }
}
