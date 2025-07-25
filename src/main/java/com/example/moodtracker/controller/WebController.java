package com.example.moodtracker.controller;

import com.example.moodtracker.entity.MoodEntry;
import com.example.moodtracker.repository.MoodEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private MoodEntryRepository moodEntryRepository;

    // Home page - show all mood entries
    @GetMapping("/")
    public String home(Model model) {
        List<MoodEntry> entries = moodEntryRepository.findAllByOrderByDateDesc();
        model.addAttribute("entries", entries);
        model.addAttribute("newEntry", new MoodEntry());
        return "index";
    }

    // Show form to add new mood entry
    @GetMapping("/add")
    public String showAddForm(Model model) {
        MoodEntry newEntry = new MoodEntry();
        newEntry.setDate(LocalDate.now());
        model.addAttribute("moodEntry", newEntry);
        return "form";
    }

    // Show form to edit existing mood entry
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        MoodEntry entry = moodEntryRepository.findById(id).orElse(new MoodEntry());
        model.addAttribute("moodEntry", entry);
        return "form";
    }

    // Handle form submission (create or update)
    @PostMapping("/save")
    public String saveMoodEntry(@ModelAttribute MoodEntry moodEntry) {
        if (moodEntry.getDate() == null) {
            moodEntry.setDate(LocalDate.now());
        }
        moodEntryRepository.save(moodEntry);
        return "redirect:/";
    }

    // Delete mood entry
    @GetMapping("/delete/{id}")
    public String deleteMoodEntry(@PathVariable Long id) {
        moodEntryRepository.deleteById(id);
        return "redirect:/";
    }
} 