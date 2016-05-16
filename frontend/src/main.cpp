#include "../include/session.h"

using namespace std;

/*

 */
int main(int argc, char *argv[]) {
    // determine whether dialog should be displayed during execution
    bool silent;
    string accounts_path = "";
    string tickets_path = "";

    if (argc == 1) {
        silent = false;
    } else if (argc == 2){
        silent = strcmp(argv[1], "-s") == 0;
    } else {
        silent = (strcmp(argv[1], "-s") == 0);

        int i = 1;
        if (silent)
            i = 2;
        accounts_path = argv[i];
        tickets_path = argv[i + 1];
    }

    // create new Session
    Session session(silent, accounts_path, tickets_path);

    int logged_in = 0;
    int shutdown = 0;
    string cmd;

    // while the program is still running
    do {
        if (!silent)
            cout << ">>  xstream  <<" << endl;

        do {
	    // enter command for transaction
            if (!silent)
                std::cout << "command >>" << endl;
            std::cin >> cmd;

            logged_in = session.command(cmd);

            // shuts the system down when
            if (logged_in == 2) {
                logged_in = 1;
                shutdown = 1;
            }
        } while (session.logged_in());

    } while (!shutdown);

    return 0;
}
