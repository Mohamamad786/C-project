package backend.event;

/**
 * This class represents a event that has tickets for sale in the
 * system.
 */
public class Event {

	/**
	 * a string that represents the title of the event
	 */
	private String title;
	/**
	 * a string that represents the name of seller
	 */
	private String seller;
	/**
	 * an integer representing the number of tickets for sale
	 */
	private int numTickets;
	/**
	 * a double that represents the price of one ticket
	 */
	private double price;

	/**
	 * Constructor method for the xstreambackend.Event class
	 * @param title a string that represents the title of the event
	 * @param seller a string that represents the name of xstreambackend.User who is the seller
	 * @param numTickets an integer representing the number of tickets for sale
	 * @param price a double that represents the price of one ticket
	 */
	public Event(String title, String seller, int numTickets, double price) {
        this.title = title;
        this.seller = seller;
        this.numTickets = numTickets;
        this.price = price;
	}

	/**
	 * 
	 * @return a string that represents the title of the event
	 */
	public String getTitle() {
		return this.title;
	}

    /**
     *
     * @return a string that represents the name of the seller
     */
	public String getSeller() {
		return this.seller;
	}

    /**
     *
     * @return an integer that represents the number of tickets for sale
     */
	public int getNumTickets() {
		return this.numTickets;
	}

    /**
     *
     * @return a double that represents the price of one ticket
     */
	public double getPrice() {
		return this.price;
	}

	/**
	 * This method is responsible for removing tickets from the number of
	 * tickets for sale. If the number of tickets to be removed exceeds the
	 * number of tickets for sale, an error is reported and method exits
	 * with status 2.
	 * @param numTickets an integer representing the number of tickets purchased.
	 * @return 0 on success, 1 on failure, 2 on AmountExceedsSellMax, 3 on AmountExceedsNumTickets
	 */
    public int sellTickets(int numTickets) throws TicketSaleLimitException, InsufficientTicketsException {
        int MAXTICKETSALELIMIT = 4;
        if (numTickets > 4)
            throw new TicketSaleLimitException(numTickets, 4);
        if ((this.numTickets - numTickets) < 0) {
            throw new InsufficientTicketsException(this.numTickets, numTickets);
        }
        this.numTickets -= numTickets;
        return numTickets;
	}
}