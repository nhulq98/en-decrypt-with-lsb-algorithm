package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class CloneImage {
    public static int rgb;

    public static int myColor( int r, int g, int b){
        rgb= (65536 * r) + (256 * g) + (b);
        return rgb;
    }

    public static void main( String args[] ){

        try {
            BufferedImage img = ImageIO.read(new File( "D://DESKTOP//Hoc Online//Các Kỹ Thuật Giấu Tin/anhmau2.jpg"));
            int clear=0x000000FF;
            int color, alpha, r, g, b;
            int w = img.getWidth();
            int h = img.getHeight();
            System.out.println("W:" + w + " H: " + h);
            BufferedImage t= new BufferedImage( w, h, BufferedImage.TYPE_INT_ARGB );

            for(int i=0; i<w; i++){
                for( int j=0; j<h; j++){
                    color = img.getRGB(i,j);
                    alpha = (color>>24) & 0xff;
                    r = (color & 0x00ff0000) >> 16;
                    g = (color & 0x0000ff00) >> 8;
                    b = color & 0x000000ff;
                    int rgb = myColor( r, g, b );
                    t.setRGB( i,j,   rgb);
                }
            } //for
            File file = new File("D://DESKTOP//Hoc Online//Các Kỹ Thuật Giấu Tin//CloneImage2.jpg");
            ImageIO.write(t, "jpg", file);
            System.out.println("export file success!!");
        } catch (IOException e){ e.printStackTrace(); }

    }//main

}
