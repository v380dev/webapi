package ua.v380dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.v380dev.model.entitys.Reference;
import ua.v380dev.service.Service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
    private Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public String startPage() {
        return "main";
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String showSpreadsheet(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "field", required = true) String field,
            @RequestParam(value = "file_from", required = true) String file_from,
            Model model,
            RedirectAttributes redirectAttrs)/* throws IOException*/ {

        if (file != null) {
            if (!service.checkInputNameFile(file.getOriginalFilename())) {
                redirectAttrs.addFlashAttribute("warning", "Некоректна назва файлу");
                return "redirect:/";
            }
            service.setFile(file);
        }
        if (!service.checkInputNameField(field)) {
            redirectAttrs.addFlashAttribute("warning", "Некоректна назва поля");
            return "redirect:/";
        }
        service.setField(field);
        Reference reference = service.getRef(file_from);
        Map<String, String> inputParameters = new LinkedHashMap();
        inputParameters.put("field", field);
        if (reference == null) {
            inputParameters.put("uploaded file", file.getOriginalFilename());
        } else {
            inputParameters.put("resource", reference.getBusinessName());
            inputParameters.put("url", reference.getUrl());
        }
        try {
            model.addAttribute("endpoints", service.getEndpoints(file_from));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        model.addAttribute("input_parameters", inputParameters);
        return "endpoints";
    }
}
