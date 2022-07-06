package ua.v380dev.model.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@AllArgsConstructor
@Data
public class Reference {
    private String businessName;
    private String url;
    public InputStream getInputStream() throws IOException {
        return new URL(url).openStream();
    }
}
