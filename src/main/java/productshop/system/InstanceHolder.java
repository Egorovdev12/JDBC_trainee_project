package productshop.system;

import productshop.repository.*;
import productshop.service.ServiceInterface;
import java.util.ArrayList;

public class InstanceHolder {

    private static ArrayList<RepositoryInterface> repositoryInstances = new ArrayList<>();

    private static ArrayList<ServiceInterface> serviceInstances = new ArrayList<>();

    public static <T extends ServiceInterface> T getService(Class<T> serviceClass) {
        for (ServiceInterface service : serviceInstances) {
            if (serviceClass.isInstance(service)) {
                return serviceClass.cast(service); // безопасное приведение к T
            }
        }
        return null;
    }

    public static void addServiceSingleton(ServiceInterface serviceSingleton) {
        serviceInstances.add(serviceSingleton);
    }

    public static void addRepositorySingleton(RepositoryInterface repositorySingleton) {
        repositoryInstances.add(repositorySingleton);
    }

    public static void showServiceContext() {
        System.out.println("Текущие экземпляры сервисов в приложении: ");
        for (ServiceInterface serviceInterface : serviceInstances) {
            System.out.println("- " + serviceInterface.getClass().getSimpleName());
        }
    }

    public static void showRepositoryContext() {
        System.out.println("Текущие экземпляры репозиториев в приложении: ");
        for (RepositoryInterface repositoryInterface : repositoryInstances) {
            System.out.println("- " + repositoryInterface.getClass().getSimpleName());
        }
    }
}
