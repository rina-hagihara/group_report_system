package actions.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class MissionEmployeeView {
    private Integer id;

    private EmployeeView employee;

    private MissionView mission;

}
