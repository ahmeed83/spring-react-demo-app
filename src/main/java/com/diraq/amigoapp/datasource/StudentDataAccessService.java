package com.diraq.amigoapp.datasource;

import com.diraq.amigoapp.model.Student;
import com.diraq.amigoapp.model.Student.Gender;
import java.util.List;
import java.util.UUID;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDataAccessService {

  private final JdbcTemplate jdbcTemplate;

  public StudentDataAccessService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Student> getAllStudents() {
    String sql = ""
        + "SELECT student_id, "
        + " first_name, "
        + " last_name, "
        + " email, "
        + " gender "
        + "FROM STUDENT";
    return jdbcTemplate.query(sql, mapStudentFromDb());
  }

  @SuppressWarnings("ConstantConditions")
  public boolean isEmailTaken(String email) {
    String sql = ""
        + "SELECT EXISTS ( "
        + " SELECT 1 "
        + " FROM student "
        + " WHERE email = ? "
        + ")";
    return jdbcTemplate.queryForObject(
        sql,
        new Object[]{email},
        ((resultSet, i) -> resultSet.getBoolean(1))
    );
  }

  public int insertStudent(UUID studentId, Student student) {
    String sql = "" +
        "INSERT INTO student"
        + " (student_id,"
        + " first_name,"
        + " last_name,"
        + " email,"
        + " gender) "
        + "VALUES (?, ?, ?, ?, ?)";
    return jdbcTemplate.update(
        sql,
        studentId,
        student.getFirstName(),
        student.getLastName(),
        student.getEmail(),
        student.getGender().name().toUpperCase()
    );
  }

  private RowMapper<Student> mapStudentFromDb() {
    return (resultSet, i) -> {
      final var studentIdStr = resultSet.getString("student_id");
      UUID studentId = UUID.fromString(studentIdStr);
      final var firstName = resultSet.getString("first_name");
      final var lastName = resultSet.getString("last_name");
      final var email = resultSet.getString("email");
      final var genderStr = resultSet.getString("gender").toUpperCase();
      final var gender = Gender.valueOf(genderStr);
      return new Student(
          studentId, firstName, lastName, email, gender
      );
    };
  }
}
