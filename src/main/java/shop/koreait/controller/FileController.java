package shop.koreait.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Log4j2
@RestController
@RequestMapping("/file")
public class FileController {
    private final File productImagesDir =  new File("C:\\Users\\Administrator\\Desktop\\KSW\\product_images");

    @GetMapping("/product/image/{name}")
    public byte[] get_file_view(@PathVariable("name") String imageName) throws Exception {
        File f = new File(productImagesDir, imageName + ".jpg");
        try (FileInputStream in = new FileInputStream(f)){
            return in.readAllBytes();
        }catch (IOException e){
            log.error("{} 파일이 존재하지 않습니다.", imageName);
            log.error(e);
        }
        return null;
    }
}
