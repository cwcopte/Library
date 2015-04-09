/**
 * 
 */
package library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author David Matuszek
 */
public class CalendarTest {
	Calendar cal;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cal = new Calendar();
	}

	/**
	 * Test method for {@link library.Calendar#getDate()}
	 * and {@link library.Calendar#advance()}.
	 */
	@Test
	public void testCalendar() {
		assertTrue(cal instanceof Calendar);
		//ADD
		assertEquals(0, cal.getDate());
		cal.advance();
		assertEquals(1, cal.getDate());
		cal.advance();
		assertEquals(2, cal.getDate());
	}
	//test the constructor???

	//new
	@Test
	public void testGetDate() {
		//basically the same as constructor??
		assertEquals(0, cal.getDate());
		cal.advance();
		assertEquals(1, cal.getDate());
		cal.advance();
		cal.advance();
		assertEquals(3, cal.getDate());

	}

	@Test
	public void testAdvance() {
		assertEquals(0, cal.getDate());
		cal.advance();
		assertEquals(1, cal.getDate());
		cal.advance();
		cal.advance();
		assertEquals(3, cal.getDate());
	}


}
