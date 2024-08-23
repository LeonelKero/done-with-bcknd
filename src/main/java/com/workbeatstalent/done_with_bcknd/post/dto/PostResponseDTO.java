package com.workbeatstalent.done_with_bcknd.post.dto;

import java.math.BigDecimal;
import java.util.List;

public record PostResponseDTO(Long id, String title, List<ImageDTO> images, BigDecimal price, Integer categoryId, Long userId,
                              LocationDTO location) {
}
