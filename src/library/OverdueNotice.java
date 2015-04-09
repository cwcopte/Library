package library;

import java.util.ArrayList;

public class OverdueNotice {

	Patron patron;
	int todaysDate;
	public OverdueNotice(Patron patron, int todaysDate) {
		// TODO Auto-generated constructor stub
		this.patron=patron;
		this.todaysDate=todaysDate;
	}
	/**
	 * Returns as a String, in a nice, humanly readable format, an overdue notice. The
	 * notice should list all the books currently checked out by the patron, along with their
	 * due dates, and call attention to which ones are overdue
	 */
	public String toString(){
		//need more construction
		//how to get Book. duedate?
		String overdueMessage="";
		ArrayList<Book> Book =patron.getBooks();
		for (Book book: Book){
			book.getDueDate();
			overdueMessage+=book.getTitle();
			overdueMessage+=book.getDueDate();
			
		}
		//.getDueDate
		return overdueMessage;
		//return patron.getName()+patron.getBooks();
		//Here are books that you checked out:
		//book1 due:
		//book2 due:
		//There are ** due items:
		//book3 overdue:
	}

}
