package com.company.organization.repository.branch;

import com.company.organization.domain.branch.Branch;
import com.company.organization.domain.branch.Warehouse;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    @Query("select (count(o) > 0) from Warehouse o where o.isActive = true and o.id = ?1")
    boolean existsById(@NotNull Long id);

    @Query("select o from Warehouse o where o.isActive = true")
    Page<Warehouse> findAllByPageable(Pageable pageable);

    @Query("select (count(w) > 0) from Warehouse w where w.isActive = true and w.address = ?1 and w.latitude = ?2 and w.latitude = ?3")
    boolean existsWarehouseByAddressAndLatitudeAndLatitude(String address, Double latitude, Double latitude2);

    @Query("select (count(w) > 0) from Warehouse w where w.isActive = true and w.branch = ?1")
    boolean existsWarehouseByBranch(Branch branch);

}