#include "../include/user.h"

using namespace std;

User::User() {

}

User::User(string name, string type, double credit) :
        name_(name),
        type_(type),
        credit_(credit) {
}

string User::name() {
    return name_;
}

string User::type() const {
    return type_;
}

double User::credit() {
    return credit_;
}

string User::user() const {
    // create tmp string containers for attributes
    string tmp_name = name_;
    string tmp_type = type_;
    char tmp_credit[9];

    // format tmp attribute containers
    tmp_name.resize(NAME_SIZE, BLANK);
    tmp_type.resize(TYPE_SIZE, BLANK);
    // if credit does not have a decimal
    if (credit_ == int(credit_))
        // format it as an int
        sprintf(tmp_credit, CREDIT_WO_DEC_FORMAT, (int) credit_);
    else
        // format it with two .2 precession
        sprintf(tmp_credit, CREDIT_W_DEC_FORMAT, credit_);

    // format the entire string - UUUUUUUUUUUUUUU_TT_CCCCCCCCC
    stringstream tmp;
    tmp << tmp_name << ' ' << tmp_type << ' ' << tmp_credit;

    return tmp.str();
}

void User::setType(string type) {
    type_ = type;
}

void User::set_name(string name) {
    name_ = name;
}

void User::setCredit(double credit) {
    credit_ = credit;
}





