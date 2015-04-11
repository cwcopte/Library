package library;

public class Calendar {
	

	private int date;
	public Calendar() {
		// TODO Auto-generated constructor stub
	this.date=0;
	}
	/**
	 * get today's date from calendar
	 * @return today's date
	 */
	public int getDate() {
		return date;
	}
	/**
	 * increase the date of calendar
	 */
	void advance(){
		this.date++;
	}
}
