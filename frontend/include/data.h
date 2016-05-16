#ifndef XSTREAM_DATA_READER_H
#define XSTREAM_DATA_READER_H

#include <iostream>
#include <fstream>
#include <string>
#include <list>

// File IO Constants
#define OPEN        "OpenFile(char flag): "
#define CLOSE       "ClosrFile(): "
#define READ        "ReadData(): "
#define WRITE       "WriteData(): "
#define ERROR       "error"

using namespace std;

/*
Data:
    Handles file I/O actions to read/write backend.data to/from UserAccounts, AvailableTickets,
    and DailyTransactions in order to obtain and update information during transactions.
 */
class Data
{
public:
    /*
    Constructor:
        set file_name_
     */
    Data(string file_name);

    /*
    ReadData: 
	Handles reading of backend.data from file to memory
	Return 0 on success, -1 on failure
    */
    int ReadData();

    /*
    WriteData:
	Handles writing of backend.data from memory to file
	Return 0 on success, -1 on failure
    */
    int WriteData();

    /*
    data_:
	list containing lines of backend.data from a text file
    */
    std::list<std::string> data_;


private:
    // file_name_: the relative path to the file.
    std::string file_name_;
    // *data_file_: the stream to read and write to the backend.data file.
    std::fstream *data_file_;

    /*
    OpenFile:
        private method that handles the opening of files. It is
        always called on read or write.
        RETURN 0 on success, -1 on failure.
     */
    int OpenFile(char flag);

    /*
    CloseFile:
        private method that handles the closing of files. Is is
        always called on read or write.
        RETURN 0 on success, -1 on failure.
     */
    int CloseFile();
};

#endif // XSTREAM_DATA_READER_H
