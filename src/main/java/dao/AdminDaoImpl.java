package dao;

import entity.Admin;
import util.DBUtil;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDaoImpl implements AdminDao, Serializable {

    @Override
    public Admin findByCode(String code) {
        //创建连接
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "select * from admin_info where admin_code = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, code);
            rs = ps.executeQuery();
            if (rs.next()) {
                Admin a = new Admin();
                a.setAdminId(rs.getInt("admin_id"));
                a.setAdminCode(rs.getString("admin_code"));
                a.setPassword(rs.getString("password"));
                a.setName(rs.getString("name"));
                a.setTelephone(rs.getString("telephone"));
                a.setEmail(rs.getString("email"));
                a.setEnrolldate(rs.getTimestamp("enrolldate"));
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取管理员信息失败", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        //没有数据返回null
        return null;
    }

}
