package com.spring.board.controller.api.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostListResponseDto {
    List<PostResponseDto> posts = new ArrayList<>();
}
