package com.lost.fake;

import com.lost.image.domain.ImagePost;
import com.lost.image.infra.entity.ImagePostJpaEntity;
import com.lost.image.service.repository.ImagePostRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FakeImagePostRepository implements ImagePostRepository {

    private final AtomicLong autoGeneratedId = new AtomicLong(1L);
    private final List<ImagePostJpaEntity> data = new ArrayList<>();

    @Override
    public ImagePost save(ImagePost image) {
        Long imageId = image.getId();
        if (imageId == null) {
            image = ImagePost.builder()
                    .id(autoGeneratedId.getAndIncrement())
                    .url(image.getUrl())
                    .fileName(image.getFileName())
                    .originalFileName(image.getOriginalFileName())
                    .fileSize(image.getFileSize())
                    .fileType(image.getFileType())
                    .createdAt(image.getCreatedAt())
                    .updatedAt(image.getUpdatedAt())
                    .build();
        } else {
            data.removeIf(item -> Objects.equals(item.getId(), imageId));
        }
        data.add(ImagePostJpaEntity.from(image, null));
        return image;
    }

    @Override
    public Optional<ImagePost> findById(Long imageId) {
        return data.stream()
                .filter(image -> image.getId().equals(imageId))
                .findAny()
                .map(ImagePostJpaEntity::toModel);
    }

    @Override
    public List<ImagePostJpaEntity> saveAll(List<ImagePostJpaEntity> imagePostJpaEntities) {
        List<ImagePostJpaEntity> result = new ArrayList<>();
        for (ImagePostJpaEntity imagePostJpaEntity : imagePostJpaEntities) {
            ImagePost image = save(imagePostJpaEntity.toModel());
            result.add(ImagePostJpaEntity.from(image, imagePostJpaEntity.getPostJpaEntity()));
        }
        return result;
    }
}
