package be.condorcet.demo11.entities;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "API_CLIENT", schema = "ORA2", catalog = "XE")
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
    @SequenceGenerator(name="client_generator", sequenceName = "API_CLIENT_SEQ", allocationSize=1)
    private Integer idclient;
    @NonNull
    private String nom;
    @NonNull
    private String prenom;
    private Integer cp;
    private String localite;
    private String rue;
    private String num;
    @NonNull
    private String tel;
    // @OneToMany(mappedBy = "client" , fetch = FetchType.EAGER)
  // @OneToMany(mappedBy = "client" , fetch = FetchType.LAZY,cascade=CascadeType.ALL, orphanRemoval=true)
   @OneToMany(mappedBy = "client")
   //LAZY est la version par défaut
   //cascadeType.ALL permet d'effacer en cascade si le client disparaît
   // orphanRemoval=true permet d'ajouter et supprimer des commandes en DB à partir de la liste
   @ToString.Exclude
    private List<Comfact> comfacts;

    /**
     * commandes en cours
     * @return commandes en cours
     */
    public List<Comfact> commandesEnCours(){
        List<Comfact> lEnCours= new ArrayList<>();
        for(Comfact cf : comfacts){
            if(cf.getEtat().equals("C")) lEnCours.add(cf);
        }
        return  lEnCours;
    }

    /**
     * factures non payees
     * @return factures non payees
     */

    public List<Comfact> facturesNonPayees(){
        List<Comfact> lNonPayees= new ArrayList<>();
        for(Comfact cf : comfacts){
            if(cf.getEtat().equals("F")) lNonPayees.add(cf);
        }
        return  lNonPayees;
    }

    /**
     * factures en retard
     * @return factures en retard
     */
    public List<Comfact> facturesRetard(){
        List<Comfact> lNonPayees= facturesNonPayees();
        List<Comfact> lRetard = new ArrayList<>();
        for(Comfact cf : lNonPayees){
            if(cf.estEnRetard()) lRetard.add(cf);
        }
        return  lRetard;
    }


    /**
     * factures payees
     * @return fatures payees
     */
    public List<Comfact> facturesPayees(){
        List<Comfact> lPayees= new ArrayList<>();
        for(Comfact cf : comfacts){
            if(cf.getEtat().equals("P")) lPayees.add(cf);
        }
        return  lPayees;
    }

    /**
     * ajout d'une commande
     * @param cf Comfact
     */
    public void addComfact(Comfact cf){
        comfacts.add(cf);
        cf.setClient(this);
    }

    /**
     * suppression d'une commande
     * @param cf Comfact
     */
    public void supComfact(Comfact cf){
        comfacts.remove(cf);
        cf.setClient(null);
    }

}




