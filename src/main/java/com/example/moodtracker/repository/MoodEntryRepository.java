package com.example.moodtracker.repository;

import com.example.moodtracker.entity.MoodEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MoodEntryRepository extends JpaRepository<MoodEntry, Long> {

    // Find all entries ordered by date (newest first)
    List<MoodEntry> findAllByOrderByDateDesc();

    // Find entries by date
    List<MoodEntry> findByDate(LocalDate date);

    // Find entries by mood
    List<MoodEntry> findByMood(String mood);

    // Find entries between dates
    List<MoodEntry> findByDateBetweenOrderByDateDesc(LocalDate startDate, LocalDate endDate);

    // Custom query to get mood statistics
    @Query("SELECT m.mood, COUNT(m) FROM MoodEntry m GROUP BY m.mood")
    List<Object[]> getMoodStatistics();
} 