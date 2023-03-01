package d83t.bpmbackend.domain.aggregate.location.dto;

import d83t.bpmbackend.domain.aggregate.location.entity.Location;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "위치 등록 응답 DTO")
public class LocationResponseDto {
    private Long id;
    private String address;
    private double latitude;
    private double longitude;

    @Builder
    public LocationResponseDto(Location location) {
        this.id = location.getId();
        this.address = location.getAddress();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }
}
