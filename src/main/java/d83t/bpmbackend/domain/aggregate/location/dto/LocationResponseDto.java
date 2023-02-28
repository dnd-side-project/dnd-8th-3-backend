package d83t.bpmbackend.domain.aggregate.location.dto;

import d83t.bpmbackend.domain.aggregate.location.entity.Location;
import lombok.Getter;

@Getter
public class LocationResponseDto {
    private Long id;
    private String address;
    private int latitude;
    private int longitude;

    public LocationResponseDto(Location location) {
        this.id = location.getId();
        this.address = location.getAddress();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }
}
