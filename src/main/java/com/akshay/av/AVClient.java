package com.akshay.av;

import java.io.IOException;
/**
 *
 * @author Akshay.Jaiswal@in.tesco.com
 *
 */
public interface AVClient {

    public AVScanResult doAVScan(String path) throws  IOException;

}
