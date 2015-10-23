package com.javachen.repository;

import com.javachen.dto.IdeaDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdeaRepository extends JpaRepository<IdeaDto, Integer> {

}
