#include "../include/accounts.h"

using namespace std;

Accounts::Accounts(string path_to_file) :
        Data(path_to_file) {
    if (ReadData() == 0) {

        // create an iterator that points to each line in file
        list<string>::iterator data_ptr = data_.begin();

        // for each line in file until EOF
        for (data_ptr; data_ptr != data_.end(); data_ptr++) {
            // get attributes from line
            string name = (*data_ptr).substr(0, NAME_SIZE);
            string type = (*data_ptr).substr(16, TYPE_SIZE);
            double credit = stod((*data_ptr).substr(19, CREDIT_SIZE));

            // trim whitespace from name
            name.erase(name.find_last_not_of(' ') + 1);

            // create a new user in memory
            new_user(name, type, credit);
        }
    }
}

User *Accounts::find(string name) {
    // search for name in accounts
    map<string, User>::iterator it = accounts_.find(name);

    // if name does not exist
    if (it == accounts_.end())
        return NULL;
    else
        // return address of User
        return &it->second;
}

int Accounts::new_user(string name, string type, double credit) {
    // create a account with key=name and value=User
    accounts_.insert(pair<string, User>(name, User(name, type, credit)));
    return 0;
}

int Accounts::del_user(string name) {
    // delete account with key=name
    accounts_.erase(name);
    return 0;
}

int Accounts::write_accounts() {
    // clear backend.data buffer
    data_.clear();
    // create a pointer to the start of accounts
    map<string, User>::iterator ptr = accounts_.begin();
    // iterate through accounts
    for (ptr; ptr != accounts_.end(); ptr++) {
        // get the formatted string version of the account
        // pointed to by ptr and insert it into backend.data
        data_.push_front(ptr->second.user());
    }
    WriteData();
    return 0;
}
