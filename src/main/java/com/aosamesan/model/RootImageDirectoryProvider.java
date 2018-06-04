package com.aosamesan.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RootImageDirectoryProvider {
    private String rootDirectoryPath;

    public String getRootDirectoryPath() {
        return rootDirectoryPath;
    }

    public void setRootDirectoryPath(String rootDirectoryPath) {
        this.rootDirectoryPath = rootDirectoryPath;
    }

    public String getPath(String belowPath) {
        return String.format("%s%s/", rootDirectoryPath, belowPath);
    }

    public List<Collection> getCollections() {
        try {
            File file = new File(rootDirectoryPath);

            if (file.isDirectory()) {
                List<Collection> collections = new ArrayList<>();
                for (File innerFile : Objects.requireNonNull(file.listFiles(File::isDirectory))) {
                    String path = innerFile.getName();
                    collections.add(new Collection(path, this));
                }
                return collections;
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
