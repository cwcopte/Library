package library;

public class Book {

	private String title;
	private String author;
	int dueDate;
	public Book(String title,String author) {
		// TODO Auto-generated constructor stub
		this.title=title;
		this.author=author;
		this.dueDate=-1;
	}
	/**
	 * getTitle of book
	 * @return Title
	 */
	public String getTitle(){
		return this.title;
	}
	/**
	 * getAuthor of book
	 * @return Author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * get dueDate of book
	 * @return dueDate
	 */
	public int getDueDate() {
		return dueDate;
	}
	/**
	 * checkOut a book and set duedate
	 * @param date set the date of duedate
	 */
	void checkOut(int date){
		//correct input
		//Books are due one week (7 days) after being checked out–no renewals.
		//if (0<date && date<8){
		this.dueDate=date;
		
		
	}
	/**
	 * checkIn a book and set duedate
	 */
	void checkIn(){
		this.dueDate=-1;
	}
	/**
	 * print info about the book
	 */
	public String toString(){
		return "Title: "+this.title+"\nAuthor: "+this.author;
	}

}
