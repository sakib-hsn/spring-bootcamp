package com.sakib.io.litespring;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClassScanner {
    public static List<Class<?>> recursiveClasses(String filePath, String packageName) throws ClassNotFoundException {
        List<Class<?>> classList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.isDirectory()) {
            return new ArrayList<>();
        }
        File[] subFiles = file.listFiles();
        for (File newFile : subFiles) {
            if (newFile.isFile() && newFile.getName().contains(".class")) {
                String className = packageName + "." + newFile.getName().replace(".class", "");
                classList.add(Class.forName(className));
            } else {
                classList.addAll(recursiveClasses(newFile.getAbsolutePath().toString(), packageName + "." + newFile.getName()));
            }
        }
        return classList;
    }
}

