#ifndef XSTREAM_INCLUDE_XSTREAM_H
#define XSTREAM_INCLUDE_XSTREAM_H

// Command Constants
#define LOGIN        11
#define LOGOUT       00
#define CREATE       01
#define DELETE       02
#define SELL         03
#define BUY          04
#define REFUND       05
#define ADDCREDIT    06
#define END          22

// Attribute Constants
#define CODE_SIZE   2
#define TYPE_SIZE   2
#define NAME_SIZE   15
#define CREDIT_SIZE 9
#define EVENT_SIZE  19
#define NUMTIC_SIZE 3
#define PRICE_SIZE  6

// Formatting Constants
#define BLANK                   ' '
#define CODE_FORMAT             "%02i"
#define CREDIT_W_DEC_FORMAT     "%09.2f"
#define CREDIT_WO_DEC_FORMAT    "%09i"
#define NUMTIC_FORMAT           "%03i"
#define PRICE_W_DEC_FORMAT      "%06.2f"
#define PRICE_WO_DEC_FORMAT     "%06i"


// Generic Error Messages
#define INPUTTOOLARGE       "Exceed max size of field."
#define INVALIDTYPE         "type error"
#define NOTLOGGED           "command only available to logged in user."
#define INVALID             "Invalid input: "
#define MEMORYERROR         "Memory error: "

// Create Error Msgs
#define NAMEEXISTS          "That name conflicts with a name on the system"

// Delete Error Msgs
#define NAMEDOESNOTEXIST    "User does not exist"
#define SELFDELETE	    "User cannot delete itself"
#define USERNOTDELETED      "User not deleted"

// Buy Error Msgs
#define EVENTDOESNOTEXIST   "Event does not exist."
#define INSUFFICIENTFUNDS   "The price exceeds the credit for this account."
#define NOTSELLER	    "This user is not the seller for the event."

//Sell Error Msgs
#define EVENTEXISTS         "That event conflicts with an event on the system."
#define PRICETOOHIGH	    "Price per ticket is too high (MAX=$1000.00)."
#define TOOMUCHTICKETS      "Number of tickets exceeds maximum limit (MAX=100)."

#define TOOMUCH             "the amount exceeds the transaction limit (MAX=$1000.00)."
#define BADCREDIT           "you do not have enough credit to complete that command."
#define ALREADYLOGGED       "you must log out to preform that task."
#define BADACCESS           "incorrect permissions."

#endif
