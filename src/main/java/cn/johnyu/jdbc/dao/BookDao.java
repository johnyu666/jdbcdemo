package cn.johnyu.jdbc.dao;

import java.util.List;

import cn.johnyu.jdbc.pojo.Book;

public interface BookDao {
	public int[] addBooks(List<Book> books);
	public List<Book> findAllBooks();
	public int addBook(Book book);
	public int updateBook(int id,Book book);
	public int deleteBook(int id);
	public Book loadBook(int id);
	
	
}
