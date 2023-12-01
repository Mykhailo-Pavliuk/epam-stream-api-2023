package com.epam.pavliuk.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "email")
public class Account {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private LocalDate birthday;
  private Sex sex;
  private LocalDate creationDate;
  private BigDecimal balance = BigDecimal.ZERO;
}
