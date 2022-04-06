package com.ole.injectionExample;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Injector {

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

    public <T> T inject(T object) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Properties properties = new Properties();
        properties.load(Injector.class.getClassLoader().getResourceAsStream(propertiesString));
        for(Field field:object.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(AutoInjectable.class)){
                field.setAccessible(true);
                var objectName = properties.getProperty(field.getType().getTypeName());
                var instance = Class.forName(objectName).getConstructor().newInstance();
                field.set(object, instance);
            }
        }
        return object;
    }
}
