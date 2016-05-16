#include "../include/backend.data.h"

Data::Data(string file_name) : file_name_(file_name) {
}

int Data::ReadData() {
    // open with read flag
    if (OpenFile('r')) {
        std::string line;
	// iterate through file and read from it to obtain backend.data
        while (getline(*data_file_, line)) {
            try
            {
		// stop reading backend.data when END is reached
                if (line.substr(0, 3) == "END") {
                    break;
                }
                data_.push_back(line);
            }
	    // throw exception
            catch (exception &e)
            {
                cout << e.what() << endl;
                this->CloseFile();
                return -1;
            }
        }
    } else {
        return -2;
    }
    this->CloseFile();
    return 0;
}

int Data::WriteData() {
    // open with write flag
    if (this->OpenFile('w')) {
        list<string>::iterator data_ptr = data_.begin();
	// iterate through map and write information to file
        for (data_ptr; data_ptr != data_.end(); data_ptr++) {
            *data_file_ << *(data_ptr) << '\n';
        }
        this->CloseFile();

    } else {
        cout << OPEN << ERROR << endl;
        return -1;
    }
    return 0;
}

int Data::OpenFile(char flag) {
    try {
        if (flag == 'w') {	// write flag
            data_file_ = new fstream(file_name_);
        } else if (flag == 'r')	// read flag
            data_file_ = new fstream(file_name_);
        else			// failure
            __throw_bad_exception();
    } catch (exception &e) {	// file io exception
        cout << e.what() << endl;
    }
    // confirm that file is open
    bool open = data_file_->is_open();
    return open;
}

int Data::CloseFile() {
    // close file
    data_file_->close();
    return 0;
}
