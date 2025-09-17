package pl.coderslab.users;

import org.springframework.stereotype.Service;
import pl.coderslab.exceptions.UserNotFoundException;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User " + id + " not found"));
    }
}
