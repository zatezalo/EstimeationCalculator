package rs.jz.calculator.model.elementDto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PdfElement {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String image;

    @NotNull
    private Double price;

    @NotNull
    private String description;

    @Override
    public String toString() {
        return "Item " + name + ", with a price of " + price + "$.\nDescription: " + description + "\n";
    }


}
