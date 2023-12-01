package com.epam.pavliuk;

import static java.util.stream.Collectors.toList;

import com.epam.pavliuk.data.Accounts;
import com.epam.pavliuk.model.Account;
import java.util.ArrayList;
import java.util.List;

public class ImperativeVsDeclarative {

  public static void main(String[] args) {
    List<Account> accounts = Accounts.generateAccountList(100);

    findAllYahooAccountsImperative(accounts);
    findAllYahooAccountsDeclarative(accounts);
  }

  private static List<Account> findAllYahooAccountsImperative(List<Account> accounts) {
    List<Account> yahooAccounts = new ArrayList<>();
    for (Account account : accounts) {
      if (account.getEmail().endsWith("@yahoo.com")) {
        yahooAccounts.add(account);
      }
    }
    return yahooAccounts;
  }

  private static List<Account> findAllYahooAccountsDeclarative(List<Account> accounts) {
    return accounts.stream()
        .filter(a -> a.getEmail().endsWith("@yahoo.com"))
        .collect(toList());
  }

}
