package be.condorcet.demo11.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APICOMFACT", schema = "ORA30", catalog = "ORCL.CONDORCET.BE")
public class Comfact {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comfact_generator")
    @SequenceGenerator(name="comfact_generator", sequenceName = "APICOMFACT_SEQ", allocationSize=1)
    private Integer idcommande;
    private Integer numfact;
   @NonNull
    private Date datecommande;
   @NonNull
    private String etat;
   @NonNull
    private BigDecimal montant;
   @NonNull
    @ManyToOne @JoinColumn(name = "IDCLIENT")
    private Client client;

    public boolean estEnRetard() {
        LocalDate maintenant = LocalDate.now();
        return maintenant.isAfter(datecommande.toLocalDate().plusDays(15));
    }
}
