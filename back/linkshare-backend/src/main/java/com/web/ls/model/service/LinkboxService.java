package com.web.ls.model.service;

import com.web.ls.model.dto.linkbox.LinkboxCreateRequest;
import com.web.ls.model.dto.linkbox.LinkboxInfoResponse;
import com.web.ls.model.dto.linkbox.LinkboxInterestRequest;
import com.web.ls.model.entity.BoxInterest;
import com.web.ls.model.entity.Interest;
import com.web.ls.model.entity.Linkbox;
import com.web.ls.model.entity.Userbox;
import com.web.ls.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    BoxCommentRepository boxCommentRepository;

    @Autowired
    BoxScrapRepository boxScrapRepository;

    @Autowired
    LikesRepository likesRepository;

    @Autowired
    LinkDetailRepository linkDetailRepository;

    @Autowired
    TreeInfoRepository treeInfoRepository;

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

    public void deleteLinkbox(int boxid) {

        userboxRepository.deleteByBoxid(boxid);
        boxInterestRepository.deleteByBoxid(boxid);
        likesRepository.deleteByBoxid(boxid);
        boxCommentRepository.deleteByBoxid(boxid);
        boxScrapRepository.deleteByBoxid(boxid);
        linkDetailRepository.deleteByBoxid(boxid);
        treeInfoRepository.deleteByBoxid(boxid);
        linkboxRepository.deleteById(boxid);
    }

    public boolean existsLinkboxById(Integer boxid) {
        if(linkboxRepository.existsById(boxid)) {
            return true;
        }
        return false;
    }

    public void updateLinkboxViewCountById(int boxid) {
        linkboxRepository.setViewCountPlusOne(boxid);
    }

    public void updateLinkboxTitle(String title, int boxid) {
        linkboxRepository.setTitle(title, boxid);
    }

    public void updateLinkboxDesc(String desc, int boxid) {
        linkboxRepository.setDesc(desc, boxid);
    }

    public List<LinkboxInfoResponse> searchAllLinkboxList() {
        List<LinkboxInfoResponse> list = new ArrayList<>();

        List<Linkbox> linkboxes = linkboxRepository.findAll();

        for (Linkbox linkbox: linkboxes) {
            LinkboxInfoResponse info = new LinkboxInfoResponse();
            info.setId(linkbox.getId());
            info.setDesc(linkbox.getDesc());
            info.setTitle(linkbox.getTitle());
            info.setViewCount(linkbox.getViewCount());
            info.setRegtime(linkbox.getRegtime());
            info.setInterests(boxInterestRepository.findInterestNameByBoxid(linkbox.getId()));
            info.setLikeCount(likesRepository.countByBoxid(linkbox.getId()));
            info.setCommentCount(boxCommentRepository.countByBoxid(linkbox.getId()));
            info.setScrapCount(boxScrapRepository.countByBoxid(linkbox.getId()));

            list.add(info);
        }
        return list;
    }

    public List<LinkboxInfoResponse> searchLinkboxListByUserId(Integer userId) {
        List<LinkboxInfoResponse> responseList = new ArrayList<>();

        List<Userbox> userBoxList = userboxRepository.findAllByUid(userId);

        for (Userbox userBox: userBoxList) {
            LinkboxInfoResponse info = new LinkboxInfoResponse();

            Linkbox box = linkboxRepository.getById(userBox.getBoxid());
            info.setId(box.getId());
            info.setDesc(box.getDesc());
            info.setTitle(box.getTitle());
            info.setViewCount(box.getViewCount());
            info.setRegtime(box.getRegtime());
            info.setInterests(boxInterestRepository.findInterestNameByBoxid(box.getId()));
            info.setLikeCount(likesRepository.countByBoxid(box.getId()));
            info.setCommentCount(boxCommentRepository.countByBoxid(box.getId()));
            info.setScrapCount(boxScrapRepository.countByBoxid(box.getId()));

            responseList.add(info);
        }
        return responseList;
    }

    public LinkboxInfoResponse searchLinkboxByBoxid(Integer boxId) {
        LinkboxInfoResponse info = new LinkboxInfoResponse();
        Linkbox box = linkboxRepository.getById(boxId);
        info.setId(box.getId());
        info.setDesc(box.getDesc());
        info.setTitle(box.getTitle());
        info.setViewCount(box.getViewCount());
        info.setRegtime(box.getRegtime());
        info.setInterests(boxInterestRepository.findInterestNameByBoxid(box.getId()));
        info.setLikeCount(likesRepository.countByBoxid(box.getId()));
        info.setCommentCount(boxCommentRepository.countByBoxid(box.getId()));
        info.setScrapCount(boxScrapRepository.countByBoxid(box.getId()));

        return info;
    }

    public void createLinkboxInterest(LinkboxInterestRequest request) {

        if(!interestRepository.existsByName(request.getInterest())) {
            Interest interest = new Interest();
            interest.setName(request.getInterest());
            interestRepository.save(interest);
        }
        BoxInterest boxInterest = new BoxInterest();
        boxInterest.setBoxid(request.getBoxid());
        boxInterest.setInterestId(interestRepository.findIdByName(request.getInterest()));
        boxInterestRepository.save(boxInterest);
    }

    public void deleteLinkboxInterest(LinkboxInterestRequest request) {

        boxInterestRepository.deleteByBoxidAndInterestId(request.getBoxid(), interestRepository.findIdByName(request.getInterest()));
    }

    public List<String> searchInterestsByBoxid(Integer boxId) {
        return boxInterestRepository.findInterestNameByBoxid(boxId);
    }
}
