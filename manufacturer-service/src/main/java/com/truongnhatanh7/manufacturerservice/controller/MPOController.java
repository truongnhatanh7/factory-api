package com.truongnhatanh7.manufacturerservice.controller;

import com.truongnhatanh7.manufacturerservice.dto.request.MPORequest;
import com.truongnhatanh7.manufacturerservice.dto.response.MPOResponse;
import com.truongnhatanh7.manufacturerservice.entity.MPO;
import com.truongnhatanh7.manufacturerservice.service.MPOService;
import com.truongnhatanh7.shared.controller.BaseController;
import com.truongnhatanh7.shared.service.BaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mpo")
public class MPOController extends BaseController<MPO, Long, MPORequest, MPOResponse> {
    private MPOService mpoService;
    public MPOController(MPOService service) {
        super(service);
        this.mpoService = service;
    }

    @PostMapping("/kafkacreate")
    public MPOResponse createKafka() {
        return this.mpoService.create();
    }

}
