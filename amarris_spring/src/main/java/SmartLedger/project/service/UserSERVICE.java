package SmartLedger.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SmartLedger.project.db.UserDB;
import SmartLedger.project.elasticSearch.document.UserDocument;
import SmartLedger.project.elasticSearch.repository.UserSearchRepository;
import SmartLedger.project.model.User;

/**
 * This class allows to do the bridge between the DB and the controller
 */
@Service
public class UserSERVICE {

    @Autowired
    private UserDB userDB; // MongoDB

    @Autowired
    private UserSearchRepository esRepository; // Elasticsearch

    public Iterable<User> getAllUsers() {
        return userDB.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userDB.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userDB.findByEmail(email);
    }

    public Optional<User> getUserByUsername(String username) {
        return userDB.findByUsername(username);
    }

    public User createUser(User user) {
        // Sauvegarde MongoDB
        User saved = userDB.save(user);

        // Synchronise Elasticsearch
        UserDocument doc = new UserDocument();
        doc.setId(saved.getId());
        doc.setUsername(saved.getUsername());
        doc.setEmail(saved.getEmail());
        esRepository.save(doc);

        return saved;
    }

    public boolean emailExists(String email) {
        return userDB.existsByEmail(email);
    }

    public boolean usernameExists(String username) {
        return userDB.existsByUsername(username);
    }

    public void deleteUser(String id) {
        userDB.deleteById(id);
        esRepository.deleteById(id);
    }

    public List<UserDocument> searchUsers(String term) {
        return esRepository.findByUsernameContainingOrEmailContaining(term, term);
    }
}