package ua.v380dev.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import ua.v380dev.model.ModelMain;
import ua.v380dev.model.entitys.Endpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@org.springframework.stereotype.Service
public class Service {
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

    public List<Endpoint> getEndpoints() throws IOException {
        var allLines = getAllLines(file);
        var nameObjs = modelMain.getNameObjects(allLines, field);
        return modelMain.findEndpoints(allLines, nameObjs);
    }

    public boolean checkInputNameField(String nameField) {
        return modelMain.checkInputFieldName(nameField);
    }

    public boolean checkInputNameFile(String nameFile) {
        return modelMain.checkInputFileName(nameFile);
    }

}
