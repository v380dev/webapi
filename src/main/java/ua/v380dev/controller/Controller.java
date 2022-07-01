package ua.v380dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.v380dev.service.Service;

import java.io.IOException;

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
            @RequestParam(value = "file", required = true) MultipartFile file,
            @RequestParam(value = "field", required = true) String field,
            Model model,
            RedirectAttributes redirectAttrs) throws IOException {
        if (field.isEmpty() || file.isEmpty()) {
            redirectAttrs.addFlashAttribute("warning", "Заповніть всі поля");
            return "redirect:/";
        }
        if (!service.checkInputNameField(field) || !service.checkInputNameFile(file.getOriginalFilename())) {
            redirectAttrs.addFlashAttribute("warning", "Ви ввели некоректні дані");
            return "redirect:/";
        }
        service.setFile(file);
        service.setField(field);
        model.addAttribute("endpoints", service.getEndpoints());
        model.addAttribute("field", field);
        return "endpoints";
    }
}
