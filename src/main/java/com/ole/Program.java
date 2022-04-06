package com.ole;

import com.ole.injectionExample.Injector;
import com.ole.injectionExample.SomeBean;
import com.ole.injectionExample.SomeImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Program {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String properties1 = "prop.properties";
        Injector injector = new Injector(properties1);
        SomeBean someBean1 = new SomeBean();
        SomeImpl some = new SomeImpl();
        System.out.println(some.getClass().getCanonicalName());
        SomeBean someBean = injector.inject(new SomeBean());
        someBean.foo();
    }
}
