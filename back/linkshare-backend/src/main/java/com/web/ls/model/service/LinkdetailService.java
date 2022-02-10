package com.web.ls.model.service;

import com.web.ls.model.dto.linkbox.linkdetail.LinkDetailUpdateRequest;
import com.web.ls.model.dto.linkbox.linkdetail.LinkdetailRequest;
import com.web.ls.model.dto.linkbox.linkdetail.LinkdetailResponse;
import com.web.ls.model.entity.*;
import com.web.ls.model.repository.LinkDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinkdetailService {

    @Autowired
    LinkDetailRepository linkDetailRepository;

    public void createLink(LinkdetailRequest request) {
        LinkDetail link = request.toEntity();
        linkDetailRepository.save(link);
    }

    public void deleteLink(Integer linkId) {
        linkDetailRepository.deleteById(linkId);
    }

    public List<LinkdetailResponse> searchLinkList(Integer boxid) {
        return linkDetailRepository.findAllByBoxid(boxid).stream()
                .map(LinkdetailResponse::fromEntity).collect(Collectors.toList());
    }

    public void updateLink(LinkDetailUpdateRequest request) {

        LinkDetail detail = linkDetailRepository.getById(request.getId());
        detail.setTitle(request.getTitle());
        detail.setDesc(request.getDesc());
        detail.setUrl(request.getUrl());
        linkDetailRepository.save(detail);
    }

}
