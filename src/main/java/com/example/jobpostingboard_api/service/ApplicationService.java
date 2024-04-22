package com.example.jobpostingboard_api.service;


import com.example.jobpostingboard_api.dto.ApplicationDto;
import com.example.jobpostingboard_api.entity.Application;
import com.example.jobpostingboard_api.entity.JobPost;
import com.example.jobpostingboard_api.repository.ApplicationRepository;
import com.example.jobpostingboard_api.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final JobPostService jobPostService;



    public ApplicationDto apply(HttpServletRequest request, ApplicationDto applicationDto, int id) {
        Application application1 = new Application();
        var userDto = userService.getMe(request);

        var user = userRepository.findByEmailAddress(userDto.getEmailAddress()).orElse(null);

        var jobPost = jobPostService.getJobpost(id);

        application1.setUser(user);
        application1.setJobPost(jobPost);
        application1.setLink(applicationDto.getLink());
        application1.setResume(applicationDto.getResume());
        application1.setCoverLetter(applicationDto.getCoverLetter());
        applicationRepository.save(application1);

        return applicationDto;


    }
}
