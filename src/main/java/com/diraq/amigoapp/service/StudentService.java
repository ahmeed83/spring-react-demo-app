package com.diraq.amigoapp.service;

import com.diraq.amigoapp.datasource.StudentDataAccessService;
import com.diraq.amigoapp.exception.ApiRequestException;
import com.diraq.amigoapp.model.Student;
import com.diraq.amigoapp.validator.EmailValidator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private StudentDataAccessService studentDataAccessService;
  private EmailValidator emailValidator;

  public StudentService(StudentDataAccessService studentDataAccessService,
      EmailValidator emailValidator) {
    this.studentDataAccessService = studentDataAccessService;
    this.emailValidator = emailValidator;
  }

  public List<Student> getAllStudents() {
    return studentDataAccessService.getAllStudents();
  }

  public void addNewStudent(Student student) {
    UUID newStudentId = Optional.ofNullable(student.getStudentId()).orElse(UUID.randomUUID());
    if (!emailValidator.test(student.getEmail())) {
      throw new ApiRequestException("Email isn't valid");
    }
    // Why should you go throw all the email while you can do it directly in the database?
//    if (getAllStudents().stream().anyMatch(stud -> stud.getEmail().equals(student.getEmail()))) {
//      throw new ApiRequestException("Email is already exists");
//    }
    // this way better and faster
    if (studentDataAccessService.isEmailTaken(student.getEmail())) {
      throw new ApiRequestException("Email is already exists");
    }
    studentDataAccessService.insertStudent(newStudentId, student);
  }
}
