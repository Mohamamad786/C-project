package backend.data;

import java.io.*;

/**
 * This class represents a base class for handling all read and write
 * operation on a file. It provides inheriting classes safe read and
 * write methods that check and report errors. It also provides
 * subclasses with methods that can be overridden to extract backend.data
 * from lines with their individual formatting. If two files are specified
 * in the constructor the first file will be used for read operations and
 * the second for write operations. If one file is specified it will
 * be used for both read and write operations.
 */
public class Data {

	/**
	 * a string representing the path of the file to be read from
	 */
	private String readFilename;
	/**
	 * a string representing the path of the file to be written to
	 */
	private String writeFilename;

	/**
	 * Constructor method for xstreambackend.Data class. Sets readFilename and
	 * writeFilename to the filename
	 * @param fileName a string representing the path of the UserAccounts file.
	 */
	public Data(String fileName) {
		this.readFilename = fileName;
        this.writeFilename = fileName;
	}

    /**
     * Constructor method for xstreambackend.Data class. Sets separate read and write files.
     * @param readFilename a string representing the path of the file to be read from
     * @param writeFilename a string representing the path of the file to be written to
     */
    public Data(String readFilename, String writeFilename) {
        this.readFilename = readFilename;
        this.writeFilename = writeFilename;
    }

	/**
	 * This method is responsible for reading in the file. For each line in
	 * the file the line is extracted and given as a parameter decode. It
	 * loops through the entire file until it reaches a end of file or if it
	 * encounters a read error, in which it reports the error and exits with
	 * error code 1.
	 * @return 0 on success, 1 on failure
	 */
    public int readData() throws FatalErrorException {
        // initialize a buffer for reading
        BufferedReader bufferedReader = null;
        try {
            // open a file for read access
            FileReader fileReader = new FileReader(this.readFilename);
            // wrap it with the buffer
            bufferedReader = new BufferedReader(fileReader);
            // create a tmp string line to hold the backend.data
            String line;
            // loops through the file until it reaches a blank line
            while ((line = bufferedReader.readLine()) != null) {
                // send the line to decode for parsing
                decode(line);
            }
        } catch (Exception e) {
            throw new FatalErrorException(e);
        } finally {
            // if file is open
            if (bufferedReader != null) {
                try {
                    // close the file
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return 0;
	}

	/**
	 * This method is responsible for writing to the file. It calls encode
	 * which provides a line of text to write to the file. It continues to
	 * call encode until EOF is received, in which it marks the file with an
	 * EOF character and closes the file. If any errors occur during a write
	 * the method reports the error, closes the file and exits with status 1.
	 * @return 0 on success, 1 on failure.
	 */
    public int writeData() throws FatalErrorException {
        // initialize a buffer for writing
        BufferedWriter bufferedWriter = null;
        try {
            // open the file with write permission
            FileWriter fileWriter = new FileWriter(this.writeFilename);
            // wrap it with the buffer
            bufferedWriter = new BufferedWriter(fileWriter);
            // create a tmp string to store backend.data
            String line = null;
            // calls encode until encode returns null
            while ((line = encode()) != null) {
                // writes the line to file
                bufferedWriter.write(line);
                // writes a newline char
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            throw new FatalErrorException(e);
        } finally {
            try {
                // if file is open
                if (bufferedWriter != null) {
                    // close the file
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                // TODO: Implement IOException handle
                e.printStackTrace(System.err);
            }
        }

        return 0;
	}

	/**
	 * This method is intended to be overridden in inheriting classes. Each
     * class should provide a method to parse and extract backend.data from the line
     * of text with their individual formatting requirements. The base class
     * simply prints the line of text to console.
     * @param line a string that represents a line from a file.
	 */
    public int decode(String line) throws FatalErrorException {
        System.out.println(line);
        return 0;
	}

	/**
	 * This method is intended to be overridden in inheriting classes. It is
	 * responsible for providing a line of text to the method writeData to
	 * write to file. Each subclass should provide a line of text
	 * formatted to match attribute characteristics. This method is called
	 * repeatedly by the writeData method until an EOF is received. The
     * base class will rewrite the backend.data in the file to the file.
     * IMPORTANT: EOF must be on a line by itself
     * @return a string that represents a line of text to be written to file
     */
    public String encode() throws FatalErrorException {
        // does nothing
        return null;
	}

}