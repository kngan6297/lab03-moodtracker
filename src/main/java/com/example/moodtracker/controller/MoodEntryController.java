package com.example.moodtracker.controller;

import com.example.moodtracker.entity.MoodEntry;
import com.example.moodtracker.repository.MoodEntryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/moods")
@CrossOrigin(origins = "*")
public class MoodEntryController {

    @Autowired
    private MoodEntryRepository moodEntryRepository;

    // GET all mood entries
    @GetMapping
    public ResponseEntity<List<MoodEntry>> getAllMoodEntries() {
        List<MoodEntry> entries = moodEntryRepository.findAllByOrderByDateDesc();
        return ResponseEntity.ok(entries);
    }

    // GET mood entry by ID
    @GetMapping("/{id}")
    public ResponseEntity<MoodEntry> getMoodEntryById(@PathVariable Long id) {
        Optional<MoodEntry> entry = moodEntryRepository.findById(id);
        return entry.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create new mood entry
    @PostMapping
    public ResponseEntity<MoodEntry> createMoodEntry(@Valid @RequestBody MoodEntry moodEntry) {
        // Set date to today if not provided
        if (moodEntry.getDate() == null) {
            moodEntry.setDate(LocalDate.now());
        }
        
        MoodEntry savedEntry = moodEntryRepository.save(moodEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntry);
    }

    // PUT update mood entry
    @PutMapping("/{id}")
    public ResponseEntity<MoodEntry> updateMoodEntry(@PathVariable Long id, 
                                                   @Valid @RequestBody MoodEntry moodEntry) {
        Optional<MoodEntry> existingEntry = moodEntryRepository.findById(id);
        
        if (existingEntry.isPresent()) {
            MoodEntry entryToUpdate = existingEntry.get();
            entryToUpdate.setDate(moodEntry.getDate());
            entryToUpdate.setMood(moodEntry.getMood());
            entryToUpdate.setNote(moodEntry.getNote());
            entryToUpdate.setEmoji(moodEntry.getEmoji());
            
            MoodEntry updatedEntry = moodEntryRepository.save(entryToUpdate);
            return ResponseEntity.ok(updatedEntry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE mood entry
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMoodEntry(@PathVariable Long id) {
        Optional<MoodEntry> entry = moodEntryRepository.findById(id);
        
        if (entry.isPresent()) {
            moodEntryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET mood entries by date
    @GetMapping("/date/{date}")
    public ResponseEntity<List<MoodEntry>> getMoodEntriesByDate(@PathVariable String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            List<MoodEntry> entries = moodEntryRepository.findByDate(localDate);
            return ResponseEntity.ok(entries);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // GET mood entries by mood type
    @GetMapping("/mood/{mood}")
    public ResponseEntity<List<MoodEntry>> getMoodEntriesByMood(@PathVariable String mood) {
        List<MoodEntry> entries = moodEntryRepository.findByMood(mood);
        return ResponseEntity.ok(entries);
    }
} 