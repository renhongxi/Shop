package com.vito16.shop.service;

import com.vito16.shop.model.Remember;
import com.vito16.shop.repository.RememberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class RememberService {

    @Autowired
    RememberRepository rememberRepository;

    public void add(Remember remember) {
        rememberRepository.save(remember);
    }

    public void delete(String uuid){
        rememberRepository.delete(uuid);
    }

    public Remember findById(String uuid){
        return rememberRepository.findOne(uuid);
    }
}
