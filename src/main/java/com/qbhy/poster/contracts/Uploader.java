package com.qbhy.poster.contracts;

import com.qbhy.poster.kernal.UploadResult;

import java.io.File;
import java.io.IOException;

public interface Uploader {
    public UploadResult upload(File file) throws IOException;
}
