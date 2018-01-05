package com.akshay.services;

import com.akshay.utils.FileUtility;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArchivalService {

    @Value("${max.folder.size.mb}")
    private int maxInMB;

    @Value("${archive.folder.path}")
    private String archiveFolder;

    @Autowired
    private FileUtility fileUtility;

    private static final Logger logger = LoggerFactory.getLogger(ArchivalService.class);


    public List<File> archive(String directory) {
        logger.debug("inside archival");
        List<File> archivedFiles = new ArrayList<>();
        logger.debug("inside archival size :"+ fileUtility.getFolderSizeInMBs(directory)+ " max:"+maxInMB);

        if (fileUtility.getFolderSizeInMBs(directory) >= maxInMB) {

            doArchival(directory, archivedFiles);
        }
        return archivedFiles;
    }

    private void doArchival(String directory, List<File> archivedFiles) {
        String fileName;
        List<File> files = fileUtility.getFilesSortedByDate(directory);
        while (fileUtility.getFolderSizeInMBs(directory) >= maxInMB && files.size() > 0) {
            fileName = files.get(0).getName();
            File destFile = new File(archiveFolder +"/" +fileName);
            try {
                FileUtils.moveFile(files.get(0), destFile);
                archivedFiles.add(files.get(0));
            } catch (IOException e) {
                logger.error("Error while moving file {} to archive folder :" ,files.get(0).getAbsolutePath());
                e.printStackTrace();
            }
            files.remove(0);
        }
    }
}
