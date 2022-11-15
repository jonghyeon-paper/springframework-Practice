package com.example.cores.sample.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

/**
 * SampleMapper
 *
 * created by jonghyeon on 2022/11/01
 */
public interface SampleMapper {

    @Select("SELECT * FROM SAMPLE")
    List<Map<String, Object>> selectSampleUseAnnotation();

    List<Map<String, Object>> selectSampleUseXml();
}
