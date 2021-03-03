package com.anonym.parsedome.mapper.ttprogram.master;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface engineering_businessMapper {

    int insertBatch(String[][] arrayList);
}
