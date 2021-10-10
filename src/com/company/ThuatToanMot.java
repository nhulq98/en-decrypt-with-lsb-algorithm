package com.company;

import java.io.*;
import java.util.ArrayList;

public class ThuatToanMot {
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
    public static byte[] toBytes(String bits) {
        byte[] bytes = new byte[bits.length() / 8];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(bits.substring(i * 8, (i + 1) * 8), 2);
        }
        return bytes;
    }
    public static byte[] getByteFromFile(String filePath) throws IOException {
        InputStream input = null;
        DataInputStream dis = null;
        byte[] arr = null;
        try {
            input = new FileInputStream(filePath);
            dis = new DataInputStream(input);
            int count = input.available();
            arr = new byte[count];
            dis.read(arr);

        } catch (IOException ex) {

        } finally {
            dis.close();
        }
        return arr;
    }
    public static String convertBooleanToString(boolean[] bits){
        StringBuffer str = new StringBuffer();
        for(int i = 0;i < bits.length; i++){
            if(bits[i] == true){
                str.append(1);
            }else{
                str.append(0);
            }
        }
        return str.toString();
    }
    public static int sumBits(boolean[] bits){
        int sum = 0;
        for(int j = 0; j < bits.length; j++){
            if(bits[j] == true){
                sum += 1;
            }
        }
        return sum;
    }
    // Su dung tinh chan le
    public static byte[] hideMes(byte[] bytes, byte[] bitMessage){
        //B1: chia khối.
            //B1.1: Đếm số lượng bit cần dấu(bit message) ==> có bn khối cần chia
        int lengthMess = bytes.length;

            //B1.2:CT tính số phần tử trong khối là: bytes.length / x = bitMessage ==> x = bytes.length / bitMessage.
        int byteElements = bytes.length / lengthMess;
            //B1.3: chia khối và đếm phần tử trong khối
        int dem = 1, sum = 0;
        for(int i = 0; i < bytes.length; i++){
            if(dem <= byteElements){
                // chuyển byte sang bit[]
                boolean[] bits = toBitArray(bytes[i], 8);
                // sau đó tính tổng các bit
                sum = sumBits(bits);
                dem++;
            }else if(dem > byteElements){
                dem = 1;
            }else{// xét khả năng dâu bit dựa trên cân bằng chẵn lẻ
                if(dem % 2 == 0){
                    
                }
            }
        }
        //B2: đếm tổng số bit 1 trong khối
        //B3: thực hiện dấu tin
        //B4: gán vào 1 mảng byte mới chứa byte đã được thay đổi
        //B5: trả về mảng byte hiện tại
        return new byte[23];
    }

    public static void main(String[] args) throws IOException {
        String filePath = "D://DESKTOP//Hoc Online//Các Kỹ Thuật Giấu Tin/binaryDCover.jpg";
        //B1: get byte from file Image
        byte[] bytes = getByteFromFile(filePath);
        byte[] bitMess = new byte[]{1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,0,0,1,1,1,0};
        // chỉ nên dấu từ bit byte 500 trở đi
        byte[] byteCover = hideMes(bytes, bitMess);
        System.out.println("");
        System.out.println(byteCover.length);
        for(int i = 0; i < byteCover.length; i++){
            System.out.print(" " + byteCover[i]);
        }

        //write file
        String filePath2 = "D://DESKTOP//Hoc Online//Các Kỹ Thuật Giấu Tin//output98.jpg";
        OutputStream os = new FileOutputStream(filePath2);
        os.write(byteCover);
    }
}
