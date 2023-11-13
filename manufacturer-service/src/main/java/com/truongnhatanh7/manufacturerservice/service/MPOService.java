package com.truongnhatanh7.manufacturerservice.service;

import com.truongnhatanh7.manufacturerservice.dto.request.MPORequest;
import com.truongnhatanh7.manufacturerservice.dto.response.MPOResponse;
import com.truongnhatanh7.manufacturerservice.entity.MPO;
import com.truongnhatanh7.manufacturerservice.entity.MPOLine;
import com.truongnhatanh7.manufacturerservice.event.GetProductByIDEvent;
import com.truongnhatanh7.manufacturerservice.repository.MPORepository;
import com.truongnhatanh7.shared.repository.BaseRepository;
import com.truongnhatanh7.shared.service.BaseService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

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
                .mpoLines(mpoRequest.getMpoLines())
                .build();
    }

    @Override
    public MPO mapPatchToOrm(MPORequest mpoRequest, MPO tInstance) {
        return null;
    }

    @Override
    public MPOResponse mapOrmToResponse(MPO tInstance) {
        return MPOResponse.builder()
                .id(tInstance.getId())
                .mpoLines(tInstance.getMpoLines())
                .build();
    }

    public MPOResponse create() {
//        CompletableFuture<SendResult<String, Object>> res = kafkaTemplate.send(
//                "getProductByID",
//                new GetProductByIDEvent(1L));
//        res.whenComplete((result, ex) -> {
//            System.out.println(result);
//        });
//        kafkaTemplate.send

        return MPOResponse.builder().build();
    }
}
