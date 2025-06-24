package com.duoc.edutech.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.duoc.edutech.controller.InstructorControllerV2;
import com.duoc.edutech.model.Instructor;

@Component
public class InstructorModelAssembler implements RepresentationModelAssembler<Instructor, EntityModel<Instructor>>{
    @SuppressWarnings("null")
    @Override
    public EntityModel<Instructor> toModel(Instructor entity){
        return EntityModel.of(entity,
        linkTo(methodOn(InstructorControllerV2.class).fetchInstructors()).withRel("instructor"));
    }

}
