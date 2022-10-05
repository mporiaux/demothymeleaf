package be.condorcet.demo11;

import be.condorcet.demo11.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Demo11Application {
    @Autowired
    private ClientRepository clientRepository;
    public static void main(String[] args) {
        SpringApplication.run(Demo11Application.class, args);
    }
}
