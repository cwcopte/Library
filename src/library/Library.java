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
	boolean endDay;
	boolean endProgram;

	//Library library;
	//keys will be the names of patrons 
	//whose values the corresponding Patron objects.
	public Library() {
		this.okToPrint=true;
		patronInfo=new HashMap<String, Patron> ();
		collection=collection();
		//servePatron=new Patron("");
		calendar=new Calendar();
		serveBook=new ArrayList<Book> ();
		searchBook=new ArrayList<Book> ();
		//duplication?
		endDay=false;
		endProgram=false;

	}
	public Library(ArrayList<Book> collection){
		this.okToPrint=false;
		//two or more constructor?
		//Book book =new Book("title","author");
		//ArrayList<Book> collection=new ArrayList<Book>();
		patronInfo=new HashMap<String, Patron> ();
		this.collection=new ArrayList<Book>();
		this.collection.addAll(collection);
		//create book volume relation
		calendar=new Calendar();
		serveBook=new ArrayList<Book> ();
		searchBook=new ArrayList<Book> ();
		endDay=false;
		endProgram=false;

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
			/*
			while(!endProgram){
				while(!endDay ){
					String[] input;
					input=scanner.next().split(" ");
					System.out.println(input.toString());
					String command=input[0];
					System.out.println(command);
					String object=input[1];
					System.out.println(object);
			 */
			//switch (scanner.next()) {
			switch (scanner.next()) {
			//swich(comment)
			case "open":
				open();
				System.out.println("Successfully open!");
				break;
			case "issueCard":
				Patron newPatron;
				newPatron=issueCard("object");
				System.out.println("card issued to"+newPatron.getName());
				break;
			case "serve":
				serve("object");
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
				/*
					}
				}
				 */
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
		calendar.advance();
		//if adding this, the test would not pass??!!
		ArrayList<OverdueNotice>  dueNotice=new ArrayList<OverdueNotice> ();
		dueNotice=createOverdueNotices();
		return dueNotice;

	}
	/**
	 * functions to send overdue notices to all patrons with overdue books
	 * @return
	 */
	ArrayList<OverdueNotice> createOverdueNotices(){
		//further developing
		ArrayList<OverdueNotice>  dueNotice=new ArrayList<OverdueNotice> ();
		
		for(String user:patronInfo.keySet())
		{
			Patron pantronItem=patronInfo.get(user);
			OverdueNotice notice=new OverdueNotice(pantronItem, calendar.getDate());
			if (notice.isOverDue){
				dueNotice.add(notice);
			}
			
		}
		return dueNotice;
	}

	/**
	 * Issue a library card.
	 * @return
	 */
	Patron issueCard(String nameOfPatron){
		//check if the user already exist

		//System.out.println(patronInfo.containsKey(nameOfPatron));
		if (patronInfo != null) {
			if (patronInfo.containsKey(nameOfPatron))
			{
				//print info?
				return patronInfo.get(nameOfPatron);
			}else{
				Patron newUser = new Patron(nameOfPatron, this);
				patronInfo.put(nameOfPatron, newUser);
				return newUser;
			}

		}
		else{
			Patron newUser = new Patron(nameOfPatron, this);
			//library?
			//System.out.println(nameOfPatron);
			//System.out.println(this);
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
		}
		return servePatron;


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
			//if(serveBook.contains(bookNumbers[i])){
			if(serveBook.size()>(bookNumbers[i]-1)){
			//if(serveBook.contains(bookNumbers[i]-1)){
				

				//may not be serveBook, checkin books after search?
				//what exactly could the function do?
				checkinBook=serveBook.get(bookNumbers[i]-1);
				servePatron.giveBack(checkinBook);
				checkedBook.add(checkinBook);
				checkinBook.checkIn();
				//remove book from collection
				collection.add(checkinBook);
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
		//System.out.println(searchBook.isEmpty());
		String partLowercase=part.toLowerCase(); 
		if (partLowercase.length()>3){
			//Only books which are currently available (not checked out) will be returned. 
			for(Book bookinfo:collection){
				if ((bookinfo.getAuthor().toLowerCase().indexOf(partLowercase)>=0)||
						(bookinfo.getTitle().toLowerCase().indexOf(partLowercase)>=0)){
					//searchBook.add(bookinfo);

					//if (!searchBook.contains(bookinfo)){
					boolean found=false;
					for(Book book: searchBook){
						//if(book.toString()==bookinfo.toString()){
						
						if(book.getAuthor()==bookinfo.getAuthor()&&
								book.getTitle()==bookinfo.getTitle()){
						 
						//if(book.equals(bookinfo)){
							//
							found=true;
							//searchBook.add(bookinfo);
						}
					}
					if (!found){
						searchBook.add(bookinfo);
					}
					//}
					//Only books which
					//are currently available (not checked out) will be returned. 

					
					//keep adding every time when click search?
				}
				//ignore duplicate version of same book
			}
		}
		//System.out.println(searchBook.isEmpty());
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
		//searchBook.clear();
		ArrayList<Book> checkedBook=new ArrayList<Book>();
		Book checkoutBook;
		//right now, do not consider this case!
		for(int i=0;i<bookNumbers.length;i++){
			if(bookNumbers[i]-1<searchBook.size()){
				//get start from 0
				//input from 1
				checkoutBook=searchBook.get(bookNumbers[i]-1);
				servePatron.take(checkoutBook);
				checkedBook.add(checkoutBook);
				//allow borrowing for 7 days from now
				int dueDate=calendar.getDate()+7;
				checkoutBook.checkOut(dueDate);
				collection.remove(checkoutBook);
				}
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
