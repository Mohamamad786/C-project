#include "../include/transaction.h"

Transactions::Transactions(string file) :
		Data(file) {
}

int Transactions::refund(int code, string buyer, string seller, double credit) {
	// add returning object from Refund to commits_
	commits_.push_back(Refund(code, buyer, seller, credit));
	return 0;
}

int Transactions::buy_sell(int code, string event_name, string seller_name, int num_tickets, double price) {
	// add returning object from BuySell to commits_
	commits_.push_back(BuySell(code, event_name, seller_name, num_tickets, price));
	return 0;
}

int Transactions::regular(int code, string name, string type, double credit) {
	// add returning object from Regular to commits_
	Regular reg(code, name, type, credit);
	commits_.push_back(reg);
	return 0;
}

int Transactions::write_transactions() {
	// create a pointer to the start of transactions
	list<Commit>::iterator ptr = commits_.begin();
	for (ptr; ptr != commits_.end(); ptr++) {
		// get the formatted string version of
		// the transaction and insert into backend.data
		data_.push_back(ptr->commit());
	}
	// write the backend.data to file.
	if (WriteData() > 0) return -1; // return -2 on bad write
	return 0;
}


