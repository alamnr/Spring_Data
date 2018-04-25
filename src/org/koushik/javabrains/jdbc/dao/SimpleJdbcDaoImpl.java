package org.koushik.javabrains.jdbc.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class SimpleJdbcDaoImpl extends SimpleJdbcDaoSupport {
	
	public int getCircleCount(){
		
		//jdbcTemplate.setDataSource(dataSource);
		int count = this.getJdbcTemplate().queryForInt("select count(*) from circle");
		return count;
	}
}
