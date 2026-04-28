package com.hugotrevizan.modules.company.useCases;

import com.hugotrevizan.modules.company.dtos.CreateJobDTO;
import com.hugotrevizan.modules.company.dtos.CreateJobResponseDTO;
import com.hugotrevizan.modules.company.entities.JobEntity;
import com.hugotrevizan.modules.company.repositories.JobRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public CreateJobResponseDTO execute(CreateJobDTO createJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id").toString();
        var jobSaved = jobRepository.save(createJobDTO.toEntity(companyId));
        return CreateJobResponseDTO.fromEntity(jobSaved);
    }
}
