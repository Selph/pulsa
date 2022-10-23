package is.hi.hbv501g.h6.hugboverkefni.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    // @Value("${cloudinary.cloudName}")
    private String cloudName = "dc6h0nrwk";

    // @Value("${cloudinary.apiKey}")
    private String apiKey = "112511792528238";

    // @Value("${cloudinary.apiSecret}")
    private String apiSecret = "sIpq6xE1V-oR39xFgnRwpdv6_3E";

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret,
            "secure", true));

    /**
     * Uploads Image to cloudinary
     * @param file MultipartFile
     * @return String Cloudinary URL containing uploaded image
     */
    public String uploadImage(MultipartFile file) {
        try {
            File uploadedFile = convertMultiPartToFile(file);
            Map uploadResult = cloudinary.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
            boolean isDeleted = uploadedFile.delete();

            if (isDeleted){
                System.out.println("File successfully deleted");
            }else
                System.out.println("File doesn't exist");
            return  uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Uploads Audio file to cloudinary
     * @param file MultipartFile
     * @return String Cloudinary URL containing uploaded audio file
     */
    public String uploadAudio(MultipartFile file) {
        try {
            File uploadedFile = convertMultiPartToFile(file);
            Map uploadResult = cloudinary.uploader().upload(uploadedFile, ObjectUtils.asMap("resource_type", "video"));
            boolean isDeleted = uploadedFile.delete();

            if (isDeleted){
                System.out.println("File successfully deleted");
            }else
                System.out.println("File doesn't exist");
            return  uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Uploads recorded audio to cloudinary
     * @param recording String DataURL from audio recording
     * @return String Cloudinary URL containing audio recording
     */
    public String uploadRecording(String recording) {
        try {
            Map uploadResult = cloudinary.uploader().upload(recording, ObjectUtils.asMap("resource_type", "video"));
            return  uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts MultipartFile to File ready for upload to cloudinary
     * @param file MultipartFile
     * @return File
     * @throws IOException
     */
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
