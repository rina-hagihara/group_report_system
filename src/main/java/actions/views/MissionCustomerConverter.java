package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.MissionCustomer;


/**
 * 従業員データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class MissionCustomerConverter {


    public static MissionCustomer toModel(MissionCustomerView mcv) {

        return new MissionCustomer(
                mcv.getId(),
                CustomerConverter.toModel(mcv.getCustomer()),
                MissionConverter.toModel(mcv.getMission()));

    }


    public static MissionCustomerView toView(MissionCustomer mc) {

        if(mc == null) {
            return null;
        }

        return new MissionCustomerView(
                mc.getId(),
                CustomerConverter.toView(mc.getCustomer()),
                MissionConverter.toView(mc.getMission()));
    }


    public static List<MissionCustomerView> toViewList(List<MissionCustomer> list) {
        List<MissionCustomerView> mcv = new ArrayList<>();

        for (MissionCustomer mc : list) {
            mcv.add(toView(mc));
        }

        return mcv;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param e DTOモデル(コピー先)
     * @param ev Viewモデル(コピー元)
     */
    public static void copyViewToModel(MissionCustomer mc, MissionCustomerView mcv) {
        mc.setId(mcv.getId());
        mc.setCustomer(CustomerConverter.toModel(mcv.getCustomer()));
        mc.setMission(MissionConverter.toModel(mcv.getMission()));
    }

}