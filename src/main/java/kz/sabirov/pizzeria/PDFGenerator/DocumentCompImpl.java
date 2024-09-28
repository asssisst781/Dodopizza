package kz.sabirov.pizzeria.PDFGenerator;

import kz.sabirov.pizzeria.dto.OrderCustomDTO;
import kz.sabirov.pizzeria.dto.OrderDTO;
import kz.sabirov.pizzeria.entities.*;
import kz.sabirov.pizzeria.services.Implementations.UserServiceImpl;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Service
public class DocumentCompImpl implements DocumentComp{
    @Autowired
    private UserServiceImpl userService;

    Random random = new Random();
    @Override
    public void createPDF(String email, PaymentMethod pm, Pizza pizza, Long payment) {
        int eightDigitNumber = 10000000 + random.nextInt(90000000);
        String fileDest = "C:\\Users\\molin\\OneDrive\\Рабочий стол\\Pizzeria\\src\\main\\resources\\documents\\" + "pdfDocument" + eightDigitNumber + ".pdf";
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        User foundUser = userService.findUser(email);

        GeoLocation geoLocation =  foundUser.getGeoLocations();
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
            contentStream.beginText();

            contentStream.newLineAtOffset(100, 700);

            contentStream.showText("User Email: " + foundUser.getEmail());
            contentStream.newLineAtOffset(0, -15);

            contentStream.showText(String.format("Country of destination: %s, region: %s", geoLocation.getCountry(), geoLocation.getRegionName()));
            contentStream.newLineAtOffset(0, -15);

            contentStream.showText(String.format("latitude: %s, longitude: %s", geoLocation.getLat(), geoLocation.getLon()));
            contentStream.newLineAtOffset(0, -15);

            contentStream.showText(String.format("Timezone: %s", geoLocation.getTimezone()));
            contentStream.newLineAtOffset(0, -15);

            contentStream.showText(String.format("Title of order: %s", pizza.getTitle()));
            contentStream.newLineAtOffset(0, -15);

            String format = "Ingredients used: ";
            List<Product> productList = pizza.getProducts();
            contentStream.showText(formatIngredients(format, productList));
            contentStream.newLineAtOffset(0, -15);

            contentStream.showText(String.format("Price of order: %d", pizza.getPrice()));
            contentStream.newLineAtOffset(0, -15);

            contentStream.showText(String.format("Total paid: %d", payment));
            contentStream.newLineAtOffset(0, -15);

            contentStream.endText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                document.save(fileDest);
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private String formatIngredients(String format, List<Product> productList) {
        StringBuilder result = new StringBuilder(format);

        if (!productList.isEmpty()) {
            result.append(" ").append(productList.get(0).getName());
            for (int i = 1; i < productList.size(); i++) {
                result.append(", ").append(productList.get(i).getName());
            }
        }

        return result.toString();
    }
}
