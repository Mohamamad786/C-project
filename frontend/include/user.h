#ifndef XSTREAM_INCLUDE_USER_H
#define XSTREAM_INCLUDE_USER_H

#include "xstream.h"
#include <iostream>
#include <sstream>
#include <list>

using namespace std;


// User Type Constants
#define AA "admin"
#define FS "full-standard"
#define BS "buy-standard"
#define SS "sell-standard"

/*
User:
    Represents a single user account in the userAccounts file.  Obtains
    and updates account information during all transactions.
    Contains all information for a single account.
 */
class User
{
public:

    /*
    Constructor: 
	base constructor for User
    */
    User();

    /*
    Constructor: 
	creates new instance of User with account information declared
    */
    User(string name, string type, double credit);

    /*
    name: 
	return account username
    */
    string name();

    /*
    type: 
	return account type
    */
    string type() const;

    /*
    credit: 
	return credit associated with account
    */
    double credit();

    /*
    user: 
	returns formatted string containing account information
	format: UUUUUUUUUUUUUUU_TT_CCCCCCCCC
	U - username
	T - account type (AA [admin], FS [full-standard], BS [buy standard], SS [sell standard])
	C - credit
	_ - space
    */
    string user() const;

    /*
    set_name: 
	update username based on parameter name
    */
    void set_name(string name);

    /*
    setType:
	update account type based on parameter type
    */
    void setType(string type);

    /*
    setCredit:
	update credit on account based on parameter credit
    */
    void setCredit(double credit);

private:
    // name_: string representing username
    string name_;
    // type_: string representing type of account
    string type_;
    // credit_: double representing amount of credit associated with account
    double credit_;
};

#endif // XSTREAM_INCLUDE_USER_H

