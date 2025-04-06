package com.pulseofthepeople.repository;

import com.pulseofthepeople.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByValueIgnoreCase(String value);
}
