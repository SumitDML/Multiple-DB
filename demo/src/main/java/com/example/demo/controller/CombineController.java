package com.example.demo.controller;

import com.example.demo.model.StudentDTO;
import com.example.demo.note.entity.Note;
import com.example.demo.model.NoteDTO;
import com.example.demo.model.Response;
import com.example.demo.note.repository.NoteRepository;
import com.example.demo.student.entity.Student;
import com.example.demo.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CombineController {

    @Autowired
    NoteRepository noteRepository;
    @Autowired
    StudentRepository studentRepository;

    @GetMapping(value = "/getData")
    public Response getResponse() {
        Response response = new Response();
        response.setStudents(studentRepository.findAll());
        response.setNotes(noteRepository.findAll());
        return response;
    }
    @PostMapping(value = "/addNote")
    public ResponseEntity saveNote(@RequestBody NoteDTO noteDTO) {
        Note note = new Note();
        note.setUserId(noteDTO.getUserId());
        note.setContent(noteDTO.getContent());
        Note note1 = noteRepository.save(note);
        return new ResponseEntity(note1, HttpStatus.OK);
    }

    @PostMapping(value = "/addStudent")
    public ResponseEntity saveStudent(@RequestBody StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        Student student1 = studentRepository.save(student);
        return new ResponseEntity(student1, HttpStatus.OK);
    }
}
