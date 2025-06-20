package com.duoc.edutech.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.duoc.edutech.controller.UserControllerV2;
import com.duoc.edutech.model.User;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>>{
    @Override
    public EntityModel<User> toModel(User entity){
        return EntityModel.of(entity,
        linkTo(methodOn(UserControllerV2.class).getUser(entity.getUsername())).withSelfRel(),
        linkTo(methodOn(UserControllerV2.class).fetchUsers()).withRel("user")
        );
    }
}
