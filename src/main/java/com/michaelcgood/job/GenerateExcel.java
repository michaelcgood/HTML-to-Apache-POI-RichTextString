package com.michaelcgood.job;


import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import com.michaelcgood.controller.WebController;
import com.michaelcgood.export.CreateWorksheet;

@Configuration
@EnableBatchProcessing
@Lazy
public class GenerateExcel {
    
    List<String> docIds = new ArrayList<String>();

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    WebController webcontroller;
    
    @Autowired
    CreateWorksheet createexcel;

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
            .tasklet(new Tasklet() {
                @Override
                public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception, JSONException {

                    String content = webcontroller.getCurrentContent();
                    
                    System.out.println("content is ::" + content);
                    Workbook wb = new XSSFWorkbook();
                    String tabName = "some";
                    createexcel.createWorkSheet(wb, content, tabName);

                    return RepeatStatus.FINISHED;
                }
            })
            .build();
    }

    @Bean
    public Job ExcelGenerator() {
        return jobBuilderFactory.get("ExcelGenerator")
            .start(step1())
            .build();

    }

}
