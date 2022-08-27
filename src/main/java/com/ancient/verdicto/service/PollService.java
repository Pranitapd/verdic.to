package com.ancient.verdicto.service;

import com.ancient.verdicto.model.Poll;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface PollService {
    Poll getTodaysPoll();
    Set<Poll> getAllPolls();
    Poll savePoll(Poll poll);
    Poll getPoll(long pollId);
}
