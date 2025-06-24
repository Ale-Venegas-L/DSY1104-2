package com.duoc.edutech.controller;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.duoc.edutech.assemblers.MaterialModelAssembler;
import com.duoc.edutech.model.Material;
import com.duoc.edutech.repository.MaterialRepo;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v2/materials")
@Tag(
    name = "Materiales V2",
    description = "Operaciones relacionadas a los materiales, con HateOAS incluído"
)
public class MaterialControllerV2 {
    @Autowired
    private MaterialRepo materialRepo;

    @Autowired
    private MaterialModelAssembler assembler;

    @Operation(summary = "Obtener todos los materiales", description = "Busca y obtiene una lista con todos los materiales registrados")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Materiales encontrados"),
        @ApiResponse(responseCode = "404", description = "Materiales no encontrados")
    })
    public CollectionModel<EntityModel<Material>> fetchMaterials(){
        List<EntityModel<Material>> materiales = materialRepo.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(materiales,
        linkTo(methodOn(MaterialControllerV2.class).fetchMaterials()).withSelfRel());
    }

    @Operation(summary = "Obtener un material", description = "Busca por su código y obtiene un material.")
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Material encontrado"),
        @ApiResponse(responseCode = "404", description = "Material no encontrado")
    })
    public EntityModel<Material> getMaterial(@PathVariable String id){
        Material material = materialRepo.findById(id).orElse(null);
        return assembler.toModel(material);
    }

    @Operation(summary = "Actualiza un material", description = "Actualiza todos los datos de un material, buscando por su nombre")
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Material actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en formato"),
        @ApiResponse(responseCode = "404", description = "Material no encontrado")
    })
    public ResponseEntity<EntityModel<Material>> updateMaterial(@PathVariable String id, @RequestBody Material material){
        material.setId(id);
        Material updateMaterial = materialRepo.save(material);
        return ResponseEntity.ok(assembler.toModel(updateMaterial));
    }

    @Operation(summary = "Guardar nuevo material", description = "Crea y registra un nuevo material")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Material guardado"),
        @ApiResponse(responseCode = "400", description = "Error en formato")
    })
    public ResponseEntity<EntityModel<Material>> createMaterial(@RequestBody Material material){
        Material newMaterial = materialRepo.save(material);
        return ResponseEntity
        .created(linkTo(methodOn(MaterialControllerV2.class)
        .getMaterial(newMaterial.getId())).toUri()).body(assembler.toModel(newMaterial));
    }

    @Operation(summary = "Elimina un material", description = "Elimina un material, buscando por su código")
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en formato"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<?> deleteMaterial(@PathVariable String id){
        materialRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
