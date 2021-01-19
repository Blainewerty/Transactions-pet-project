package ru.milov.transactions.service.services;

import ru.milov.transactions.service.services.serviceapp.ServiceAppBill;
import ru.milov.transactions.service.services.serviceapp.ServiceAppTransaction;
import ru.milov.transactions.service.services.serviceapp.ServiceAppUser;
import static ru.milov.transactions.dao.DaoFactory.getUserDao;

public class ServiceFactory {

    private static DigestService digestService;

    public static DigestService getDigestService() {
        if (digestService == null) {
            digestService = new DigestService();
        }
        return digestService;
    }

    private static ServiceConverter serviceConverter;

    public static ServiceConverter getServiceConverter() {
        if (serviceConverter == null){
            serviceConverter = new ServiceConverter();
        }
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

    private static ServiceAppBill serviceAppBill;

    public static ServiceAppBill getServiceAppBill() {
        if (serviceAppBill == null){
            serviceAppBill = new ServiceAppBill();
        }
        return serviceAppBill;
    }

    private static ServiceAppUser serviceAppUser;

    public static ServiceAppUser getServiceAppUser() {
        if(serviceAppUser == null){
            serviceAppUser = new ServiceAppUser();
        }
        return serviceAppUser;
    }

    private static ServiceAppTransaction serviceAppTransaction;

    public static ServiceAppTransaction getServiceAppTransaction() {
        if (serviceAppTransaction == null){
            serviceAppTransaction = new ServiceAppTransaction();
        }
        return serviceAppTransaction;
    }
}
