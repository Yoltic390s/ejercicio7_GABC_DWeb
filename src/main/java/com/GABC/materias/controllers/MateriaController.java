package com.GABC.materias.controllers;

import com.GABC.materias.dto.MateriaDTO;
import com.GABC.materias.models.Materia;
import com.GABC.materias.services.MateriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/materias")
public class MateriaController {

    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping
    public String listarMaterias(Model model) {
        model.addAttribute("materias", materiaService.getAllMaterias());
        return "listado";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("materiaDTO", new MateriaDTO());
        return "formulario-crear";
    }

    @PostMapping("/guardar")
    public String guardarMateria(@ModelAttribute MateriaDTO materiaDTO) {
        Materia materia = new Materia(null, materiaDTO.getNombre(), materiaDTO.getCreditos());
        materiaService.addMateria(materia);
        return "redirect:/materias";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Materia> materiaOpt = materiaService.getMateriaById(id);
        if (materiaOpt.isPresent()) {
            Materia m = materiaOpt.get();
            MateriaDTO dto = new MateriaDTO();
            dto.setId(m.getId());
            dto.setNombre(m.getNombre());
            dto.setCreditos(m.getCreditos());
            model.addAttribute("materiaDTO", dto);
            return "formulario-actualizar";
        }
        return "redirect:/materias";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarMateria(@PathVariable Long id, @ModelAttribute MateriaDTO materiaDTO) {
        Materia materia = new Materia(id, materiaDTO.getNombre(), materiaDTO.getCreditos());
        materiaService.updateMateria(materia);
        return "redirect:/materias";
    }

    @GetMapping("/eliminar/{id}")
    public String mostrarFormularioEliminar(@PathVariable Long id, Model model) {
        Optional<Materia> materiaOpt = materiaService.getMateriaById(id);
        if (materiaOpt.isPresent()) {
            model.addAttribute("materia", materiaOpt.get());
            return "formulario-eliminiar";
        }
        return "redirect:/materias";
    }

    @PostMapping("/borrar/{id}")
    public String borrarMateria(@PathVariable Long id) {
        materiaService.deleteMateria(id);
        return "redirect:/materias";
    }
}