package library;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OverdueNoticeTest {
	//created by myself
	OverdueNotice overdueNotice;
	OverdueNotice overdueNotice1;
	Patron david;
	int todaysDate;
	Book book;
	Book newBook;
	@Before
	public void setUp() throws Exception {
		//patron=new Patron();
		//WHY NULL?
		Patron david = new Patron("David", null);
		todaysDate=3;
		//what is the today's date do?
		//show how many days dued? or other info?
		overdueNotice=new OverdueNotice(david,todaysDate);
		book =new Book("A Bend in the River","V.S. Naipaul");
		david.take(book);
		book.checkOut(7);
		//Calendar cal=new Calendar();
		//cal.advance();
		Patron Emma = new Patron("Emma", null);
		overdueNotice1=new OverdueNotice(Emma,todaysDate);
		newBook =new Book("Equal Rites", "Terry Pratchett");
		Emma.take(newBook);
		newBook.checkOut(2);
	}
	@Test
	public void testOverdueNotice() {
		//test constructor
		assertTrue(overdueNotice instanceof OverdueNotice);

	}
	@Test
	public void testToString() {
		//need something combine calendar with patron
		//but can not do in their seperate class
		//System.out.println(overdueNotice.toString());
		assertTrue(overdueNotice.toString().equals("Name: David\nTitle: A Bend in the River\nAuthor: V.S. Naipaul\n"));
		

		//System.out.println(overdueNotice1.toString());
		assertTrue(overdueNotice1.toString().equals("Name: Emma\nTitle: Equal Rites\nAuthor: Terry Pratchett Overdue\n"));
		
		
		//fail("Not yet implemented");
	}

}
