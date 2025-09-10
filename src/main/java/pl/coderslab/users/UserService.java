package pl.coderslab.users;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CO JESLI NIE BEDZIE TAKIEGO ID?????
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
