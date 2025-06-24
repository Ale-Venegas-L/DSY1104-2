package com.duoc.edutech.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.duoc.edutech.controller.MaterialControllerV2;
import com.duoc.edutech.model.Material;

@Component
public class MaterialModelAssembler implements RepresentationModelAssembler<Material, EntityModel<Material>>{
    @SuppressWarnings("null")
    @Override
    public EntityModel<Material> toModel(Material entity){
        return EntityModel.of(entity,
            linkTo(methodOn(MaterialControllerV2.class).getMaterial(entity.getId())).withSelfRel(),
            linkTo(methodOn(MaterialControllerV2.class).fetchMaterials()).withRel("material")
        );
    }

}
