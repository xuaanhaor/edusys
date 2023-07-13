/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.helper;

import com.edusys.entity.NhanVien;

public class Auth {
    //khai báo user =>Duy trì user đăng nhập vào hệ thống
    public static NhanVien user=null;
    //Xóa user khi người dùng đăng xuất
    public static void clear(){
        Auth.user=null;
    }
    //Kiểm tra đăng nhập hay chưa?
    public static boolean isLogin(){
        return Auth.user!=null;//user khác null =>Đã đăng nhập
    }
    //Kiểm tra vai trò trưởng phòng hay nhân viên 
    public static boolean isManager(){
        return Auth.isLogin() && user.getVaiTro();
    }
    //học lại static vs non static
    //access modifiers: private, default, protected, public
    public NhanVien nv;
    public static void main(String[] args) {
        Auth a = new Auth();
        a.nv = new NhanVien();
    }
}
