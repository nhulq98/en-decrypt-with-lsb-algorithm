package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.BinaryOperator;


public class ConvertRGBToBinaryImage {

    public void convertRGBToBinaryImage() throws IOException {
        BufferedImage img = null;
        String fileName = "D://DESKTOP//Hoc Online//Các Kỹ Thuật Giấu Tin//anhmau2.jpg"; // if image was not in current directory copy the path of the image
        try {
            //Read in new image file
            img = ImageIO.read(new File(fileName));
        }
        catch (IOException e){
            System.out.println("Error: "+e);
        }
        // get property image to create new image with it
        int h=img.getHeight();
        int w=img.getWidth();
        //create a new BufferedImage with the width and height
        BufferedImage bufferedImage = new BufferedImage(w,h, BufferedImage.TYPE_BYTE_BINARY);
        if (img == null) {
            System.out.println("No image loaded");
        }
        else {
            for(int i=0;i<w;i++)
            {
                for(int j=0;j<h;j++)
                {
                    //Get RGB Value
                    int val = img.getRGB(i, j);
                    //Convert to three separate channels
                    int r = (0x00ff0000 & val) >> 16;
                    int g = (0x0000ff00 & val) >> 8;
                    int b = (0x000000ff & val);
                    int m=(r+g+b);
                    //(255+255+255)/2 =383 middle of dark and light
                    if(m>=383)
                    {
                        // for light color it set white
                        bufferedImage.setRGB(i, j, Color.WHITE.getRGB());
                    }
                    else{
                        // for dark color it will set black
                        bufferedImage.setRGB(i, j, 0);
                    }
                }
            }
        }


        File file = new File("D://DESKTOP//Hoc Online//Các Kỹ Thuật Giấu Tin//ImageBinary2.jpg");
        ImageIO.write(bufferedImage, "jpg", file);
    }

    public static void main(String[] args) throws IOException {
        BufferedImage img = null;
        String fileName = "D://DESKTOP//Hoc Online//Các Kỹ Thuật Giấu Tin/binaryD.png"; // if image was not in current directory copy the path of the image
        try {
            //Read in new image file
            img = ImageIO.read(new File(fileName));
        }
        catch (IOException e){
            System.out.println("Error: "+e);
        }
        // get property image to create new image with it
        int h=img.getHeight();
        int w=img.getWidth();
        //create a new BufferedImage with the width and height
        BufferedImage bufferedImage = new BufferedImage(w,h, BufferedImage.TYPE_BYTE_BINARY);
        if (img == null) {
            System.out.println("No image loaded");
        }
        else {
            for(int i=0;i<w;i++)
            {
                for(int j=0;j<h;j++)
                {
                    //Get RGB Value
                    int val = img.getRGB(i, j);

                    // get object color at location RGB(i, j)
                    Color c = new Color(val);
                    int red = c.getRed();
                    int green = c.getGreen();
                    int blue = c.getBlue();
                    //==========buoc quan trong khi setRGB===================
                    //B1: create color with value define
//                    Color d = new Color(red, green, blue);
//                    // B2: get value RGB form Color d
//                    int valRGB = d.getRGB();
//                    //B3: set value RGB
//                    img.setRGB(i, j, valRGB);
                    //===========================================//
                    System.out.println("Đỏ: " + Integer.toBinaryString(c.getRed()) + " green: " + Integer.toBinaryString(c.getGreen()) + " blue: " + Integer.toBinaryString(c.getBlue()));

                    //Convert to three separate channels
                    int r = (0x00ff0000 & val) >> 16;
                    int g = (0x0000ff00 & val) >> 8;
                    int b = (0x000000ff & val);
                    int m=(r+g+b);
                    //(255+255+255)/2 =383 middle of dark and light
                    Color white = new Color(255,255,255);
                    Color black = new Color(0, 0, 0);
                    if(m>=383)
                    {
                        // for light color it set white
                        bufferedImage.setRGB(i, j, white.WHITE.getRGB());
                    }
                    else{
                        // for dark color it will set black
                        bufferedImage.setRGB(i, j, black.BLACK.getRGB());
                    }
                }
            }
        }


        //File file = new File("D://DESKTOP//Hoc Online//Các Kỹ Thuật Giấu Tin//binaryLSB.jpg");
        //ImageIO.write(bufferedImage, "jpg", file);
    }
}
