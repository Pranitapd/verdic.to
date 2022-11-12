package com.ancient.verdicto.service;

import com.ancient.verdicto.dto.SignUpesponseDto;
import com.ancient.verdicto.dto.SignupDto;
import com.ancient.verdicto.exception.CustomException;
import com.ancient.verdicto.model.DatabaseSequence;
import com.ancient.verdicto.model.User;
import com.ancient.verdicto.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Autowired
    private MongoOperations mongoOperations;
    Logger logger = (Logger) LoggerFactory.getLogger(UserService.class);

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserDetails(Long userId) {
        return null;
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public SignUpesponseDto signUp(SignupDto signupDto) throws CustomException {
        //Check if email exists
        if(Objects.nonNull(userRepository.findByEmailId(signupDto.getEmailId()))){
            throw new CustomException("User already exist");
        }
        //Encrypt the password
        String encryptedPassword = signupDto.getPassword();
        try{
            encryptedPassword = hashPassword(encryptedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }
        //Create a new user
        User newUser = new User(generateSequence(User.SEQUENCE_NAME),encryptedPassword,signupDto.getEmailId());
        try{
            userRepository.save(newUser);
            return new SignUpesponseDto("success","user created successfully");
        } catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }

    public long generateSequence(String seqName) throws CustomException {
//        DatabaseSequence counter = mongoOperations.findAndModify(Query.query(Criteria.where("_id").is(seqName)),
//                new Update().inc("seq",1),
//                FindAndModifyOptions.options().returnNew(true).upsert(true),
//                DatabaseSequence.class);
//        return !Objects.isNull(counter) ? counter.getSeq() : 1;

        //get sequence id
        Query query = new Query(Criteria.where("_id").is(seqName));

        //increase sequence id by 1
        Update update = new Update();
        update.inc("seq", 1);

        //return new increased id
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        //this is the magic happened.
        DatabaseSequence seqId =
                mongoOperations.findAndModify(query, update, options, DatabaseSequence.class);

        //if no id, throws SequenceException
        //optional, just a way to tell user when the sequence id is failed to generate.
        if (seqId == null) {
            throw new CustomException("Unable to get sequence id for key : " + seqName);
        }

        return seqId.getSeq();

    }
}
