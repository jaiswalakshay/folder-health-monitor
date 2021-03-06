package com.akshay.services;

import com.akshay.utils.FileUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;


@Component
public class SecurityService {

    private Set<String> insecureExtensions;

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    @Autowired
    private FileUtility fileUtility;

    public SecurityService(@Value("${invalid.extensions}") String commaSepExtensions) {
        logger.debug("Invalid extensions :"+ commaSepExtensions);
        this.insecureExtensions = new HashSet<>(Arrays.asList(commaSepExtensions.split(",")));
    }

    public List<File> securityCheck(String directory) {
        List<File> deletedFiles = new ArrayList<>();
        List<File> files = fileUtility.getFiles(directory);
        logger.debug("List of files :"+files);
        files.forEach(file -> {
            if (!isValidExtension(fileUtility.getExtension(file))) {
                deletedFiles.add(file);
            }
        });
        fileUtility.deleteFiles(deletedFiles);
        return deletedFiles;
    }

    private boolean isValidExtension(String extension) {
        logger.debug("Is Valid Ext :" + extension);
        if (insecureExtensions == null || !insecureExtensions.contains(extension)) {
            logger.debug("Is Valid Ext 2");
            return true;
        }
        return false;
    }
}
