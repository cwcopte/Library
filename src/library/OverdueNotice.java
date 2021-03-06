package library;

import java.util.ArrayList;

public class OverdueNotice {


	private Patron patron;
	private int todaysDate;
	private boolean overdue;

	public OverdueNotice(Patron patron, int todaysDate) {
		// TODO Auto-generated constructor stub
		this.patron=patron;
		this.todaysDate=todaysDate;
		this.overdue = false;

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
		String bookInfo="";
		ArrayList<Book> Book =patron.getBooks();
		
		//if book is null?
		overdueMessage += "Name: "+patron.getName()+"\n";
		for (Book book: Book){

			bookInfo+="Title: "+book.getTitle()+"\n";
			bookInfo+="Author: "+book.getAuthor();
			if (this.todaysDate==book.getDueDate()+1){
				//David[Title: A Bend in the River\nAuthor: V.S. Naipaul]
				bookInfo+=" Overdue\n";
			}
			else{
				
				bookInfo+="\n";
			}

		}
		overdueMessage+=bookInfo;
		//.getDueDate
		return overdueMessage;
		//return patron.getName()+patron.getBooks();
		//Here are books that you checked out:
		//book1 due:
		//book2 due:
		//There are ** due items:
		//book3 overdue:
	}
/**
 * check if there is any book overdue
 * @return true if there is book overdue
 */
	public boolean isOverdue() {
		
		for (Book book: patron.getBooks()){
			if (this.todaysDate==book.getDueDate()+1){
				overdue = true;
				
			}

		}return overdue;
		
	}



}
