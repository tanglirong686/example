package com.multiple.data.source.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multiple.data.source.mapper.MultipleDataSourceMapper;
import com.multiple.data.source.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService{

	@Autowired
	private MultipleDataSourceMapper multipleDataSourceMapper;
	
	@Override
	@PostConstruct
	public void test() {
		multipleDataSourceMapper.getListFromMaster().stream().forEach(d->{
			System.out.println(d);
		});
		
		multipleDataSourceMapper.getListFromCluster().stream().forEach(d->{
			System.out.println(d);
		});
		
		multipleDataSourceMapper.getListFromCluster1().stream().forEach(d->{
			System.out.println(d);
		});
	}
}