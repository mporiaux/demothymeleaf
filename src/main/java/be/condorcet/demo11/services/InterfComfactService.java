package be.condorcet.demo11.services;

import be.condorcet.demo11.entities.Comfact;
import be.condorcet.demo11.entities.Client;

import java.util.List;

public interface InterfComfactService extends InterfService<Comfact> {
    public List<Comfact> getComfacts(Client cl);
}
