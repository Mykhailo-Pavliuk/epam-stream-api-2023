package com.epam.pavliuk.model;

import java.math.BigDecimal;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
public class CreditAccount extends Account {
    private BigDecimal creditBalance;

    public Optional<BigDecimal> getCreditBalance() {
        return Optional.ofNullable(creditBalance);
    }
}
