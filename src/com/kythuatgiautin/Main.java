package com.kythuatgiautin;

import java.io.FileWriter;
import java.io.IOException;


import static java.lang.System.exit;

public class Main extends ThuatToan {
    private static int VALUE_DEFAULT = 63;// ảnh bmp có 63 byte đầu là lưu thông tin ảnh nên chỉ lưu từ byte thứ 67 trở đi

    // trả về mảng byte đã dấu tin
    public static byte[] processWarterMark() {
        String pathImg = inputNameImg();
        //B1: get binary from message
        byte[] bitMess = getBitFromMessage();
        //B2: get byte from file Image
        byte[] bytes = new byte[0];
        try {
            bytes = getByteFromFile(pathImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*cấu trúc ảnh BMP
            Bitmap Header (14 bytes): Giúp nhận dạng tập tin bitmap.
            Bitmap Information (40 bytes): Lưu một số thông tin chi tiết giúp hiển thị ảnh.
         */
        //theo tính chất của ảnh BMP thì chỉ được dấu tin từ byte thứ 55
        //B3: Giấu message(Dấu tin từ byte 500 trở đi thì ảnh sẽ không thay đổi nhiều)
        //Nhập key
        int k = inputKey();
        byte[] byteCover = hideMes(bytes, (VALUE_DEFAULT + k), bitMess);
        return byteCover;
    }

    //return MESSAGE hide
    public static String processDecode() throws IOException {
        // B1: input img need Decode
        String path = inputNameImg();
        //B2: get byte from image
        byte[] bytes = getByteFromFile(path);
        //B3: enter input key
        System.out.println("Nhập vào Khóa K: ");
        int k = Integer.parseInt(scanner.nextLine());
        //B4: enter input number character had hide
        System.out.println("Nhập vào số lượng ký tự đã dấu: ");
        int offset = Integer.parseInt(scanner.nextLine());
        //B5: decode, tách thủy vân
        byte[] waterMark = decode(bytes, VALUE_DEFAULT + k, offset);
        //B6: convert bit to text
        String message = convertBitToText(waterMark);
        return message;
    }

    public static void xuLyChucNangTheoLuaChon(int luachon) throws IOException {
        switch (luachon) {
            case 1: {// Thủy vân
                System.out.println("=========Thủy vân ảnh===========");
                byte[] bytes = processWarterMark();
                System.out.println("Giấu tin thành công!!!");
                System.out.println("============Ghi file=========== ");
                String pathImg = inputNameImg();
                writeFile(bytes, pathImg);
                break;
            }
            case 2: {// Tách thủy vân
                System.out.println("=========Tách Thủy vân trong ảnh===========");
                String message = processDecode();
                System.out.println("Tách thủy vân thành công!!!");
                //Ghi file
                FileWriter fileWriter = new FileWriter("D://message.txt");
                fileWriter.write("Bản quyền của: " + message);
                fileWriter.close();
                System.out.println("Decode Message: " + message);
                break;
            }
            case 3: {
                System.out.println("Kết thúc chương trình");
                exit(0);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        int luachon = 0;
        while (luachon != 3) {
            showMenu();
            luachon = choice();
            xuLyChucNangTheoLuaChon(luachon);
        }

    }
}
