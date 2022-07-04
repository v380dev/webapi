package ua.v380dev.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartFile;
import ua.v380dev.model.ModelMain;
import ua.v380dev.model.entitys.Endpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@org.springframework.stereotype.Service
@PropertySource("classpath:reference_file.properties")
public class Service {
    @Value("${file.download.ehals1}")
    URL eHals1address;
    @Value("${file.download.ehals2}")
    URL eHals2address;

    private MultipartFile file;
    private String field;
    private ModelMain modelMain;

    @Autowired
    public Service(ModelMain modelMain) {
        this.modelMain = modelMain;
    }

    private List<String> getAllLines(MultipartFile file) throws IOException {
        List<String> listAllLines = new ArrayList<>();

        var is = file.getInputStream();
        var br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null) {
            listAllLines.add(line);
        }
        return listAllLines;
    }

    private List<String> getAllLines(InputStream is) throws IOException {
//    private List<String> getAllLines(URLClassLoader file) throws IOException {
        List<String> listAllLines = new ArrayList<>();

        var br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null) {
            listAllLines.add(line);
        }
        return listAllLines;
    }

    public List<Endpoint> getEndpoints(String radioButton) throws IOException {
        var allLines = getAllLines(getReferenceFile(radioButton));
        var nameObjs = modelMain.getNameObjects(allLines, field);
        return modelMain.findEndpoints(allLines, nameObjs);
    }
    public boolean checkInputNameField(String nameField) {
        return modelMain.checkInputFieldName(nameField);
    }

    public boolean checkInputNameFile(String nameFile) {
        return modelMain.checkInputFileName(nameFile);
    }

    public InputStream getReferenceFile(String radioButton) throws IOException {
        return switch (radioButton) {
            case "eHals1_API" -> eHals1address.openStream();
            case "eHals2_API" -> eHals2address.openStream();
            case "local_file" -> file.getInputStream();
            default -> InputStream.nullInputStream();//відредагувати
        };
    }


}
