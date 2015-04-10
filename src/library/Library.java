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
				println("Oops, Bankrupted!!");
				quit();
			}

			if (isOpen) {
				switch (command) {
				case 1: //open
					println("Warning: Lirary already opened, please close first!");
					break;
				case 2: //issue card
					println("Input the name of the customer");
					issueCard(stringInput());
					break;
				case 3: //serve
					println("Input the name of the customer");
					serve(stringInput());
					print(servedPatronDetail());
					break;

				case 4:
					//use , to seperate
					//check in 
					if (servePatron!=null) {
						if (servePatron.getBooks().size() == 0) {
							println("There is no book to check in!");
						} else {
							println("Input the book number to checkin");
							String[] bookNum=stringInput().split(",");
							int inputNum;
							for(String num: bookNum){
								try {

									inputNum=Integer.parseInt(num);
									checkIn(inputNum);
									//println("Successfully checkin!");
								}

								catch (Exception e) {
									println("Please enter integer according to the list!");
									//print search result
									print(servedPatronDetail());
								}

							}
						}

					} else {
						println("Please set who you want to serve!");
					}

					break;
				case 5: //search
					println("Input the book name or author to search");
					search(stringInput());
					print(searchedResult());
					break;
				case 6: //check out
					if (servePatron != null && !searchBook.isEmpty()) {
						print(searchedResult());
						println("Input the book numbers to checkout, like 1,2");
						String[] bookNum1=stringInput().split(",");
						int inputNum1;
						for(String num: bookNum1){
							try {

							inputNum1=Integer.parseInt(num);
							checkOut(inputNum1);
						}
							catch (Exception e) {
								println("Please enter integer according to the list!");

							}
						}
					} else {
						println("please serve and search before checkout");
					}
					break;
				case 7:
					close();
					println("Closed! Good night!");
					break;
				default:
					break;
				}
			} else if (command == 1) {


				ArrayList<OverdueNotice> overdueNotice=new ArrayList<OverdueNotice>();
				overdueNotice=open();
				println("Today is "+calendar.getDate()+". Library is open, enjoy your day!");
				//overdueNotice.get(0).toString();
				if(overdueNotice.size()==0){
					println("no overdue notice.");
				}
				else{
					for(OverdueNotice notice: overdueNotice){
						println(notice.toString());
					}
					println("overdue notice sent to ***");
				}
				//return overdue notice


			} else {
				println("Please open the Library first");
			}
		}
	}

	/**
	 * print the searched results
	 * @return
	 */
	String searchedResult() {

		String result = "Result: \n";
		for (int i=0; i< searchBook.size(); i++){
			result += Integer.toString(i+1) + ", Title: "+searchBook.get(i).getTitle()+", Author: "+searchBook.get(i).getAuthor() + "\n";

		}

		return result;
	}

	/**
	 * Print the detais of the patron like name, checked book and due date
	 */
	String servedPatronDetail() {
		if (servePatron == null) {
			return "";
		}

		String detailsToPrint="Name: ";
		detailsToPrint += servePatron.getName() + "\n";

		
		detailsToPrint += "Number of Checked out books: "+servePatron.getBooks().size()+"\n";
		int i = 1;
		for (Book book: servePatron.getBooks()) {
			detailsToPrint += Integer.toString(i)+
					" Title: "+book.getTitle()+
					", Author: "+book.getAuthor()+
					", Due Date: "+book.getDueDate()+"\n";
			i++;

		}

		return detailsToPrint;
	}

	/**
	 * ask user to input a String Input
	 */
	String stringInput(){
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
			println(output);
			try {
				command = Integer.parseInt(in.next());

			} catch (Exception e) {
				command = 0;
			}
			if (command == 0) {
				print(manual);
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
			System.out.print(patronInfo.get(name).getBooks().get(0).getDueDate());
			System.out.println(patronInfo.get(name));
			System.out.println(calendar.getDate());
			//testing
			OverdueNotice insOverdueNotice = new OverdueNotice(patronInfo.get(name), calendar.getDate());
			if (insOverdueNotice.isOverdue())
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
			println("Warning: Customer already exist!");
			return patronInfo.get(nameOfPatron);
		}
		else{
			//Patron newUser = new Patron(nameOfPatron, null);
			println("Congrat! Customer "+nameOfPatron+" is our member now!");
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
		if (patronInfo.containsKey(nameOfPatron)) {
			servePatron=patronInfo.get(nameOfPatron);
		} else {
			println("Warning, Customer not exist");
			servePatron = null;
			return null;
		}
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
				if (servePatron.getBooks().contains(checkInBook)) {
					checkInBook.checkIn();
					checkInBooks.add(checkInBook);
					servePatron.giveBack(checkInBook);
					collection.add(checkInBook);
					println(checkInBook.getTitle() + " is successfully checked in!");
				} else {
					println(checkInBook.getTitle() +" was already checked in");
				}

			} else {
				println("The number you input is not in the list, please serve to see the list!");
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
						if ((book.getAuthor().equals(bookinfo.getAuthor())) && book.getTitle().equals(bookinfo.getTitle())) {
							haveBook = true;
						}
					}
					if (!haveBook) {
						searchBook.add(bookinfo);
					}

				}
			}
		}
		else{
			println("The input should be at least 4 character.");
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
					if (servePatron.getBooks().contains(checkOutBook)) {
						println("Sorry! "+ checkOutBook.getTitle()+ " was already checked out");
					} else {
						checkOutBooks.add(checkOutBook);
						checkOutBook.checkOut(calendar.getDate()+7);
						servePatron.take(checkOutBook);
						collection.remove(checkOutBook);
						println(checkOutBook.getTitle()+ " is successfully checkout!");
					}

				} else {
					println("One customer can only check out 3 books at a time!");
				}
			} else {
				println("number "+bookNumbers[i]+" is not valid");
			}
		}

		return checkOutBooks;
	}

	/**
	 * close the library
	 */
	void close() {
		servePatron = null;
		isOpen = false;
	}


	/**
	 * shut done
	 */
	void quit() {
		System.exit(0);
	}
}


