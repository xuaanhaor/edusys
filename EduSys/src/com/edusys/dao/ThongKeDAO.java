/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.edusys.helper.jdbcHelper;

public class ThongKeDAO {

    public List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);;
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
        	e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    public List<Object[]> getBangDiem(int maKH) {
        String sql = "{CALL sp_BangDiem(?)}";
        String[] cols = {"MaNH", "hoten", "diem"};
        return this.getListOfArray(sql, cols, maKH);
    }
    public static ResultSet rs = null;

    public List<Object[]> getLuongNguoiHoc() {
        String sql = "{CALL sp_ThongKeNguoiHoc}";
        String[] cols = {"Nam", "SoLuong", "DauTien", "CuoiCung"};
        return this.getListOfArray(sql, cols);
    }
//  

    public List<Object[]> getDiemChuyenDe() {
        String sql = "{CALL sp_ThongKeDiem}";
        String[] cols = {"ChuyenDe", "SoHocVien", "ThapNhat", "CaoNhat", "TrungBinh"};
        return this.getListOfArray(sql, cols);
    }

    public List<Object[]> getDoanhThu(int nam) {
        String sql = "{CALL sp_ThongKeDoanhThu (?)}";
        String[] cols = {"ChuyenDe", "SoKhoaHoc", "SoHocVien", "DoanhThu", "ThapNhat", "CaoNhat", "TrungBinh"};
        List<Object[]> list = this.getListOfArray(sql, cols, nam);
        for (Object[] os : list) {
            os[3] = String.format("%.1f", os[3]);
            os[4] = String.format("%.1f", os[4]);
            os[5] = String.format("%.1f", os[5]);
            os[6] = String.format("%.1f", os[6]);
        }
        return list;
    }

//    public List<Object[]> getDoanhThu(int nam) {
//        String sql = "{CALL sp_DoanhThu (?)}";
//        String[] cols = {"ChuyenDe", "SoKH", "SoHV", "DoanhThu", "ThapNhat", "CaoNhat", "TrungBinh"};
//        return this.getListOfArray(sql, cols, nam);
//    }
}
