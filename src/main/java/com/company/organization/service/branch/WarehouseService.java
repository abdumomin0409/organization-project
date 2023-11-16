package com.company.organization.service.branch;

import com.company.organization.domain.branch.Branch;
import com.company.organization.domain.branch.Warehouse;
import com.company.organization.exception_handler.exception.ValidateException;
import com.company.organization.payload.branch.WarehouseDTO;
import com.company.organization.payload.branch.WarehouseResponse;
import com.company.organization.repository.branch.BranchRepository;
import com.company.organization.repository.branch.WarehouseRepository;
import com.company.organization.service.BaseService;
import com.company.organization.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class WarehouseService implements BaseService {
    private final WarehouseRepository warehouseRepository;
    private final BranchService branchService;
    private final UserService userService;

    Logger logger = Logger.getLogger(WarehouseService.class.getName());
    private final BranchRepository branchRepository;

    public WarehouseResponse create(WarehouseDTO dto) {
        logger.log(Level.INFO, "WarehouseService create method called by dto %s".formatted(dto.toString()));
        if (existsWarehouseByAddressAndLatitudeAndLatitude(dto.address(), dto.latitude(), dto.longitude()))
            throw new ValidateException("Warehouse already exists by address, latitude, longitude", -1400002);
        Warehouse warehouse = Warehouse.warehouseBuilder()
                .address(dto.address())
                .name(dto.name())
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .isActive(true)
                .build();
        warehouse.setKeeperId(userService.getById(dto.keeperId()));
        Branch branch = branchService.getById(dto.branchId());
        if (existsWarehouseByBranch(branch))
            throw new ValidateException("Warehouse already exists by branch", -1400002);
        warehouse.setBranch(branch);
        Warehouse saved = warehouseRepository.save(warehouse);
        return WarehouseResponse.builder()
                .id(saved.getId())
                .keeperId(saved.getKeeperId().getId())
                .branchId(saved.getBranch().getId())
                .address(saved.getAddress())
                .name(saved.getName())
                .longitude(saved.getLongitude())
                .latitude(saved.getLatitude())
                .build();
    }

    public WarehouseResponse updateById(Long id, WarehouseDTO dto) {
        logger.log(Level.INFO, "WarehouseService updateById method called by id %d and dto %s".formatted(id, dto.toString()));
        if (!existsById(id))
            throw new ValidateException("Warehouse not found", -1400001);
        Warehouse byId = getById(id);
        if (!byId.getAddress().equals(dto.address()) && existsWarehouseByAddressAndLatitudeAndLatitude(dto.address(), dto.latitude(), dto.longitude()))
            throw new ValidateException("Warehouse already exists by address, latitude, longitude", -1400002);
//        WAREHOUSE_MAPPER.fromUpdateToWarehouse(dto, byId);
        byId.setName(dto.name());
        byId.setAddress(dto.address());
        byId.setLatitude(dto.latitude());
        byId.setLongitude(dto.longitude());
        byId.setKeeperId(userService.getById(dto.keeperId()));
        Branch branch = branchService.getById(dto.branchId());
        if (!byId.getBranch().equals(branch) && existsWarehouseByBranch(branch))
            throw new ValidateException("Warehouse already exists by branch", -1400002);
        byId.setBranch(branch);
        Warehouse saved = warehouseRepository.save(byId);
        return WarehouseResponse.builder()
                .id(saved.getId())
                .keeperId(saved.getKeeperId().getId())
                .branchId(saved.getBranch().getId())
                .address(saved.getAddress())
                .name(saved.getName())
                .longitude(saved.getLongitude())
                .latitude(saved.getLatitude())
                .build();
    }

    public WarehouseResponse deleteById(Long id) {
        logger.log(Level.INFO, "WarehouseService deleteById method called by id %d".formatted(id));
        if (!existsById(id))
            throw new ValidateException("Warehouse not found", -1400001);
        Warehouse warehouse = getById(id);
        warehouse.setIsActive(false);
        Warehouse saved = warehouseRepository.save(warehouse);
        return WarehouseResponse.builder()
                .id(saved.getId())
                .keeperId(saved.getKeeperId().getId())
                .branchId(saved.getBranch().getId())
                .address(saved.getAddress())
                .name(saved.getName())
                .longitude(saved.getLongitude())
                .latitude(saved.getLatitude())
                .build();
    }

    public WarehouseResponse getId(Long id) {
        logger.log(Level.INFO, "WarehouseService getById method called by id %d".formatted(id));
        if (!existsById(id))
            throw new ValidateException("Warehouse not found", -1400001);
        Warehouse warehouse = getById(id);
        return WarehouseResponse.builder()
                .id(warehouse.getId())
                .keeperId(warehouse.getKeeperId().getId())
                .branchId(warehouse.getBranch().getId())
                .address(warehouse.getAddress())
                .name(warehouse.getName())
                .longitude(warehouse.getLongitude())
                .latitude(warehouse.getLatitude())
                .build();
    }

    public Warehouse getById(Long id) {
        logger.log(Level.INFO, "WarehouseService getById method called by id %d".formatted(id));
        return warehouseRepository.findById(id).orElseThrow(() -> new ValidateException("Warehouse not found", -1400005));
    }

    public List<WarehouseResponse> getAll() {
        logger.log(Level.INFO, "WarehouseService getAll method called");
        ArrayList<WarehouseResponse> list = new ArrayList<>();
        warehouseRepository.findAll().forEach(warehouse -> list.add(
                WarehouseResponse.builder()
                        .id(warehouse.getId())
                        .keeperId(warehouse.getKeeperId().getId())
                        .branchId(warehouse.getBranch().getId())
                        .address(warehouse.getAddress())
                        .name(warehouse.getName())
                        .longitude(warehouse.getLongitude())
                        .latitude(warehouse.getLatitude())
                        .build()));
        return list;
    }

    public Page<Warehouse> getAllFixed(Pageable pageable) {
        logger.log(Level.INFO, "WarehouseService getAllFixed method called");
        return warehouseRepository.findAllByPageable(pageable);
    }

    public boolean existsById(Long id) {
        logger.log(Level.INFO, "WarehouseService existsById method called by id %d".formatted(id));
        return warehouseRepository.existsById(id);
    }

    public boolean existsWarehouseByAddressAndLatitudeAndLatitude(String address, Double latitude, Double longitude) {
        logger.log(Level.INFO, "WarehouseService exists Warehouse By Address And Latitude And Latitude method called by address %s, latitude %s, longitude %s".formatted(address, latitude, longitude));
        return warehouseRepository.existsWarehouseByAddressAndLatitudeAndLatitude(address, latitude, longitude);
    }

    public boolean existsWarehouseByBranch(Branch branch) {
        logger.log(Level.INFO, "WarehouseService exists Warehouse By Branch method called by dto {}", branch.toString());
        return warehouseRepository.existsWarehouseByBranch(branch);
    }

}
