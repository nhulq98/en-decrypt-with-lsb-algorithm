package com.kythuatgiautin;

import java.io.*;
import java.util.Scanner;

public class HamHoTro {
    public static String[] menu = {"1. Thủy Vân", "2. Tách Thủy Vân", "3. exit"};
    public static String FORMAT_IMG = ".bmp";
    static Scanner scanner = new Scanner(System.in);

    // methods support process input, output
    public static void showMenu(){
        // print menu
        for (int i = 0; i < menu.length; i++) {
            System.out.println(menu[i]);
        }
    }
    public static int choice() {
        int choice = 0;
        do {
            System.out.print("Nhap vao lua chon:  ");
            choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > 3) {
                System.out.println("Lựa chọn sai!!");
            }
        } while (choice < 1 || choice > 3);
        return choice;
    }
    //return Path Image
    public static String inputNameImg(){
        System.out.println("Nhập vào tên ảnh: ");
        String nameImg = scanner.nextLine();
        String pathImg = "D://" + nameImg + FORMAT_IMG;
        return pathImg;
    }
    public static int inputKey(){
        int k = -1;
        do {
            System.out.println("Nhap vao Khoa K: ");
            k = Integer.parseInt(scanner.nextLine());
            if(k < 0){
                System.out.println("sai. K >= 0");
            }
        }while (k < 0);
        return k;
    }

    // methods support process bit, byte, boolean
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

    //methods support process String, Byte, binary
    public static byte[] convertStringToByte(String binary){
        StringBuffer stringBuffer = new StringBuffer(binary);
        byte[] arr = new byte[stringBuffer.length()];
        for(int i = 0; i < arr.length; i++){
            arr[i] = Byte.parseByte(Character.toString(stringBuffer.charAt(i)));
        }
        return arr;
    }
    public static String convertTextToBinary(String text){
        String binaryResult = "";
        for(char a : text.toCharArray()){
            String binString = Integer.toBinaryString((int) a);
            binaryResult += ("00000000" + binString).substring(binString.length());
        }
        return binaryResult;
    }
    public static String inputString(String note){
        System.out.println(note);
        String str = scanner.nextLine();
        return str;
    }
    public static byte[] getBitFromMessage(){
        String mess = inputString("Nhập vào message: ");
        String binaryMess = convertTextToBinary(mess);
        byte[] arrbitMess = convertStringToByte(binaryMess);
        return arrbitMess;
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
    // Method process with File
    public static void writeFile(byte[] bytes, String filePath) throws IOException {
        //write file
        OutputStream os = new FileOutputStream(filePath);
        os.write(bytes);
        System.out.println("ghi file thành công");
    }
}
