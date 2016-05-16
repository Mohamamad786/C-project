package backend.event;

/**
 * InsufficientTicketsException:
 * <brief description of class>
 */
public class InsufficientTicketsException extends Exception {

    private int numTicketsLeft;
    private int numTicketsPurchaced;

    public InsufficientTicketsException(int numTicketsLeft, int numTicketsPurchaced) {
        super("");
        this.numTicketsLeft = numTicketsLeft;
        this.numTicketsPurchaced = numTicketsPurchaced;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + numTicketsLeft + numTicketsPurchaced;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
