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
@Table(name = "API_COMFACT", schema = "ORA2", catalog = "XE")
public class Comfact {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comfact_generator")
    @SequenceGenerator(name="comfact_generator", sequenceName = "API_COMFACT_SEQ", allocationSize=1)
    private Integer numcommande;
    private Integer numfact;
   @NonNull
    private Date datecom;
   @NonNull
    private String etat;
   @NonNull
    private BigDecimal montant;
   @NonNull
    @ManyToOne @JoinColumn(name = "IDCLIENT")
    private Client client;

    public boolean estEnRetard() {
        LocalDate maintenant = LocalDate.now();
        return maintenant.isAfter(datecom.toLocalDate().plusDays(15));
    }
}
