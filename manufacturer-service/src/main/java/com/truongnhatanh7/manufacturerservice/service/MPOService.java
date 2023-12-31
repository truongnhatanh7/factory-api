package com.truongnhatanh7.manufacturerservice.service;

import com.truongnhatanh7.manufacturerservice.dto.request.MPOLineRequest;
import com.truongnhatanh7.manufacturerservice.dto.request.MPORequest;
import com.truongnhatanh7.manufacturerservice.dto.response.MPOResponse;
import com.truongnhatanh7.manufacturerservice.entity.MPO;
import com.truongnhatanh7.manufacturerservice.entity.MPOLine;
import com.truongnhatanh7.manufacturerservice.entity.MPOStatus;
import com.truongnhatanh7.manufacturerservice.event.ApproveMPOEvent;
import com.truongnhatanh7.manufacturerservice.event.GetProductByIDEvent;
import com.truongnhatanh7.manufacturerservice.repository.MPORepository;
import com.truongnhatanh7.shared.repository.BaseRepository;
import com.truongnhatanh7.shared.service.BaseService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@Service
public class MPOService extends BaseService<MPO, Long, MPORequest, MPOResponse> {
    private MPORepository mpoRepository;
    private KafkaTemplate<String, Object> kafkaTemplate;
    public MPOService(MPORepository repository,
                      KafkaTemplate<String, Object> kafkaTemplate) {
        super(repository);
        this.mpoRepository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public MPO mapRequestToOrm(MPORequest mpoRequest) {
        return MPO.builder()
                .requestDate(mpoRequest.getRequestDate())
                .approveDate(mpoRequest.getApproveDate())
                .status(mpoRequest.getStatus())
                .build();
    }

    @Override
    public MPO mapPatchToOrm(MPORequest mpoRequest, MPO tInstance) {
        return MPO.builder()
                .requestDate(mpoRequest.getRequestDate() == null ?
                        tInstance.getRequestDate() :
                        mpoRequest.getRequestDate())
                .approveDate(mpoRequest.getApproveDate() == null ?
                        tInstance.getApproveDate() :
                        mpoRequest.getApproveDate())
                .status(mpoRequest.getStatus() == null ?
                        tInstance.getStatus() :
                        mpoRequest.getStatus())
                .build();
    }

    @Override
    public MPOResponse mapOrmToResponse(MPO tInstance) {
        return MPOResponse.builder()
                .id(tInstance.getId())
                .status(tInstance.getStatus())
                .build();
    }
    public ResponseEntity<MPOLine> addMPOLine(MPOLineRequest mpoLineRequest) {
        // TODO: Check if product id is valid
        MPO mpo = mpoRepository.findById(mpoLineRequest.getMpoId())
                .stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        MPOLine mpoLine = mpo.addMpoLine(mpoLineRequest);
        mpoRepository.save(mpo);
        return new ResponseEntity<>(mpoLine, HttpStatus.OK);
    }

    public ResponseEntity<Void> approveMpo(Long mpoId) {
        MPO mpo = mpoRepository.findById(mpoId)
                .stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        // After saga is completed -> mpo will be approved
        mpo.setStatus(MPOStatus.PROCESSING);
        mpoRepository.save(mpo);

        kafkaTemplate.send("mpo.product.topic",
                ApproveMPOEvent.builder()
                        .mpoId(mpoId)
                        .mpoLines(mpo.getMpoLines().stream().map(l ->
                                MPOLineRequest.builder()
                                        .mpoId(mpo.getId())
                                        .requestQty(l.getRequestQty())
                                        .productId(l.getProductId())
                                        .build())
                                .toList())
                        .build());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void handleMpoRollback(Long mpoId) {
        MPO mpo = mpoRepository.findById(mpoId)
                .stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        mpo.setStatus(MPOStatus.ROLLBACK);
        mpoRepository.save(mpo);
    }
}
