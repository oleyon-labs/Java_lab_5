package com.ole;

import com.ole.injectionExample.Injector;
import com.ole.injectionExample.SomeBean;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Program {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String properties = "prop.properties";
        Injector injector = new Injector(properties);
        SomeBean someBean = injector.inject(new SomeBean());
        someBean.foo();

        System.out.println("--------------");

        //пробуем с другими настройками
        String properties2 = "prop2.properties";
        injector.setPropertiesString(properties2);
        someBean = injector.inject(new SomeBean());
        someBean.foo();
    }
}
