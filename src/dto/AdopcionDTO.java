package dto;

import java.time.LocalDate;

public record AdopcionDTO (int idAdopcion, String nombre, String tipo, String nombreAdoptante, LocalDate fechaAdopcion){
}
