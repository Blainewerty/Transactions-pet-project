package ru.milov.transactions;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.milov.transactions.repository.RepositoryUserTest;
import ru.milov.transactions.service.services.ServiceAppBillTest;
import ru.milov.transactions.service.services.ServiceAppTransactionTest;
import ru.milov.transactions.service.services.ServiceAppUserTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ServiceAppBillTest.class, ServiceAppTransactionTest.class, ServiceAppUserTest.class})
public class TestSuiteServices {
}
