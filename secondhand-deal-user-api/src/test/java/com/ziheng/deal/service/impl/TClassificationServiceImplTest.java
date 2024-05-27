package com.ziheng.deal.service.impl;

import com.ziheng.deal.db.domain.DTO.ClassificationDTO;
import com.ziheng.deal.db.entity.TClassification;
import com.ziheng.deal.db.service.TClassificationService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TClassificationServiceImplTest {
    @Resource
    TClassificationService classificationService;
    @Test
    void test01(){
        List<ClassificationDTO> allClassification = classificationService.getAllClassification();
        for (ClassificationDTO i : allClassification) {
            System.out.println(i);
        }

    }

    @Test
    void test02() {
        List<ClassificationDTO> classificationDTOS = classificationService.GetClassificationTow();
        for (ClassificationDTO i : classificationDTOS) {
            System.out.println(i);
        }
    }

    @Test
    void test03() {
        TClassification tClassification = new TClassification();
        tClassification.setClassificationName("床上用品");
        tClassification.setParentId(8);
        classificationService.AddClassification(tClassification, "人才");
    }

    @Test
    void test04(){
        ClassificationDTO byIdClassificationInfo = classificationService.getByIdClassificationInfo(12);
        System.out.println(byIdClassificationInfo);
    }

    @Test
    void test05(){
        TClassification tClassification = new TClassification();
        tClassification.setClassificationId(15);
        tClassification.setClassificationName("母婴");
//        tClassification.setParentId(11);
        classificationService.getByIdUpdateClassification(tClassification, "ziheng");
    }

    @Test
    void test06(){
        classificationService.getByIdDeleteClassification(15);
    }
}
