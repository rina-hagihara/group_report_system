package actions.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class MissionCustomerView {
    private Integer id;

    private CustomerView customer;

    private MissionView mission;

}
