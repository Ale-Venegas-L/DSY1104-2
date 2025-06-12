package com.duoc.edutech;

import com.duoc.edutech.model.*;
import com.duoc.edutech.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private InstructorRepo instructorRepo;

    @Autowired
    private CursoRepo cursoRepo;

    @Autowired
    private MaterialRepo materialRepo;

    @Override
    public void run(String... args) throws Exception{
        Faker faker = new Faker();
        Random random = new Random();

        //user
        for (int i = 0; i < 50; i++) {
            User usr = new User();
            usr.setUsername(faker.internet().username());
            usr.setEmail(faker.internet().emailAddress());
            usr.setPassword(faker.bothify("?#?#?#?#"));
            usr.setNumcel(faker.number().numberBetween(900000000, 999999999));
            userRepo.save(usr);
        }

        //instructor
        for (int i = 0; i < 10; i++) {
            Instructor inst = new Instructor();
            inst.setPNombre(faker.name().firstName());
            inst.setSNombre(faker.name().firstName());
            inst.setPApellido(faker.name().lastName());
            inst.setMApellido(faker.name().lastName());
            inst.setTitulo(faker.university().degree());
            inst.setNumcel(faker.number().numberBetween(900000000, 999999999));
            inst.setEmailInst(faker.internet().emailAddress());
            inst.setCursos(null);
            instructorRepo.save(inst);
        }
        List<Instructor> instructors = instructorRepo.findAll();
        
        //curso
        for (int i = 0; i < 10; i++) {
            Curso curso = new Curso();
            curso.setIdCurso(faker.bothify("???###"));
            curso.setNombreCurso(faker.computer().type());
            curso.setCosto(faker.number().numberBetween(500, 100000));
            curso.setDuracion(random.nextFloat());
            curso.setInstructor(instructors.get(random.nextInt(instructors.size())));
            cursoRepo.save(curso);
        }

        List<Curso> cursos = cursoRepo.findAll();
        
        //material
        for (int i = 0; i < 20; i++) {
            Material material = new Material();
            material.setCurso(cursos.get(random.nextInt(cursos.size())));
            material.setId(faker.bothify("???####"));
            materialRepo.save(material);
        }
    }

}
