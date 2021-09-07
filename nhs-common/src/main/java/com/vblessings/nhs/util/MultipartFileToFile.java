package com.vblessings.nhs.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;

/**
 * 文件上传工具
 */
@Slf4j
public class MultipartFileToFile {
    /**
     * MultipartFile 转 File
     *
     * @param file webfile
     */
    public static File multipartFileToFile(MultipartFile file) {
        File toFile=null;
        try(InputStream ins = file.getInputStream()){
            toFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
            inputStreamToFile(ins, toFile);
        }catch (IOException e){
            log.error("MultipartFile to file failed! {}",e.getMessage());
        }
        return toFile;
    }

    /**
     * 获取流文件
     * @param ins 流
     * @param file 文件
     */
    private static void inputStreamToFile(InputStream ins, File file) {
        try(OutputStream os = new FileOutputStream(file)) {
            int bytesRead;
            int buffSize=8192;
            byte[] buffer = new byte[buffSize];
            while ((bytesRead = ins.read(buffer, 0, buffSize)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            log.error("MultipartFile to file failed! {}",e.getMessage());
        }
    }

    public static File transFormAndRename(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        String[] filename = originalFilename.split("\\.");
        //临时文件
        File file= null;
        try {
//            file = File.createTempFile(filename[0], "."+filename[1]);
            file = File.createTempFile("temp", "."+filename[filename.length - 1]);
            multipartFile.transferTo(file);
            //重命名
            file = FileUtil.rename(file, RandomUtil.randomString(18), true, true);
        } catch (IOException e) {
            log.error("MultipartFile to file failed! {}",e.getMessage());
        }
        return file;
    }

    public static File rename(File file){
        return FileUtil.rename(file, RandomUtil.randomString(18), true, true);
    }
}
