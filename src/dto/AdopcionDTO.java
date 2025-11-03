package dto;

import java.time.LocalDate;

/**
 * DTO inmutable el cual representa una adopción que contiene datos combinados de las tablas
 * adopción y animal.
 *
 * @param idAdopcion
 * @param nombre
 * @param tipo
 * @param nombreAdoptante
 * @param fechaAdopcion
 */
public record AdopcionDTO (int idAdopcion, String nombre, String tipo, String nombreAdoptante, LocalDate fechaAdopcion){ }
