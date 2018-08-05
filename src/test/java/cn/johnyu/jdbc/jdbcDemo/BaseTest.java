package cn.johnyu.jdbc.jdbcDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.johnyu.jdbc.pojo.Book;
import cn.johnyu.jdbc.util.DBUtil;
import oracle.jdbc.driver.OracleDriver;

public class BaseTest {
	public static void main(String[] args) throws Exception {
		List<Book> books=new ArrayList<Book>();
		String sql = "select id,book_name bname ,price from books";
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String bname = rs.getString("bname");
			double price = rs.getDouble("price");
			Book book=new Book(id, bname, price);
			books.add(book);
//			System.out.println(id + "\t" + bname + "\t" + price);
		}
		DBUtil.close(con, pst, rs);

	}

	public static void main1(String[] args) throws Exception {
		Book book=new Book(null,"thiking in java",23.45);
		String sql = "insert into books(id,book_name,price) values(john_seq10.nextval,?,?)";
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, book.getBname());
		pst.setDouble(2, book.getPrice());

		// pst.execute();
		int rs = pst.executeUpdate();
		System.out.println(rs);
		DBUtil.close(con, pst, null);
	}

}
