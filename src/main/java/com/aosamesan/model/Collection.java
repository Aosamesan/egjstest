package com.aosamesan.model;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Collection {
    private String path;
    private RootImageDirectoryProvider rootImageDirectoryProvider;

    Collection(String path, RootImageDirectoryProvider rootImageDirectoryProvider) {
        this.path = path;
        this.rootImageDirectoryProvider = rootImageDirectoryProvider;
    }

    public String getPath() {
        return path;
    }

    public String getDirectory() {
        return rootImageDirectoryProvider.getPath(path);
    }

    public List<String> getCollection() {
        try {
            File directory = new File(rootImageDirectoryProvider.getPath(path));

            if (directory.isDirectory()) {
                List<String> imageList = new ArrayList<>();
                for (File image : Objects.requireNonNull(directory.listFiles(Collection::isImageFile))) {
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
