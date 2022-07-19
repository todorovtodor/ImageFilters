package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) {
        try {
            //Pick Color
            Scanner myObj = new Scanner(System.in);
            System.out.println("Pick a color ");
            String typeOfColor = myObj.nextLine();

            //Read image
            final Class<Main> mainClass = Main.class;
            final ClassLoader classLoader = mainClass.getClassLoader();
            final URL imageUrl = classLoader.getResource("download.jpg");
            final BufferedImage bufferedImage = ImageIO.read(imageUrl);


            //Blur
            BlurFilters blurredImage=new BlurFilters();
            blurredImage.saveFile(bufferedImage);

            //Color filter
            ColorFilters colorChange=new ColorFilters();
            colorChange.saveFile(bufferedImage,typeOfColor);

            //Both filters
            BothFilters blurredForBothFilters=new BothFilters();
            blurredForBothFilters.saveFile(bufferedImage);

            //Hue
            HueFilters hueImage=new HueFilters();
            hueImage.saveFile(bufferedImage);

        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }





}