package com.example.meaningful_data.rest.api.controllers;

import com.example.meaningful_data.rest.api.models.MeaningFulData;
import com.example.meaningful_data.rest.api.services.MeaningFulDataService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Authot: Albert Akimov
 * @Date: 23.08.2021
 * @Description:
 */
@RestController
@RequestMapping("/api/v1/auth/meaningful_data")
public class MeaningFulDataController extends AbstractController<MeaningFulData, MeaningFulDataService> {

    public MeaningFulDataController(MeaningFulDataService service) {
        super(service, MeaningFulData.class);
    }
}
