package com.xiaobai.dao;

import com.xiaobai.entity.Inventories;
import java.util.List;

public interface InventoriesMapper {
    int deleteByPrimaryKey(Integer inventoriesId);

    int insert(Inventories record);

    Inventories selectByPrimaryKey(Integer inventoriesId);

    List<Inventories> selectAll();

    int updateByPrimaryKey(Inventories record);
}