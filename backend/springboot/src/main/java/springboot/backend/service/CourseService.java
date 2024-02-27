package springboot.backend.service;

import org.springframework.stereotype.Component;
import springboot.backend.dao.CourseDao;
import springboot.backend.model.Course;

import java.util.List;

@Component
public class CourseService {
    private CourseDao courseDao;

    public CourseService(CourseDao courseDao){
        this.courseDao = courseDao;
    }

    public List<Course> getAllCourses(){
        return courseDao.findAll();
    }
}
