package org.example;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
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
            System.out.println("Pick which Picture to print 1 2 3 4");
            String whichToPrint= myObj.nextLine();

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

            //Print to Printer
            PrintActionListener printToPrinter=new PrintActionListener(bufferedImage);
            printToPrinter.whichToPrint(bufferedImage,typeOfColor,whichToPrint);





        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }





}