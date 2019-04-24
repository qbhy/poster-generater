package com.qbhy.poster.contracts;

import java.io.File;
import java.io.IOException;

public interface Uploader {
    public Result upload(File file) throws IOException;
}
