#ifndef XSTREAM_EVENT_H
#define XSTREAM_EVENT_H

#include "xstream.h"
#include <iostream>
#include <sstream>
#include <list>

using namespace std;

/*
Event:
    Represents a single event in the AvailableTickets file.  Obtains
    and updates event information during the buy and sell transactions.
    Contains all information for a single event.
 */
class Event
{
public:

    /*
    Constructor: 
	base constructor for Event
    */
    Event();

    /*
    Constructor: 
	creates new instance of event with information declared
    */
    Event(string event, string seller, int num_tickets , double price);

    /*
    eventname: 
	returns name of event
    */
    string eventname();

    /*
    seller: 
	returns username of seller
    */
    string seller();

    /*
    num_tickets: 
	returns number of tickets
    */
    int num_tickets();

    /*
    price: 
	returns price of event
    */
    double price();

    /*
    event:
	returns formatted string containing event information
	format: EEEEEEEEEEEEEEEEEEE_SSSSSSSSSSSSS_TTT_PPPPPP
	E - event name
	S - username for seller
	T - number of tickets
	P - price
	_ - space
    */
    string event() const;

    /*
    set_event_name: 
	updates event name based on parameter event
    */
    void set_event_name(string event);

    /*
    setSeller: 
	updates seller username based on parameter seller
    */
    void setSeller(string seller);

    /*    
    setNumTickets: 
	updates number of tickets based on parameter num_tickets
    */
    void setNumTickets(int num_tickets);

    /*    
    setPrice: updates price per ticket based on parameter price
     */
    void setPrice(double price);

private:
    
    //event_: string representing the name of the event
    string event_;
    //seller_: string representing the username of the seller
    string seller_;
    //num_tickets_: integer representing the number of tickets available for an event
    int num_tickets_;
    //price_: double representing the price for each ticket
    double price_;
};

#endif // XSTREAM_EVENT_H
