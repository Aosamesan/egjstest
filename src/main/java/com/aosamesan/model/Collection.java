package com.aosamesan.model;

import com.aosamesan.util.FileComparatorUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;

public class Collection {
    private String path;
    private RootImageDirectoryProvider rootImageDirectoryProvider;
    private List<String> collection;
    private int collectionNumber;
    private boolean isValid;

    Collection(int collectionNumber, String path, RootImageDirectoryProvider rootImageDirectoryProvider) {
        this.collectionNumber = collectionNumber;
        this.path = path;
        this.rootImageDirectoryProvider = rootImageDirectoryProvider;
        this.collection = extractCollection();
        this.isValid = !collection.isEmpty();
    }

    public boolean getIsValid() {
        return isValid;
    }

    public String getPath() {
        return path;
    }

    public String getDirectory() {
        return rootImageDirectoryProvider.getPath(path);
    }

    public String getRepresentativeImage() {
        return collection.get(0);
    }

    public int getCollectionNumber() {
        return collectionNumber;
    }

    public List<String> getCollection() {
        return collection;
    }

    private List<String> extractCollection() {
        try {
            File directory = new File(rootImageDirectoryProvider.getPath(path));

            if (directory.isDirectory()) {
                List<String> imageList = new ArrayList<>();
                List<File> fileList = Arrays.asList(Objects.requireNonNull(directory.listFiles(Collection::isImageFile)));
                fileList.sort(FileComparatorUtil::compareFile);
                for (File image : fileList) {
                    imageList.add(String.format("/image/%s/%s", path, image.getName()));
                }
                return imageList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private static boolean isImageFile(File f) {
        String path = f.getPath().toLowerCase();

        return path.endsWith(".png") || path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".gif");
    }
}
