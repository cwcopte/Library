package library;

import java.util.ArrayList;

public class Patron {


	private String name;
	private Library library;
	private ArrayList<Book> book;
	/*
//paula.take(book);
	public static void main(String[] args){
		Book bookname = new Book("Disappearing Nightly", "Laura Resnick");
		//System.out.println(bookname.toString());
		Patron paula = new Patron("Paula", null);
		//System.out.println(paula.toString());
		//paula.take(bookname);
	}*/
	public Patron(String name, Library library) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.library=library;
		//where to use library
		this.book=new ArrayList<Book>();
	}

	public String getName() {
		return name;
	}
	void take(Book book){
		//book.getTitle();
		//book.getAuthor();
		
		//date? change
		this.book.add(book);
		//System.out.println(this.book.toString());
	}
	void giveBack(Book book){
		this.book.remove(book);
	}
	public ArrayList<Book> getBooks() {
		return book;
	}
	public String toString(){
		return this.name;
	}
}
