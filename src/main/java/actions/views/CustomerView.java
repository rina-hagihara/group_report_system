package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class CustomerView {
    private Integer id;

    private String code;

    private String name;

    private String tel;

    private String address;

    private LocalDateTime moveDay;

    private Integer payFlag;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer deleteFlag;
}