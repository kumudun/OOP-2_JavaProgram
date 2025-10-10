package entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @Column(name = "code", length = 3, nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "rate_to_usd", nullable = false)
    private double rateToUSD;

    protected Currency() { }

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
