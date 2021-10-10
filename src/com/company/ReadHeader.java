package com.company;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ReadHeader {

    public static void main(String[] args) {
        try {

            /*  input image  */
            BufferedImage image = ImageIO.read(new File("D://anh.bmp"));

            /* doc header chu yeu lay height, width
             *  cac thuoc tinh khac co the bo qua
             *
             */
            int height = image.getHeight();
            int width = image.getWidth();
            
            int pixel = image.getRGB(1, 1);
            System.out.println("=====================================");
            System.out.println("pixel : " + pixel);
            System.out.println("=====================================");
            /*
             * ghi result ra gile text:
             * ghi height and widht value
             * ghi color RGB
             */
//            FileWriter fileWriter = new FileWriter("D://DESKTOP//Hoc Online//Các Kỹ Thuật Giấu Tin//result_text2.txt");
//            fileWriter.write("header anh: \n");
//            fileWriter.write("chieu cao: " + height + "\n");
//            fileWriter.write("chieu rong: " + width + "\n");
//            fileWriter.write("bang mau: \n");

            int count = 0;
            for (int i = 0; i < width; i++) {
                for(int j = 0; j < height; j++) {
                    count++;
                    Color c = new Color(image.getRGB(i, j));

                    System.out.println(" red: " + c.getRed() + " green: " + c.getGreen() + " blue: " + c.getBlue() + "\n");
                    //fileWriter.write("vi tri " + count + " : " + toBinary(c.getRed()) + toBinary(c.getGreen()) + toBinary(c.getBlue())+"\n");
                }
            }

            //fileWriter.close();
            System.out.println("write file complete!!");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static String toBinary(int c) {
        return  Integer.toBinaryString(c);
    }
}

