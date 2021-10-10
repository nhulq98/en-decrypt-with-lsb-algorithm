package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CodeTuTay {

    //=======================

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
    //====================================


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

    public String integerToBinary(int n) {
        return Integer.toBinaryString(n);
    }

    public String[] stringToArr(String str) {
        String[] rs = str.split("");
        return rs;
    }

    public void insertUseLSB(String[] str, int bitMess) {
        // chèn vào phần tử cuối cùng trong mảng là bit có trọng số là 1
        str[str.length - 1] = Integer.toString(bitMess);
    }

    public int stringToInteger(String str) {
        return Integer.parseInt(str);
    }

    //get 3 value R G B form object "Color". Then add into List
    public ArrayList<Integer> getColorRGB(int valRGB) {
        // get object color at location RGB(i, j)
        Color c = new Color(valRGB);
        // get 3 RGB
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        ArrayList<Integer> listColor = new ArrayList<Integer>();
        listColor.add(red);
        listColor.add(green);
        listColor.add(blue);
        return listColor;
    }

    //convert integer to binary. then add into List
    public ArrayList<String> getBinaryWithStringFormat(ArrayList<Integer> listRGB) {
        ArrayList<String> listBinary = new ArrayList<>();
        listBinary.add(integerToBinary(listRGB.get(0)));
        listBinary.add(integerToBinary(listRGB.get(1)));
        listBinary.add(integerToBinary(listRGB.get(2)));
        return listBinary;
        //B1: convert value form Integer to Binary(format String)
//                String redBinary = integerToBinary(listRGB.get(0));
//                String greenBinary = integerToBinary(listRGB.get(1));
//                String blueBinary = integerToBinary(listRGB.get(2));
    }

    // Convert elements String in list<String> to String[]. Then add into List
    public ArrayList<String[]> getListStringArr(ArrayList<String> listBinary) {
        ArrayList<String[]> list = new ArrayList<>();
        list.add(stringToArr(listBinary.get(0)));
        list.add(stringToArr(listBinary.get(1)));
        list.add(stringToArr(listBinary.get(2)));
//        String[] arr_Red = stringToArr(listBinary.get(0));
//        String[] arr_Green = stringToArr(listBinary.get(1));
//        String[] arr_Blue = stringToArr(listBinary.get(2));
        return list;
    }

    // convert String[] to StringBuffer. then add into List
    public ArrayList<StringBuffer> StringArrToStringBuff(ArrayList<String[]> listArrStr) {
        ArrayList<StringBuffer> list = new ArrayList<>();
        for (int i = 0; i < listArrStr.size(); i++) {
            StringBuffer stringBuffer = new StringBuffer();
            //convert String[] to StringBuff
            for (int k = 0; k < listArrStr.get(i).length; k++) {
                stringBuffer.append(listArrStr.get(i)[k]);
            }
            list.add(stringBuffer);
        }
        return list;
//        StringBuffer str_Red = new StringBuffer();
//        for(int k = 0; k < list_Binary_FomatStringArr.get(0).length; k++) {
//            str_Red.append(list_Binary_FomatStringArr.get(0)[k]);
//        }
    }

    //convert binary(format String) to Int. then add into List
    public ArrayList<Integer> listBinaryFormatInteger(ArrayList<StringBuffer> listBuff) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Integer.parseInt(listBuff.get(0).toString(), 2));
        list.add(Integer.parseInt(listBuff.get(1).toString(), 2));
        list.add(Integer.parseInt(listBuff.get(2).toString(), 2));
        return list;
    }

    public int insertBitOfPixel(ArrayList<Integer> listRGB, ArrayList<Integer> listBitScret){
        //B1: convert integer to binary. và cho vào list<String>
        ArrayList<String> listBinary = getBinaryWithStringFormat(listRGB);
        System.out.println("Trước khi cộng bit==Đỏ: " + listBinary.get(0) + " green: " + listBinary.get(1) + " blue: " + listBinary.get(2));
        //B2: chuyển các String of list<String> thành List<String[]> chứa các String[] tương ứng
        ArrayList<String[]> list_Binary_FomatStringArr = getListStringArr(listBinary);
        //================CHÈN BIT đối với ảnh màu=================
        // bởi vì ảnh màu dạng 8 bit. mà mỗi pixel có 3 màu RGB<==>(11111111,11111111,11111111)
        //==> có 3 bit thấp(LSB) đc sử dụng để chèn message

        //B3: thực hiện chèn 3 bit của message vào trong image sử dụng bit LSB
        //VD: message = "101";
        insertUseLSB(list_Binary_FomatStringArr.get(0), listBitScret.get(0));
        insertUseLSB(list_Binary_FomatStringArr.get(1), listBitScret.get(1));
        insertUseLSB(list_Binary_FomatStringArr.get(2), listBitScret.get(2));

        // set valueRGB from value of R, G, B color changed!!
        //first: convert list<String[]> to List<StringBuffer>
        ArrayList<StringBuffer> listBuff = StringArrToStringBuff(list_Binary_FomatStringArr);

        //second: convert binary(format String) to Int
        ArrayList<Integer> listBinary_fomartInteger = listBinaryFormatInteger(listBuff);

        //B5: create color with value define
        Color color = new Color(listBinary_fomartInteger.get(0), listBinary_fomartInteger.get(1), listBinary_fomartInteger.get(2));

        // B2: get value RGB form Color d
        int valRGB2 = color.getRGB();

        return valRGB2;
    }


    public void outValueColor(BufferedImage img) {
        // get property image to create new image with it
        int h = img.getHeight();
        int w = img.getWidth();
        //create a new BufferedImage with the width and height
        BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        // clone image and watermarking
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                //Get RGB Value of image original
                int valRGB = img.getRGB(i, j);
                //lấy ra 3 giá trị riêng biệt RGB và cho vào list
                ArrayList<Integer> listRGB = getColorRGB(valRGB);
                //==========CÁC bước quan trọng khi thay đổi các bit trong RGB setRGB===================
                // B1: add bit message into List
                ArrayList<Integer> listBitOfMess = new ArrayList<>();
                listBitOfMess.add(1);
                listBitOfMess.add(0);
                listBitOfMess.add(1);
                if (i == 0 && j == 0) {
                    // B2: get value RGB form pixel changed
                    int valRGB2 = insertBitOfPixel(listRGB, listBitOfMess);
                    //B3: set value RGB
                    //bufferedImage.setRGB(i, j, valRGB2);
                    img.setRGB(i, j, valRGB2);
                    // SUCCESS!!!
                } else {//=========== COPPY IMAGE ==========
                    //Get RGB Value
                    int val = img.getRGB(i, j);
                    // get color of pixel
                    //Color c = new Color(val);
                    //bufferedImage.setRGB(i, j, c.getRGB());
                    //img.setRGB(i, j, c.getRGB());
                    img.setRGB(i, j, val);
                }

            }
        }
        //export file image
//        File file = new File("D://DESKTOP//Hoc Online//Các Kỹ Thuật Giấu Tin//hinhthuyvan.jpg");
//        try {
//            ImageIO.write(img, "jpg", file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //========check value after water marking=====/
//        int h1 = bufferedImage.getHeight();
//        int w1 = bufferedImage.getWidth();
//        for (int i = 0; i < w1; i++) {
//            for (int j = 0; j < h1; j++) {
//                //Get RGB Value
//                int val = bufferedImage.getRGB(i, j);
//                // get object color at location RGB(i, j)
//                Color c = new Color(val);
//                // get 3 RGB
//                int red = c.getRed();
//                int green = c.getGreen();
//                int blue = c.getBlue();
//
//                if (i == 0 && j == 0) {
//                    System.out.println("value image Clone after cộng bit: ");
//                    System.out.println("red: " + red);
//                    System.out.println("green: " +  Integer.toBinaryString(green));
//                    System.out.println("blue: " +  blue);
//                }
//            }
//        }
    }

    public static void main(String[] args) {
        CodeTuTay code = new CodeTuTay();
        //String fileName = "D://DESKTOP//Hoc Online//Các Kỹ Thuật Giấu Tin/hinhthuyvan.jpg";
        String fileName = "D://DESKTOP//Hoc Online//Các Kỹ Thuật Giấu Tin/anhmau2.jpg";
        BufferedImage img = code.getInfoImage(fileName);
        code.outValueColor(img);
    }
}
