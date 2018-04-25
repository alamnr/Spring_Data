package org.koushik.javabrains.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;


import org.koushik.javabrains.jdbc.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class JdbcDaoImpl {
	
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate ;//= new JdbcTemplate();
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	//private SimpleJdbcTemplate  simpleJdbcTemplate;
	
	
	/*public Circle getCircle(int circleId)
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
		
	}*/
	
	public int getCircleCount(){
		
		//jdbcTemplate.setDataSource(dataSource);
		int count = jdbcTemplate.queryForInt("select count(*) from circle");
		return count;
	}
	
	
	public String getCircleName(int circleId){
		String sql = "select name from circle where id = ? ";
		return jdbcTemplate.queryForObject(sql, new Object[]{circleId},String.class);
	}
	
	public Circle getCircleById(int circleId){
		String sql = "select * from circle where id = ? ";
		return jdbcTemplate.queryForObject(sql,new Object[]{circleId}, new CircleMapper());
	}
	
	public List<Circle> getAllCircles(){
		String sql="select * from circle";
		return jdbcTemplate.query(sql, new CircleMapper());
	}
	
	private static final class CircleMapper implements RowMapper<Circle>{

		@Override
		public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
			Circle circle = new Circle(rs.getInt("id"),rs.getString("name"));
			return circle;
		}
		
	}
	
	public void insertCircle(Circle  circle){
		String sql = "insert into circle (id, name) values(?,?) ";
		jdbcTemplate.update(sql, new Object[]{circle.getId(),circle.getName()});
		
	}
	
	public void createTriangletable(){
		String sql = "create table triangle (id integer, name varchar(50))";
		jdbcTemplate.execute(sql);
	}
	
	public void insertNamedParamCircle(Circle circle){
		String sql = "insert into circle (id, name) values (:id,:name)";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id",circle.getId()).addValue("name", circle.getName());
		namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	

	@Autowired
	public void setDataSource(DataSource dataSource) {
		//this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	

}
