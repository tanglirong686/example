package com.multiple.data.source.mapper;

import java.util.List;
import java.util.Map;

import com.multiple.data.source.database.DataSource;
import com.multiple.data.source.database.DataSourceConstant;

public interface MultipleDataSourceMapper {
	
	@DataSource(DataSourceConstant.MASTER)
	List<Map<String ,Object>> getListFromMaster();
	
	@DataSource(DataSourceConstant.CLUSTER)
	List<Map<String ,Object>> getListFromCluster();
	
	@DataSource(DataSourceConstant.CLUSTER1)
	List<Map<String ,Object>> getListFromCluster1();
}