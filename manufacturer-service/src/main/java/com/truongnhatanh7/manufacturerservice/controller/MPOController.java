package com.truongnhatanh7.manufacturerservice.controller;

import com.truongnhatanh7.manufacturerservice.dto.request.MPOLineRequest;
import com.truongnhatanh7.manufacturerservice.dto.request.MPORequest;
import com.truongnhatanh7.manufacturerservice.dto.response.MPOResponse;
import com.truongnhatanh7.manufacturerservice.entity.MPO;
import com.truongnhatanh7.manufacturerservice.entity.MPOLine;
import com.truongnhatanh7.manufacturerservice.service.MPOService;
import com.truongnhatanh7.shared.controller.BaseController;
import com.truongnhatanh7.shared.service.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mpo")
public class MPOController extends BaseController<MPO, Long, MPORequest, MPOResponse> {
    private MPOService mpoService;
    public MPOController(MPOService service) {
        super(service);
        this.mpoService = service;
    }

    @PostMapping("/add-mpo-line")
    public ResponseEntity<MPOLine> addMpoLine(
            @RequestBody MPOLineRequest mpoLineRequest
            ) {
        return mpoService.addMPOLine(mpoLineRequest);
    }

    @PatchMapping("/approve-mpo/{mpoId}")
    public ResponseEntity<Void> approveMpo(
            @PathVariable(value = "mpoId") Long mpoId
    ) {
        return mpoService.approveMpo(mpoId);
    }

}
