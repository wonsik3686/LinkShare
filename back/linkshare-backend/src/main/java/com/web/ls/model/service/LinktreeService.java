package com.web.ls.model.service;

import com.sun.source.tree.Tree;
import com.web.ls.model.dto.linkbox.linktree.TreeInfoCreateRequest;
import com.web.ls.model.dto.linkbox.linktree.TreeInfoUpdateRequest;
import com.web.ls.model.entity.TreeInfo;
import com.web.ls.model.repository.TreeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinktreeService {

    @Autowired
    TreeInfoRepository treeInfoRepository;

    public void createLinktree(TreeInfoCreateRequest request) {
        TreeInfo info = request.toEntity();
        treeInfoRepository.save(info);
    }

    public void deleteLinktree(Integer treeid) {
        treeInfoRepository.deleteById(treeid);
    }

    public void updateLinktree(TreeInfoUpdateRequest request) {
        TreeInfo tree = treeInfoRepository.getById(request.getId());
        tree.setJson(request.getJson());
        treeInfoRepository.save(tree);
    }

    public List<TreeInfo> searchTreeListByBoxid(Integer boxid) {
        return treeInfoRepository.findAllByBoxid(boxid);
    }
}
