package com.example.SpringEndApp.controller;

import com.example.SpringEndApp.model.TableModel;
import com.example.SpringEndApp.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tables")
public class TableController {
    private final TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public String listTables(Model model) {
        model.addAttribute("tables", tableService.getAllTables());
        return "table/list";
    }

    @GetMapping("/new")
    public String newTableForm(Model model) {
        model.addAttribute("table", new TableModel());
        return "table/form";
    }

    @PostMapping
    public String saveTable(@Valid @ModelAttribute("table") TableModel table, BindingResult result) {
        if (result.hasErrors()) {
            return "table/form";
        }
        tableService.saveTable(table);
        return "redirect:/tables";
    }

    @GetMapping("/edit/{id}")
    public String editTableForm(@PathVariable Long id, Model model) {
        TableModel table = tableService.getTableById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid table Id:" + id));
        model.addAttribute("table", table);
        return "table/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
        return "redirect:/tables";
    }
}