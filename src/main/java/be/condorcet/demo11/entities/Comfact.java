package be.condorcet.demo11.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "API_COMFACT", schema = "ORA2", catalog = "XE")
public class Comfact {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comfact_generator")
    @SequenceGenerator(name="comfact_generator", sequenceName = "API_COMFACT_SEQ", allocationSize=1)
    private Integer numcommande;
    private Integer numfact;
    private Date datecom;
    private String etat;
    private BigDecimal montant;
    @ManyToOne @JoinColumn(name = "IDCLIENT")
    private Client client;
}
