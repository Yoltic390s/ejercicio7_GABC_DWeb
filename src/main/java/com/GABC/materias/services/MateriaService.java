package com.GABC.materias.services;

import com.GABC.materias.models.Materia;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MateriaService {
    private final List<Materia> materias = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Materia> getAllMaterias() {
        return materias;
    }

    public Optional<Materia> getMateriaById(Long id) {
        return materias.stream().filter(m -> m.getId().equals(id)).findFirst();
    }

    public void addMateria(Materia materia) {
        materia.setId(counter.getAndIncrement());
        materias.add(materia);
    }

    public void updateMateria(Materia materiaActualizada) {
        getMateriaById(materiaActualizada.getId()).ifPresent(materia -> {
            materia.setNombre(materiaActualizada.getNombre());
            materia.setCreditos(materiaActualizada.getCreditos());
        });
    }

    public void deleteMateria(Long id) {
        materias.removeIf(m -> m.getId().equals(id));
    }
}