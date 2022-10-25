package be.condorcet.demo11.services;

import be.condorcet.demo11.entities.Client;
import be.condorcet.demo11.entities.Comfact;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceStub implements InterfClientService{


    @Override
    public Client create(Client client) throws Exception{
        client.setIdclient(1);
        return client;
    }

    @Override
    public Client read(Integer id) throws Exception {
        Client cl = new Client(id,"NomTest","PrenomTest",1000,"LocTest","Rue Test","1","0001",new ArrayList<>());
        cl.getComfacts().add(new Comfact(1,1, Date.valueOf(LocalDate.now()),"C",new BigDecimal(1000),cl));
        return cl;
    }

    @Override
    public Client update(Client client) throws Exception{
      return client;
    }

    @Override
    public void delete(Client client) throws Exception {
    }



    @Override
    public List<Client> read(String nom) {
        List<Client>lc = new ArrayList<>();
        lc.add(new Client(1,nom,"PrenomTest",1000,"LocTest","Rue Test","1","0001",null));
        lc.add(new Client(2,nom,"PrenomTest2",2000,"LocTest2","Rue Test2","2","0002",null));
        return lc;
    }

    @Override
    public Client read(String nom, String prenom, String tel) {
        return null;
    }

    @Override
    public List<Client> all() throws Exception {

        List<Client>lc = new ArrayList<>();
        lc.add(new Client(1,"Durant","PrenomTest",1000,"LocTest","Rue Test","1","0001",null));
        lc.add(new Client(2,"Dupont","PrenomTest2",2000,"LocTest2","Rue Test2","2","0002",null));
        return lc;
    }
}
