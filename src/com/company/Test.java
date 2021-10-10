package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import java.io.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;

public class Test {

    public String convertTextToBinary(String text){
        String binaryResult = "";
        for(char a : text.toCharArray()){
            String binString = Integer.toBinaryString((int) a);
            binaryResult += ("00000000" + binString).substring(binString.length());
        }
        return binaryResult;
    }

    public String convertIntToBin(int number){
        String a = Integer.toBinaryString(number);
        return a;
    }
    public void insertUseLSB(String[] str, String bitMess){
        str[str.length - 1] = bitMess;
    }

    public static BufferedImage replace(BufferedImage image, int target, int preferred) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, image.getType());
        int color;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                color = image.getRGB(i, j);
                if(i == 0 && j == 0){
                    System.out.println("value RGB: "+color);
                    //B1: bo vao color api để get value R G B
                    Color a = new Color(color);
                    System.out.println("RGB color a: " + a.getRGB());
                    System.out.println("Red: "+a.getRed() + " Green: " + a.getGreen() + " Blue: " + a.getBlue());
                    // B2: set value need change
//                    Color b = new Color(a.getRed(), 98, a.getBlue());
//                    System.out.println("Red: "+b.getRed() + " Green: " + b.getGreen() + " Blue: " + b.getBlue());
//                    //B3: set value RGB(pixel)
//                    System.out.println("RGB color b: " + b.getRGB());
//                    newImage.setRGB(i, j, b.getRGB());
                }
                if (color == target) {
                    newImage.setRGB(i, j, preferred);
                }
                else {
                    newImage.setRGB(i, j, color);
                }
            }
        }

        return newImage;
    }
    // Chia mảng 2 chiều các số nguyên thành các mảng con với K cho trc. sau đó cho các mảng con này vào List
    // Mục đích: Muốn thay đổi mảng con nào thì thì chỉ cần dùng List.get(index) để lấy mảng đó ra thao tác
    public ArrayList<int[][]> chiaMangVoiThamSo(int[][] arr, int k){//DK: k>=2
        ArrayList<int[][]> listArrInteger = new ArrayList<>();
        for(int i = 0; i < arr[0].length; i++){
            for(int j = 0; j < arr[0].length; j++){
                int[][] subArr= new int[k][k];
                // add into subArr
                for(int m = 0; m < subArr[0].length; m++){
                    for(int n = 0; n < subArr[0].length; n++){
                        subArr[m][n] = arr[i + k][j + k];
                    }
                }
                listArrInteger.add(subArr);
            }
        }
        return listArrInteger;
    }

    public byte[] extractBytes (String ImageName) throws IOException {
        // open image
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

        return ( data.getData() );
    }

    static boolean[] bits(byte b) {
        int n = 8;
        final boolean[] set = new boolean[n];
        while (--n >= 0) {
            set[n] = (b & 0x80) != 0;
            b <<= 1;
        }
        return set;
    }


    public static BufferedImage  byteArrayToImage(byte[] bytes){
        BufferedImage bufferedImage=null;
        try {
            InputStream inputStream = new ByteArrayInputStream(bytes);
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return bufferedImage;
    }

    public int[] convertBooleanToInt(boolean a[]){
        int[] b = new int[8];
        for(int i = 0; i < a.length; i++){
            if(a[i] == true){
                b[i] = 1;
            }
        }
        return b;
    }

    public static boolean[] toBitArray(int number, int length) {
        boolean[] bits = new boolean[length];
        boolean negative = number < 0;
        number = Math.abs(number);
        boolean reverse = false;
        for (int i = bits.length - 1; i >= 0; i--) {
            bits[i] = number % 2 == (reverse ? 0 : 1);
            number = number / 2 ;
            if (negative && bits[i])
                reverse = true;
        }
        return bits;
    }

    public static boolean[] toBitArray(byte[] bytes) throws Exception {
        boolean[] bits = new boolean[bytes.length * 8];
        for (int i = 0; i < bytes.length; i++) {
            System.arraycopy(toBitArray(bytes[i], 8), 0, bits, i * 8, 8);
        }
        return bits;
    }

    public static byte[] toBytes(String bits) {
        byte[] bytes = new byte[bits.length() / 8];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(bits.substring(i * 8, (i + 1) * 8), 2);
        }
        return bytes;
    }

    public BufferedImage getInfoImage(String fileName) {
        BufferedImage img = null;
        try {
            //Read in new image file
            img = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        return img;
    }
    public int[] lietkeUS(int n){
        int[] a = new int[100];
        int dem  = 0;
        for(int  i = n / 2; i > 0; i--){
            int m = (n / i);
            if(n % i == 0){
                a[dem++] = i;
            }
        }
        return a;
    }

    public static String convertBitToText(byte[] arrBit){
        StringBuffer stringBuffer = new StringBuffer();
        //convert Byte to String
        for(int i = 0; i < arrBit.length; i++){
            if(i != 0 && i % 8 == 0){
                stringBuffer.append(" ");
            }
            if(arrBit[i] == 1)
                stringBuffer.append(1);
            else
                stringBuffer.append(0);
        }
        //ta convert binary to text
        StringBuffer message = new StringBuffer();
        int index = 0;
        while(index < stringBuffer.length()) {
            String temp = stringBuffer.substring(index, index+8);
            Integer num = Integer.parseInt(temp,2);
            char letter =(char) (int)num;
            message.append(letter);
            index +=9;
        }
        return message.toString();
    }

    public static void main(String[] args) throws Exception {
        byte[] bytes = {0,1,1,0,0,0,0,1,0,1,1,0,0,0,1,0};
        String message = convertBitToText(bytes);
        System.out.println(message);

    }
}
