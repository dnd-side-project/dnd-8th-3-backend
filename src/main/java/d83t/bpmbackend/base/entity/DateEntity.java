package d83t.bpmbackend.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
public class DateEntity {

    @Column(name = "created_at")
    private ZonedDateTime createdDate;

    @Column(name = "update_at")
    private ZonedDateTime modifiedDate;

    @PrePersist
    void prePersist() {
        this.createdDate = ZonedDateTime.now();
        this.modifiedDate = ZonedDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        this.modifiedDate = ZonedDateTime.now();
    }
}

