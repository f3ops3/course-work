package com.course.repository;

import com.course.model.Kekv;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KekvRepository extends JpaRepository<Kekv, UUID> {
}
