package springboot.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springboot.backend.service.CloudFrontService;
import springboot.backend.model.Course;
import springboot.backend.model.openfda.FdaBrandMedSearchResult;
import springboot.backend.service.ApiService;
import springboot.backend.service.CourseService;
import springboot.backend.service.S3Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class BuiltAppController {
    private ApiService apiService;
    private CloudFrontService cloudFrontService;
    private S3Service s3Service;
    private CourseService courseService;

    public BuiltAppController(ApiService apiService, CloudFrontService cloudFrontService,S3Service s3Service, CourseService courseService) {
        this.apiService = apiService;
        this.cloudFrontService = cloudFrontService;
        this.s3Service = s3Service;
        this.courseService = courseService;
    }

    @ResponseBody
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String hello(){
        return "hello world";
    }

    @ResponseBody
    @RequestMapping(value = "/med_search/{name}", method = RequestMethod.GET)
    public FdaBrandMedSearchResult[] minutesReadFromISBN(@PathVariable String name){
        return apiService.fdaMedSearch(name);
    }
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    @RequestMapping(value = "/signed_url/{url}", method = RequestMethod.GET)
    public String GenerateSignedUrl(@PathVariable String url){
        return cloudFrontService.createSignedUrl(url);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    @RequestMapping(value = "/list_bucket", method = RequestMethod.GET)
    public String ListBucketObjects(){
        return cloudFrontService.createSignedUrl("?list-type=2");
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public List<Course> listCourses() {
        s3Service.listBucketObjects("built-video-secured");
        return courseService.getAllCourses();
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/presigned/{key}", method = RequestMethod.GET)
    public String GeneratePresignedUrl(@PathVariable String key) {
        Map<String, String> METADATA = new HashMap<>();
//        METADATA.put("meta1", "value1");
        return s3Service.createPresignedUrl("built-video-secured", key, METADATA);
    }
}
