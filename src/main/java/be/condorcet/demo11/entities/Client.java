package be.condorcet.demo11.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

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
    @OneToMany(mappedBy = "client" , fetch = FetchType.LAZY) //LAZY est la version par défaut
    @ToString.Exclude
    private Collection<Comfact> comfacts;
}




