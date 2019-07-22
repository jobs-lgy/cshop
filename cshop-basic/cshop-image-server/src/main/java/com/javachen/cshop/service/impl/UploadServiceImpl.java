package com.javachen.cshop.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.javachen.cshop.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    /**
     * 支持上传的文件类型
     */
    private static final List<String> suffixes = Arrays.asList("image/png", "image/jpeg", "image/jpg");
    @Autowired
    private FastFileStorageClient storageClient;

    @Override
    public String upload(MultipartFile file) {
        /**
         * 1.图片信息校验
         *      1)校验文件类型
         *      2)校验图片内容
         * 2.保存图片
         *      1)生成保存目录
         *      2)保存图片
         *      3)拼接图片地址
         */
        try {
            String type = file.getContentType();
            if (!suffixes.contains(type)) {
                log.info("上传文件失败，文件类型不匹配：{}", type);
                return null;
            }
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                log.info("上传失败，文件内容不符合要求");
                return null;
            }

            StorePath storePath = this.storageClient.uploadFile(
                    file.getInputStream(), file.getSize(), getExtension(file.getOriginalFilename()), null);

            return "http://image.cshop.com/" + storePath.getFullPath();
        } catch (Exception e) {
            return null;
        }
    }

    public String getExtension(String fileName) {
        return StringUtils.substringAfterLast(fileName, ".");
    }
}
