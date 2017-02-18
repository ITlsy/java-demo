package com.lsy.service;

import java.io.InputStream;

/**
 * Created by Administrator on 2017/2/17 0017.
 */
public interface FileService {
    String uploadFile(String originalFilename, String contentType, InputStream inputStream);
}
