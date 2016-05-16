package backend.event;

/**
 * TicketSaleLimitException:
 * <brief description of class>
 */
public class TicketSaleLimitException extends Exception {

    private int numTickets;
    private int limit;

    public TicketSaleLimitException(int numTickets, int limit) {
        super("");
        this.numTickets = numTickets;
        this.limit = limit;
    }

    @Override
    public String getMessage() {
        return maxOrMin() + super.getMessage();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String maxOrMin() {
        if (numTickets > limit)
            return "Maximum Ticket Sale Limit Exceeded " + String.format("%d > %d", numTickets, limit);
        else
            return "Minimum Ticket Sale Limit Exceeded " + String.format("%d < %d", numTickets, limit);
    }
}
