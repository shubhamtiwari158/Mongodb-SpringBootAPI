package mongodbexample.repo;

import mongodbexample.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepositroy extends MongoRepository<Student,String> {

    void deleteById(String id);

    Optional<Student> findById(String id);
}
