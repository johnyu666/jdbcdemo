package cn.johnyu.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.johnyu.jdbc.pojo.Book;
import cn.johnyu.jdbc.util.DBUtil;

public class BookDaoImplWithDbcp implements BookDao{
	
	@Override
	public List<Book> findAllBooks() {
		List<Book> books=new ArrayList<Book>();
		String sql = "select id,book_name bname ,price from books";
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			con = DBUtil.getConnectionWithDbcp();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String bname = rs.getString("bname");
				double price = rs.getDouble("price");
				Book book=new Book(id, bname, price);
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.close(con, pst, rs);
		return books;
	}

	@Override
	public int addBook(Book book) {
		
		String sql = "insert into books(id,book_name,price) values(john_seq10.nextval,?,?)";
		Connection con=null;
		PreparedStatement pst=null;
		int rs=0;
		try {
			con = DBUtil.getConnectionWithDbcp();
			pst = con.prepareStatement(sql);
			pst.setString(1, book.getBname());
			pst.setDouble(2, book.getPrice());

			rs = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.close(con, pst, null);
		return rs;
	}

	@Override
	public int updateBook(int id, Book book) {
		String sql = "update books set book_name=?, price=? where id=?";
		Connection con=null;
		
		PreparedStatement pst=null;
		int rs=0;
		try {
			con = DBUtil.getConnectionWithDbcp();
			con.setAutoCommit(false);
			pst = con.prepareStatement(sql);
			pst.setString(1, book.getBname());
			pst.setDouble(2, book.getPrice());
			pst.setInt(3, id);
			rs = pst.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.close(con, pst, null);
		return rs;
	}

	@Override
	public int deleteBook(int id) {
		String sql = "delete books  where id=?";
		Connection con=null;
		PreparedStatement pst=null;
		int rs=0;
		try {
			con = DBUtil.getConnectionWithDbcp();
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.close(con, pst, null);
		return rs;
	}

	@Override
	public Book loadBook(int id) {
		Book book=null;
		String sql = "select id,book_name bname ,price from books where id=?";
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			con = DBUtil.getConnectionWithDbcp();
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				String bname = rs.getString("bname");
				double price = rs.getDouble("price");
				book=new Book(id, bname, price);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close(con, pst, rs);
		return book;
	}

	@Override
	public int[] addBooks(List<Book> books) {
		String sql = "insert into books(id,book_name,price) values(john_seq10.nextval,?,?)";
		Connection con=null;
		PreparedStatement pst=null;
		
		int[] rs=null;
		try {
			con = DBUtil.getConnectionWithDbcp();
			pst = con.prepareStatement(sql);
			for (int i = 0; i < books.size(); i++) {
				pst.setString(1, books.get(i).getBname());
				pst.setDouble(2, books.get(i).getPrice());
				pst.addBatch();
			}
			rs = pst.executeBatch();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.close(con, pst, null);
		return rs;
	}

}
