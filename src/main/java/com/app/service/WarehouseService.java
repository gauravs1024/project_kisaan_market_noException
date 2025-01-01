package com.app.service;

import com.app.model.Warehouse;
import com.app.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public int saveWarehouse(Warehouse warehouse) {
        return warehouseRepository.saveWarehouse(warehouse);
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.getAllWarehouses();
    }

    public Warehouse getWarehouseById(int id) {
        return warehouseRepository.getWarehouseById(id);
    }
}
