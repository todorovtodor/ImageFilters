package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public  class ColorFilters implements ImageFilters {
    String filterColor;
    final Class<Main> mainClass = Main.class;
    final ClassLoader classLoader = mainClass.getClassLoader();



    public void typeOfColor(String filterColor) {
        this.filterColor= filterColor;


    }

    @Override
    public BufferedImage filer(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int p = bufferedImage.getRGB(x,y);
                Color color = new Color(bufferedImage.getRGB(x,y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int onlyRed = new Color(red, 0, 0).getRGB();
                int onlyGreen = new Color(0, green, 0).getRGB();
                int onlyBlue = new Color(0, 0, blue).getRGB();


                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p & 0xff;
                if(filterColor.equals("Green")) {
                    bufferedImage.setRGB(x, y, onlyGreen);
                }else if (filterColor.equals("Red")){
                    bufferedImage.setRGB(x, y, onlyRed);
                }else {
                    bufferedImage.setRGB(x, y, onlyBlue);
                }

                }
            }return bufferedImage;
        }
        public File saveFile(BufferedImage bufferedImage,String colorPick) throws IOException {
            final String colorChangePath = classLoader.getResource("").getPath();
            final File colorChangeFile = new File(colorChangePath + "/ColorChange.jpg");
            ColorFilters colorChange=new ColorFilters();
            colorChange.typeOfColor(colorPick);
            FileWrite colorChangeWrite=new FileWrite(colorChangeFile);
            colorChangeWrite.write(colorChange.filer(bufferedImage));
            return colorChangeFile;

        }


    }







