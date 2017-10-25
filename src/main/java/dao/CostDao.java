package dao;

import entity.Cost;

import java.util.List;

public interface CostDao {
    //查询
    List<Cost> findAll();
    //保存
    void save(Cost c);
    //删除
    void delete(Cost c);
    //修改
    Cost findById(int id);
    //更新
    void update(Cost c);
}
