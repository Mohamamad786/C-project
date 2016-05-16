package backend.user;

/**
 * MaxFundsExceededException:
 * <brief description of class>
 */
public class CreditLimitException extends Exception {

    private double credit;
    private double amount;
    private double limit;

    public CreditLimitException(double amount, double credit, double limit) {
        super("");
        this.credit = credit;
        this.amount = amount;
        this.limit = limit;
    }

    @Override
    public String getMessage() {
        return maxOrMin() + super.getMessage();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String maxOrMin() {
        if ((credit + amount) > limit) {
            if (amount == 0)
                return "Maximum Credit Limit Exceeded " + String.format("%.2f > %.2f", credit, limit);
            return "Maximum Credit Limit Exceeded " + String.format("%.2f + %.2f > %.2f", credit, amount, limit);
        } else {
            if (amount == 0)
                return "Minimum Credit Limit Exceeded " + String.format("%.2f < %.2f", credit, limit);
            return "Minimum Credit Limit Exceeded " + String.format("%.2f - %.2f < %.2f", credit, amount, limit);
        }
    }
}