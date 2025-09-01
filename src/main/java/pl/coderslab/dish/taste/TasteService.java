package pl.coderslab.dish.taste;

import org.springframework.stereotype.Service;

@Service
public class TasteService {
    private final TasteRepository tasteRepository;

    public TasteService(TasteRepository tasteRepository) {
        this.tasteRepository = tasteRepository;
    }

}
