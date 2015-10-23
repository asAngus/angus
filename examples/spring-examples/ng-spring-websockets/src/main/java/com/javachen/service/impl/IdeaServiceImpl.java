package com.javachen.service.impl;

import com.javachen.dto.IdeaDto;
import com.javachen.model.Idea;
import com.javachen.repository.IdeaRepository;
import com.javachen.service.IdeaService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class IdeaServiceImpl implements IdeaService {

    @Autowired
    private IdeaRepository repo;

    @Autowired
    private Mapper mapper;

    public List<Idea> getIdeas() {
        List<IdeaDto> list = repo.findAll();
        List<Idea> out = new ArrayList<Idea>();
        for (IdeaDto dto : list) {
            out.add(mapper.map(dto, Idea.class));
        }
        return out;
    }

    @Transactional
    @Override
    public Idea addIdea(Idea idea) {
        IdeaDto dto = mapper.map(idea, IdeaDto.class);
        return mapper.map(repo.saveAndFlush(dto), Idea.class);
    }

    @Transactional
    @Override
    public Idea updateIdea(Idea idea) {
        IdeaDto dto = repo.findOne(idea.getId());
        dto.setDescription(idea.getDescription());
        dto.setVotes(idea.getVotes());
        dto.setTitle(idea.getTitle());
        return mapper.map(repo.saveAndFlush(dto), Idea.class);
    }

    @Transactional
    @Override
    public void deleteIdea(Idea idea) {
        repo.delete(idea.getId());
    }
}
