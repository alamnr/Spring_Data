package org.koushik.javabrains.jdbc;

import org.koushik.javabrains.jdbc.dao.JdbcDaoImpl;
import org.koushik.javabrains.jdbc.model.Circle;

public class JdbcDemo {

	public static void main(String[] args) {
		Circle circle = new JdbcDaoImpl().getCircle(1);
		System.out.println(circle.getName());
	}

}
