package com.sakib.io;

import com.sakib.io.filters.AuthenticationFilter;
import com.sakib.io.filters.LoggingFilter;
import com.sakib.io.litespring.ApplicationContext;
import com.sakib.io.litespring.LiteSpringApplication;
import com.sakib.io.litespring.annotation.PackageScan;
import jakarta.servlet.Filter;

import java.util.ArrayList;
import java.util.List;


@PackageScan(scanPackages = {"com.sakib.io"})
public class MainApplication {

    public static void main(String[] args) throws Exception {

        List<Filter> filterList = new ArrayList<>();
        // maintain order of filter
        filterList.add(new AuthenticationFilter());
        filterList.add(new LoggingFilter());

        ApplicationContext applicationContext = LiteSpringApplication.run(
                MainApplication.class, filterList);

    }
}


