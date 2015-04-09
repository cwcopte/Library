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
	public String getTitle(){
		return this.title;
	}
	public String getAuthor() {
		return author;
	}
	public int getDueDate() {
		return dueDate;
	}
	void checkOut(int date){
		//correct input
		//Books are due one week (7 days) after being checked out–no renewals.
		//if (0<date && date<8){
		this.dueDate=date;
		
		
	}
	void checkIn(){
		this.dueDate=-1;
	}
	public String toString(){
		return "Title: "+this.title+"\nAuthor: "+this.author;
	}

}
