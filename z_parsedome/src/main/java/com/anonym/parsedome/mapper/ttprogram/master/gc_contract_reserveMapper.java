package com.anonym.parsedome.mapper.ttprogram.master;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface gc_contract_reserveMapper{
    int insertBatch(String[][] arrayList);
}