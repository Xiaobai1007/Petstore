package com.xiaobai.dao;

import com.xiaobai.entity.Pet;
import java.util.List;

public interface PetMapper {
    int deleteByPrimaryKey(Integer petId);

    int insert(Pet record);

    List<Pet> selectByPrimaryKey(Pet pet);

    List<Pet> selectAll();

    int updateByPrimaryKey(Pet record);

    List<Pet> selectByPetStatus(Pet pet);
}