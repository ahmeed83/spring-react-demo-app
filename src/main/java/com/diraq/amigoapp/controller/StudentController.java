package com.diraq.amigoapp.controller;

import com.diraq.amigoapp.exception.ApiRequestException;
import com.diraq.amigoapp.model.Student;
import com.diraq.amigoapp.service.StudentService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("students")
public class StudentController {

  private StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public List<Student> getAllStudents() {
    final var students = studentService.getAllStudents();
    if (students == null || students.size() == 0) {
      throw new ApiRequestException("Oops connect get students YEY!");
    }
    return students;
  }

  @PostMapping
  public void addNewStudent(@RequestBody @Valid Student student) {
    studentService.addNewStudent(student);
  }
}
