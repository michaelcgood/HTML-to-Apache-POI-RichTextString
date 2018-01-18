package com.michaelcgood.controller;

import java.io.IOException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.michaelcgood.job.GenerateExcel;

@Controller
public class WebController {

    private String currentContent;
    @Autowired
    JobLauncher jobLauncher;
    
    @Autowired
    GenerateExcel exceljob; 

    @GetMapping("/")
    public ModelAndView getHome() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;

    }
    

    @PostMapping("/export")
    public String postTheFile(@RequestBody String body, RedirectAttributes redirectAttributes, Model model)
        throws IOException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {


        setCurrentContent(body);

        Job job = exceljob.ExcelGenerator();
        jobLauncher.run(job, new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters()
            );

        return "redirect:/";
    }

    public String getCurrentContent() {
        return currentContent;
    }

    public void setCurrentContent(String currentContent) {
        this.currentContent = currentContent;
    }

}
