package com.epam.pavliuk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.epam.pavliuk.exception.EntityNotFoundException;
import com.epam.pavliuk.model.Account;
import com.epam.pavliuk.model.Sex;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PracticalTasksTests {

    private PracticalTasks streams;
    private static List<Account> accounts = Arrays.asList(
            new Account(1L, "Justin", "Butler", "justin.butler@gmail.com",
                    LocalDate.parse("2003-04-17"), Sex.MALE, LocalDate.parse("2016-06-13"), BigDecimal.valueOf(172966)),
            new Account(2L, "Olivia", "Cardenas", "cardenas@mail.com",
                    LocalDate.parse("1930-01-19"), Sex.FEMALE, LocalDate.parse("2014-06-21"), BigDecimal.valueOf(38029)),
            new Account(3L, "Nolan", "Donovan", "nolandonovan@gmail.com",
                    LocalDate.parse("1925-04-19"), Sex.MALE, LocalDate.parse("2011-03-10"), BigDecimal.valueOf(13889)),
            new Account(4L, "Lucas", "Lynn", "lucas.lynn@yahoo.com",
                    LocalDate.parse("1987-05-25"), Sex.MALE, LocalDate.parse("2009-03-05"), BigDecimal.valueOf(16980))
    );

    @BeforeEach
    void setUp() {
        streams = new PracticalTasks(accounts);
    }

    @Test
    @Order(1)
    void findRichestPerson() {
        Optional<Account> expectedPerson = Optional.of(accounts.get(0));
        Optional<Account> actualRichestPerson = streams.findRichestPerson();

        assertEquals(expectedPerson, actualRichestPerson);
    }

    @Test
    @Order(2)
    void findAccountsByBirthdayMonth() {
        List<Account> expectedList = getExpectedList();
        List<Account> aprilAccounts = streams.findAccountsByBirthdayMonth(Month.APRIL);

        assertEquals(expectedList, aprilAccounts);
    }

    @Test
    @Order(3)
    void separateMaleAccounts() {
        Map<Boolean, List<Account>> expectedAccountMap = getExpectedMaleMap();
        Map<Boolean, List<Account>> maleToAccountsMap = streams.partitionMaleAccounts();

        assertEquals(expectedAccountMap, maleToAccountsMap);
    }

    private Map<Boolean, List<Account>> getExpectedMaleMap() {
        Map<Boolean, List<Account>> expectedMap = new HashMap<>(2);
        expectedMap.put(Boolean.TRUE, Arrays.asList(accounts.get(0), accounts.get(2), accounts.get(3)));
        expectedMap.put(Boolean.FALSE, Arrays.asList(accounts.get(1)));
        return expectedMap;
    }

    private List<Account> getExpectedList() {
        return Arrays.asList(accounts.get(0), accounts.get(2));
    }

    @Test
    @Order(4)
    void groupAccountsByEmailDomain() {
        Map<String, List<Account>> expectedEmailMap = getExpectedEmailMap();
        Map<String, List<Account>> emailDomainToAccountsMap = streams.groupAccountsByEmailDomain();

        assertEquals(expectedEmailMap, emailDomainToAccountsMap);
    }

    private Map<String, List<Account>> getExpectedEmailMap() {
        Map<String, List<Account>> expectedEmailMap = new HashMap<>();
        expectedEmailMap.put("gmail.com", Arrays.asList(accounts.get(0), accounts.get(2)));
        expectedEmailMap.put("mail.com", Arrays.asList(accounts.get(1)));
        expectedEmailMap.put("yahoo.com", Arrays.asList(accounts.get(3)));

        return expectedEmailMap;
    }

    @Test
    @Order(5)
    void getNumOfLettersInFirstAndLastNames() {
        int numOfLettersInFirstAndLastNames = streams.getNumOfLettersInFirstAndLastNames();

        assertEquals(47, numOfLettersInFirstAndLastNames);
    }

    @Test
    @Order(6)
    void calculateTotalBalance() {
        BigDecimal totalBalance = streams.calculateTotalBalance();

        assertEquals(BigDecimal.valueOf(241864), totalBalance);
    }


    @Test
    @Order(7)
    void sortByFirstAndLastNames() {
        List<Account> sortedList = streams.sortByFirstAndLastNames();

        assertEquals(1L, sortedList.get(0).getId().longValue());
        assertEquals(4L, sortedList.get(1).getId().longValue());
        assertEquals(3L, sortedList.get(2).getId().longValue());
        assertEquals(2L, sortedList.get(3).getId().longValue());

    }

    @Test
    @Order(8)
    void containsAccountWithEmailDomain() {
        assertTrue(streams.containsAccountWithEmailDomain("gmail.com"));
        assertTrue(streams.containsAccountWithEmailDomain("yahoo.com"));
        assertFalse(streams.containsAccountWithEmailDomain("ukr.net"));
    }

    @Test
    @Order(9)
    void getBalanceByEmail() {
        Account account = accounts.get(1);
        BigDecimal balance = streams.getBalanceByEmail(account.getEmail());

        assertEquals(account.getBalance(), balance);
    }

    @Test
    @Order(10)
    void getBalanceByEmailThrowsException() {
        String fakeEmail = "fake@mail.com";
        try {
            streams.getBalanceByEmail(fakeEmail);
            fail("Should throw exception");
        } catch (Exception e) {
            assertTrue(e instanceof EntityNotFoundException);
            assertEquals(String.format("Cannot find Account by email=%s", fakeEmail), e.getMessage());
        }
    }

    @Test
    @Order(11)
    void collectAccountsById() {
        Map<Long, Account> idToAccountMap = streams.collectAccountsById();

        assertEquals(accounts.get(0), idToAccountMap.get(1L));
        assertEquals(accounts.get(1), idToAccountMap.get(2L));
        assertEquals(accounts.get(2), idToAccountMap.get(3L));
        assertEquals(accounts.get(3), idToAccountMap.get(4L));
    }

    @Test
    @Order(12)
    void collectBalancesByEmailForAccountsCreatedOn() {
        Account account = accounts.get(3);

        Map<String, BigDecimal> emailToBalanceMap = streams.collectBalancesByEmailForAccountsCreatedOn(account.getCreationDate().getYear());

        assertEquals(Map.of(account.getEmail(), account.getBalance()), emailToBalanceMap);
    }

    @Test
    @Order(13)
    void groupFirstNamesByLastNames() {
        Map<String, Set<String>> lastToFirstNamesMap = streams.groupFirstNamesByLastNames();

        assertEquals(4, lastToFirstNamesMap.size());
        assertEquals(Set.of("Justin"), lastToFirstNamesMap.get("Butler"));
        assertEquals(Set.of("Olivia"), lastToFirstNamesMap.get("Cardenas"));
        assertEquals(Set.of("Nolan"), lastToFirstNamesMap.get("Donovan"));
        assertEquals(Set.of("Lucas"), lastToFirstNamesMap.get("Lynn"));
    }

    @Test
    @Order(14)
    void groupCommaSeparatedFirstNamesByBirthdayMonth() {
        Map<Month, String> birthdayMonthToFirstNamesMap = streams.groupCommaSeparatedFirstNamesByBirthdayMonth();

        assertEquals(3, birthdayMonthToFirstNamesMap.size());
        assertEquals("Olivia", birthdayMonthToFirstNamesMap.get(Month.JANUARY));
        assertEquals("Justin, Nolan", birthdayMonthToFirstNamesMap.get(Month.APRIL));
        assertEquals("Lucas", birthdayMonthToFirstNamesMap.get(Month.MAY));
    }

    @Test
    @Order(15)
    void groupTotalBalanceByCreationMonth() {
        Map<Month, BigDecimal> totalBalanceByAccountCreationMonth = streams.groupTotalBalanceByCreationMonth();

        assertEquals(2, totalBalanceByAccountCreationMonth.size());
        assertEquals(BigDecimal.valueOf(210995), totalBalanceByAccountCreationMonth.get(Month.JUNE));
        assertEquals(BigDecimal.valueOf(30869), totalBalanceByAccountCreationMonth.get(Month.MARCH));
    }

    private static void processName(Map<Character, Long> resultMap, String name, int nameLengthBound) {
        if (name.length() >= nameLengthBound) {
            var chars = name.toLowerCase().toCharArray();
            for (Character c : chars) {
                if (resultMap.putIfAbsent(c, 1L) != null) {
                    resultMap.compute(c, (k, counter) -> counter + 1L);
                }
            }
        }
    }
}


