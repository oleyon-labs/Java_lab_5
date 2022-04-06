package com.ole.injectionExample;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Класс, осуществляющий инициализацию полей, помеченных аннотацией @AutoInjectable,
 * объектами классов, заданных в файле properties
 */
public class Injector {
    /**
     * путь файла properties
     */
    private String propertiesString;

    public String getPropertiesString() {
        return propertiesString;
    }

    public void setPropertiesString(String propertiesString) {
        this.propertiesString = propertiesString;
    }

    public Injector(String propertiesString){
        this.propertiesString = propertiesString;
    }

    /**
     * инициализирует все помеченные аннотацией поля объекта object объектами, взятыми из properties
     * выбрасывает исключение при неудачной попытке инициализации хотя бы 1 поля
     * @param object объект, в котором будут инициализироваться поля
     * @param <T> тип объекта
     * @return Тот же объект с инициализированными полями
     */
    public <T> T inject(T object) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Properties properties = new Properties();
        properties.load(Injector.class.getClassLoader().getResourceAsStream(propertiesString));
        for(Field field:object.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(AutoInjectable.class)){
                field.setAccessible(true);
                var objectName = properties.getProperty(field.getType().getCanonicalName());
                var instance = Class.forName(objectName).getConstructor().newInstance();
                field.set(object, instance);
            }
        }
        return object;
    }
}
