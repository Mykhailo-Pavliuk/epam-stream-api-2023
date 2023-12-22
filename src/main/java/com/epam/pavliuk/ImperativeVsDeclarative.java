package com.epam.pavliuk;

import static java.util.stream.Collectors.toList;

import com.epam.pavliuk.data.Accounts;
import com.epam.pavliuk.model.Account;
import java.util.ArrayList;
import java.util.List;

public class ImperativeVsDeclarative {

  public static void main(String[] args) {
    List<Account> accounts = Accounts.generateAccountList(100);

    findAllGoogleAccountsImperative(accounts);
    findAllGoogleAccountsDeclarative(accounts);
  }

  private static List<Account> findAllGoogleAccountsImperative(List<Account> accounts) {
    List<Account> googleAccounts = new ArrayList<>();
    for (Account account : accounts) {
      if (account.getEmail().endsWith("@gmail.com")) {
        googleAccounts.add(account);
      }
    }
    return googleAccounts;
  }

  private static List<Account> findAllGoogleAccountsDeclarative(List<Account> accounts) {
    return accounts.stream()
        .filter(a -> a.getEmail().endsWith("@gmail.com"))
        .collect(toList());
  }

}
