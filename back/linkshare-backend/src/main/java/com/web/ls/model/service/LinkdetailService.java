package com.web.ls.model.service;

import com.web.ls.model.dto.linkbox.linkdetail.LinkDetailUpdateRequest;
import com.web.ls.model.dto.linkbox.linkdetail.LinkdetailRequest;
import com.web.ls.model.dto.linkbox.linkdetail.LinkdetailResponse;
import com.web.ls.model.entity.*;
import com.web.ls.model.repository.LinkDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Optional<LinkDetail> updateLink(LinkDetailUpdateRequest request) {
        Optional<LinkDetail> link = linkDetailRepository.findById(request.getId());

        link.ifPresent(l -> {
            if(request.getTitle() != null && request.getTitle().equals("")) {
                l.setTitle(request.getTitle());
            }

            if(request.getDesc() != null && request.getDesc().equals("")) {
                l.setDesc(request.getDesc());
            }

            if(request.getUrl() != null && request.getUrl().equals("")) {
                l.setUrl(request.getUrl());
            }

            linkDetailRepository.save(l);
        });

        return link;
    }
}
