package unit;

import backend.event.*;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;

/**
 * Tickets:
 * <brief description of class>
 */
public class TicketsTests {
    private Event valid;
    private Event titleExceedsMax;
    private Event sellerNameExceedsMax;
    private Event numTicketsExeedsMax;
    private Event numTicketsAtTopLimit;
    private Event numTicketsAtBottomLimit;
    private Event priceExceedsMax;

    private String lineA;
    private String lineB;
    private Event event;

    private boolean expt;

    private Tickets tickets;

    @Before
    public void setUp() {
        tickets = new Tickets();
        valid = new Event("ValidTicket", "User1", 5, 50.25);
        titleExceedsMax = new Event("InvalidTicketTitleExceedMax", "Valid", 5, 50.25);
        sellerNameExceedsMax = new Event("InvalidSeller", "SellerNameExceedsMax", 5, 50.25);
        numTicketsExeedsMax = new Event("InvalidNumTickets", "ExceedsMax", 101, 50.25);
        numTicketsAtTopLimit = new Event("InvalidNumTickets", "ExceedsMax", 100, 50.25);
        numTicketsAtBottomLimit = new Event("InvalidNumTickets", "ExceedsMax", 0, 50.25);
        priceExceedsMax = new Event("InvalidPrice", "ExceedsMax", 3, 1000.01);

        lineA = "test1                User1           032 050.02";
        event = new Event("test1", "User1", 32, 50.02);
        lineB = "test1                User1          1032 050.02";
    }

    @After
    public void tearDown() throws Exception {
        tickets = null;
        valid = null;
        titleExceedsMax = null;
        sellerNameExceedsMax = null;
        numTicketsExeedsMax = null;
        numTicketsAtTopLimit = null;
        numTicketsAtBottomLimit = null;
        priceExceedsMax = null;
    }

    public int newEvent(Event event) throws IllegalSellerException, IllegalNumTicketsException, IllegalPriceException, IllegalTitleException {
        String title = event.getTitle();
        String seller = event.getSeller();
        int numTickets = event.getNumTickets();
        double price = event.getPrice();
        return tickets.newEvent(title, seller, numTickets, price);
    }

    @Test
    public void testNewEvent() throws Exception {
        // expected pass test
        assertEquals(0, newEvent(valid));
        assertEquals(0, newEvent(valid));
        assertEquals(0, newEvent(numTicketsAtBottomLimit));
        assertEquals(0, newEvent(numTicketsAtBottomLimit));

        // expected fail tests
        assertEquals(1, newEvent(titleExceedsMax));
        assertEquals(1, newEvent(sellerNameExceedsMax));
        assertEquals(1, newEvent(numTicketsExeedsMax));
    }

    @Test
    public void testGetEvent() throws Exception {
        assertEquals(0, newEvent(valid));
        assertEquals(0, newEvent(valid));
        assertNotNull(tickets.getEvent(valid.getTitle(), valid.getSeller()));
        assertNull(tickets.getEvent(titleExceedsMax.getTitle(), titleExceedsMax.getSeller()));
    }

    @Test
    public void testDeleteSellerEvents() throws Exception {
        assertEquals(0, newEvent(valid));
        assertEquals(0, newEvent(valid));
        assertEquals(0, tickets.deleteSellerEvents(valid.getSeller()));
        TestCase.assertNull(tickets.getEvent(valid.getTitle(), valid.getSeller()));
        assertEquals(1, tickets.deleteSellerEvents(valid.getSeller()));

        // User does not exist
        assertEquals(0, newEvent(valid));
        assertEquals(1, tickets.deleteSellerEvents(titleExceedsMax.getSeller()));
    }

    @Test
    public void testDecode() throws Exception {
        assertNull(tickets.getEvent(event.getTitle(), event.getSeller()));
        assertEquals(0, tickets.decode(lineA));

        Event tmp = tickets.getEvent(event.getTitle(), event.getSeller());
        assertNotNull(tmp);
        assertEquals(event.getTitle(), tmp.getTitle());
        assertEquals(event.getSeller(), tmp.getSeller());
        assertEquals(event.getNumTickets(), tmp.getNumTickets());
        assertEquals(event.getPrice(), tmp.getPrice());

        assertEquals(0, tickets.deleteSellerEvents(event.getSeller()));
        assertNull(tickets.getEvent(event.getTitle(), event.getSeller()));

        assertEquals(1, tickets.decode(lineB));
        assertNull(tickets.getEvent(event.getTitle(), event.getSeller()));
    }

    @Test
    public void testEncode() throws Exception {
        assertNull(tickets.getEvent(event.getTitle(), event.getSeller()));
        assertEquals(0, tickets.decode(lineA));
        assertNotNull(tickets.getEvent(event.getTitle(), event.getSeller()));

        assertEquals(lineA, tickets.encode());
    }
}
