package springboot.backend.dao;

import springboot.backend.model.Course;

import java.util.List;

public interface CourseDao {
    List<Course> findAll();
}
