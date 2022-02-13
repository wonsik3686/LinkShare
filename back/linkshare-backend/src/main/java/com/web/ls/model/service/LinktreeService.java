package com.web.ls.model.service;

import com.web.ls.exception.AlreadyExistTreeInfoException;
import com.web.ls.model.dto.linkbox.linktree.TreeInfoCreateRequest;
import com.web.ls.model.dto.linkbox.linktree.TreeInfoResponse;
import com.web.ls.model.dto.linkbox.linktree.TreeInfoUpdateRequest;
import com.web.ls.model.entity.TreeInfo;
import com.web.ls.model.repository.TreeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.List;

@Service
public class LinktreeService {

    @Autowired
    TreeInfoRepository treeInfoRepository;

    public void createLinktree(TreeInfoCreateRequest request) {
        if(!treeInfoRepository.existsByBoxid(request.getBoxid())) {
            TreeInfo info = request.toEntity();
            treeInfoRepository.save(info);
        } else {
            throw new AlreadyExistTreeInfoException();
        }
    }

    public void deleteLinktree(Integer treeid) {
        treeInfoRepository.deleteById(treeid);
    }

    public void updateLinktree(TreeInfoUpdateRequest request) {
        TreeInfo tree = treeInfoRepository.getById(request.getId());
        tree.setJson(request.getJson());
        treeInfoRepository.save(tree);
    }

    public List<TreeInfoResponse> searchTreeListByBoxid(Integer boxid) {
        List<TreeInfoResponse> list = new ArrayList<TreeInfoResponse>();
        for (TreeInfo treeInfo: treeInfoRepository.findAllByBoxid(boxid)) {
            list.add(TreeInfoResponse.fromEntity(treeInfo));
        }

        return list;
    }
}
