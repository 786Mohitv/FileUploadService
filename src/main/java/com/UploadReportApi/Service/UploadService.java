package com.UploadReportApi.Service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UploadService {

    public void save(MultipartFile file, String root) {
    	File directory  = new File(root);
    	if (!directory.exists()) {
            directory.mkdir();
            
    }
    	Path newRoot = Paths.get(root);

        try {
            String extension = "."+FilenameUtils.getExtension(file.getOriginalFilename());
            System.out.print(extension);
            String Name = new SimpleDateFormat("ddMMyyyyHHmmss'.txt'").format(new Date());
            String filename = file.getOriginalFilename().replace(extension,"").concat(Name).concat(extension);

            Files.copy(file.getInputStream(), newRoot.resolve(filename));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
}
