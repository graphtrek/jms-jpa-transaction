package co.grtk.entity;

import com.neovisionaries.i18n.CurrencyCode;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Setter
@Builder
@ToString
@Embeddable
@NoArgsConstructor(force = true, access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class Amount implements Serializable {

    @Column(name = "AMOUNT_VALUE", nullable = false)
    private BigInteger value;

    @Column(name = "AMOUNT_PRECISION", nullable = false)
    private Integer precision;

    @Enumerated(EnumType.STRING)
    @Column(name = "AMOUNT_CURRENCY", nullable = false)
    private CurrencyCode currency;

    public BigDecimal getComputedAmount() {
        return new BigDecimal(value).movePointLeft(precision);
    }

    @Transient
    public BigDecimal getRealValue() {
        if (precision == null) {
            precision = 0;
        }
        return getComputedAmount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Amount) {
            Amount amount = (Amount) o;
            return Objects.equals(getValue(), amount.getValue()) &&
                    Objects.equals(getPrecision(), amount.getPrecision()) &&
                    Objects.equals(getCurrency(), amount.getCurrency());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getPrecision(), getCurrency());
    }
}
