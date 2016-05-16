package backend.transaction;

/**
 * This class represents a transaction. It is responsible
 * for storing and retrieving transaction backend.data.
 */
public class Record {

	/**
	 * an integer that represents a transaction code
	 */
	private int code;

    /**
     * Constructor for xstreambackend.Record class
     * @param code an integer that represents the transaction code.
     */
    public Record(int code) {
        this.code = code;
    }

    /**
     *
     * @return an integer that represents the transaction code
     */
	public int getCode() {
		return this.code;
	}


}