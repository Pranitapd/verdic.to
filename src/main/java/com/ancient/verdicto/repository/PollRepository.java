package com.ancient.verdicto.repository;

import com.ancient.verdicto.model.Poll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PollRepository extends MongoRepository<Poll, Long> {
    List<Poll> findByPollStartDateBetween(LocalDate fromDate, LocalDate todate);
    Poll findByPollStartDate(LocalDate today);
    Poll findByPollId(long pollId);
}
