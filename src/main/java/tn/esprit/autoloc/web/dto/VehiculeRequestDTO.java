package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;

import java.math.BigDecimal;

/**
 * DTO d'entrée (POST / PUT) : seuls les champs que le client a le droit d'envoyer.
 * Ni id, ni relations (agence, maintenances, équipements, réservations).
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeRequestDTO {

    @NotBlank
    @Size(max = 20)
    private String immatriculation;

    @NotBlank
    @Size(max = 50)
    private String marque;

    @NotBlank
    @Size(max = 50)
    private String modele;

    @NotNull
    private CategorieVehicule categorie;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal tarifJournalier;

    @NotNull
    private StatutVehicule statut;
}
