package cn.johnyu.jdbc.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.johnyu.jdbc.pojo.Book;

public class BooDaoImplTest {
	private static BookDao bookDao;
	@BeforeClass
	public static void init() {
		bookDao=new BookDaoImpl();
	}
	@Test
	public void testAddBook() throws Exception {
		Book book=new Book(null,"core java",43.87);
		int rs=bookDao.addBook(book);
		Assert.assertTrue(rs>0);
	}
	@Test
	public void testFindAllBooks() {
		List<Book> books=bookDao.findAllBooks();
		Assert.assertTrue(books!=null);
		Assert.assertTrue(!books.isEmpty());
	}
	@Test
	public void testLoadBook() {
		Book book=bookDao.loadBook(1);
		Assert.assertTrue(book!=null);
		Book book1=bookDao.loadBook(1000);
		Assert.assertTrue(book1==null);
	}
	@Test
	public void testUpdateBook() {
		Book book=new Book(1,"aa",78.98);
		int rs=bookDao.updateBook(1, book);
		Assert.assertTrue(rs>0);
	}
	@Test
	public void testDeletBook() throws Exception {
		int rs=bookDao.deleteBook(1);
		Assert.assertTrue(rs>0);
	}
	@Test
	public void testAddBooks() throws Exception {
		List<Book> books=new ArrayList<Book>();
		for (int i = 0; i < 5; i++) {
			Book book=new Book(i,"book"+i,i*10);
			books.add(book);
		}
		int[] rs=bookDao.addBooks(books);
		Assert.assertTrue(rs!=null);
	}
	@Test
	public void testAddDouble() throws Exception {
		Book book1=new Book(null,"a1",43.87);
		Book book2=new Book(null,"a2",43.87);
		bookDao.addBook(book1);
		int m=1/0;
		bookDao.addBook(book2);
	}
	
	
}
