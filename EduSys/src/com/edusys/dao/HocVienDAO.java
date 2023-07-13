/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.HocVien;
import com.edusys.helper.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HocVienDAO extends EduSysDAO<HocVien, Integer> {

    String INSERT_SQL = "insert into HocVien(MaKH,MaNH,Diem) values(?,?,?)";
    String UPDATE_SQL = "update HocVien set MaKH=?,MaNH=?,Diem=? where MaHV=?";
    String DELETE_SQL = "delete from HocVien where MaHV=?";
    String SELECT_ALL_SQL = "select * from HocVien";
    String SELECT_BY_ID = "select * from HocVien where MaHV=?";

    @Override
    public void insert(HocVien entity) {
        jdbcHelper.update(INSERT_SQL, entity.getMaKH(), entity.getMaNH(), entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
        jdbcHelper.update(UPDATE_SQL, entity.getMaKH(), entity.getMaNH(), entity.getDiem(), entity.getMaHV());

    }

    @Override
    public void delete(Integer id) {
        jdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<HocVien> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);

    }

    @Override
    public HocVien selectByID(Integer id) {
        List<HocVien> list = this.selectBySQL(SELECT_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<HocVien> selectBySQL(String sql, Object... args) {
        try {
            List<HocVien> list = new ArrayList<HocVien>();
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                HocVien hv = new HocVien();
                hv.setMaHV(rs.getInt("MaHV"));
                hv.setMaKH(rs.getInt("MaKH"));
                hv.setMaNH(rs.getString("MaNH"));
                hv.setDiem(rs.getFloat("Diem"));
                list.add(hv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<HocVien> selectByKhoaHoc(int MaKH) {
        String sql = "select *from HocVien where MaKH= ?";
        return this.selectBySQL(sql, MaKH);
    }
}
