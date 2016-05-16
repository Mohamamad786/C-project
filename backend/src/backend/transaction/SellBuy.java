package backend.transaction;

/**
 * This class is represents a return transaction. It is responsible for providing access to all of it attributes. It inherits the
 * attribute code and the method getCode() from xstreambackend.Record.
 */
public class SellBuy extends Record {

	/**
	 * a string that represents the name of the xstreambackend.Event
	 */
	private String title;
	/**
	 * a string that represents the name of the xstreambackend.User whom is the
	 * seller
	 */
	private String seller;
	/**
	 * an integer representing the number of tickets for sale
	 */
	private int numTickets;
	/**
	 * a double that represents the price of the ticket
	 */
	private double price;

	/**
	 * Constructor for xstreambackend.SellBuy class. Inherits the constructor from xstreambackend.Record
	 * and sets the attribute code.
     * @param code an integer representing the transaction number.
	 * @param title a string that represents the name of the xstreambackend.Event
	 * @param seller a string that represents the name of the xstreambackend.User
	 * @param numTickets an integer representing the number of tickets for sale
	 * @param price a double that represents the price of a ticket
	 */
	public SellBuy(int code, String title, String seller, int numTickets, double price) {
        super(code);
        this.title = title;
        this.seller = seller;
        this.numTickets = numTickets;
        this.price = price;
	}

	public String getTitle() {
		return this.title;
	}

	public String getSeller() {
		return this.seller;
	}

	public int getNumTickets() {
		return this.numTickets;
	}

	public double getPrice() {
		return this.price;
	}

}