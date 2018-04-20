package com.grecha.billpayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

@EnableJpaAuditing
@SpringBootApplication
public class BillPaymentApplication {

	public static void createNewDatabase(String fileName) {

		String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;

		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(BillPaymentApplication.class, args);
		createNewDatabase("/billpayment.db");
	}


}
