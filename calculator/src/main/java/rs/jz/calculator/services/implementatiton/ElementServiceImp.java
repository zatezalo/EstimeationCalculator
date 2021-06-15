package rs.jz.calculator.services.implementatiton;

import org.springframework.stereotype.Service;
import rs.jz.calculator.exeptions.ApiRequestException;
import rs.jz.calculator.model.Element;
import rs.jz.calculator.model.elementDto.AddElementDto;
import rs.jz.calculator.repositories.ElementRepository;
import rs.jz.calculator.services.ElementService;

import java.util.List;

@Service
public class ElementServiceImp implements ElementService {
    private final ElementRepository elementRepository;

    public ElementServiceImp(ElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }

    @Override
    public Element getElementById(Long id) {
        return elementRepository.findById(id).get();
    }

    @Override
    public List<Element> getAllElements() {
        return elementRepository.findAll();
    }

    @Override
    public void addElement(AddElementDto addElementDto) {
        Element testElement = elementRepository.findByName(addElementDto.getName());
        if (testElement != null) {
            throw new ApiRequestException("That element with the name " + addElementDto.getName() + " already exists!!!");
        } else {
            Element element = new Element();
            element.setName(addElementDto.getName());
            element.setPrice(addElementDto.getPrice());
            element.setImage(addElementDto.getImage());
            elementRepository.save(element);
        }
    }

    @Override
    public void editElement(Element element) {
        Element currentElement = elementRepository.findById(element.getId()).get();
        Element testElement  = elementRepository.findByName(element.getName());

        if (testElement != null && !testElement.getId().equals(currentElement.getId())) {
            throw new ApiRequestException("That element with the name " + currentElement.getName() + " already exists!!!");
        } else {
            currentElement.setName(element.getName());
            currentElement.setPrice(element.getPrice());
            currentElement.setImage(element.getImage());
            elementRepository.save(currentElement);
        }
    }

    @Override
    public void deleteElement(Long id) {
        elementRepository.deleteById(id);
    }
}
