package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Device;
import softuni.exam.models.entity.enums.DeviceTypeEnum;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findByBrandAndModel(String brand, String model);

    Optional<List<Device>> findByDeviceTypeAndPriceLessThanAndStorageGreaterThanEqual(DeviceTypeEnum type, double price, int storage);

}
