package com.UploadReportApi.Controller;

import com.UploadReportApi.Response.ResponseMessage;
import com.UploadReportApi.Service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;

@RestController
public class UploadController {
    @Autowired
    UploadService uploadService;
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
                                                      @RequestParam("storagePath") String storagePath,
                                                      @RequestParam("ProjectId") String ProjectId,
                                                      @RequestParam("ContractNo") String ContractNo,
                                                      @RequestParam("SubmissionId") String SubmissionId) {
        String message = "";
        try {
            uploadService.save(file, storagePath+"/report");
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
