package com.farmacia.farmacia.util;

import com.farmacia.farmacia.dto.MarcaDTO;
import com.farmacia.farmacia.entities.Marca;

public class MarcaMapper {
    
    public static MarcaDTO toDtoCreate(Marca marca) {
        MarcaDTO marcaDTO = new MarcaDTO(marca.getId(), marca.getNome());
        return marcaDTO;
    }

    public static Marca toEntityCreate(MarcaDTO marcaDTO) {
        Marca marca = new Marca(marcaDTO.getId(), marcaDTO.getNome());
        return marca;
    }

    public static MarcaDTO toDtoUpdate(Marca marca) {
        MarcaDTO marcaDTO = new MarcaDTO(marca.getNome());
        return marcaDTO;
    }

    public static Marca toEntityUpdate(Marca marca, MarcaDTO marcaDTO) {
        marca.setNome(marcaDTO.getNome());
        return marca;
    }

    public static Marca toEntityUpdate(MarcaDTO marcaDTO) {
        Marca marca = new Marca(marcaDTO.getNome());
        return marca;
    }
}
