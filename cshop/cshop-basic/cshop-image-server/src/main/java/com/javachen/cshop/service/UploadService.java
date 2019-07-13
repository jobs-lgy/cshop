package com.javachen.cshop.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
