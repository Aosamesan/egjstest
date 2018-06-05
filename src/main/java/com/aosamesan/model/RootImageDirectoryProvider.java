package com.aosamesan.model;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RootImageDirectoryProvider {
    private String rootDirectoryPath;
    private List<Collection> collectionList;

    @PostConstruct
    public void init() {
        collectionList = extractCollections();
    }

    public String getRootDirectoryPath() {
        return rootDirectoryPath;
    }

    public void setRootDirectoryPath(String rootDirectoryPath) {
        this.rootDirectoryPath = rootDirectoryPath;
    }

    public String getPath(String belowPath) {
        return String.format("%s%s/", rootDirectoryPath, belowPath);
    }

    public Collection getCollection(int collectionNumber) {
        return collectionList.get(collectionNumber);
    }

    private List<Collection> extractCollections() {
        try {
            File file = new File(rootDirectoryPath);

            if (file.isDirectory()) {
                int index = 0;
                List<Collection> collections = new ArrayList<>();
                for (File innerFile : Objects.requireNonNull(file.listFiles(File::isDirectory))) {
                    String path = innerFile.getName();
                    Collection collection = new Collection(index++, path, this);
                    if (collection.getIsValid()) {
                        collections.add(collection);
                    }
                }
                return collections;
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }

    }

    public List<Collection> getCollections() {
        return collectionList;
    }
}
