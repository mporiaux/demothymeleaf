package be.condorcet.demo11;

import be.condorcet.demo11.entities.Client;
import be.condorcet.demo11.entities.Comfact;
import be.condorcet.demo11.repositories.ClientRepository;
import be.condorcet.demo11.repositories.ComfactRepository;
import be.condorcet.demo11.services.ClientServiceImpl;
import be.condorcet.demo11.services.ComfactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/comfacts")
public class GestComfact {

/*    @Autowired
    private ComfactRepository comfactRepository;

    @Autowired
    private ClientRepository clientRepository;*/

    @Autowired
    private ClientServiceImpl clientServiceImpl;
    @Autowired
    private ComfactServiceImpl comfactServiceImpl;

    @RequestMapping("/rechparcli")
    public String read(@RequestParam int idclient, Map<String, Object> model) {
        System.out.println("recherche du client n° " + idclient);
        try {
          /*  Optional<Client> ocl = clientRepository.findById(idclient);//findById lance une exception si id inconnu
            Client cl = ocl.get();

            List<Comfact> lcf = comfactRepository.findComfactByClient(cl);*/
             Client cl = clientServiceImpl.read(idclient);
             List<Comfact> lcf = comfactServiceImpl.getComfacts(cl);
            model.put("moncli",cl);
            model.put("clicf", lcf);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "affclicf";
    }
}

