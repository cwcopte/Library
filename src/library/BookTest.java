package library;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BookTest {
	//all created by me
	Book book;
	Book book1;
	@Before
	public void setUp() throws Exception {
		book =new Book("title","author");
		book1 =new Book("A Bend in the River","V.S. Naipaul");
	}
	@Test
	public void testBook() {
		assertTrue(book instanceof Book);
		//test constructor
		assertEquals(book.getAuthor(),"author");
		assertEquals(book.getDueDate(),-1,0.0);
		assertEquals(book.getTitle(),"title");
		//fail("Not yet implemented");

		assertTrue(book1 instanceof Book);
		assertEquals(book1.getAuthor(),"V.S. Naipaul");
		assertEquals(book1.getDueDate(),-1,0.0);
		assertEquals(book1.getTitle(),"A Bend in the River");
	}
	@Test
	public void testGetTitle() {
		assertEquals(book.getTitle(),"title");
		assertEquals(book1.getTitle(),"A Bend in the River");
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAuthor() {
		assertEquals(book.getAuthor(),"author");
		assertEquals(book1.getAuthor(),"V.S. Naipaul");
		//fail("Not yet implemented");
	}

	@Test
	public void testGetDueDate() {
		assertEquals(book.getDueDate(),-1,0.0);
		book.checkOut(7);
		assertEquals(book.getDueDate(),7,0.0);
		book.checkIn();
		assertEquals(book.getDueDate(),-1,0.0);

		assertEquals(book1.getDueDate(),-1,0.0);
		book1.checkOut(3);
		assertEquals(book1.getDueDate(),3,0.0);

		//do wrong input check here ? or in Library class?
		/*
		book1.checkOut(10);
		assertEquals(book1.getDueDate(),-1,0.0);
		 */
		//if the book has been checkout, it can not be recheckout?
		//multiple copy of the books, the volume of them or just the duplication should be deal with?
		//fail("Not yet implemented");
	}

	@Test
	public void testCheckOut() {

		//fail("Not yet implemented");
	}

	@Test
	public void testCheckIn() {
		assertEquals(book1.getDueDate(),-1,0.0);
		book1.checkOut(7);
		assertEquals(book1.getDueDate(),7,0.0);
		book1.checkIn();
		assertEquals(book.getDueDate(),-1,0.0);

		//fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		assertTrue(book.toString().equals("Title: title\nAuthor: author"));
		assertTrue(book1.toString().equals("Title: A Bend in the River\nAuthor: V.S. Naipaul"));

		//fail("Not yet implemented");
	}

}
