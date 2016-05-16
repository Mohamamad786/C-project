#ifndef XSTREAM_COMMIT_H
#define XSTREAM_COMMIT_H

#include "xstream.h"
#include <sstream>
#include <string.h>

using namespace std;

/*
Commit:
    Responsible for writing transaction information to DailyTransaction when a transaction
    except for login and end is complete.  This class just handles the transaction code while
    Refund, BuySell and Regular take care of the transaction information.
*/
class Commit
{
public:
    /*
    Constructor:
	formats transaction code as a string
    */
    Commit(int code);

    /*
    commit:
	virtual string which returns the line containing the transaction information
    */
    virtual string commit() {
        return commit_;
    }

    // commit_: string representing the transaction (one line in DailyTransactions)
    string commit_;
    // code_: char array representing transaction code as a string
    char code_[CODE_SIZE];

};


/*
Refund:
    Responsible for writing the transaction information for refund to DailyTransactions after
    the transaction is successful.
*/
class Refund : public Commit
{
public:
    /*
    Constructor:
	formats attributes for Refund transaction before writing it to DailyTransactions
	format: XX_UUUUUUUUUUUUUUU_SSSSSSSSSSSSSSS_CCCCCCCCC
	X - code (05 - refund)
	U - buyer's username
	S - seller's username
	C - credit to be transferred
	_ - space
    */
    Refund(int code, string buyer_name, string seller_name, double credit);

    // commit: overrides commit() froom Commit
    string commit();

private:
    // buyer_name_: string representing formatted username for buyer
    string buyer_name_;
    // seller_name_: string representing formatted username for seller
    string seller_name_;
    // credit_: char array representing credit formatted as a string
    char credit_[CREDIT_SIZE];
};

/*
BuySell:
    Responsible for writing the transaction information for buy and sell to DailyTransactions after
    the transaction is successful.
*/
class BuySell : public Commit
{
public:

    /*
    Constructor:
	formats attributes for Buy and Sell transactions before writing it to DailyTransactions
	format: XX_EEEEEEEEEEEEEEEEEEE_SSSSSSSSSSSSS_TTT_PPPPPP
	X - code (03 - sell, 04 - buy)
	E - event name
	S - seller's username
	T - number of tickets
	P - price per ticket
	_ - space
    */
    BuySell(int code, string event_name, string seller_name, int num_tickets, double price);

    // commit: overrides commit() froom Commit
    string commit();

private:
    // event_name_: string representing formatted event name
    string event_name_;
    // seller_name_: string representing formatted username for seller
    string seller_name_;
    // num_tickets_: char array representing number of tickets formatted as a string
    char num_tickets_[NUMTIC_SIZE];
    // price_: char array representing price formatted as a string
    char price_[PRICE_SIZE];
};


/*
Regular:
    Responsible for writing the transaction information for logout, create, delete and addcredit
    to DailyTransactions after the transaction is successful.
*/
class Regular : public Commit
{
public:
    /*
    Constructor:
	formats attributes for Regular transactions before writing it to DailyTransactions
	format: XX_UUUUUUUUUUUUUUU_TT_CCCCCCCCC
	X - code (00 - logout, 01 - create, 02 - delete, 06 - add credit)
	U - username
	T - account type
	C - credit
	_ - space
    */
    Regular(int code, string name, string type, double credit);

    // commit: overrides commit() froom Commit
    string commit();

private:
    // name_: string representing username
    string name_;
    // type_: string representing account type
    string type_;
    // credit_: char array representing credit formatted as a string
    char credit_[CREDIT_SIZE];
};

#endif // XSTREAM_COMMIT_H

