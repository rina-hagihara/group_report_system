package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.Mission;

/**
 * 従業員データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class MissionConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param mv MissionViewのインスタンス
     * @return Missionのインスタンス
     */
    public static Mission toModel(MissionView mv) {

        return new Mission(
                mv.getId(),
                mv.getTitle(),
                mv.getContent(),
                mv.getVisitStart(),
                mv.getVisitFinish(),
                mv.getTrackSize(),
                mv.getMoveStart(),
                mv.getMoveFinish(),
                mv.getPayDue(),
                mv.getCreatedAt(),
                mv.getUpdatedAt(),
                mv.getDeleteFlag() == null
                        ? null
                        : mv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                ? JpaConst.MIS_DEL_TRUE
                                : JpaConst.MIS_DEL_FALSE);
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param e Missionのインスタンス
     * @return MissionViewのインスタンス
     */
    public static MissionView toView(Mission m) {
        if(m == null) {
            return null;
        }



        return new MissionView(
                m.getId(),
                m.getTitle(),
                m.getContent(),
                m.getVisitStart(),
                m.getVisitFinish(),
                m.getTrackSize(),
                m.getMoveStart(),
                m.getMoveFinish(),
                m.getPayDue(),
                m.getCreatedAt(),
                m.getUpdatedAt(),
                m.getDeleteFlag() == null
                        ? null
                        : m.getDeleteFlag() == JpaConst.MIS_DEL_TRUE
                                ? AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                : AttributeConst.DEL_FLAG_FALSE.getIntegerValue());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<MissionView> toViewList(List<Mission> list) {
        List<MissionView> mvs = new ArrayList<>();

        for (Mission m : list) {
            mvs.add(toView(m));
        }

        return mvs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param e DTOモデル(コピー先)
     * @param mv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Mission m, MissionView mv) {
        m.setId(mv.getId());
        m.setTitle(mv.getTitle());
        m.setContent(mv.getContent());
        m.setVisitStart(mv.getVisitStart());
        m.setVisitFinish(mv.getVisitFinish());
        m.setTrackSize(mv.getTrackSize());
        m.setMoveStart(mv.getMoveStart());
        m.setMoveFinish(mv.getMoveFinish());
        m.setPayDue(mv.getPayDue());
        m.setCreatedAt(mv.getCreatedAt());
        m.setUpdatedAt(mv.getUpdatedAt());
        m.setDeleteFlag(mv.getDeleteFlag());

    }

}