/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.KhoaHoc;
import com.edusys.helper.jdbcHelper;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer> {

    String INSERT_SQL = "INSERT INTO KhoaHoc(MaCD,HocPhi,ThoiLuong,NgayKG,GhiChu,MaNV) Values(?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE KhoaHoc SET MaCD=?, HocPhi=?, ThoiLuong=?, NgayKG=?, GhiChu=?, MaNV=? WHERE MaKH=?";
    String DELETE_SQL = "DELETE FROM KhoaHoc where MaKH=?";
    String SELECT_ALL_SQL = "SELECT * FROM KhoaHoc";
    String SELECT_BY_ID = "SELECT * FROM KhoaHoc where MaKH=?";
    java.sql.Date jdate;

    public Date convertDate(java.util.Date date) {
        jdate = new java.sql.Date(date.getTime());
        return jdate;
    }

    @Override
    public void insert(KhoaHoc entity) {
        jdbcHelper.update(INSERT_SQL, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV());
    }

    @Override
    public void update(KhoaHoc entity) {
        jdbcHelper.update(UPDATE_SQL, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV(), entity.getMaKH());
    }

    @Override
    public void delete(Integer key) {
        jdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public KhoaHoc selectByID(Integer key) {
        List<KhoaHoc> list = this.selectBySQL(SELECT_BY_ID,key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<KhoaHoc> selectBySQL(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                KhoaHoc kh = new KhoaHoc();
                kh.setMaKH(rs.getInt("MaKH"));
                kh.setMaCD(rs.getString("MaCD"));
                kh.setHocPhi(rs.getFloat("HocPhi"));
                kh.setThoiLuong(rs.getInt("ThoiLuong"));
                kh.setNgayKG(rs.getDate("NgayKG"));
                kh.setGhiChu(rs.getString("GhiChu"));
                kh.setMaNV(rs.getString("MaNV"));
                kh.setNgayTao(rs.getDate("NgayTao"));
                list.add(kh);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<KhoaHoc> selectByChuyenDe(String macd) {
        String sql = "SELECT * FROM KhoaHoc WHERE MaCD=?";
        return this.selectBySQL(sql, macd);
    }

    public List<Integer> selectYears() {
        String sql = "SELECT DISTINCT year(NgayKG) Year FROM KhoaHoc ORDER BY Year DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
