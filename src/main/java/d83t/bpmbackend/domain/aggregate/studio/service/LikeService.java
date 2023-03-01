package d83t.bpmbackend.domain.aggregate.studio.service;

import d83t.bpmbackend.domain.aggregate.user.entity.User;

public interface LikeService {
    void createLike(User user, Long reviewId);
    void deleteLike(User user, Long reviewId);
}
