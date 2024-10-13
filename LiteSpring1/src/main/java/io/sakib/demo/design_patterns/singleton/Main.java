package io.sakib.demo.design_patterns.singleton;

public class Main {
    public static void main(String[] args) {
        Cache cache = Cache.getInstance();
        cache.putKeyValue("hello", "world");
        System.out.println("cache.getData(\"hello\") = " + cache.getValue("hello"));
    }
}
