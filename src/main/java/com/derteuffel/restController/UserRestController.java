package com.derteuffel.restController;

import com.derteuffel.data.User;
import com.derteuffel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by derteuffel on 07/01/2019.
 */
@RestController
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Collection<User> list(){
        return userRepository.findAllByOrderByUserIdDesc();
    }

    //users visitor management function

    @GetMapping("/chiefs")
    public List<User> chiefs() {
        List<User> users= userRepository.findAllByActiveOrderByUserIdDesc(null);
        List<User> users1= userRepository.findAllByCategory("Chef des travaux");
        List<User> chiefs= new ArrayList<>();
        for (User user : users){
            for (int i=0; i<users1.size(); i++){
                if (user.getUserId().equals(users1.get(i).getUserId())){
                    chiefs.add(user);
                }
            }
        }
        System.out.println(chiefs);
        return chiefs;
    }

    @GetMapping("/assistants")
    public List<User> assistants() {
        List<User> users= userRepository.findAllByActiveOrderByUserIdDesc(null);
        List<User> users1= userRepository.findAllByCategory("Assistant");
        List<User> assistants= new ArrayList<>();
        for (User user : users){
            for (int i=0; i<users1.size(); i++){
                if (user.getUserId().equals(users1.get(i).getUserId())){
                    assistants.add(user);
                }
            }
        }
        System.out.println(assistants);
        return assistants;
    }
    @GetMapping("/professeurs")
    public List<User> professors() {
        List<User> users= userRepository.findAllByActiveOrderByUserIdDesc(null);
        List<User> users1= userRepository.findAllByCategory("Professeur");
        List<User> professeurs= new ArrayList<>();
        for (User user : users){
            for (int i=0; i<users1.size(); i++){
                if (user.getUserId().equals(users1.get(i).getUserId())){
                    professeurs.add(user);
                }
            }
        }
        System.out.println(professeurs);
        return professeurs;
    }
    @GetMapping("/primaries")
    public List<User> primaries() {
        List<User> users= userRepository.findAllByActiveOrderByUserIdDesc(null);
        List<User> users1= userRepository.findAllByCategory("Primaire");
        List<User> primaries= new ArrayList<>();
        for (User user : users){
            for (int i=0; i<users1.size(); i++){
                if (user.getUserId().equals(users1.get(i).getUserId())){
                    primaries.add(user);
                }
            }
        }
        System.out.println(primaries);
        return primaries;
    }
    @GetMapping("/secondaries")
    public List<User> secondary() {
        List<User> users= userRepository.findAllByActiveOrderByUserIdDesc(null);
        List<User> users1= userRepository.findAllByCategory("Secondaire");
        List<User> secondaries= new ArrayList<>();
        for (User user : users){
            for (int i=0; i<users1.size(); i++){
                if (user.getUserId().equals(users1.get(i).getUserId())){
                    secondaries.add(user);
                }
            }
        }
        System.out.println(secondaries);
        return secondaries;
    }
    @GetMapping("/student")
    public List<User> students() {
        List<User> users= userRepository.findAllByActiveOrderByUserIdDesc(null);
        List<User> users1= userRepository.findAllByCategory("Etudiant");
        List<User> students= new ArrayList<>();
        for (User user : users){
            for (int i=0; i<users1.size(); i++){
                if (user.getUserId().equals(users1.get(i).getUserId())){
                    students.add(user);
                }
            }
        }
        System.out.println(students);
        return students;
    }

    @GetMapping("/master")
    public List<User> master() {
        List<User> users= userRepository.findAllByActiveOrderByUserIdDesc(null);
        List<User> users1= userRepository.findAllByDiplomOrderByUserIdDesc("Master1&2");
        List<User> userList= new ArrayList<>();
        for (User user: users){
            for (int i=0; i< users1.size(); i++){
                if (user.getUserId().equals(users1.get(i).getUserId())){
                    userList.add(user);
                }
            }
        }
        return userList;
    }

    @GetMapping("/doctorat")
    public List<User> doctorat() {
        List<User> users= userRepository.findAllByActiveOrderByUserIdDesc(null);
        List<User> users1= userRepository.findAllByDiplomOrderByUserIdDesc("Phd/Doctorat");
        List<User> userList= new ArrayList<>();
        for (User user: users){
            for (int i=0; i< users1.size(); i++){
                if (user.getUserId().equals(users1.get(i).getUserId())){
                    userList.add(user);
                }
            }
        }
        return userList;
    }

}
