package com.akshay.av;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;


@Component
public class ClamAVClient implements AVClient {

    private static final String FRESHCLAM = "freshclam";
    private static final String FOUND = "FOUND";
    private static final String CLAMDSCAN_COMMAND = "clamdscan ";
    private static final String UTF_8 = "UTF-8";
    private Process scanProcess;


    /**
     * @param path of file or directory
     * @return AVScanResult
     * @throws IOException
     */
    public AVScanResult doAVScan(String path) throws IOException {
        AVScanResult scanResult = new AVScanResult();
        String cmdString = CLAMDSCAN_COMMAND + path;
        scanProcess = Runtime.getRuntime().exec(cmdString);
        setOutput(scanResult);
        setErrorDetails(scanResult);
        return scanResult;
    }

    /**
     * setOutput() sets the output result if scan is successful and also sets
     * the flag whether file/folder is infected or not
     *
     * @throws IOException
     */
    private void setOutput(AVScanResult scanResult) throws IOException {
        InputStream inputstream = scanProcess.getInputStream();
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputstream, writer, UTF_8);
        String output = writer.toString();
        scanResult.setOutput(output);
        if (output.contains(FOUND)) {
            scanResult.setInfected(true);
        } else {
            scanResult.setInfected(false);
        }
    }

    /**
     * setErrorDetails() sets the error details if scan is NOT successful e.g
     * file not found error
     *
     * @throws IOException
     */
    private void setErrorDetails(AVScanResult scanResult) throws IOException {
        InputStream errorstream = scanProcess.getErrorStream();
        StringWriter errorwriter = new StringWriter();
        IOUtils.copy(errorstream, errorwriter, UTF_8);
        String error = errorwriter.toString();
        scanResult.setError(error);
        if (error.equals("")) {
            scanResult.setSuccess(true);
            ;
        } else {
            scanResult.setSuccess(false);
        }
    }

    /**
     * @throws IOException
     */
    public void updateAVDataBase() throws IOException {
        String cmdString = FRESHCLAM;
        Process p = Runtime.getRuntime().exec(cmdString);
        InputStream inputstream = p.getInputStream();
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputstream, writer, UTF_8);
        String output = writer.toString();
        System.out.println("" + output);
    }

}