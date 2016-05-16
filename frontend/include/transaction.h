#ifndef XSTREAM_DAILY_TRANSACTIONS_H
#define XSTREAM_DAILY_TRANSACTIONS_H

#include "backend.data.h"
#include "../include/commit.h"
#include <iostream>

using namespace std;

// relative path for file that contains Transaction backend.data.
#define TRANSACTIONS "backend.data/DailyTransactions.txt"

/*
Transactions:
    Creates and handles transaction records for the session.
    Store all transactions into memory during a session and
    at end of session writes memory to DailyTransaction file.
 */
class Transactions : public Data
{
public:
    /*
    Constructor:
        sets file name to DailyTransactions file.
     */
    Transactions(string file=TRANSACTIONS);

    /*
    refund:
        creates a Refund object and inserts it into memory.
        Return 0 on success, -1 on failure.
     */
    int refund(int code, string username, string type_, double credit);

    /*
    buy_sell:
        creates a BuySell object and inserts it into memory.
        Return 0 on success, -1 on failure.
     */
    int buy_sell(int code, string buyer_name, string seller_name, int num_tickets, double price);

    /*
    regular:
        creates a Regular object and inserts it into memory.
        Return 0 on success, -1 on failure.
     */
    int regular(int code, string name, string type, double credit);

    /*
    write_transactions:
        converts transactions in memory to a list<string> then
        writes the list to the DailyTransactions file.
        Returns 0 in success, -1 on failure.
     */
    int write_transactions();

private:
    /*
    commits_:
        a list containing all the transactions of the session.
     */
    list<Commit> commits_;
};

#endif
