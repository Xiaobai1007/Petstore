package com.xiaobai.dao;

import com.xiaobai.entity.Pet;
import java.util.List;

public interface PetMapper {
    int deleteByPrimaryKey(Integer petId);

    int insert(Pet record);

    Pet selectByPrimaryKey(Integer petId);

    List<Pet> selectAll();

    int updateByPrimaryKey(Pet record);

    Pet selectByPetStatus(String petStatus);
}