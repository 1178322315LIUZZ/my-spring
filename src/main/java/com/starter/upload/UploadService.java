package com.starter.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UploadService {
    private UploadProperties uploadProperties;

    public UploadService(UploadProperties uploadProperties) {
        this.uploadProperties = uploadProperties;
    }
    public List<String> uploads(List<MultipartFile> files) throws Exception {
        List<String> list=new ArrayList<String>();
        for (MultipartFile file : files) {
            if(file.getSize()>0){
                String filename = new Date().getTime()+file.getOriginalFilename();
                File file1 = new File(uploadProperties.getPath(), filename);
                if(!file1.getParentFile().exists()){
                    file1.mkdirs();
                }
                file.transferTo(file1);
                list.add(filename);
            }
        }
        return list;
    }
    public String upload(MultipartFile file) throws Exception {
        String filename="";
            if(file.getSize()>0){
                filename = new Date().getTime()+file.getOriginalFilename();
                File file1 = new File(uploadProperties.getPath(), filename);
                if(!file1.getParentFile().exists()){
                    file1.mkdirs();
                }
                file.transferTo(file1);
            }
        return filename;
    }
}
