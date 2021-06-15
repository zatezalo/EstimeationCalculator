package rs.jz.calculator.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.jz.calculator.model.Element;
import rs.jz.calculator.model.elementDto.AddElementDto;
import rs.jz.calculator.model.pdfDto.PdfDto;
import rs.jz.calculator.services.ElementService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/element")
@CrossOrigin("http://localhost:4200")
public class ElementController {
    @Autowired
    private ElementService elementService;

    @GetMapping("")
    public List<Element> getElements() {
        return elementService.getAllElements();
    }

    @GetMapping("/{id}")
    public Element getElements(@PathVariable("id") Long id) {
        return elementService.getElementById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addElement")
    public void addElement(@RequestBody AddElementDto addElementDto) {
        elementService.addElement(addElementDto);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}/editElement")
    @CrossOrigin(origins = "http://localhost:4200")
    public void editElement(@RequestBody Element element, @PathVariable("id") Long id) {
        element.setId(id);
        elementService.editElement(element);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteElement(@PathVariable("id") Long id) {
        elementService.deleteElement(id);
    }

    @PostMapping("/createPdf")
    public void createPdf(@RequestBody PdfDto pdfDto) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Estimation.pdf"));
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            String[] chunks = pdfDto.toString().split("\n");

            for (String s: chunks){
                Paragraph paragraph = new Paragraph(s, font);
                document.add(paragraph);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        document.close();

        try {
            System.out.println(sendSimpleMessage());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static JsonNode sendSimpleMessage() throws UnirestException {
        String API_KEY = "7ce5553b97421d93cceb3ff94a73bd1c-1d8af1f4-cbad33b3";
        String YOUR_DOMAIN_NAME = "sandbox1a210c1d29654f9fa3acde4837b8d072.mailgun.org";
        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + YOUR_DOMAIN_NAME + "/messages")
                .basicAuth("api", API_KEY)
                .field("from", "zatezalo123@gmail.com")
                .field("to", "zatezalojovan00@gmail.com")
                .field("subject", "Estimation")
                .field("text", "This is our estimation for you order")
                .field("attachment", new File("Estimation.pdf"))
                .asJson();

        return request.getBody();
    }
}

