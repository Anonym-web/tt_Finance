package com.anonym.parsedome.mapper.ttwhmis.master;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface net_business_provinceMapper {

    int insertBatch(String[][] arrayList);
}
