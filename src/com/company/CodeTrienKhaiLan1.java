package com.company;

import java.io.*;
import java.util.Scanner;

public class CodeTrienKhaiLan1 {
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
    public static byte[] inputMessage(){
        System.out.println("Nhap vao message: ");
        Scanner scanner = new Scanner(System.in);
        String mess = scanner.nextLine();
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
    // Dấu bit với vị trí bắt đầu là K(vị trí của byte thứ k (K >= 500) bởi vì dấu trước byte thứ 500 sẽ có nguy cơ hư ảnh).
    public static byte[] hideMes(byte[] bytesImage, int k, byte[] bitMes){
        //Tạo mảng mới
        byte[] newBytes = new byte[bytesImage.length];
        int lengthBitMess = bitMes.length;
        //dem: chỉ số of bitMess, dem2: chỉ số of newBytes
        int dem = 0, dem2 = 0;
        int check = 0;
        //System.out.println("Byte của Image: ");
        for(int i = 0; i < bytesImage.length; i++){
            check = 0;
            //System.out.print(" " + bytesImage[i]);
            byte[] bytes = new byte[1];
            if(i >= k && i < (k + lengthBitMess)){
                check = 1;// đánh dấu là bit đã bị thay đổi
                // bắt đầu dấu tại đây
                //B1: chuyển byte về dạng bit và lưu dạng boolean[]
                boolean[] bits = toBitArray(bytesImage[i], 8);
                //B2: nhúng bitMessage vào bit LSB
                if(bitMes[dem] == 1) {
                    bits[bits.length - 1] = true;
                    dem++;
                }
                else {
                    bits[bits.length - 1] = false;
                    dem++;
                }
                //B3: chuyển bit sang byte
                    //chuyển arrayBoolean sang String
                String bitStr = convertBooleanToString(bits);
                // convert bit String to bytes
                bytes = toBytes(bitStr);
                //update value for byte[](chứa dữ liệu đã ẩn)
                newBytes[dem2++] = bytes[0];
            }

            if(check == 0){// bit not change
                newBytes[dem2++] = bytesImage[i];
            }

        }
        return newBytes;
    }

    /*
    * đầu vào byte ảnh, khóa K, Offset(số lượng ký tự đã dấu)
    * B1: đi vào byte thứ k và lấy all các byte từ k --> k+ Offset * 8.
    * Vd: k = 500, offset = 10
    * ===> 500 <= byte <= 580(500 + 10*8)
    * */
    // tách thủy vân với K(so byte bắt đầu dấu thủy vân), offset(so luong tu trong tin nhắn)
    public static byte[] decode(byte[] bytesImage, int k, int offset){
        // ta se tach thuy van bat dau tu vi tri k
        System.out.println("tách bit: ");
        int dem = 0;
        byte[] bitHide = new byte[offset * 8];
        for(int i = 0; i < bytesImage.length; i++){
            if(i >= k && i < (k + offset * 8)){
                // B1: đi vào byte lấy bit lsb và bỏ vào mảng
                    //B1.1: convert byte to bit[]
                boolean[] arrbit = toBitArray(bytesImage[i], 8);
                    //B1.2: bo vao trong bitHide
                if(arrbit[arrbit.length - 1] == true){
                    bitHide[dem] = 1;
                    dem++;
                }
                if(arrbit[arrbit.length - 1] == false){
                    bitHide[dem] = 0;
                    dem++;
                }
            }
        }
        return bitHide;
    }

    public static void main(String[] args) throws IOException {
        String filePath = "D://DESKTOP//Hoc Online//Các Kỹ Thuật Giấu Tin/output96.jpg";
        //B1: get byte from file Image
        byte[] bytes = getByteFromFile(filePath);
        //B2: get bit binary from message
//        byte[] bitMess = inputMessage();
        //B3: Giấu message(chỉ nên dấu từ bit byte 500 trở đi)
//        byte[] byteCover = hideMes(bytes, 500, bitMess);

        //B4: decode, tách thủy vân
        byte[] waterMark = decode(bytes, 500, 12);

//        B5: convert bit to text
        String message = convertBitToText(waterMark);
        System.out.println("Decode Message: " + message);

        //write file
//        String filePath2 = "D://DESKTOP//Hoc Online//Các Kỹ Thuật Giấu Tin//output96.jpg";
//        OutputStream os = new FileOutputStream(filePath2);
//        os.write(byteCover);
//        System.out.println("Dấu bit thành công");
    }
}
