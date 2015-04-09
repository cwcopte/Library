package library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Scanner;
public class Library {
	private boolean okToPrint;
	private HashMap<String, Patron> patronInfo;
	ArrayList<Book> collection;
	Calendar calendar;
	//return result for checkin and checkout
	ArrayList<Book> serveBook;
	//return search result for user input
	ArrayList<Book> searchBook;
	//HashMap <Book,Integer> bookVolume;
	Patron servePatron;
	boolean endDay=false;
	boolean endProgram=false;

	Library library;
	//keys will be the names of patrons 
	//whose values the corresponding Patron objects.
	public Library() {
		this.okToPrint=true;
		ArrayList<Book> collection=collection();
		//create book volume relation

	}
	public Library(ArrayList<Book> collection){
		this.okToPrint=false;
		//two or more constructor?
		Book book =new Book("title","author");
		//ArrayList<Book> collection=new ArrayList<Book>();
		this.collection=new ArrayList<Book>();
		this.collection.addAll(collection);
		//create book volume relation

	}


	public static void main(String[] args){
		Library library=new Library();
		//ArrayList<Book> collection=libray.collection();
		ArrayList<Book> collection=library.collection();
		System.out.println(collection.toString());
		//System.out.println(library.bookVolume.get(new Book("Zuleika Dobson", "Max Beerbohm")));
		
		//System.out.println(library.bookVolume.get(collection.get(1)));
		library.start();
	}





	void start(){

		System.out.println("What do you want to do with the library? \n "
				+ "'open' -Open the library in the morning. \n " 
				+ "'issueCard' -Issue a library card.. \n "
				+ "'serve' -serve Patron’s name. \n " 
				+ "'checkIn' -checkIn Book numbers."
				+ "'search' -search search string. \n "
				+ "'checkOut' -checkOut Book numbers. \n "
				+ "'close' -Close the library in the evening. \n "
				+ "'quit' -Exit this program. \n ");
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			while(!endProgram){
				while(!endDay ){
					String[] input;
					input=scanner.next().split(" ");
					String command=input[0];
					String object=input[1];
					//switch (scanner.next()) {
					switch (command) {
					case "open":
						open();
						System.out.println("Successfully open!");
						break;
					case "issueCard":
						Patron newPatron;
						newPatron=issueCard(object);
						System.out.println("card issued to"+newPatron.getName());
						break;
					case "serve":
						serve(object);
						System.out.println("The list of book(s) checked out by "+servePatron.getName());
						break;
					case "checkIn":
						//String[] bookNumber;
						//bookNumber=object.split(",");
						//i = Integer.parseInt(bookNumber); 
						checkIn(1);
						System.out.println("Book: "+"checked in.");
						break;
					case "search":
						search("part");
						System.out.println("The search result are:");
						//String message;
						//print("+searchBook.get(index)")
						break;
					case "checkOut":
						checkOut(1);
						System.out.println("Thanks for using polynomial caculator!");
						break;
					case "close":
						close();
						System.out.println("Successfully close library!");
						break;
					case "quit":
						quit();
						System.out.println("Thanks for using Library manage system!");
						break;

					}
				}
			}scanner.close();
		}

	}
	private ArrayList<Book> collection() {
		File file = new File("books.txt");
		ArrayList<Book> collection = new ArrayList<Book>();
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			while (true) {
				String line = reader.readLine();
				//System.out.println(line.toString());
				if (line == null) break;
				line = line.trim();
				if (line.equals("")) continue; // ignore possible blank lines
				String[] bookInfo = line.split(" :: ");
				collection.add(new Book(bookInfo[0], bookInfo[1]));
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return collection;
	}

	void print(String message){
		if (this.okToPrint)
			System.out.print(message);
	}
	void println(String message){
		if (this.okToPrint)
			System.out.println(message);
	}



	/**Starts the day. Then sends overdue notices to all patrons with overdue books
	 * Open the library in the morning. 
	 * @return
	 */
	ArrayList<OverdueNotice> open(){
		//further developing
		/*
		ArrayList<OverdueNotice>  dueNotice=new ArrayList<OverdueNotice> ();
		Calendar cal=new Calendar();//where this variable should belong to?
		cal.advance();
		Patron david = new Patron("David", null);
		OverdueNotice overdueNotice=new OverdueNotice(david,1);
		 */
		//calendar.advance();
		//if adding this, the test would not pass??!!
		ArrayList<OverdueNotice>  dueNotice=new ArrayList<OverdueNotice> ();
		//return createOverdueNotices();
		return dueNotice;

	}
	/**
	 * functions to send overdue notices to all patrons with overdue books
	 * @return
	 */
	ArrayList<OverdueNotice> createOverdueNotices(){
		//further developing
		ArrayList<OverdueNotice>  dueNotice=new ArrayList<OverdueNotice> ();
		return dueNotice;
	}

	/**
	 * Issue a library card.
	 * @return
	 */
	Patron issueCard(String nameOfPatron){
		//check if the user already exist
		if (patronInfo.containsKey(nameOfPatron))
		{
			//print info?
			return patronInfo.get(nameOfPatron);
		}
		else{
			//Patron newUser = new Patron(nameOfPatron, null);
			Patron newUser = new Patron(nameOfPatron, this.library);
			patronInfo.put(nameOfPatron, newUser);
			return newUser;
		}

	}
	/**
	 * serve Patron's name
	 * @param nameOfPatron
	 * @return
	 */
	Patron serve(String nameOfPatron){
		if (patronInfo.containsKey(nameOfPatron))
		{
			servePatron=patronInfo.get(nameOfPatron);
			serveBook=servePatron.getBooks();
			return servePatron;
		}else{
			//what to return if there is a typo or whatever?
			Patron nullPatron=new Patron("name",this.library);
			//serveBook=servePatron.getBooks();
			return nullPatron;
		}

	}
	/**
	 * Check in books (by number) held by user.
	 * @param bookNumbers
	 * @return
	 */

	ArrayList<Book> checkIn(int... bookNumbers){
		
		//suppose bookNumber=1

		//, ArrayList<Book> book, Patron patron
		ArrayList<Book> checkedBook=new ArrayList<Book>();
		int i=0;
		Book checkinBook;
		//right now, do not consider this case!
		for(i=0;i<bookNumbers.length;i++){
			if(serveBook.contains(bookNumbers[i])){
				//may not be serveBook, checkin books after search?
				//what exactly could the function do?
				checkinBook=serveBook.get(bookNumbers[i]);
				servePatron.giveBack(checkinBook);
				checkedBook.add(checkinBook);
				checkinBook.checkIn();
				//remove book from collection
				/*
			if (bookVolume.get(checkinBook)==1){

			}else{
				//only remove the first one
			}*/
				collection.remove(checkinBook);
			}
		}
		return checkedBook;
		//what if return 2 books with the same name? checkin, checkout
		//scanner.next()
	}
	/**
	 * Search for books, and print a numbered list of books that are found.
	 * @param part
	 * @return
	 */
	ArrayList<Book> search(String part){
		//the search string be at least 4 characters long
		//ArrayList<Book> searchBook;
		searchBook.clear();
		String partLowercase=part.toLowerCase(); 
		if (partLowercase.length()>3){
			for(Book bookinfo:collection){
				if ((bookinfo.getAuthor().toLowerCase().indexOf(partLowercase)>0)||
						(bookinfo.getTitle().toLowerCase().indexOf(partLowercase)>0)){
					searchBook.add(bookinfo);
					//keep adding every time when click search?
				}
				//ignore duplicate version of same book
			}
		}
		return searchBook;

	}
	/**
	 * Check out books (by number) found by search.
	 * @param bookNumbers
	 * @return
	 */
	ArrayList<Book> checkOut(int... bookNumbers){
		//suppose bookNumber=1
		//checkIn
		// ArrayList<Book> book, Patron patron
		searchBook.clear();
		ArrayList<Book> checkedBook=new ArrayList<Book>();
		int i=0;
		Book checkoutBook;
		//right now, do not consider this case!
		for(i=0;i<bookNumbers.length;i++){
			checkoutBook=searchBook.get(bookNumbers[i]);
			servePatron.take(checkoutBook);
			checkedBook.add(checkoutBook);
			//allow borrowing for 7 days
			checkoutBook.checkOut(7);
		}
		return checkedBook;
		//what if return 2 books with the same name? checkin, checkout
		//scanner.next()
	}
	/**
	 * Close the library in the evening.
	 */

	void close(){
		endDay=true;
	}
	/**
	 * Exit this program
	 */
	void quit(){
		endProgram=true;
	}

}
