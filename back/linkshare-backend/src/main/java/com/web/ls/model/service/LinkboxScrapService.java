package com.web.ls.model.service;

import com.web.ls.model.dto.linkbox.BoxScrapCreateRequest;
import com.web.ls.model.entity.BoxScrap;
import com.web.ls.model.repository.BoxScrapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkboxScrapService {

    @Autowired
    BoxScrapRepository boxScrapRepository;

    public void createBoxScrap(BoxScrapCreateRequest request) {
        BoxScrap boxScrap = request.toEntity();
        boxScrapRepository.save(boxScrap);
    }

    public void deleteBoxScrap(Integer scrapId) {
        boxScrapRepository.deleteById(scrapId);
    }

    public List<BoxScrap> searchBoxScrapListByBoxid(Integer boxId) {
        return boxScrapRepository.findAllByBoxidOrderByRegtime(boxId);
    }

    public List<BoxScrap> searchBoxScrapListByUid(Integer uid) {
        return boxScrapRepository.findAllByUidOrderByRegtime(uid);
    }
}
