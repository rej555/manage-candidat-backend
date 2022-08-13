package procheacksa.students.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import procheacksa.students.dio.CandidatRepository;
import procheacksa.students.entites.Candidateur;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin("*")
public class CandidatController {
    public Resource resource;
    @Autowired
    private CandidatRepository candidatRepository;


    @GetMapping("/getCandidatImage/{fileCode}")
    public Object downloadFile(@PathVariable("fileCode") Long filecode
    ){
        // FileDownloadUtil downloadUtil = new FileDownloadUtil();
        // Resource resource =  null;
        List<Resource> resourceList =  new ArrayList<Resource>();
        List<CandidatsImages> demandeImages =  new ArrayList<CandidatsImages>();
        try{
            Path uploadDirectory;
            List<Path> foundFileList = new ArrayList<>();
            uploadDirectory = Paths.get("File-Candidats");
            Files.list(uploadDirectory).forEach(file -> {
                if( file.getFileName().toString().startsWith(String.valueOf(filecode))){
                    foundFileList.add(file);
                    // return;
                }
            });
            if(foundFileList.size() > 0){
                foundFileList.forEach(foundFiles -> {
                    try {

                        this.resource = new UrlResource(foundFiles.toUri());
                        resourceList.add(this.resource);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                });
                return resourceList;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        if (resourceList.size() == 0){
            return 0;
        }
        resourceList.forEach(resource -> {
            try {

                InputStream stream = resource.getInputStream();
                long size = resource.getFile().length();
                byte[] byteArr = convertStreamToByteArray(stream, size);
                CandidatsImages demandeImages1 = new CandidatsImages(resource.getFilename(), filecode.toString(), null, byteArr);
                // demandeImages.add(new DemandeImages(resource.getFilename(), filecode, null, null));
                demandeImages.add(demandeImages1);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        System.out.println(demandeImages);
        return demandeImages;
        // download le fichier ------------------------------
        //String contentType = "application/octet-stream";
        //String headerValue = "attachement; file=\""+ resource.getFilename()+ "\"";
        // return ResponseEntity.ok()
        //         .contentType(MediaType.parseMediaType(contentType))
        //         .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
        //       .body(resource);
        //---------------
        // return demandeImages;
    }
    @GetMapping("/Delete/all")
    public void Delete(){
         candidatRepository.deleteAll();
    }
    @GetMapping("/candidat/all")
    public List<Candidateur> AllCandidat(){
        return candidatRepository.findAll();
    }
     @GetMapping("/candidatDelete/{id}")
    public Number candidatDelete(@PathVariable(name = "id") Long id){
         candidatRepository.deleteById(id);
         return id;
    }
    @PostMapping("/newCandidat")
    public Candidateur newCandidat(@RequestBody Candidateur candidateurDto){
        candidatRepository.save(candidateurDto);
        return candidateurDto;
    }
    @PostMapping("/UploadFileWebnewCandidat/{id}")
    public Number uploadFileWeb(
            @RequestPart("imageFile") MultipartFile multipartFile,
            @PathVariable(name = "id") Long id) throws IOException {
        Candidateur candidateur = candidatRepository.findById(id).get();
        // delete old picture + save the new one
        String fileName = StringUtils.cleanPath(id  + "-"+ candidateur.getName()+ candidateur.getPrenom()  + multipartFile.getOriginalFilename());

         Path uploadirectory = Paths.get("File-Candidats/");

        try (InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadirectory.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return 1;
        } catch (IOException ioException){
            throw new IOException("Error saving upload file: " + ioException);
        }

    }
    public byte[] convertStreamToByteArray(InputStream stream, long size) throws IOException {

        // check to ensure that file size is not larger than Integer.MAX_VALUE.
        if (size > Integer.MAX_VALUE) {
            return new byte[0];
        }

        byte[] buffer = new byte[(int)size];
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        int line = 0;
        // read bytes from stream, and store them in buffer
        while ((line = stream.read(buffer)) != -1) {
            // Writes bytes from byte array (buffer) into output stream.
            os.write(buffer, 0, line);
        }
        stream.close();
        os.flush();
        os.close();
        return os.toByteArray();
    }

}


