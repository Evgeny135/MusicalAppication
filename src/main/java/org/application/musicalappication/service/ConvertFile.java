package org.application.musicalappication.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class ConvertFile {

    private File file = new File("src/main/resources/targetFile.tmp");
    public File convertMultipartFileToFile(MultipartFile multipartFile){

        try (OutputStream os = new FileOutputStream(file)) {
            os.write(multipartFile.getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    public void deleteTemplateFile(){
        file.delete();
    }
}
