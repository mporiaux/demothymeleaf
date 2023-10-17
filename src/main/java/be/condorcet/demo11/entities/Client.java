package be.condorcet.demo11.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APICLIENT", schema = "ORA30", catalog = "ORCL.CONDORCET.BE")
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
    @SequenceGenerator(name="client_generator", sequenceName = "APICLIENT_SEQ", allocationSize=1)
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

    @JsonIgnore
    // @OneToMany(mappedBy = "client" , fetch = FetchType.EAGER)
  // @OneToMany(mappedBy = "client" , fetch = FetchType.LAZY,cascade=CascadeType.ALL, orphanRemoval=true)
   @OneToMany(mappedBy = "client")
   //LAZY est la version par défaut
   //cascadeType.ALL permet d'effacer en cascade si le client disparaît
   // orphanRemoval=true permet d'ajouter et supprimer des commandes en DB à partir de la liste
   @ToString.Exclude
    private List<Comfact> comfacts;


}




