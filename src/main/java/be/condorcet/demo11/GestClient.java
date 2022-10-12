package be.condorcet.demo11;

import be.condorcet.demo11.entities.Client;
import be.condorcet.demo11.entities.Comfact;
import be.condorcet.demo11.repositories.ClientRepository;
import be.condorcet.demo11.services.ClientServiceImpl;
import be.condorcet.demo11.services.ComfactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class GestClient {

   /*  @Autowired
        private ClientRepository clientRepository;*/

    @Autowired
     private ClientServiceImpl clientServiceImpl;

    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model){
        System.out.println("recherche clients");
        try {
           // Collection<Client> lcl= clientRepository.findAll();
            Collection<Client> lcl= clientServiceImpl.all();
            model.put("mesClients", lcl);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "affichagetousClients";
    }

    @RequestMapping("/create")
    public String create(@RequestParam String nom,@RequestParam String prenom,@RequestParam String tel, Map<String, Object> model){
        System.out.println("création de client");
        Client cl = new Client(nom,prenom,tel);
        try {
             clientServiceImpl.create(cl);//mise à jour du client avec son id par l'environnement
            cl = clientServiceImpl.read(cl.getIdclient());
            cl.setCp(1000);
            clientServiceImpl.update(cl);
            model.put("nouvcli",cl);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la création-------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "nouveauClient";
    }

    @RequestMapping("/read")
    public String read(@RequestParam int idclient, Map<String, Object> model){
        System.out.println("recherche du client n° "+idclient);
           try {
               Client cl = clientServiceImpl.read(idclient);
               cl.getComfacts().add(new Comfact(Date.valueOf(LocalDate.now()),"F",BigDecimal.valueOf(1000),cl));
               clientServiceImpl.update(cl);
               model.put("moncli",cl);
           }catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "affclient";
    }

}
