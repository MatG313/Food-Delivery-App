package com.project.food.infrastructure.database.repository.jpa;

import com.project.food.infrastructure.database.entity.AddressEntity;
import com.project.food.integration.configuration.PersistenceContainerTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
class AddressJpaRepositoryTest {

    @Autowired
    private AddressJpaRepository addressJpaRepository;

    @Test
    public void testFindByCityAndPostalCodeAndStreetAndCountry() {
        AddressEntity address = new AddressEntity();
        address.setCity("City");
        address.setStreet("Street");
        address.setCountry("Country");
        address.setPostalCode("12345");
        addressJpaRepository.save(address);

        Integer addressId = addressJpaRepository.findByCityAndPostalCodeAndStreetAndCountry(
                "City", "Street", "Country", "12345");

        assertEquals(address.getAddressId(), addressId);
    }

}