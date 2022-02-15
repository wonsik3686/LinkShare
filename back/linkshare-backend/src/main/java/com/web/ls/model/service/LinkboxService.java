package com.web.ls.model.service;

import com.web.ls.LinkshareBackendApplication;
import com.web.ls.model.dto.linkbox.LinkboxCreateRequest;
import com.web.ls.model.dto.linkbox.LinkboxInfoResponse;
import com.web.ls.model.dto.linkbox.LinkboxInterestRequest;
import com.web.ls.model.entity.*;
import com.web.ls.model.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.*;

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

    @Autowired
    UserInterestRepository userInterestRepository;

    @Autowired
    FollowRepository followRepository;

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
            list.add(prepareLinkboxInfoResponse(linkbox));
        }
        return list;
    }



    public List<LinkboxInfoResponse> searchLinkboxListByUserId(Integer userId) {

        List<LinkboxInfoResponse> responseList = new ArrayList<>();
        List<Userbox> userBoxList = userboxRepository.findAllByUid(userId);
        for (Userbox userBox: userBoxList) {
            LinkboxInfoResponse info = new LinkboxInfoResponse();
            Linkbox box = linkboxRepository.getById(userBox.getBoxid());
            responseList.add(prepareLinkboxInfoResponse(box));
        }
        return responseList;
    }

    public LinkboxInfoResponse searchLinkboxByBoxid(Integer boxId) {

        LinkboxInfoResponse info = new LinkboxInfoResponse();
        Linkbox box = linkboxRepository.getById(boxId);
        return prepareLinkboxInfoResponse(box);
    }

    public List<LinkboxInfoResponse> searchLinkboxListByLikes() {

        List<Integer> boxIdList = likesRepository.findTopBoxIdOrderByCountLimit6();
        List<LinkboxInfoResponse> resList = new ArrayList<>();

        for (Integer boxid: boxIdList) {
            Optional<Linkbox> box = linkboxRepository.findById(boxid);
            if(box.isPresent()) {
                resList.add(prepareLinkboxInfoResponse(box.get()));
            }
        }
        return resList;
    }

    public List<LinkboxInfoResponse> searchLinkboxListByUserInterests(Integer uid) {

        List<UserInterest> userInterestList = userInterestRepository.findAllByUid(uid);
        Map<Integer, LinkboxInfoResponse> linkboxInfoMap = new HashMap<Integer, LinkboxInfoResponse>();
        for (UserInterest userInterest: userInterestList) {
            Integer interestId = userInterest.getInterestId();
            for (BoxInterest boxInterest: boxInterestRepository.findAllByInterestId(interestId)) {
                Optional<Linkbox> linkbox = linkboxRepository.findById(boxInterest.getBoxid());
                if(linkbox.isPresent()) {
                    linkboxInfoMap.put(linkbox.get().getId(), prepareLinkboxInfoResponse(linkbox.get()));
                }
            }
        }

        return new ArrayList<>(linkboxInfoMap.values());
    }

    public List<LinkboxInfoResponse> searchLinkboxListByFollow(Integer uid) {

        List<Follow> followList = followRepository.findAllByUid(uid);
        Map<Integer, LinkboxInfoResponse> linkboxInfoMap = new HashMap<Integer, LinkboxInfoResponse>();
        for (Follow follow :
                followList) {
            List<Userbox> userboxList = userboxRepository.findAllByUid(follow.getFolloweeId());
            for (Userbox userbox:
                 userboxList) {
                linkboxInfoMap.put(userbox.getBoxid(), prepareLinkboxInfoResponse(linkboxRepository.getById(userbox.getBoxid())));
            }
        }
        return new ArrayList<>(linkboxInfoMap.values());
    }

    public List<LinkboxInfoResponse> searchLinkboxListByKeyword(String keyword) {

        Map<Integer, LinkboxInfoResponse> linkboxInfoMap = new HashMap<Integer, LinkboxInfoResponse>();

        List<Interest> interestList = interestRepository.findAllByNameContains(keyword);
        for (Interest interest:
             interestList) {
            List<BoxInterest> boxInterestList = boxInterestRepository.findAllByInterestId(interest.getId());
            for (BoxInterest boxInterest:
                 boxInterestList) {
                linkboxInfoMap.put(boxInterest.getBoxid(), prepareLinkboxInfoResponse(
                        linkboxRepository.getById(boxInterest.getBoxid())));
            }
        }

        List<Linkbox> linkboxListTitle = linkboxRepository.findAllByTitleContains(keyword);
        for (Linkbox box:
             linkboxListTitle) {
            linkboxInfoMap.put(box.getId(), prepareLinkboxInfoResponse(
                    linkboxRepository.getById(box.getId())
            ));
        }

        List<Linkbox> linkboxListDesc = linkboxRepository.findAllByDescContains(keyword);
        for (Linkbox box:
                linkboxListDesc) {
            linkboxInfoMap.put(box.getId(), prepareLinkboxInfoResponse(
                    linkboxRepository.getById(box.getId())
            ));
        }

        return new ArrayList<>(linkboxInfoMap.values());
    }

    private LinkboxInfoResponse prepareLinkboxInfoResponse(Linkbox linkbox) {
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

        Userbox userbox = userboxRepository.findByBoxidOrderById(linkbox.getId());
        info.setUid(userbox.getUid());

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
