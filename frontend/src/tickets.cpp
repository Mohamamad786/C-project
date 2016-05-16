#include "../include/tickets.h"

using namespace std;

Tickets::Tickets(string path_to_event) :
        Data(path_to_event) {
    if (ReadData() == 0) {

        // create an iterator that points to each line in file
        list<string>::iterator data_ptr = data_.begin();

        // for each line in file until EOF
        for (data_ptr; data_ptr != data_.end(); data_ptr++) {
            if ((*data_ptr) != "") {
                // get attributes from line
                string event = (*data_ptr).substr(0, EVENT_SIZE);
                string seller = (*data_ptr).substr(20, NAME_SIZE);
                int num_tic = stoi((*data_ptr).substr(36, NUMTIC_SIZE));
                double price = stod((*data_ptr).substr(40, PRICE_SIZE));

                // trim whitespace from event and seller name
                event.erase(event.find_last_not_of(' ') + 1);
                seller.erase(seller.find_last_not_of(' ') + 1);

                // create a new user in memory
                new_event(event, seller, num_tic, price);
            }
        }
    }
}

Event *Tickets::find_event(string event) {
    // search for event in tickets
    map<string, Event>::iterator it = tickets_.find(event);

    // if event does not exist
    if (it == tickets_.end())
        return NULL;
    else
        // return address of Event
        return &it->second;
}

int Tickets::new_event(string event, string seller, int num_tic, double price) {
    // create an event with key=event_name and value=Event
    tickets_.insert(pair<string, Event>(event, Event(event, seller, num_tic, price)));
    return 0;
}

int Tickets::write_events() {
    // clear backend.data buffer
    data_.clear();
    // create a pointer to the start of accounts
    map<string, Event>::iterator ptr = tickets_.begin();
    for (ptr; ptr != tickets_.end(); ptr++) {
        // get the formatted string version of the account
        // pointed to by ptr and insert it into backend.data
        data_.push_front(ptr->second.event());
    }
    WriteData();
    return 0;
}

int Tickets::print_tickets() {
    map<string, Event>::iterator ptr = tickets_.begin();
    // print ticket information during iteration through map of tickets
    for (ptr; ptr != tickets_.end(); ptr++) {
        cout << (*ptr).second.event() << endl;
    }
    return 0;
}
