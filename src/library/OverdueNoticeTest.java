package library;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OverdueNoticeTest {
	//created by myself
	OverdueNotice overdueNotice;
	Patron david;
	int todaysDate;
	Book book;
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
		//Calendar cal=new Calendar();
		//cal.advance();
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
		System.out.println(overdueNotice.toString());
		assertTrue(overdueNotice.toString().equals("David[Title: A Bend in the River\nAuthor: V.S. Naipaul]"));

		//fail("Not yet implemented");
	}

}
