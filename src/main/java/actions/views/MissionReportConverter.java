package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.MissionReport;


/**
 * 従業員データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class MissionReportConverter {


    public static MissionReport toModel(MissionReportView mrv) {

        return new MissionReport(
                mrv.getId(),
                ReportConverter.toModel(mrv.getReport()),
                MissionConverter.toModel(mrv.getMission()));

    }


    public static MissionReportView toView(MissionReport mr) {

        if(mr == null) {
            return null;
        }

        return new MissionReportView(
                mr.getId(),
                ReportConverter.toView(mr.getReport()),
                MissionConverter.toView(mr.getMission()));
    }


    public static List<MissionReportView> toViewList(List<MissionReport> list) {
        List<MissionReportView> mrv = new ArrayList<>();

        for (MissionReport mr : list) {
            mrv.add(toView(mr));
        }

        return mrv;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param e DTOモデル(コピー先)
     * @param ev Viewモデル(コピー元)
     */
    public static void copyViewToModel(MissionReport mr, MissionReportView mrv) {
        mr.setId(mrv.getId());
        mr.setReport(ReportConverter.toModel(mrv.getReport()));
        mr.setMission(MissionConverter.toModel(mrv.getMission()));
    }

}