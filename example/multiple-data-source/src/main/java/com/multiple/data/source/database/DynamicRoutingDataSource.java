package com.multiple.data.source.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 	动态数据源切换配置
 **/

@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
	
    @Override
    protected Object determineCurrentLookupKey() {
    	// 数据源名称
        String dataSourceName = DynamicDataSourceContextHolder.getDataSourceRouterKey();
        if (StringUtils.isEmpty(dataSourceName)) {
        	log.error("当前数据源为空！！！");
        }
        return dataSourceName;
    }
}