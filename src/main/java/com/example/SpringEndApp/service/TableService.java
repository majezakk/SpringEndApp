package com.example.SpringEndApp.service;

import com.example.SpringEndApp.model.TableModel;
import com.example.SpringEndApp.repository.TableModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableService {

    private final TableModelRepository tableRepository;

    @Autowired
    public TableService(TableModelRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<TableModel> getAllTables() {
        return tableRepository.findAll();
    }

    public Optional<TableModel> getTableById(Long id) {
        return tableRepository.findById(id);
    }

    public TableModel saveTable(TableModel table) {
        return tableRepository.save(table);
    }

    public void deleteTable(Long id) {
        tableRepository.deleteById(id);
    }
}