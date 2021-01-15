package ru.milov.transactions.service.services;

import static ru.milov.transactions.dao.DaoFactory.getUserDao;

public class ServiceFactory {

    private static DigestService digestService = new DigestService();

    public static DigestService getDigestService() {
        return digestService;
    }

    private static ServiceConverter serviceConverter = new ServiceConverter();

    public static ServiceConverter getServiceConverter() {
        return serviceConverter;
    }

    private static ServiceSecurity serviceSecurity = new ServiceSecurity(
            getUserDao(),
            getDigestService(),
            getServiceConverter()
    );

    public static ServiceSecurity getServiceSecurity() {
        return serviceSecurity;
    }
}
