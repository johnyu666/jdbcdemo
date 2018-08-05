package cn.johnyu.jdbc.pojo;

public class Book {
	private Integer id;
	private String bname;
	private double price;
	
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(Integer id, String bname, double price) {
		super();
		this.id = id;
		this.bname = bname;
		this.price = price;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
