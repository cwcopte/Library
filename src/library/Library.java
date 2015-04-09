package library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.acl.Owner;
import java.util.*;

public class Library {
	//instance variable
	private boolean okToPrint;
	ArrayList<Book> collection;
	HashMap<String, Patron> patronInfo;
	Calendar calendar;
	//indicate whether it's open
	boolean isOpen;
	//the patron currently served
	Patron servePatron;
	HashMap<Integer, Book> checkedBooks;
	ArrayList<Book> searchBook;
	boolean isServe = false;


	public Library() {
		okToPrint = true;
		collection = new ArrayList<Book>();
		collection = readBookCollection();
		patronInfo = new HashMap<String, Patron> ();
		calendar = new Calendar();
		isOpen = false;
	}

	public Library(ArrayList<Book> collection) {
		okToPrint = false;
		this.collection = new ArrayList<Book>();
		this.collection = collection;
		patronInfo = new HashMap<String, Patron> ();
		calendar = new Calendar();
		isOpen = false;
	}


	/**
	 * read book collection from txt
	 * @return
	 */
	private ArrayList<Book> readBookCollection() {
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

	public static void main(String[] args){
		//creates one Library
		Library library = new Library();
		library.start();
	}

	/**
	 * start the program.
	 */
	void start() {
		int command;
		while (true) {
			command = askUserInput();

			if (command == 8) {
				quit();
			}

			if (isOpen) {
				switch (command) {
				case 1:
					System.out.println("Warning: Bank already opened, please close first!\n");
					break;
				case 2:
					String issueName = patronNameInput();
					issueCard(issueName);
					break;
				case 3:
					break;
				case 4:
					if (isServe) {

					} else {
						System.out.println("Please select the customer you served!");
					}
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				default:
					break;
				}
			} else if (command == 1) {
				open();
			} else {
				System.out.println("Please open the bank first");
			}
		}
	}


	/**
	 * ask user to input a name
	 */
	String patronNameInput(){
		Scanner in = new Scanner(System.in);
		return in.nextLine();
	}

	/**
	 * ask user for the command
	 */
	int askUserInput() {
		Scanner in = new Scanner(System.in);
		int command = 0;

		String output = "Please command, input 0 to get help";

		String manual = "Please input a command between 1 and 8\n";
		manual+="1. Open the library!\n";
		manual+="2. Issue a library card!\n";
		manual+="3. Serve a patron!\n";
		manual+="4. Check in Books held by user!\n";
		manual+="5. Search for books!\n";
		manual+="6. Check out books!\n";
		manual+="7. Close the library!\n";
		manual+="8. Exit this program!\n";

		while ((command<1)||(command >8)){
			System.out.println(output);
			try {
				command = Integer.parseInt(in.next());

			} catch (Exception e) {
				command = 0;
			}
			if (command == 0) {
				System.out.println(manual);
			}
		}

		return command;
	}

	/**
	 * Print message if okToPrint is true
	 * @param message
	 */
	void print(String message) {
		if (okToPrint) {
			System.out.print(message);
		}
	}

	/**
	 * Print message if okToPrint is true
	 * @param message
	 */
	void println(String message) {
		if (okToPrint) {
			System.out.println(message);
		}
	}

	/**
	 * Start the day and sends overdue notice
	 * @return
	 */
	ArrayList<OverdueNotice> open() {
		//		if (isOpen) {
		//			System.out.println("Already opened, please close first!");
		//			return new ArrayList<OverdueNotice>();
		//		}
		calendar.advance();
		isOpen = true;
		return createOverdueNotice();
	}


	/**
	 * create a list of overdue notice
	 * @return
	 */
	ArrayList<OverdueNotice> createOverdueNotice () {
		ArrayList<OverdueNotice> dueNotice = new ArrayList<OverdueNotice> ();
		for (String name: patronInfo.keySet()) {
			OverdueNotice insOverdueNotice = new OverdueNotice(patronInfo.get(name), calendar.getDate());
			if (insOverdueNotice.overdue())
				dueNotice.add(insOverdueNotice);
		}
		return dueNotice;
	}

	/**
	 * Issue library card to a patron
	 * @param nameOfPatron
	 * @return
	 */
	Patron issueCard(String nameOfPatron) {
		//check if the user already exist
		if (patronInfo.containsKey(nameOfPatron))
		{
			//print info?
			return patronInfo.get(nameOfPatron);
		}
		else{
			//Patron newUser = new Patron(nameOfPatron, null);
			Patron newUser = new Patron(nameOfPatron, this);
			patronInfo.put(nameOfPatron, newUser);
			return newUser;
		}
	}

	/**
	 * set the person now serving
	 * @param nameOfPatron
	 * @return
	 */
	Patron serve(String nameOfPatron) {
		isServe = true;
		servePatron=patronInfo.get(nameOfPatron);
		int i = 1;
		checkedBooks = new HashMap<Integer, Book> ();
		for (Book book:servePatron.getBooks()) {
			checkedBooks.put(i, book);
			i++;
		}		
		return servePatron;
	}

	/**
	 * check in books
	 * @param bookNumbers
	 * @return
	 */
	ArrayList<Book> checkIn(int... bookNumbers) {
		ArrayList<Book> checkInBooks=new ArrayList<Book>();

		Book checkInBook;
		for(int i=0; i<bookNumbers.length;i++){
			if(checkedBooks.keySet().contains(bookNumbers[i])){
				checkInBook = checkedBooks.get(bookNumbers[i]);
				checkInBook.checkIn();
				checkInBooks.add(checkInBook);
				servePatron.giveBack(checkInBook);
				collection.add(checkInBook);
			}
		}
		return checkInBooks;
	}

	/**
	 * search books
	 * @param part
	 * @return
	 */
	ArrayList<Book> search(String part) {
		searchBook = new ArrayList<>();
		String partLowercase=part.toLowerCase(); 

		if (partLowercase.length()>3){
			for(Book bookinfo:collection){
				boolean haveBook = false;
				if ((bookinfo.getAuthor().toLowerCase().indexOf(partLowercase)>=0)||
						(bookinfo.getTitle().toLowerCase().indexOf(partLowercase)>=0)){
					for (Book book:searchBook) {
						if ((book.getAuthor() == bookinfo.getAuthor()) && book.getTitle() == bookinfo.getTitle()) {
							haveBook = true;
						}
					}
					if (!haveBook) {
						searchBook.add(bookinfo);
					}

				}
			}
		}
		return searchBook;
	}

	/**
	 * checkout books
	 * @param bookNumbers
	 * @return
	 */
	ArrayList<Book> checkOut(int... bookNumbers) {
		ArrayList<Book> checkOutBooks=new ArrayList<Book>();
		Book checkOutBook;

		for(int i=0; i<bookNumbers.length;i++){
			if (bookNumbers[i]<= searchBook.size()) {
				checkOutBook = searchBook.get(bookNumbers[i]-1);
				//only three books could be checked out per person
				if(servePatron.getBooks().size()<3){
					checkOutBooks.add(checkOutBook);
					checkOutBook.checkOut(calendar.getDate()+7);
					servePatron.take(checkOutBook);
					collection.remove(checkOutBook);
				}

			}
		}

		return checkOutBooks;
	}

	/**
	 * close the library
	 */
	void close() {
		isServe = false;
		isOpen = false;
	}


	/**
	 * shut done
	 */
	void quit() {
		System.exit(0);
	}
}


