#ifndef XSTREAM_USER_ACCOUNTS_H
#define XSTREAM_USER_ACCOUNTS_H

#include "backend.data.h"
#include "user.h"
#include <iostream>
#include <string>
#include <map>

// path to the UserAccount.txt file
#define USER_ACCOUNTS "backend.data/UserAccounts.txt"

/*
Accounts:
    Creates and handles all current and new accounts.
    Store all account information into memory during a session and,
    after the create, delete, refund, addcredit, buy and logout transactions, 
    writes memory to UserAccounts file.
 */
class Accounts : public Data
{
public:
    /*
    Constructor:
        sets file name to UserAccount file.
     */
    Accounts(string path_to_file=USER_ACCOUNTS);

    /*
    find:
	Searches through map to find username (which is the key)
        Returns address of account in success, NULL on failure.
     */
    User *find(string name);

    /*
    new_user:
	Creates user account and inserts it into memory.
        Returns 0 in success, -1 on failure.
     */
    int new_user(string name, string type, double credit);

    /*
    del_user:
	Deletes user account from memory.
        Returns 0 in success, -1 on failure.
     */
    int del_user(string name);

    /*
    write_accounts:
        converts account information in memory to a map<string, Event> then
        writes the map to the AvailableTickets file.
        Returns 0 in success, -1 on failure.
     */
    int write_accounts();


private:
    /*
    accounts_:
        a map (with the name as a key and the account itself as a value)
	 containing all account information.
     */
    map<string, User> accounts_;
};

#endif // XSTREAM_USER_ACCOUNTS_H


