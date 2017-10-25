package dao;

import entity.Cost;
import util.DBUtil;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CostImpl implements CostDao, Serializable {

    /**
     * 查询模块
     * @return
     */
    @Override
    public List<Cost> findAll() {
        //创建连接
        Connection conn = null;
        Statement st = null;
        ResultSet rs= null;

        try {
            conn = DBUtil.getConnection();
            String sql = "select * from cost order by cost_id";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            List<Cost> list = new ArrayList<Cost>();
            while (rs.next()) {
                Cost c = creatCost(rs);
                list.add(c);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败", e);
        } finally {
            DBUtil.close(conn, st, rs);
        }
    }

    /**
     * 增加保存模块
     * @param c
     */
    @Override
    public void save(Cost c) {
        //创建连接
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "insert into cost values(cost_seq.nextval, ?, ?, ?, ?, 1, ?, sysdate, null, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, c.getName());
            //setInt()和setDouble()不允许传入空值null，但业务需要传入null，此时要将数据当作对象来看
            ps.setObject(2, c.getBaseDuration());
            ps.setObject(3, c.getBaseCost());
            ps.setObject(4, c.getUnitCost());
            ps.setString(5, c.getDescr());
            ps.setString(6, c.getCostType());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("增加资费信息失败", e);
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }

    /**
     * 删除模块
     * @param c
     */
    @Override
    public void delete(Cost c) {
        //创建连接
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "delete * from cost where cost_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getCostId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除资费信息失败", e);
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }

    /**
     * 修改保存模块
     * @param id
     * @return
     */
    @Override
    public Cost findById(int id) {
        //创建连接
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs= null;

        try {
            conn = DBUtil.getConnection();
            String sql = "select * from cost where cost_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Cost c = creatCost(rs);
                return c;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取资费信息失败", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        //没有数据返回null
        return null;
    }


    /**
     * 保存修改资费信息失败
     * @param c
     */
    @Override
    public void update(Cost c) {
        //创建连接
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "update cost set name = ?, cost_type = ?, base_duration = ?, base_cost = ?, unit_cost = ?, descr = ? where cost_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, c.getName());
            //setInt()和setDouble()不允许传入空值null，但业务需要传入null，此时要将数据当作对象来看
            ps.setString(2, c.getCostType());
            ps.setObject(3, c.getBaseDuration());
            ps.setObject(4, c.getBaseCost());
            ps.setObject(5, c.getUnitCost());
            ps.setString(6, c.getDescr());
            ps.setInt(7, c.getCostId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("保存修改资费信息失败", e);
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }

    /**
     * 提取方法封装，CTRL+ALT+M
     * @param rs
     * @return
     * @throws SQLException
     */
    private Cost creatCost(ResultSet rs) throws SQLException {
        Cost c = new Cost();
        c.setCostId(rs.getInt("cost_id"));
        c.setName(rs.getString("name"));
        c.setBaseDuration(rs.getInt("base_duration"));
        c.setBaseCost(rs.getDouble("base_cost"));
        c.setUnitCost(rs.getDouble("unit_cost"));
        c.setStatus(rs.getString("status"));
        c.setDescr(rs.getString("descr"));
        c.setCreatime(rs.getTimestamp("creatime"));
        c.setStartime(rs.getTimestamp("startime"));
        c.setCostType(rs.getString("cost_type"));
        return c;
    }
}
