package com.ancient.verdicto.controller;

import com.ancient.verdicto.model.Poll;
import com.ancient.verdicto.service.PollService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Set;

@RestController
public class PollController {

    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @ResponseBody
    @GetMapping("/allPolls")
    public Set<Poll> getAllPolls(){
        return pollService.getAllPolls();
    }

    @ResponseBody
    @GetMapping("/todaysPoll")
    public Poll getTodaysPoll(@RequestParam long userId){
        return pollService.getTodaysPoll();
    }

    @PostMapping("/createPoll")
    public ResponseEntity<String> createNewPoll(@RequestBody Poll poll){
        pollService.savePoll(poll);
        return new ResponseEntity<>("Poll created", HttpStatus.OK);
    }

    @PostMapping("/submitPollAnswer")
    public void submitPollAnswer(@RequestParam long pollId,
                                 @RequestParam String userId,
                                 @RequestParam String answer){
        //Todo save the answer given by a user for given question
    }

//    @PatchMapping("/endPoll") -- this gives error
    @RequestMapping(value = "/endPoll",
            produces = "application/json",method = RequestMethod.PATCH)
    public ResponseEntity<String> endPoll(@RequestParam long pollId,
                        @RequestParam String userId){
        Poll poll = pollService.getPoll(pollId);
        poll.setActive(false);
        pollService.savePoll(poll);
        return new ResponseEntity<>("Poll has ended succefully", HttpStatus.OK);
    }
}
