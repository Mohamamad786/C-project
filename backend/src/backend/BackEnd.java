package backend;

import backend.data.FatalErrorException;
import backend.event.Event;
import backend.event.Tickets;
import backend.transaction.*;
import backend.user.Accounts;
import backend.user.User;
import backend.user.UserExistsException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class controls the systems memory elements and
 * performs all the transactions from the DailyTransactions
 * file. It validate each transaction and outputs new
 * UserAccounts and AvailableTickets files for the front
 * end to use the next day.
 */
public class BackEnd {

	/**
	 * Provides access to the all of the DailyTransactions in
	 * memory.
	 */
	private Transactions transactions;
	/**
	 * Provides access to all UserAccounts in memory.
	 */
	private Accounts accounts;
	/**
	 * provides access to all tickets in the systems memory.
	 */
	private Tickets tickets;

    private User loggedIn;

    /**
     * Default constructor for OperationManager class.
     * It is primarily used for testing purposes.
     */
    public BackEnd(Transactions transactions, Accounts accounts, Tickets tickets) {
        this.transactions = transactions;
        this.accounts = accounts;
        this.tickets = tickets;
        this.loggedIn = null;
    }

	/**
     * Constructor for OperationManager
     * @param transactionsFile a string representing the path of the DailyTransactions file.
	 * @param accountsFile a string representing the path of the UserAccounts file.
	 * @param ticketsFile a string representing the path of the AvailableTickets file.
	 */
    public BackEnd(String transactionsFile, String accountsFile, String ticketsFile) {
        this.transactions = new Transactions(transactionsFile);
        this.accounts = new Accounts(accountsFile, "UserAccounts.out"); // needs to be changed to a new file path
        this.tickets = new Tickets(ticketsFile, "AvailableTickets.out"); // needs to be changes to a new file path
        // set the first sessions logged in user to null
        this.loggedIn = null;
    }

    public void readFiles() throws BackEndException {
        try {
            accounts.readData();
            tickets.readData();
            transactions.readData();
        } catch (FatalErrorException e) {
            throw new BackEndException(e);
        }
    }

	/**
	 * This method is responsible for looping through the transaction in
	 * memory and calling the appropriate method to execute each
	 * transaction. It is also responsible for validating input and
	 * reporting a fatal error on invalid input.
	 * @return 0 on success, 1 on failure, 2 on fatal failure.
	 */
	public int processTransactions() {

        Logger logger = Logger.getLogger(this.getClass().getName());

        try {
            readFiles();
        } catch (BackEndException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

        // set first current logged in user
        loggedIn = setLoggedIn();

        // initialize and empty Record
        Record cur;
        while ((cur = this.transactions.getTransaction()) != null) {
            try {
                switch (cur.getCode()) {
                    case 0:
                        loggedIn = setLoggedIn();
                        break;
                    case 1:
                        doCreate((Regular) cur);
                        break;
                    case 2:
                        if (doDelete((Regular) cur) > 0)
                            // TODO: implement error handling
                            break;
                        break;
                    case 3:
                        if (doSell((SellBuy) cur) > 0)
                            // TODO: implement error handling
                            break;
                        break;
                    case 4:
                        doBuy((SellBuy) cur);
                        break;
                    case 5:
                        doRefund((Return) cur);
                        break;
                    case 6:
                        doAddCredit((Regular) cur);
                        break;
                    default:
                        return 2;
                }
            } catch (BackEndException e) {
                logger.log(Level.WARNING, e.getMessage() + cur.toString());
            }
        }

        return 0;
	}

    public void writeFiles() throws FatalErrorException {
        accounts.writeData();
        tickets.writeData();
    }

    /**
     * This method is responsible for setting the sessions currently logged
     * in user.
     * @return 0 on success, 1 on failure
     */
    public User setLoggedIn() {
        String name = transactions.getLoggedInUser();
        User tmp = null;
        if ((tmp = accounts.getUser(name)) == null) {
            Record record;
            while ((record = transactions.getTransaction()) != null) {
                if (record.getCode() == 0) {
                    return setLoggedIn();
                }
            }
        }
        return tmp;
    }

	/**
	 * This method executes a create new user transaction on the system. It
	 * is called by doTransaction when the transaction code is 01. It
     * proceeds to call the newUser() method in Accounts. If any errors are
     * reported it unit.logs them to xstream.log and outputs the error to
     * terminal.
     * @param regular a transaction record of type Regular
     * @return 0 on success, 1 on failure.
	 */
    private int doCreate(Regular regular) throws BackEndException {
        if (!loggedIn.getType().equals("AA"))
            return 1;

        try {
            return this.accounts.newUser(regular.getName(), regular.getType(), regular.getCredit());
        } catch (Exception e) {
            throw new BackEndException(e);
        }
    }

	/**
	 * This method is responsible for deleting a user account on the
	 * system. It is called by doTransaction when the transaction code is
     * 02. It calls the deleteUser method from the Accounts class. If any
     * errors are reported it unit.logs them to xstream.log and terminal.
     * @param record a transaction record of type Regular
     * @return 0 on success, 1 on failure.
	 */
    private int doDelete(Regular record) throws BackEndException {
        if (!loggedIn.getType().equals("AA"))
            return 1;

        try {
            this.accounts.deleteUser(record.getName());
        } catch (UserExistsException e) {
            throw new BackEndException(e);
        }
        return 0;
    }

	/**
	 * This method is responsible for performing a buy tickets transaction
	 * on the system. It is called by doTransaction when the transaction
     * code is 04. It calls the buyTickets method from the Tickets class.
     * If any errors are reported it unit.logs them to xstream.log and terminal.
     * @param record a transaction record of type SellBuy
     * @return 0 on success, 1 on failure.
	 */
    private int doBuy(SellBuy record) throws BackEndException {
        try {
            // set a tmp event to the event in memory
            Event event = this.tickets.getEvent(record.getTitle(), record.getSeller());

            // set a tmp seller to the user in memory
            User seller = this.accounts.getUser(record.getSeller());

            // set a tmp buyer to the user in memory
            User buyer = this.loggedIn;

            // calculate num of tickets purchased
            int numTicketPurchased = event.getNumTickets() - record.getNumTickets();
            // validate num of tickets purchased is less than 4

            // calculate total price paid
            double total = numTicketPurchased * event.getPrice();

            // preform the purchase of tickets
            event.sellTickets(numTicketPurchased);
            // remove credit from buyer
            buyer.removeCredit(total);
            // add credit to seller
            seller.addCredit(total);

        } catch (Exception e) {
            throw new BackEndException(e);
        }

        return 0;
    }

	/**
	 * This method is responsible for performing a sell tickets transaction
	 * on the system. It is called by doTransaction when the transaction
     * code is 03. It calls the sellTickets method from the Tickets class.
     * If any errors are reported it unit.logs them to xstream.log and outputs to
     * terminal.
     * @param record a transaction record of type SellBuy
     * @return 0 on success, 1 on failure.
	 */
    private int doSell(SellBuy record) throws BackEndException {
        if (loggedIn.getType().equals("SB"))
            return 2;
        try {
            return this.tickets.newEvent(record.getTitle(), record.getSeller(), record.getNumTickets(), record.getPrice());
        } catch (Exception e) {
            throw new BackEndException(e);
        }
    }

	/**
	 * This method is responsible for performing a refund transaction
	 * on the system. It is called by doTransaction when the transaction
	 * code is 05. It calls the removeCredit and addCredit method from the
     * Accounts class. Any errors reported are logged in xstream.log and
     * terminal.
	 * @param record a transaction record of type Refund
	 * @return 0 on success, 1 on failure.
	 */
    private int doRefund(Return record) throws BackEndException {
        // check that the sessions user is an admin
        if (!this.loggedIn.getType().equals("AA"))
            return 1;

        try {
            // create a reference to the buyer in memory
            User buyer = this.accounts.getUser(record.getBuyer());
            // validate buyer exists on the system
            if (buyer == null) {
                return 1;
            }
            // validate that buyer account will not exceed MaxAccountLimit
            if ((buyer.getCredit() + record.getRefund()) > 999999999)
                return 1;

            // create a reference to the seller in memory
            User seller = this.accounts.getUser(record.getSeller());
            // validate seller exists on the system
            if (seller == null) {
                return 1;
            }

            // remove funds from seller with validation
            seller.removeCredit(record.getRefund());

            // add funds to buyer with validation
            buyer.addCredit(record.getRefund());
        } catch (Exception e) {
            throw new BackEndException(e);
        }

        return 0;
	}

	/**
	 * This method is responsible for performing a addCredit transaction
	 * on the system. It is called by doTransaction when the transaction
     * code is 06. It calls the addCredit method from the Accounts class.
     * Any errors reported are logged in xstream.log and outputs to terminal.
     * @param record a transaction record of type Regular
     * @return 0 on success, 1 on failure.
	 */
    private int doAddCredit(Regular record) throws BackEndException {
        // check that the sessions user is an admin
        if (!this.loggedIn.getType().equals("AA"))
            return 1;

        try {
            // create a reference to the user
            User user = this.accounts.getUser(record.getName());
            // add the credit with validation
            user.addCredit(record.getCredit());
        } catch (Exception e) {
            throw new BackEndException(e);
        }
        return 0;
	}

	/**
	 * xstream - backend
     *
     * This is the main program for running the backend of xstream. This program is intended to
     * validate the frontend transactions for a day. The files are read in and stored in backend.data
     * structures at the construction of the object. The processTransactions method processes
     * each transaction and validates it in the system. At the end of the program the new files
     * are generated.
     *
     * To run with specific backend.data files, include the paths in cmd-line. The order
     * is DailyTransactions file, UserAccounts file, AvailableTickets file.
     *
     * If no files are specified then the default backend.data files will be used
     * 
     * All documentation is in doc/Phase4
     *
	 * @param args an array of strings from the command-line which represent filenames
	 */
	public static void main(String[] args) {
        String transactionsFile = "DailyTransactions.txt";
        String userAccountsFile = "UserAccounts.txt";
        String availableTicketsFile = "AvailableTickets.txt";
        BackEnd backEnd = new BackEnd(transactionsFile, userAccountsFile, availableTicketsFile);
        backEnd.processTransactions();
    }
}
