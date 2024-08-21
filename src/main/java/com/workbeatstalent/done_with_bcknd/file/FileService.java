package com.workbeatstalent.done_with_bcknd.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    String upload(final String path, final MultipartFile file) throws IOException;

    InputStream download(final String path, final String filename) throws FileNotFoundException;
}
