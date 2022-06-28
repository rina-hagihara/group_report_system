package actions.views;

import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = JpaConst.TABLE_MR)



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class MissionReportView {
    private Integer id;

    private ReportView report;

    private MissionView mission;

}
