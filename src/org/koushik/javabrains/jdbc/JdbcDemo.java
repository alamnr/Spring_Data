package org.koushik.javabrains.jdbc;

import org.koushik.javabrains.jdbc.dao.HibernateDaoImpl;
import org.koushik.javabrains.jdbc.dao.JdbcDaoImpl;
import org.koushik.javabrains.jdbc.dao.SimpleJdbcDaoImpl;
import org.koushik.javabrains.jdbc.model.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcDemo {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		//JdbcDaoImpl dao = ctx.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
		
		/*Circle circle = dao.getCircle(1);
		System.out.println(circle.getName());*/
		
		/*System.out.println(dao.getCircleCount());
		System.out.println(dao.getCircleName(1));
		System.out.println(dao.getCircleById(1).getName());
	*/	
		//dao.insertCircle(new Circle(3,"Third Circle"));
		//System.out.println("Size: "+dao.getAllCircles().size());
		
		//dao.createTriangletable();
		
		//dao.insertNamedParamCircle(new Circle(4,"Fourth Circle"));
		
		//SimpleJdbcDaoImpl simpleJdbcDaoImpl = ctx.getBean("simpleJdbcDaoImpl", SimpleJdbcDaoImpl.class);
		//System.out.println("Circle count- "+ simpleJdbcDaoImpl.getCircleCount());
		
		HibernateDaoImpl hibernateDaoImpl = ctx.getBean("hibernateDaoImpl", HibernateDaoImpl.class);
		
		System.out.println("Hibernate circle count: "+hibernateDaoImpl.getCircleCount());
		
	}

}
