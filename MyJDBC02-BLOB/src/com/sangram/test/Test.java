package com.sangram.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test {

	public static void main(String[] args) {

		try {
			//blob inserting to oracle Database
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SAMDB", "sam123");
			PreparedStatement pst = con.prepareStatement("insert into user_image values(?,?)");
			pst.setInt(1, 111);
			File file = new File("Z:/zDOCUMENTS/images/elli.jpg");
			FileInputStream fis = new FileInputStream(file);
			pst.setBinaryStream(2, fis, file.length());
			int update = pst.executeUpdate();
			System.out.println(update);
			System.out.println("just checking");
		} catch (Exception e) {
		  e.printStackTrace();
		}

		try {
			//blob retrieving from oracle Database
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SAMDB", "sam123");
			PreparedStatement pst = con.prepareStatement("select * from user_image where id=?");
			pst.setInt(1, 111);
			ResultSet rs = pst.executeQuery();
			rs.next();
			FileOutputStream fos = new FileOutputStream("Z:/zDOCUMENTS/images/output/elliLOS.jpg");
			InputStream is = rs.getBinaryStream(2);
			int val = is.read();
			while(val!=-1)
			{
				fos.write(val);
				val = is.read();
			}
            System.out.println("image retrieved successfully");
		} catch (Exception e) {
          e.printStackTrace();
		}
	}

}
