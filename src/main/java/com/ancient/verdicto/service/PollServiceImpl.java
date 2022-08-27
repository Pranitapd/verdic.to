package com.ancient.verdicto.service;

import com.ancient.verdicto.model.Poll;
import com.ancient.verdicto.repository.PollRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PollServiceImpl implements PollService {
    private final PollRepository pollRepository;

    public PollServiceImpl(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @Override
    public Poll getTodaysPoll() {
        LocalDate todaysDate = LocalDate.now();
        return pollRepository.findByPollStartDate(todaysDate);
    }

    @Override
    public Set<Poll> getAllPolls() {
        Set<Poll> allPolls = new HashSet<>();
        pollRepository.findAll().iterator().forEachRemaining(allPolls::add);
        return allPolls;
    }

    @Override
    public Poll savePoll(Poll poll) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        return pollRepository.save(poll);
    }

    @Override
    public Poll getPoll(long pollId) {
        return pollRepository.findByPollId(pollId);
    }


}
