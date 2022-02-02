package com.web.ls.model.service;

import com.web.ls.model.dto.linkbox.LinkboxCreateRequest;
import com.web.ls.model.entity.BoxInterest;
import com.web.ls.model.entity.Interest;
import com.web.ls.model.entity.Linkbox;
import com.web.ls.model.entity.Userbox;
import com.web.ls.model.repository.BoxInterestRepository;
import com.web.ls.model.repository.InterestRepository;
import com.web.ls.model.repository.LinkboxRepository;
import com.web.ls.model.repository.UserboxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkboxService {

    @Autowired
    LinkboxRepository linkboxRepository;

    @Autowired
    UserboxRepository userboxRepository;

    @Autowired
    BoxInterestRepository boxInterestRepository;

    @Autowired
    InterestRepository interestRepository;

    public void createLinkbox(LinkboxCreateRequest request) {
        Linkbox linkbox = new Linkbox();
        linkbox.setTitle(request.getTitle());
        linkbox.setDesc(request.getDesc());
        linkbox.setViewCount(0);
        linkbox = linkboxRepository.save(linkbox);

        Userbox userbox = new Userbox();
        userbox.setUid(request.getUid());
        userbox.setBoxid(linkbox.getId());
        userboxRepository.save(userbox);

        for (String interestName: request.getInterests()) {
            if(!interestRepository.existsByName(interestName)) {
                Interest interest = new Interest();
                interest.setName(interestName);
                interestRepository.save(interest);
            }
            BoxInterest boxInterest = new BoxInterest();
            boxInterest.setBoxid(linkbox.getId());
            boxInterest.setInterestId(interestRepository.findIdByName(interestName));
            boxInterestRepository.save(boxInterest);
        }

    }
}
