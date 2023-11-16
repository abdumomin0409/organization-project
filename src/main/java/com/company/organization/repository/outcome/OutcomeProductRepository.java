package com.company.organization.repository.outcome;

import com.company.organization.domain.outcome.Outcome;
import com.company.organization.domain.outcome.OutcomeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface OutcomeProductRepository extends JpaRepository<OutcomeProduct, Long> {

    @Query("select i from OutcomeProduct i where i.organizationProduct.id = ?1")
    OutcomeProduct findByOrganizationProduct(Long organizationProduct);

    @Query("select i from OutcomeProduct i where i.outcome = ?1")
    ArrayList<OutcomeProduct> findAllByIncome(Outcome income);


}