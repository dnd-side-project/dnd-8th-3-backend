package d83t.bpmbackend.domain.aggregate.studio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "스튜디오 등록 요청 DTO")
public class StudioRequestDto {
    @NotBlank(message = "스튜디오 이름은 필수입니다")
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private int phone;
    private String sns;
    private String openHours;
    private String price;

    @Builder
    public StudioRequestDto(String name, String address, double latitude, double longitude, int phone, String sns, String openHours, String price) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.sns = sns;
        this.openHours = openHours;
        this.price = price;
    }
}
