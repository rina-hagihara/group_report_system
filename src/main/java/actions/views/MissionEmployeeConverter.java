package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.MissionEmployee;


/**
 * 従業員データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class MissionEmployeeConverter {


    public static MissionEmployee toModel(MissionEmployeeView mev) {

        return new MissionEmployee(
                mev.getId(),
                EmployeeConverter.toModel(mev.getEmployee()),
                MissionConverter.toModel(mev.getMission()));

    }


    public static MissionEmployeeView toView(MissionEmployee me) {

        if(me == null) {
            return null;
        }

        return new MissionEmployeeView(
                me.getId(),
                EmployeeConverter.toView(me.getEmployee()),
                MissionConverter.toView(me.getMission()));
    }


    public static List<MissionEmployeeView> toViewList(List<MissionEmployee> list) {
        List<MissionEmployeeView> mev = new ArrayList<>();

        for (MissionEmployee me : list) {
            mev.add(toView(me));
        }

        return mev;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param e DTOモデル(コピー先)
     * @param ev Viewモデル(コピー元)
     */
    public static void copyViewToModel(MissionEmployee me, MissionEmployeeView mev) {
        me.setId(mev.getId());
        me.setEmployee(EmployeeConverter.toModel(mev.getEmployee()));
        me.setMission(MissionConverter.toModel(mev.getMission()));
    }

}