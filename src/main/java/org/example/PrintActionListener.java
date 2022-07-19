package org.example;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

public class PrintActionListener implements Runnable {


    private BufferedImage image;

    public PrintActionListener(BufferedImage image) {
        this.image = image;
    }

    @Override
    public void run() {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(new ImagePrintable(printJob, image));

        if (printJob.printDialog()) {
            try {
                printJob.print();
            } catch (PrinterException prt) {
                prt.printStackTrace();
            }
        }
    }

    public class ImagePrintable implements Printable {

        private double x, y, width;

        private int orientation;

        private BufferedImage image;

        public ImagePrintable(PrinterJob printJob, BufferedImage image) {
            PageFormat pageFormat = printJob.defaultPage();
            this.x = pageFormat.getImageableX();
            this.y = pageFormat.getImageableY();
            this.width = pageFormat.getImageableWidth();
            this.orientation = pageFormat.getOrientation();
            this.image = image;
        }

        @Override
        public int print(Graphics g, PageFormat pageFormat, int pageIndex)
                throws PrinterException {
            if (pageIndex == 0) {
                int pWidth = 0;
                int pHeight = 0;
                if (orientation == PageFormat.PORTRAIT) {
                    pWidth = (int) Math.min(width, (double) image.getWidth());
                    pHeight = pWidth * image.getHeight() / image.getWidth();
                } else {
                    pHeight = (int) Math.min(width, (double) image.getHeight());
                    pWidth = pHeight * image.getWidth() / image.getHeight();
                }
                g.drawImage(image, (int) x, (int) y, pWidth, pHeight, null);
                return PAGE_EXISTS;
            } else {
                return NO_SUCH_PAGE;
            }
        }

    }

    public void whichToPrint(BufferedImage bufferedImage, String colorPick, String picToPrint) throws IOException {
        if (picToPrint.equals("1")) {
            BlurFilters blurredImage = new BlurFilters();
            File savedBlurredImg = blurredImage.saveFile(bufferedImage);
            BufferedImage printImage = ImageIO.read(savedBlurredImg);
            PrintActionListener printActionListener = new PrintActionListener(printImage);
            printActionListener.run();

        } else if (picToPrint.equals("2")) {
            ColorFilters colorChange = new ColorFilters();
            File savedColorChange=colorChange.saveFile(bufferedImage, colorPick);
            BufferedImage printImage = ImageIO.read(savedColorChange);
            PrintActionListener printActionListener = new PrintActionListener(printImage);
            printActionListener.run();


        } else if (picToPrint.equals("3")) {
            BothFilters blurredForBothFilters = new BothFilters();
            File savedBlurrBoth = blurredForBothFilters.saveFile(bufferedImage);
            BufferedImage printImage = ImageIO.read(savedBlurrBoth);
            PrintActionListener printActionListener = new PrintActionListener(printImage);
            printActionListener.run();


        } else if (picToPrint.equals("4")) {
            HueFilters hueImage = new HueFilters();
            File cropImage = hueImage.saveFile(bufferedImage);
            BufferedImage printImage = ImageIO.read(cropImage);
            PrintActionListener printActionListener = new PrintActionListener(printImage);
            printActionListener.run();
        }
    }
}

