package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWrite implements ImageWriter {
    public File file;

    public FileWrite(File file) {
        this.file = file;
    }

    @Override
    public void write(BufferedImage bufferedImage) throws IOException {
        final FileOutputStream blurredImageOutputStream = new FileOutputStream(file);
        ImageIO.write(bufferedImage, "png", blurredImageOutputStream);
    }
}
