#ifndef XSTREAM_EVENTS_H
#define XSTREAM_EVENTS_H

#include "backend.data.h"
#include "event.h"
#include <iostream>
#include <string>
#include <map>

// relative path that contains ticket backend.data
#define EVENTS "backend.data/AvailableTickets.txt"

/*
Tickets:
    Creates and handles all events with available tickets.
    Store all event information into memory during a session and,
    after the buy, sell and logout transactions, writes memory to 
    AvailableTickets file.
 */
class Tickets : public Data
{
public:
    /*
    Constructor:
        sets file name to AvailableTickets file.
     */
    Tickets(string path_to_file=EVENTS);

    /*
    find_event:
	Searches through map to find name of event (which is the key)
        Returns address of event in success, NULL on failure.
     */
    Event *find_event(string event);

    /*
    new_event:
	Creates event and inserts it into memory.
        Returns 0 in success, -1 on failure.
     */
    int new_event(string event, string seller, int num_tic, double price);

    /*
    write_events:
        converts event information in memory to a map<string, Event> then
        writes the map to the AvailableTickets file.
        Returns 0 in success, -1 on failure.
     */
    int write_events();

    /*
    print_tickets:
	Prints event information after logging in.
        Returns 0 in success, -1 on failure.
     */
    int print_tickets();


private:
    /*
    tickets_:
        a map (with the name as a key and the event itself as a value)
	 containing all event information.
     */
    map<string, Event> tickets_;

};


#endif // XSTREAM_TICKETS_H
