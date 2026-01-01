package productshop.system;

import org.reflections.Reflections;
import productshop.system.annotations.Repository;
import productshop.system.annotations.Service;
import productshop.repository.RepositoryInterface;
import productshop.service.ServiceInterface;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationStarter {

    public static void start() {
        scanClasspathAndFoundAnnotations();
        DataDownloader dataDownloader = new DataDownloader(ConnectionManager.getInstance());
        dataDownloader.downloadDataAboutCurrentProductsAndCustomers();
    }

    private static void scanClasspathAndFoundAnnotations() {
        Reflections reflections = new Reflections("productshop");

        Set<Class<? extends ServiceInterface>> annotatedServices =
                reflections.getSubTypesOf(ServiceInterface.class)
                        .stream()
                        .filter(clazz -> clazz.isAnnotationPresent(Service.class))
                        .collect(Collectors.toSet());

        Set<Class<? extends RepositoryInterface>> annotatedRepositories =
                reflections.getSubTypesOf(RepositoryInterface.class)
                        .stream()
                        .filter(clazz -> clazz.isAnnotationPresent(Repository.class))
                        .collect(Collectors.toSet());

        for (Class<?> clazz : annotatedServices) {
            Method getInstanceMethod;
            try {
                getInstanceMethod = clazz.getMethod("getInstance");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            Object inst;
            try {
                inst = getInstanceMethod.invoke(null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }

            if (inst instanceof ServiceInterface service) {
                InstanceHolder.addServiceSingleton(service);
            } else {
                throw new RuntimeException("Метод getInstance() в " + clazz + " вернул не ServiceInterface");
            }
        }

        for (Class<?> clazz : annotatedRepositories) {
            Method getInstanceMethod;
            try {
                getInstanceMethod = clazz.getMethod("getInstance");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            Object inst;
            try {
                inst = getInstanceMethod.invoke(null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }

            if (inst instanceof RepositoryInterface repo) {
                InstanceHolder.addRepositorySingleton(repo);
            } else {
                throw new RuntimeException("Метод getInstance() в " + clazz + " вернул не RepositoryInterface");
            }
        }
    }
}
