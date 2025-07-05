package com.thee5176.record.springboot_cqrs_command.Application.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thee5176.record.springboot_cqrs_command.Infrastructure.repository.EntryRepository;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Entries;




@RestController
@RequestMapping("/entries")
public class EntryController {
    @Autowired
    EntryRepository entryRepository;

    @GetMapping
    public List<Entries> getEntry() {
        return entryRepository.getEntry();
    }

    @PostMapping
    public ResponseEntity<String> createEntry(@RequestParam Entries entry) {
        entryRepository.createEntry(entry);
        
        return ResponseEntity.ok("created entry succesfully");
    }
    

    @PutMapping
    public ResponseEntity<String> updateEntry(@RequestParam UUID uuid, @RequestBody Entries entry) {
        entryRepository.updateEntry(uuid, entry);

        return ResponseEntity.ok("updated entry succesfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteEntry(@RequestParam UUID uuid) {
        entryRepository.deleteEntry(uuid);

        return ResponseEntity.ok("deleted entry succesfully");
    }
}
