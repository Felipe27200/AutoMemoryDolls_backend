package com.proyecto.grupo_3.controller;

import com.proyecto.grupo_3.dto.CrearCartaDTO;
import com.proyecto.grupo_3.entity.Carta;
import com.proyecto.grupo_3.error_handling.exception.GeneralException;
import com.proyecto.grupo_3.response.BasicResponse;
import com.proyecto.grupo_3.service.AutoMemoryDollService;
import com.proyecto.grupo_3.service.CartaEstadoService;
import com.proyecto.grupo_3.service.CartaService;
import com.proyecto.grupo_3.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/cartas")
public class CartaController {
    private final CartaService cartaService;
    private final AutoMemoryDollService autoMemoryDollService;
    private final ClienteService clienteService;
    private final CartaEstadoService cartaEstadoService;

    @Autowired
    public CartaController(CartaService cartaService, AutoMemoryDollService autoMemoryDollService, ClienteService clienteService, CartaEstadoService cartaEstadoService) {
        this.cartaService = cartaService;
        this.autoMemoryDollService = autoMemoryDollService;
        this.clienteService = clienteService;
        this.cartaEstadoService = cartaEstadoService;
    }

    @PostMapping("/")
    public ResponseEntity<Carta> save (@Valid @RequestBody CrearCartaDTO cartaDTO)
    {
        Carta newCarta = this.convertirDto(cartaDTO);

        this.validarCarta(newCarta);

        Carta nuevoCarta = this.cartaService.save(newCarta);

        return new ResponseEntity<>(nuevoCarta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carta> update(@PathVariable Long id, @Valid @RequestBody CrearCartaDTO cartaDTO)
    {
        this.validarId(id);
        Carta carta = this.convertirDto(cartaDTO);

        return new ResponseEntity<>(this.cartaService.update(carta, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        this.validarId(id);

        String mensaje = this.cartaService.delete(id);
        BasicResponse response = new BasicResponse(mensaje, "successfull");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carta> findById(@PathVariable Long id)
    {
        this.validarId(id);

        return new ResponseEntity<>(this.cartaService.findByid(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Carta>> findAll()
    {
        return new ResponseEntity<>(this.cartaService.findAll(), HttpStatus.OK);
    }

    private void validarCarta(Carta carta)
    {
        if (carta.getContenido() == null || carta.getContenido().trim().isEmpty())
            throw new GeneralException("El contenido de la carta es requerido");
        if (carta.getMotivo() == null || carta.getMotivo().trim().isEmpty())
            throw new GeneralException("El motivo de la carta es requerido");
        if (carta.getCliente() == null || carta.getCliente().getId() <= 0)
            throw new GeneralException("El id del cliente es requerido");
        if (carta.getAutoMemoryDolls() == null || carta.getAutoMemoryDolls().getId() <= 0)
            throw new GeneralException("El id de la Auto Memory Doll es requerido");

        if (!carta.getAutoMemoryDolls().isEstado())
        {
            throw new GeneralException("La Memory Doll "
                    + carta.getAutoMemoryDolls().getNombre()
                    +" está inactiva");
        }

        if (this.autoMemoryDollService.countCartasProcesoByDoll(carta.getAutoMemoryDolls().getId()) > 5)
        {
            throw new GeneralException("La Doll "
                + carta.getAutoMemoryDolls().getNombre()
                + " cuenta con 5 o más cartas en proceso. Seleccione otra."
            );
        }
    }

    private void validarId(Long id)
    {
        if (id == null || id <= 0)
            throw new GeneralException("El id del carta debe ser mayor a cero");

    }

    private Carta convertirDto(CrearCartaDTO cartaDTO)
    {
        Carta carta = new Carta();

        carta.setMotivo(cartaDTO.getMotivo());
        carta.setContenido(cartaDTO.getContenido());

        carta.setCliente(this.clienteService.findByid(cartaDTO.getClienteId()));
        carta.setAutoMemoryDolls(this.autoMemoryDollService.findByid(cartaDTO.getAutoMemoryDollId()));
        carta.setCartaEstado(this.cartaEstadoService.findByid(cartaDTO.getCartaEstadoId()));

        this.validarCarta(carta);

        return carta;
    }
}
