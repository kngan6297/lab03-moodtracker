package com.example.moodtracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "mood_entries")
public class MoodEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Date is required")
    @Column(nullable = false)
    private LocalDate date;

    @NotBlank(message = "Mood is required")
    @Column(nullable = false)
    private String mood;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column
    private String emoji;

    // Default constructor
    public MoodEntry() {}

    // Constructor with fields
    public MoodEntry(LocalDate date, String mood, String note, String emoji) {
        this.date = date;
        this.mood = mood;
        this.note = note;
        this.emoji = emoji;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    @Override
    public String toString() {
        return "MoodEntry{" +
                "id=" + id +
                ", date=" + date +
                ", mood='" + mood + '\'' +
                ", note='" + note + '\'' +
                ", emoji='" + emoji + '\'' +
                '}';
    }
} 