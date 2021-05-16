package com.zhj.abstractrouting.datasource;

import java.util.ArrayList;
import java.util.List;

public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    private static List<String> dataSourceNames = new ArrayList<>();

    public static void setDataSourceName(String name) {
        CONTEXT_HOLDER.set(name);
    }

    public static String getDataSourceName() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSourceName() {
        CONTEXT_HOLDER.remove();
    }

    public static void addDataSourceNames(String name) {
        dataSourceNames.add(name);
    }

    public static boolean containsDataSource(String dataSourceName) {
        return dataSourceNames.contains(dataSourceName);
    }
}
