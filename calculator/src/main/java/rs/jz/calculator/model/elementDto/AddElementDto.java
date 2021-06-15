package rs.jz.calculator.model.elementDto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class AddElementDto {
    @NotNull
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private String image;

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
