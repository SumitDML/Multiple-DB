package com.example.demo.note.repository;



import com.example.demo.note.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS)
public interface NoteRepository extends JpaRepository<Note, String> {

}