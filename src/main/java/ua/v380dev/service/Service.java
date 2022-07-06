package ua.v380dev.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;
import ua.v380dev.model.ModelMain;
import ua.v380dev.model.entitys.Endpoint;
import ua.v380dev.model.entitys.Reference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@org.springframework.stereotype.Service
@PropertySource("classpath:reference_file.properties")
public class Service {
    @Autowired
    private Environment env;

    private MultipartFile file;
    private String field;
    private ModelMain modelMain;

    @Autowired
    public Service(ModelMain modelMain) {
        this.modelMain = modelMain;
    }


    private List<String> getAllLines(InputStream is) throws IOException {
        List<String> listAllLines = new ArrayList<>();

        var br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null) {
            listAllLines.add(line);
        }
        return listAllLines;
    }

    public List<Endpoint> getEndpoints(String radioButton) throws IOException {
        var allLines = getAllLines(getInputStream(radioButton));
        var nameObjs = modelMain.getNameObjects(allLines, field);
        return modelMain.findEndpoints(allLines, nameObjs);
    }
    public boolean checkInputNameField(String nameField) {
        return modelMain.checkInputFieldName(nameField);
    }

    public boolean checkInputNameFile(String nameFile) {
        return modelMain.checkInputFileName(nameFile);
    }

    public InputStream getInputStream(String radioButton) throws IOException {
        if(radioButton.equals("local_file")) {
            return file.getInputStream();
        }
        Reference ref = getRef(radioButton);
        System.out.println("name="+ref.getBusinessName());
        System.out.println("url="+ref.getUrl());
        return getRef(radioButton).getInputStream();
    }

    public Reference getRef(String radioButton) {
        return switch (radioButton) {
            case "ehealth" -> new Reference(env.getProperty("name.ehealth"), env.getProperty("url.ehealth"));
            case "ehealth_med" -> new Reference(env.getProperty("name.ehealth_med"), env.getProperty("url.ehealth_med"));
            case "ehealth_mis" -> new Reference(env.getProperty("name.ehealth_mis"), env.getProperty("url.ehealth_mis"));
            case "ehealth_mis_med" -> new Reference(env.getProperty("name.ehealth_mis_med"), env.getProperty("url.ehealth_mis_med"));
            default -> null;//відредагувати
        };
    }
}
