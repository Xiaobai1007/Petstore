package com.xiaobai.dao;

import com.xiaobai.entity.Photo;
import java.util.List;

public interface PhotoMapper {
    int deleteByPrimaryKey(Integer photoId);

    int insert(Photo record);

    Photo selectByPrimaryKey(Integer photoId);

    List<Photo> selectAll();

    int updateByPrimaryKey(Photo record);
}