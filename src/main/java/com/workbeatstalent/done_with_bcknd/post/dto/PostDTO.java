package com.workbeatstalent.done_with_bcknd.post.dto;

import java.math.BigDecimal;
import java.util.List;

public record PostDTO(String title, Double price, String description, List<String> images, String category,
                      Long authorId, Double latitude, Double longitude) {
}
