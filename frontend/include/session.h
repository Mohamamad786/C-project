#ifndef XSTREAM_SESSION_H
#define XSTREAM_SESSION_H

#include "user.h"
#include "accounts.h"
#include "tickets.h"
#include "transaction.h"

/*
Session:
    Provides users with access to session controls. Handles
    the creation and control of memory elements. Provides
    users with access to backend.data elements.
 */
class Session
{
public:
    /*
    Constructor:
        sets logged_in_ to NULL and creates the new
        Transactions memory
     */
    Session(bool silent=false, string accounts_path="", string tickets_path="");

    /*
    logged_in:
        RETURN false if logged_in_ points to NULL
     */
    bool logged_in() const;

    /*
    command:
        starts a session
     */
    int command(string cmd);



private:
    // silent_: determines whether to include prompts in the command line
    bool silent_;           // argv[1]
    // accounts_file_ : Is Null if defined in args, otherwise is:
    string accounts_path_;  // argv[2]
    // tickets_file_ : Is Null if defined in args, otherwise is:
    string tickets_path_;   // argv[3]
    // logged_in_: account currently logged in
    User *logged_in_;
    // accounts_: list of accounts from UserAccounts
    Accounts *accounts_;
    // transactions_: list of transactions made in a session
    Transactions *transactions_;
    // tickets_: list of events from AvailableTickets
    Tickets * tickets_;

    /*
    Login:
        enables a user to login to a session. Prompts
        user for username.
        RETURN 0 on success, -1 on invalid input,
               -2 on invalid username
     */
    int Login();

    /*
    Logout:
        logs the current user out of the session and writes
        all transactions, account changes and ticket changes to
        their respective files
        RETURN 0 on success or -int for error
     */
    int Logout();

    /*
    Create:
        enables admin users to create a new user. The method
        creates a new account in memory and then writes the new
        user to UserAccounts file
        RETURN 0 on success or -int for error
     */
    int Create();

    /*
    Delete:
        enables admin users to delete a user account. The method
        deletes the account in memory and then writes the deleted
        account to the UserAccounts file
        RETURN 0 on success or -int for error
     */
    int Delete();

    /*
    Sell:
        enables users with sell privileges and are logged to sell
        tickets to an event. the method creates post tickets to an event for sale.
        RETURN 0 on success or -int for error
     */
    int Sell();

    /*
    Buy:
        enables users with buy privileges to purchase tickets to an event.  The method
	    subtracts the number of tickets purchased from the ones available for the event.
	    It also subtracts the total cost from the current user's credit.  The updated
	    information for the event as well as the current user is written to AvailableTickets
	    and UserAccounts respectively.
	    RETURN 0 on success or -int for error
     */
    int Buy();

    /*
    Refund:
	    enables users to exchange credit between accounts.  The method subtracts
	    the amount from one user's credit and adds that amount to the other user's
	    credit, and then writes the updated information to the UserAccounts file
	    RETURN 0 on success or -int for error
    */
    int Refund();

    /*
    AddCredit:
	    enables users to add credit into their own account or the account of their choice.
	    The method adds the credit (not exceeding $1000.000) to either their own account or
	    an account of their choice (admin only).  Once the credit is added, the updated
	    information is added to the UserAccounts file.
	    RETURN 0 on success or -int for error
    */
    int AddCredit();
};

#endif // XSTREAM_SESSION_H
