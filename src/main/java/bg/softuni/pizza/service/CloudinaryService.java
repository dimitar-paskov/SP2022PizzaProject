package bg.softuni.pizza.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

@Service
public class CloudinaryService {

    private static final String URL = "url";
    private static final String TEMP_FILE = "temp_file";
    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadPicture(MultipartFile picture) throws IOException {
        File temp = File.createTempFile(TEMP_FILE, picture.getOriginalFilename());
        picture.transferTo(temp);

        try {
            @SuppressWarnings("unchecked")
            Map<String, String> result = this.cloudinary
                    .uploader()
                    .upload(temp, Map.of("folder", "projectPizza"));

            return result
                    .getOrDefault(
                            URL,
                            "https://res.cloudinary.com/dofeaskyi/image/upload/v1659437651/projectPizza/pizzas/Pizza-logo-transparent-PNG_mtjqyr.png");
        } finally {
            temp.delete();
        }
    }
}
