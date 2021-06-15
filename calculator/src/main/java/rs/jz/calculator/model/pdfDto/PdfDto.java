package rs.jz.calculator.model.pdfDto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import rs.jz.calculator.model.elementDto.PdfElement;

import java.util.List;

@Getter
@Setter
public class PdfDto {
    @NotNull
    private Double sum;

    @NotNull
    private String description;

    @NotNull
    private List<PdfElement> elements;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        //builder.append("# Your estimation");
        builder.append(getDescription() + "\n");
        for (PdfElement element: getElements()) {
            builder.append(element.toString());
        }
        builder.append(" \n");
        builder.append("The final sum is " + getSum());
        //System.out.println(builder.toString());
        return builder.toString();
    }

    public Double getSum() {
        return sum;
    }

    public List<PdfElement> getElements() {
        return elements;
    }

    public String getDescription() {
        return description;
    }
}
