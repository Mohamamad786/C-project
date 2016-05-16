package backend.transaction;

import backend.data.Data;

import java.util.LinkedList;
import java.util.Queue;

public class Transactions extends Data {

	/**
	 * A queue that represents all the transaction records in the
     * DailyTransactions file. This backend.data structure is memory efficient
     * because it discards the memory element after processing.
	 */
	private Queue<Record> records;
    /**
     * A queue of Regular transactions that represent the sessions logged in users
     * This backend.data structure is memory efficient as is disposes of the element after
     * processing.
     */
    private Queue<String> loggedInUsers;


    /**
     * default constructor for Transactions class.
     * primarily used for testing purposes
     */
    public Transactions() {
        super("AvailableTickets.txt");
        this.records = new LinkedList<Record>();
        this.loggedInUsers = new LinkedList<String>();
    }

	/**
     * Constructor for Transactions class. Reads in all transactions from
     * DailyTransactions file and stores it in memory.
	 * @param fileName A string that represents the path to the DailyTransactions file.
	 */
	public Transactions(String fileName) {
        super(fileName);
        this.records = new LinkedList<Record>();
        this.loggedInUsers = new LinkedList<String>();
	}

	/**
	 * This method is responsible retrieving a transaction record from the
	 * by removing the first element from the Queue
	 * @return A object that represents a transaction record, null on end of list
	 */
	public Record getTransaction() {        // initialize a tmp record
        return this.records.poll();
	}

    /**
     * This method is responsible for retrieving the next logged in user from the Queue.
     * @return a string the represents the name of the logged in user
     */
    public String getLoggedInUser() {
       return this.loggedInUsers.poll();
    }

	/**
	 * This method is responsible for parsing the line of text and inserting
	 * the appropriate transaction record into the transactions list
	 * @param line a string that represents a a line of a file
     * @return 0 on success, 1 on failure, 2 on InvalidCodeError
	 */
	public int decode(String line) {
        // initialize a temp record
        Record tmp = null;
        // extract the code from the line
        int code = new Integer(line.substring(0,2));

        if (code == 0 || code == 1 || code == 2 || code == 6) {// if tr code is Create, Delete or addCredit
            // extract name of user
            String name = (line.substring(3, 18)).trim();
            // extract type of user
            String type = line.substring(19, 21);
            // extract users credit
            double credit = new Double(line.substring(22));
            // create a new Regular Transaction
            tmp = new Regular(code, name, type, credit);
            if (code == 0)
                // add the sessions user to the loggedIn user list
                this.loggedInUsers.add(name);

        } else if (code == 3 || code == 4) {// extract event title
            String title = line.substring(3, 23).trim();
            // extract seller's name
            String seller = line.substring(24, 39).trim();
            // extract the number of tickets for sale
            int numTickets = new Integer(line.substring(40, 43));
            // extract the price of the ticket
            double price = new Double(line.substring(44));
            // create a new SellBuy transaction
            tmp = new SellBuy(code, title, seller, numTickets, price);

        } else if (code == 5) {// extract the buyers name
            String buyer = line.substring(3, 18).trim();
            // extract the sellers name
            String seller = line.substring(19, 34).trim();
            // extract the refund amount
            double refund = new Double(line.substring(35));
            // create a new Return transaction
            tmp = new Return(code, buyer, seller, refund);

        } else {
            return 2;

        }

        try {

            // add transaction to memory
            this.records.add(tmp);

        } catch (UnsupportedOperationException e){
            e.printStackTrace(System.err);
            return 1;
        } catch (ClassCastException e) {
            e.printStackTrace(System.err);
            return 1;
        } catch (NullPointerException e) {
            e.printStackTrace(System.err);
            return 1;
        } catch (IllegalArgumentException e) {
            e.printStackTrace(System.err);
            return 1;
        }

        // If add successful
        return 0;
	}

}