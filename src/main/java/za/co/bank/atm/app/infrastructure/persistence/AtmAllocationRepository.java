package za.co.bank.atm.app.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import za.co.bank.atm.app.domain.Atm_Allocation;

import java.util.List;

/**
 * @author Arthur Gwatidzo email:arthur.gwatidzo@gmail.com
 */
public interface AtmAllocationRepository extends JpaRepository<Atm_Allocation, Integer> {

    @Query(value = "SELECT aa.*, de.value,(aa.count*de.value) as demominationSum" +
            " FROM atm_allocation aa " +
            "   join denomination de on aa.denomination_id = de.denomination_id " +
            " where aa.atm_id = :atmId " +
            " order by de.value desc", nativeQuery = true)
    List<Object[]> getListOfDenominationsAndSumByAtmId(@Param("atmId") Integer atmId);
}
