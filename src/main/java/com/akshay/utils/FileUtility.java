package com.akshay.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FileUtility {

    private static final Logger logger = LoggerFactory.getLogger(FileUtility.class);


    public List<File> getFilesAndFolders(String directoryName) {
        return Arrays.asList(new File(directoryName).listFiles());
    }

    public List<File> getFilesSortedByDate(String directoryName) {
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        Arrays.sort(fList, (f1, f2) -> Long.valueOf(f1.lastModified()).compareTo(f2.lastModified()));
        List<File> list = new ArrayList<>();
        list.addAll(Arrays.asList(fList));
        return list;
    }

    public List<File> getFiles(String directoryName) {
        List<File> list = new ArrayList<File>();
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                list.add(file);
            }
        }
        return list;
    }

    public String getExtension(File file) {
        if (file != null) {
            String path = file.getAbsolutePath();
            return path.substring(path.lastIndexOf(".") + 1, path.length());
        }
        return null;
    }

    public List<File> getFolders(String directoryName) {
        List<File> listOfFolders = new ArrayList<File>();
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isDirectory()) {
                listOfFolders.add(file);
            }
        }
        return listOfFolders;
    }

    /**
     * List all files from a directory and its subdirectories
     *
     * @param directoryName to be listed
     */
    public List<File> getFilesAndFilesSubDirectories(String directoryName) {
        List<File> listOfFilesAndFolders = new ArrayList<File>();
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                listOfFilesAndFolders.add(file);
            } else if (file.isDirectory()) {
                getFilesAndFilesSubDirectories(file.getAbsolutePath());
            }
        }
        return listOfFilesAndFolders;
    }

    public void deleteFiles(File... files) {
        for (File file : files) {
            file.delete();
        }
    }

    public void deleteFiles(List<File> files) {
        for (File file : files) {
            file.delete();
        }
    }

    public long folderSize(File dir) {
        long length = 0;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                length += file.length();
            else
                length += folderSize(file);
        }
        return length;
    }

    public long getFolderSizeInMBs(String dir) {
        File directory = new File(dir);
        return folderSize(directory) / (1024 * 1024);
    }
}