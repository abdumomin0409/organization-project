package com.company.organization.service.branch;

import com.company.organization.domain.branch.Branch;
import com.company.organization.exception_handler.exception.ValidateException;
import com.company.organization.payload.branch.BranchDTO;
import com.company.organization.payload.branch.BranchResponse;
import com.company.organization.repository.branch.BranchRepository;
import com.company.organization.service.BaseService;
import com.company.organization.service.organization.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.company.organization.mapper.branch.BranchMapper.BRANCH_MAPPER;

@Service
@RequiredArgsConstructor
public class BranchService implements BaseService {
    private final BranchRepository branchRepository;
    private final OrganizationService organizationService;
    Logger logger = Logger.getLogger(BranchService.class.getName());

    public BranchResponse create(BranchDTO dto) {
        logger.log(Level.INFO, "BranchService create method called");
        if (existsByName(dto.name()))
            throw new ValidateException("Branch already exists", -1400002);
        Branch branch = BRANCH_MAPPER.fromCreateToBranch(dto);
        branch.setOrganization(organizationService.getById(dto.organizationId()));
        branch.setIsActive(true);
        Branch saved = branchRepository.save(branch);
        return BranchResponse.builder()
                .id(saved.getId())
                .address(saved.getAddress())
                .workBegin(saved.getWorkBegin())
                .name(saved.getName())
                .organizationId(saved.getOrganization().getId())
                .build();
    }

    public BranchResponse updateById(Long id, BranchDTO dto) {
        logger.log(Level.INFO, "BranchService updateById method called");
        if (!existsById(id))
            throw new ValidateException("Branch not found", -1400001);
        Branch byId = getById(id);
        if (!byId.getName().equals(dto.name()) && existsByName(dto.name()))
            throw new ValidateException("Branch already exists", -1400002);
        BRANCH_MAPPER.fromUpdateToBranch(dto, byId);
        byId.setOrganization(organizationService.getById(dto.organizationId()));
        Branch saved = branchRepository.save(byId);
        return BranchResponse.builder()
                .organizationId(saved.getOrganization().getId())
                .workBegin(saved.getWorkBegin())
                .address(saved.getAddress())
                .id(saved.getId())
                .name(saved.getName())
                .build();
    }

    public BranchResponse deleteById(Long id) {
        logger.log(Level.INFO, "BranchService deleteById method called");
        if (!existsById(id))
            throw new ValidateException("Branch not found", -1400001);
        Branch branch = getById(id);
        branch.setIsActive(false);
        Branch saved = branchRepository.save(branch);
        return BranchResponse.builder()
                .name(saved.getName())
                .id(saved.getId())
                .address(saved.getAddress())
                .organizationId(saved.getOrganization().getId())
                .workBegin(saved.getWorkBegin())
                .build();
    }

    public BranchResponse getId(Long id) {
        logger.log(Level.INFO, "BranchService deleteById method called");
        if (!existsById(id))
            throw new ValidateException("Branch not found", -1400001);
        Branch branch = getById(id);
        return BranchResponse.builder()
                .name(branch.getName())
                .id(branch.getId())
                .address(branch.getAddress())
                .organizationId(branch.getOrganization().getId())
                .workBegin(branch.getWorkBegin())
                .build();
    }

    public Branch getById(Long id) {
        logger.log(Level.INFO, "BranchService getById method called");
        return branchRepository.findById(id).orElseThrow(() -> new ValidateException("Branch not found", -1400005));
    }

    public List<BranchResponse> getAll() {
        logger.log(Level.INFO, "BranchService getAll method called");
        ArrayList<BranchResponse> list = new ArrayList<>();
        branchRepository.findAll().forEach(branch -> list.add(
                new BranchResponse(branch.getId(), branch.getName(), branch.getAddress(), branch.getOrganization().getId(), branch.getWorkBegin())
        ));
        return list;
    }

    public Page<Branch> getAllFixed(Pageable pageable) {
        logger.log(Level.INFO, "BranchService getAllFixed method called");
        return branchRepository.findAllByPageable(pageable);
    }

    public boolean existsByName(String name) {
        logger.log(Level.INFO, "BranchService existsByName method called");
        return branchRepository.existsByName(name);
    }

    public boolean existsById(Long id) {
        logger.log(Level.INFO, "BranchService existsById method called");
        return branchRepository.existsById(id);
    }


}
