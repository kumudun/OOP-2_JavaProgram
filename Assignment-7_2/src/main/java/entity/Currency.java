package entity;

import java.util.Objects;

public class Currency {
    private String code;       // e.g. "EUR"
    private String name;       // e.g. "Euro"
    private double rateToUSD;  // column: rate_to_usd

    public Currency(String code, String name, double rateToUSD) {
        this.code = code;
        this.name = name;
        this.rateToUSD = rateToUSD;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getRateToUSD() { return rateToUSD; }

    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setRateToUSD(double rateToUSD) { this.rateToUSD = rateToUSD; }

    @Override public String toString() { return code + " - " + name; }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;
        Currency c = (Currency) o;
        return Objects.equals(code, c.code);
    }
    @Override public int hashCode() { return Objects.hash(code); }
}
