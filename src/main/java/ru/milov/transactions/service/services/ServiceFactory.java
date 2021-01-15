package ru.milov.transactions.service.services;

import ru.milov.transactions.service.services.serviceapp.ServiceAppBill;
import ru.milov.transactions.service.services.serviceapp.ServiceAppTransaction;
import ru.milov.transactions.service.services.serviceapp.ServiceAppUser;
import ru.milov.transactions.service.services.servicesql.ServiceSQLBill;
import ru.milov.transactions.service.services.servicesql.ServiceSQLTransaction;
import ru.milov.transactions.service.services.servicesql.ServiceSQLUser;
import static ru.milov.transactions.dao.DaoFactory.getUserDao;

public class ServiceFactory {

    private static final DigestService digestService = new DigestService();

    public static DigestService getDigestService() {
        return digestService;
    }

    private static final ServiceConverter serviceConverter = new ServiceConverter();

    public static ServiceConverter getServiceConverter() {
        return serviceConverter;
    }

    private static final ServiceSecurity serviceSecurity = new ServiceSecurity(
            getUserDao(),
            getDigestService(),
            getServiceConverter()
    );

    public static ServiceSecurity getServiceSecurity() {
        return serviceSecurity;
    }

    private static final ServiceAppBill serviceAppBill = new ServiceAppBill();

    public static ServiceAppBill getServiceAppBill(){
        return serviceAppBill;
    }

    private static final ServiceAppUser serviceAppUser = new ServiceAppUser();

    public static ServiceAppUser getServiceAppUser(){
        return serviceAppUser;
    }

    private static final ServiceAppTransaction serviceAppTransaction = new ServiceAppTransaction();

    public static ServiceAppTransaction getServiceAppTransaction(){
        return serviceAppTransaction;
    }

    private static final ServiceSQLBill SERVICE_SQL_BILL = new ServiceSQLBill();

    public static ServiceSQLBill getServiceSqlBill(){
        return SERVICE_SQL_BILL;
    }

    private static final ServiceSQLTransaction SERVICE_SQL_TRANSACTION = new ServiceSQLTransaction();

    public static ServiceSQLTransaction getServiceSqlTransaction(){
        return SERVICE_SQL_TRANSACTION;
    }

    private static final ServiceSQLUser SERVICE_SQL_USER = new ServiceSQLUser();

    public static ServiceSQLUser getServiceSqlUser(){
        return SERVICE_SQL_USER;
    }

}
