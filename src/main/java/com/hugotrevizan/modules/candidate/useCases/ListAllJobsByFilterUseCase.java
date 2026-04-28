package com.hugotrevizan.modules.candidate.useCases;

import com.hugotrevizan.modules.company.dtos.CreateJobResponseDTO;
import com.hugotrevizan.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<CreateJobResponseDTO> execute(String filter) {
        var jobs = jobRepository.findByDescriptionContainingIgnoreCase(filter);
        return jobs.stream().map(CreateJobResponseDTO::fromEntity).toList();
    }
}
