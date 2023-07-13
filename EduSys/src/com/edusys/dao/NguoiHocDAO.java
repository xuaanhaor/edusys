/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.NguoiHoc;
import com.edusys.helper.jdbcHelper;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String> {

    String INSERT_SQL = "INSERT INTO NguoiHoc(MaNH,HoTen,NgaySinh,GioiTinh,DienThoai,Email,GhiChu,MaNV,NgayDK,Hinh,TrinhDo) Values(?,?,?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE NguoiHoc set HoTen=?,NgaySinh=?,GioiTinh=?,DienThoai=?,Email=?,GhiChu=?,MaNV=?,NgayDK=?,Hinh=?,TrinhDo=? where MaNH=?";
    String DELETE_SQL = "DELETE FROM NguoiHoc where MaNH=?";
    String SELECT_ALL_SQL = "SELECT * FROM NguoiHoc";
    String SELECT_BY_ID_SQL = "SELECT*FROM NguoiHoc WHERE MaNH=?";

    public Date convertDate(java.util.Date date) {
        java.sql.Date jdate = new java.sql.Date(date.getTime());
        return jdate;
    }

    @Override
    public void insert(NguoiHoc entity) {
        jdbcHelper.update(INSERT_SQL,
                entity.getMaNH(),
                entity.getHoTen(),
                entity.getNgaySinh(),
                entity.getGioiTinh(),
                entity.getDienThoai(),
                entity.getEmail(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayDK(),
                entity.getHinh(),
                entity.getTrinhDo());
    }

    @Override
    public void update(NguoiHoc entity) {
        jdbcHelper.update(UPDATE_SQL, entity.getHoTen(),
                entity.getNgaySinh(),
                entity.getGioiTinh(),
                entity.getDienThoai(),
                entity.getEmail(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayDK(),
                entity.getHinh(),
                entity.getTrinhDo(),
                entity.getMaNH());
    }

    @Override
    public void delete(String key) {
        jdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public NguoiHoc selectByID(String id) {
        List<NguoiHoc> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NguoiHoc> selectBySQL(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                NguoiHoc nh = new NguoiHoc();
                nh.setMaNH(rs.getString("MaNH"));
                nh.setHoTen(rs.getString("HoTen"));
                nh.setNgaySinh(rs.getDate("NgaySinh"));
                nh.setGioiTinh(rs.getBoolean("GioiTinh"));
                nh.setDienThoai(rs.getString("DienThoai"));
                nh.setEmail(rs.getString("Email"));
                nh.setGhiChu(rs.getString("GhiChu"));
                nh.setMaNV(rs.getString("MaNV"));
                nh.setNgayDK(rs.getDate("NgayDK"));
                nh.setHinh(rs.getString("Hinh"));
//                nh.setTrinhDo(rs.getString("TrinhDo"));
                list.add(nh);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<NguoiHoc> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ? or DienThoai LIKE  ? or MaNH LIKE  ?";
        return this.selectBySQL(sql, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
    }

    public List<NguoiHoc> selectNotInCourse(int MaKH, String keyword) {
        String sql = "select * from NguoiHoc"
                + " where HoTen like ? And"
                + " MaNH not in (select MaNH from HocVien where MaKH = ?)";
        return this.selectBySQL(sql, "%" + keyword + "%", MaKH);
    }
}
