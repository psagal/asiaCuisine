package pl.coderslab.dish.taste;

import org.springframework.stereotype.Service;


@Service
public class TasteService {
    private final TasteRepository tasteRepository;

    public TasteService(TasteRepository tasteRepository) {
        this.tasteRepository = tasteRepository;
    }

    // Mapping to DTO
    public TasteDTO convertTasteToDTO(Taste taste) {
        return TasteDTO.builder()
                .dominantTastes(taste.getDominantTastes())
                .spiciness(taste.getSpiciness())
                .build();
    }

    // Mapping from DTO
    public Taste convertToTaste(TasteDTO tasteDTO) {
        Taste taste = new Taste();
        taste.setDominantTastes(tasteDTO.getDominantTastes());
        taste.setSpiciness(tasteDTO.getSpiciness());
        return taste;
    }

}
