package com.project.food.infrastructure.database.repository.jpa;

import com.project.food.infrastructure.database.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressJpaRepository extends JpaRepository<AddressEntity, Integer> {

    @Query("SELECT addressId FROM AddressEntity WHERE postalCode= :pos_code AND city= :city AND street= :street AND country= :country")
    Integer findByCityAndPostalCodeAndStreetAndCountry(
            @Param("city") String city,
            @Param("street") String street,
            @Param("country") String country,
            @Param("pos_code") String pos_code
    );
}
