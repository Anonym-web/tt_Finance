package com.anonym.parsedome.mapper.ttprogram.salveof;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface gc_bidMapperTest {
    int insertBatch(String[][] arrayList);
}