package com.example.demo.model;

import com.example.demo.note.entity.Note;
import com.example.demo.student.entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class Response {
    List<Student> students;
    List<Note> notes;
}
