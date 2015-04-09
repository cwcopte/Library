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

	}
	
	public boolean overdue() {
		if (overdue) {
			return true;
		} else {
			return false;
		}
		
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
		String overdueBook="";
		ArrayList<Book> Book =patron.getBooks();
		
		//if book is null?

		for (Book book: Book){
			//System.out.print(this.todaysDate);
			//System.out.print(book.getDueDate());
			if (this.todaysDate>book.getDueDate()){
				//David[Title: A Bend in the River\nAuthor: V.S. Naipaul]

				overdueBook+="Title: "+book.getTitle()+"\n";
				overdueBook+="Author: "+book.getAuthor();
				//overdueMessage+=book.getDueDate();
			}

		}
		if (!overdueBook.isEmpty()){
			overdue = true;
			overdueMessage += patron.getName()+" ";
			overdueMessage+="[";
			overdueMessage+=overdueBook;
			overdueMessage+="]";
		}
		
		else{
			overdue = false;
			overdueMessage += patron.getName()+" ";
			overdueMessage+="There is no overdue book.";
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
