package com.workbeatstalent.done_with_bcknd.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String upload(final String path, final MultipartFile file) throws IOException {
        final var filename = file.getOriginalFilename();
        final var filePath = file + File.pathSeparator + filename;
        final var fileObject = new File(path);

        // Create folder if not exists
        if (!fileObject.exists()) fileObject.mkdir();

        // Copy the uploaded file into the folder
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return filename;
    }

    @Override
    public InputStream download(final String path, final String filename) throws FileNotFoundException {
        final var filePath = path + File.pathSeparator + filename;
        return new FileInputStream(filePath);
    }
}
