package ru.milov.transactions.dao;

import ru.milov.transactions.service.DigestService;
import ru.milov.transactions.service.SecurityService;
import ru.milov.transactions.service.ServiceConverter;
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

    private static SecurityService securityService;

    public static SecurityService getSecurityService(){
        if(securityService == null){
            securityService = new SecurityService(
                    getUserDao(),
                    getDigestService(),
                    getServiceConverter()
            );
        }
        return securityService;
    }
}
