package be.condorcet.demo11.services;

import be.condorcet.demo11.entities.Client;

import java.util.List;

public interface  InterfClientService extends InterfService<Client> {
    public List<Client> read(String nom);
    public Client read(String nom, String prenom, String tel);
}

