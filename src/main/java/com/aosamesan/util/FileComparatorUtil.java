package com.aosamesan.util;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileComparatorUtil {
    private static final Pattern ONLY_NUMBER_PATTERN = Pattern.compile("^([0-9]+)$");

    public static int compareFile(File file1, File file2) {
        return compareFileName(file1.getName(), file2.getName());
    }

    public static int compareFileName(String name1, String name2) {
        String ext1 = name1.substring(name1.indexOf('.'), name1.length() - 1);
        String ext2 = name2.substring(name2.indexOf('.'), name2.length() - 1);
        String exceptExtName1 = name1.substring(0, name1.indexOf('.') );
        String exceptExtName2 = name2.substring(0, name2.indexOf('.'));

        Matcher matcher1 = ONLY_NUMBER_PATTERN.matcher(exceptExtName1);
        Matcher matcher2 = ONLY_NUMBER_PATTERN.matcher(exceptExtName2);

        if (matcher1.find() && matcher2.find()) {
            long nameAsNumber1 = Long.parseLong(matcher1.group(1));
            long nameAsNumber2 = Long.parseLong(matcher2.group(1));

            if (nameAsNumber1 == nameAsNumber2) {
                return ext1.compareTo(ext2);
            } else {
                return Long.compare(nameAsNumber1, nameAsNumber2);
            }
        } else {
            return name1.compareTo(name2);
        }
    }
}
