package springboot.backend.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import springboot.backend.model.Course;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCourseDao implements CourseDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcCourseDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Course course = mapRowToCourse(results);
            courses.add(course);
        }

        return courses;
    }

    private Course mapRowToCourse(SqlRowSet rs) {
        Course course = new Course();
        course.setCourse_id(rs.getInt("course_id"));
        course.setCourse_name(rs.getString("course_name"));
        course.setImage_key(rs.getString("image_key"));
//        course.setDescription(rs.get);
        course.setPrice(rs.getBigDecimal("price"));
        return course;
    }
}
