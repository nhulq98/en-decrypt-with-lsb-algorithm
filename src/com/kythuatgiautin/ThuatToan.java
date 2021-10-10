package com.kythuatgiautin;

public class ThuatToan extends HamHoTro {

    // Dấu bit với vị trí bắt đầu là K(vị trí của byte thứ k (K >= 500) bởi vì dấu trước bit thứ 500 sẽ có nguy cơ hư ảnh).
    public static byte[] hideMes(byte[] bytesImage, int k, byte[] bitMes){
        //Tạo mảng mới để lưu ảnh được giấu tin
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
}
