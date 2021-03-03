package com.anonym.parsedome.mapper.ttprogram.master;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface gc_business2Mapper{
    int insertBatch(String[][] arrayList);
}