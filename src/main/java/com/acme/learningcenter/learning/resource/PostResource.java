package com.acme.learningcenter.learning.resource;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostResource {
    private Long id;
    private String title;
    private String description;
    private String content;
}
