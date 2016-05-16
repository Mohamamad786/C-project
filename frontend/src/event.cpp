#include "../include/event.h"

using namespace std;

Event::Event() {

}

Event::Event(string event, string seller, int num_tickets , double price) :
        event_(event),
	    seller_(seller),
        num_tickets_(num_tickets),
        price_(price) {
}

string Event::eventname() {
    return event_;
}

string Event::seller() {
    return seller_;
}

int Event::num_tickets() {
    return num_tickets_;
}

double Event::price() {
    return price_;
}

string Event::event() const {
    // create tmp string containers for attributes
    string tmp_event = event_;
    string tmp_seller = seller_;
    char tmp_num_tickets[3];
    char tmp_price[6];

    // format tmp attribute containers
    tmp_event.resize(EVENT_SIZE, BLANK);
    tmp_seller.resize(NAME_SIZE, BLANK);
    // format number of tickets as an int
    sprintf(tmp_num_tickets, NUMTIC_FORMAT, num_tickets_);
    // if price does not have a decimal
    if (price_ == int(price_))
        // format it as an int
        sprintf(tmp_price, PRICE_WO_DEC_FORMAT, (int) price_);
    else
        // format it with two .2 precision
        sprintf(tmp_price, PRICE_W_DEC_FORMAT, price_);

    // format the entire string - EEEEEEEEEEEEEEEEEEE_SSSSSSSSSSSSS_TTT_PPPPPP
    stringstream tmp;
    tmp << tmp_event << ' ' << tmp_seller << ' ' << tmp_num_tickets << ' ' << tmp_price;

    return tmp.str();
}

void Event::setNumTickets(int num_tickets) {
    num_tickets_ = num_tickets;
}

void Event::setSeller(string seller){
    seller_ = seller;
}

void Event::set_event_name(string event) {
    event_ = event;
}

void Event::setPrice(double price) {
    price_ = price;
}





