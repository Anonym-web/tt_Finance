package com.anonym.parsedome.mapper.ttprogram.master;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface gc_business1Mapper{
    int insertBatch(String[][] arrayList);
}