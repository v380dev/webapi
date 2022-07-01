package ua.v380dev.model;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ua.v380dev.model.entitys.Endpoint;
import ua.v380dev.model.states.State;
import ua.v380dev.model.states.StateOut;
import ua.v380dev.model.util.HolderPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@Component
public class ModelMain {

    public static List<String> getNameObjects(List<String> listAllLines, String field) {
        Pattern patternParrentObj = HolderPattern.getObjName();
        List<String> listObj = new ArrayList<>();
        listObj.add(field);
        for (int i = 0; i < listObj.size(); i++) {
            String currentObj = listObj.get(i);
            boolean objFound = false;

            for (int j = listAllLines.size() - 1; j >= 0; j--) {
                String currentStr = listAllLines.get(j);
                if (!objFound) {//якщо вложений об'єкт не знайдено - шукаємо далі
                    if (!currentStr.contains("#") && currentStr.contains("`" + currentObj + "`")) {
                        objFound = true;
                    }
                }
                if (objFound) {//вложений об'єкт знайдено - шукаємо батьківський
                    if (currentStr.contains("#")) {
                        Matcher matcher = patternParrentObj.matcher(currentStr);
                        while (matcher.find()) {
                            if (!listObj.contains(matcher.group())) {
                                listObj.add(matcher.group());
                            }
                        }
                        objFound = false;
                    }
                }
            }
        }
        return listObj;
    }


    public List<Endpoint> findEndpoints(List<String> listAllLines, List<String> listObj) {

        List<Endpoint> endpoints = new ArrayList<>();
        State state = new StateOut(listObj, endpoints);
        for (int i = 0; i < listAllLines.size(); i++) {
            state = state.update(listAllLines.get(i), i + 1);
        }
        return endpoints;
    }

    public boolean checkInputFieldName(String nameField) {
        Matcher matcher = HolderPattern.getInputNameField().matcher(nameField);
        return matcher.find();
    }

    public boolean checkInputFileName(String nameFile) {
        Matcher matcher = HolderPattern.getInputNameFile().matcher(nameFile);
        return matcher.find();
    }
}