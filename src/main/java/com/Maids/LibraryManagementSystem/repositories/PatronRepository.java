package com.Maids.LibraryManagementSystem.repositories;

import com.Maids.LibraryManagementSystem.models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {
}
