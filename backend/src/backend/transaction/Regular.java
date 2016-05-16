package backend.transaction;

/**
 * This class represents a regular transaction in the
 * systems memory. It is responsible for providing access to
 * regular transaction information.
 */
public class Regular extends Record {

	/**
	 * a string that represents the name of xstreambackend.User
	 */
	private String name;
	/**
	 * a string that represents the type of user
	 */
	private String type;
	/**
	 * a double that represents the amount of credit a xstreambackend.User has.
	 */
	private double credit;

	/**
	 * Constructor method for xstreambackend.Regular class. Calls the constructor of super
	 * to set code.
	 * @param code an integer representing the transaction number.
	 * @param name a string that represents the name of a user
	 * @param type a string that represents the type of user
	 * @param credit
	 */
	public Regular(int code, String name, String type, double credit) {
        super(code);
        this.name = name;
        this.type = type;
        this.credit = credit;
	}

    /**
     *
     * @return a string that represents the name of a user
     */
	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @return a string that represents the type of user
	 */
	public String getType() {
		return this.type;
	}

    /**
     *
     * @return a double that represents the amount of credit
     */
	public double getCredit() {
		return this.credit;
	}

}