package com.akshay.av;

/**
 * @author Akshay.Jaiswal@in.tesco.com
 */
public class AVScanResult {
    private boolean isInfected;
    private boolean isSuccess;
    private String error;
    private String output;

    public boolean isInfected() {
        return isInfected;
    }

    public void setInfected(boolean isInfected) {
        this.isInfected = isInfected;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

}