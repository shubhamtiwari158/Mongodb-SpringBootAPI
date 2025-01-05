package mongodbexample.controller;

import mongodbexample.model.Student;
import mongodbexample.repo.StudentRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class MyController {

    @Autowired
    private StudentRepositroy studentRepositroy;

    // Create a new student
    @PostMapping("/")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        Student savedStudent = this.studentRepositroy.save(student);
        return ResponseEntity.ok(savedStudent);
    }

    // Get all students
    @GetMapping("/")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(this.studentRepositroy.findAll());
    }

    // Get a single student by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable String id) {
        Optional<Student> student = this.studentRepositroy.findById(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.status(404).body("Student not found");
        }
    }

    // Update an existing student
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id, @RequestBody Student updatedStudent) {
        Optional<Student> existingStudent = this.studentRepositroy.findById(id);
        if (existingStudent.isPresent()) {
            updatedStudent.setId(id); // Ensure the ID remains the same
            Student savedStudent = this.studentRepositroy.save(updatedStudent);
            return ResponseEntity.ok(savedStudent);
        } else {
            return ResponseEntity.status(404).body("Student not found");
        }
    }

    // Delete a student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id) {
        Optional<Student> student = this.studentRepositroy.findById(id);
        if (student.isPresent()) {
            this.studentRepositroy.deleteById(id);
            return ResponseEntity.ok("Student deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Student not found");
        }
    }
}
