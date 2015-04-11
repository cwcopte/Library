package library;

import java.util.ArrayList;

public class Patron {


	private String name;
	private Library library;
	private ArrayList<Book> book;

	public Patron(String name, Library library) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.library=library;
		this.book=new ArrayList<Book>();
	}
/**
 * get name of the patron
 * @return patron name
 */
	public String getName() {
		return name;
	}
	/**
	 * take the book with this patron
	 * @param book
	 */
	void take(Book book){

		this.book.add(book);

	}
	/**
	 * return the book to libray
	 * @param book
	 */
	void giveBack(Book book){
		this.book.remove(book);
	}
	/**
	 * get the booklist of the patron
	 * @return booklist
	 */
	public ArrayList<Book> getBooks() {
		return book;
	}
	/**
	 * print the name of patron
	 */
	public String toString(){
		return this.name;
	}
}
