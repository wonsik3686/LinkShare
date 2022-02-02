package com.web.ls.model.dto.linkbox;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LinkboxCreateRequest implements Serializable {
    private final int uid;
    private final String title;
    private final String desc;
    private final List<String> interests;
}
