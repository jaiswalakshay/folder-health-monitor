package com.akshay.domain;

import java.io.File;
import java.util.List;

public class Report {

    private File directory;

    private List<File> deletedFiles;

    private List<File> archivedFiles;

    @Override
    public String toString() {
        return "Report{" +
                "directory=" + directory +
                ", deletedFiles=" + deletedFiles +
                ", archivedFiles=" + archivedFiles +
                '}';
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public List<File> getDeletedFiles() {
        return deletedFiles;
    }

    public void setDeletedFiles(List<File> deletedFiles) {
        this.deletedFiles = deletedFiles;
    }

    public List<File> getArchivedFiles() {
        return archivedFiles;
    }

    public void setArchivedFiles(List<File> archivedFiles) {
        this.archivedFiles = archivedFiles;
    }


}
