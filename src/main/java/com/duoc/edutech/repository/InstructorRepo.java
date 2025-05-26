package com.duoc.edutech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.duoc.edutech.model.Instructor;


@Repository
public interface InstructorRepo extends JpaRepository<Instructor, String>{
    Instructor findByEmailInst(String emailInst);
}
