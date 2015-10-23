package com.javachen.service;

import com.javachen.model.Idea;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IdeaService {

    List<Idea> getIdeas();

    @Transactional
    Idea addIdea(Idea idea);

    @Transactional
    Idea updateIdea(Idea idea);

    @Transactional
    void deleteIdea(Idea idea);
}
