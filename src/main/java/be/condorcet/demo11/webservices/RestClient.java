package be.condorcet.demo11.webservices;

import be.condorcet.demo11.entities.Client;
import be.condorcet.demo11.services.InterfClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/clients")
public class RestClient {
    @Autowired
    private InterfClientService myClientServiceImpl;

    //-------------------Retrouver le client correspondant à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> getClient(@PathVariable(value = "id") int id) throws Exception {
        System.out.println("recherche du client d' id " + id);
        Client client = myClientServiceImpl.read(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    //-------------------Retrouver les clients portant un nom donné--------------------------------------------------------
    @RequestMapping(value = "/nom={nom}", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> listClientsNom(@PathVariable(value = "nom") String nom) throws Exception {
        System.out.println("recherche de " + nom);
        List<Client> clients;
        clients = myClientServiceImpl.read(nom);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    //-------------------Retrouver le client correspondant à un triplet (nom,prénom,tel) unique donné--------------------------------------------------------
    @RequestMapping(value = "/{nom}/{prenom}/{tel}", method = RequestMethod.GET)
    public ResponseEntity<Client> getClientUnique(@PathVariable(value = "nom") String nom,
                                                  @PathVariable(value = "prenom") String prenom,
                                                  @PathVariable(value = "tel") String tel) throws Exception {
        System.out.println("recherche du client " + nom + " " + prenom + " " + tel);
        Client client = myClientServiceImpl.read(nom, prenom, tel);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    //-------------------Créer un client--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Client> createClient(@RequestBody Client client) throws Exception {
        System.out.println("Création de Client " + client.getNom());
        myClientServiceImpl.create(client);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    //-------------------Mettre à jour un client d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Client> majClient(@PathVariable(value = "id") int id, @RequestBody Client nouvcli) throws Exception {
        System.out.println("maj de client id =  " + id);
        nouvcli.setIdclient(id);
        Client clact = myClientServiceImpl.update(nouvcli);
        return new ResponseEntity<>(clact, HttpStatus.OK);
    }

    //-------------------Effacer un client d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteClient(@PathVariable(value = "id") int id) throws Exception {
        System.out.println("effacement du client d'id " + id);
        Client client = myClientServiceImpl.read(id);
        myClientServiceImpl.delete(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Retrouver tous les clients --------------------------------------------------------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> listClient() throws Exception {
        System.out.println("recherche de tous les clients");
        return new ResponseEntity<>(myClientServiceImpl.all(), HttpStatus.OK);
    }

    //-------------------Retrouver tous les clients triés et par page--------------------------------------------------------
    @RequestMapping(value = "/allp", method = RequestMethod.GET)
    public ResponseEntity<Page<Client>> listClient(Pageable pageable) throws Exception {
        System.out.println("recherche de tous les clients");
        return new ResponseEntity<>(myClientServiceImpl.allp(pageable), HttpStatus.OK);
    }
}
    //-------------------Gérer les erreurs--------------------------------------------------------

