package com.workbeatstalent.done_with_bcknd.file;

import com.workbeatstalent.done_with_bcknd.util.Utils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
//import java.net.InetAddress;

@RestController
@RequestMapping(path = {"/api/v1/files"})
public record FileController(FileService fileService, Utils utils) {

    @PostMapping(path = {"/upload"})
    public ResponseEntity<String> upload(final @RequestPart MultipartFile image) throws IOException {
        final var filename = this.fileService.upload(this.utils.getLocalFilePath(), image);
        return new ResponseEntity<>(filename + " successfully uploaded", HttpStatus.CREATED);
    }

    @GetMapping(path = {"/download/{filename}"})
    public ResponseEntity<Void> download(final @PathVariable(name = "filename") String filename, final HttpServletResponse servletResponse) throws IOException {
//        System.out.println(" *****> " + servletResponse.getLocale().getDisplayCountry());
//        System.out.println("=== HOST ===> " + InetAddress.getLocalHost().getHostAddress());
        final var file = this.fileService.download(this.utils.getLocalFilePath(), filename);
        servletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(file, servletResponse.getOutputStream());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
