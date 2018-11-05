package com.xiaobai.dao;

import com.xiaobai.entity.Apiresponse;
import java.util.List;

public interface ApiresponseMapper {
    int deleteByPrimaryKey(Integer apiresponseCode);

    int insert(Apiresponse record);

    Apiresponse selectByPrimaryKey(Integer apiresponseCode);

    List<Apiresponse> selectAll();

    int updateByPrimaryKey(Apiresponse record);
}