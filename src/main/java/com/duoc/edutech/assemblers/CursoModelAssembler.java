package com.duoc.edutech.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.duoc.edutech.controller.CursoControllerV2;
import com.duoc.edutech.model.Curso;

@Component
public class CursoModelAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>>{
    @SuppressWarnings("null")
    @Override
    public EntityModel<Curso> toModel(Curso entity){
        return EntityModel.of(entity,
        linkTo(methodOn(CursoControllerV2.class).getCurso(entity.getIdCurso())).withSelfRel(),
        linkTo(methodOn(CursoControllerV2.class).fetchCursos()).withRel("curso")
        );
    }
}