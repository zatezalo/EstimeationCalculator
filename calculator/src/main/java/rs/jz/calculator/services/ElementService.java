package rs.jz.calculator.services;

import rs.jz.calculator.model.Element;
import rs.jz.calculator.model.elementDto.AddElementDto;

import java.util.List;

public interface ElementService {
    Element getElementById(Long id);
    List<Element> getAllElements();
    void addElement(AddElementDto addElementDto);
    void editElement(Element element);
    void deleteElement(Long id);
}
