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
		String overdueBook="";
		ArrayList<Book> Book =patron.getBooks();
		overdueMessage+=patron.getName()+" ";
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
		overdueMessage+="[";
		overdueMessage+=overdueBook;
		overdueMessage+="]";
		}
		else{
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
