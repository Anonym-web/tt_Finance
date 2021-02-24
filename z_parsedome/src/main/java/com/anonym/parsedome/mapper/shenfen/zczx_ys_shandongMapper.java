package com.anonym.parsedome.mapper.shenfen;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface zczx_ys_shandongMapper {

    int insertBatch(List<String> arrayList);
}
