package com.spring.board.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateForm {
    public Long id;
    public String title;
    public String content;
}
