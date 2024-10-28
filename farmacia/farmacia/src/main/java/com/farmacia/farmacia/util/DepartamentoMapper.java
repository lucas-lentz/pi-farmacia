package com.farmacia.farmacia.util;

import java.util.List; // Importa a classe List
import java.util.stream.Collectors; // Importa a classe Collectors
import com.farmacia.farmacia.dto.DepartamentoDTO;
import com.farmacia.farmacia.entities.Departamento;

public class DepartamentoMapper {

    public static DepartamentoDTO toDtoCreate(Departamento departamento) {
        DepartamentoDTO departamentoDTO = new DepartamentoDTO(departamento.getId(), departamento.getNome());
        return departamentoDTO;
    }

    public static Departamento toEntityCreate(DepartamentoDTO departamentoDTO) {
        Departamento departamento = new Departamento(departamentoDTO.getId(), departamentoDTO.getNome());
        return departamento;
    }

    public static DepartamentoDTO toDtoUpdate(Departamento departamento) {
        return new DepartamentoDTO(departamento.getId(), departamento.getNome());
    }

    public static Departamento toEntityUpdate(Departamento departamento, DepartamentoDTO departamentoDTO) {
        departamento.setNome(departamentoDTO.getNome());
        return departamento;
    }

    public static Departamento toEntityUpdate(DepartamentoDTO departamentoDTO) {
        Departamento departamento = new Departamento(departamentoDTO.getNome());
        return departamento;
    }

    // Método para converter uma lista de Departamento para uma lista de DepartamentoDTO
    public static List<DepartamentoDTO> toDtoList(List<Departamento> departamentos) {
        return departamentos.stream()
                           .map(DepartamentoMapper::toDtoCreate) // Usando o método toDtoCreate para cada elemento
                           .collect(Collectors.toList()); // Coleta os resultados em uma lista
    }
}
