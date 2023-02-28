package d83t.bpmbackend.domain.aggregate.location.dto;

import d83t.bpmbackend.domain.aggregate.location.entity.Location;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "위치 등록 요청 DTO")
public class LocationRequestDto {
    @NotBlank
    private String address;
    @NotNull
    private int latitude;
    @NotNull
    private int longitude;

    @Builder
    public LocationRequestDto(String address, int latitude, int longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location toEntity() {
        return Location.builder()
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
