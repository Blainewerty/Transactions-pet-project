package ru.milov.transactions.service;

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

    public static ServiceConverter getServiceConverter(){
        if(serviceConverter == null){
            serviceConverter = new ServiceConverter();
        }
        return serviceConverter;
    }

    private static ServiceSecurity serviceSecurity;

    public static ServiceSecurity getServiceSecurity(){
        if(serviceSecurity == null){
            serviceSecurity = new ServiceSecurity(
                    getUserDao(),
                    getDigestService(),
                    getServiceConverter()
            );
        }
        return serviceSecurity;
    }
}
