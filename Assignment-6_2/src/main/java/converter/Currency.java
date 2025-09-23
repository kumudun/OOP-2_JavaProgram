package converter;


public class Currency {
    private final String code;
    private final String name;
    private final double rateToUSD; // conversion rate to fixed currency USD

    public Currency(String code, String name, double rateToUSD) {
        this.code = code;
        this.name = name;
        this.rateToUSD = rateToUSD;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getRateToUSD() {
        return rateToUSD;
    }

    @Override
    public String toString() {
        return code + " — " + name;
    }
}


