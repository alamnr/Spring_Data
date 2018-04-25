package org.koushik.javabrains.jdbc;

import org.koushik.javabrains.jdbc.dao.JdbcDaoImpl;
import org.koushik.javabrains.jdbc.model.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcDemo {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		JdbcDaoImpl dao = ctx.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
		
		/*Circle circle = dao.getCircle(1);
		System.out.println(circle.getName());*/
		
		System.out.println(dao.getCircleCount());
		System.out.println(dao.getCircleName(1));
		System.out.println(dao.getCircleById(1).getName());
		System.out.println("Size: "+dao.getAllCircles().size());
	}

}
