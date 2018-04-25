package org.koushik.javabrains.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.koushik.javabrains.jdbc.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class JdbcDaoImpl {
	
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate ;//= new JdbcTemplate();
	
	
	public Circle getCircle(int circleId)
	{
		Connection conn = null;
		
		Circle circle = null;
		
		
		
		try {
			
			conn = dataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("select * from circle where id = ?");
			ps.setInt(1, circleId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				circle = new Circle(circleId, rs.getString("name"));
			}
			rs.close();
			ps.close();
			conn.close();
			
			
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return circle;
		
	}
	
	public int getCircleCount(){
		
		//jdbcTemplate.setDataSource(dataSource);
		int count = jdbcTemplate.queryForInt("select count(*) from circle");
		return count;
	}
	

	public DataSource getDataSource() {
		return dataSource;
	}


	@Autowired
	public void setDataSource(DataSource dataSource) {
		//this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	

}
