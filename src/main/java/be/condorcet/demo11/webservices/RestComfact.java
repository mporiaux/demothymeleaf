/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.condorcet.demo11.webservices;

import be.condorcet.demo11.entities.Client;
import be.condorcet.demo11.entities.Comfact;
import be.condorcet.demo11.services.InterfClientService;
import be.condorcet.demo11.services.InterfComfactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/comfacts")
public class RestComfact {

    @Autowired
    private InterfComfactService comfactServiceImpl;
    @Autowired
    private InterfClientService clientServiceImpl;

    //-------------------Retrouver la commande correspondant à un n° donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Comfact> getComfact(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche de la commande n° " + id);
        Comfact cf = comfactServiceImpl.read(id);
        return new ResponseEntity<>(cf, HttpStatus.OK);
    }

    //-------------------Retrouver la commande correspondant à un n° donné--------------------------------------------------------
    @RequestMapping(value = "/idclient={id}", method = RequestMethod.GET)
    public ResponseEntity<List<Comfact>> getComfactClient(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche des commandes du client d'id " + id);
        Client cl = clientServiceImpl.read(id);
        List<Comfact> lcf = comfactServiceImpl.getComfacts(cl);
        return new ResponseEntity<>(lcf, HttpStatus.OK);
    }

    //-------------------Créer une commande--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Comfact> createComfact(@RequestBody Comfact cf) throws Exception {
        System.out.println("Création de la commande du client" + cf.getClient());
        comfactServiceImpl.create(cf);
        return new ResponseEntity<>(cf, HttpStatus.OK);
    }

    //-------------------Mettre à jour une commande d'un n° donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
     public ResponseEntity<Comfact> majClient(@PathVariable(value = "id") int id,@RequestBody Comfact nouvcf) throws Exception{
        System.out.println("maj de la commade n° " + id);
        nouvcf.setNumcommande(id);
        Comfact cfact = comfactServiceImpl.update(nouvcf);
        return new ResponseEntity<>(cfact, HttpStatus.OK);
    }

    //-------------------Effacer une commande d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteComfact(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement de la commande n°" + id);
        Comfact cf = comfactServiceImpl.read(id);
        comfactServiceImpl.delete(cf);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}
