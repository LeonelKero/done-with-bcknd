package com.workbeatstalent.done_with_bcknd.post.dto;

public record PostRequestDTO(String title, Double price, String description, String categoryName, Double latitude, Double longitude) {
}
