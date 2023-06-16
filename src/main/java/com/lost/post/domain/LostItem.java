package com.lost.post.domain;

import com.lost.image.domain.Image;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LostItem {

    private final String name;
    private final Address address;
    private final List<Image> images;

    @Builder
    public LostItem(String name, Address address, List<Image> images) {
        this.name = name;
        this.address = address;
        this.images = images;
    }
}