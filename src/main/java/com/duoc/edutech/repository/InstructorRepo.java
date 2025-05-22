package com.duoc.edutech.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.duoc.edutech.model.Instructor;

@Repository
public class InstructorRepo {
    private List<Instructor> instructores = new ArrayList<>();

    //mostrar todo
    public List<Instructor> mostrarInstructors(){
        return instructores;
    }

    //buscar por instructor
    public Instructor buscarInstructor(String mail){
        for (Instructor instructor : instructores) {
            if (instructor.getEmail().contains(mail)) {
                return instructor;
            }
        }
        return null;
    }

    //añadir
    public Instructor guardarInstructor(Instructor inst){
        instructores.add(inst);
        return inst;
    }

    //eliminar
    public void eliminar(String name){
        Instructor instr = buscarInstructor(name);
        if (instr!=null) {
            instructores.remove(instr);
        }
    }

    //modificar
    public Instructor actualizaInstructor(Instructor inst){
        Instructor instr = new Instructor();
        for (Instructor instructor : instructores) {
            if (instructor.getEmail()==inst.getEmail()) {
                instr.setEmail(inst.getEmail());
            }
        }

        instr.setNombreInst(inst.getNombreInst());
        instr.setNumcelInst(inst.getNumcelInst());
        instr.setTitulo(inst.getTitulo());
        return instr;
    }
}
