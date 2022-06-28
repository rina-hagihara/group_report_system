package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.MissionConverter;
import actions.views.MissionCustomerConverter;
import actions.views.MissionCustomerView;
import actions.views.MissionEmployeeConverter;
import actions.views.MissionEmployeeView;
import actions.views.MissionReportConverter;
import actions.views.MissionReportView;
import actions.views.MissionView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.JpaConst;
import models.Mission;
import models.MissionCustomer;
import models.MissionEmployee;
import models.MissionReport;
import models.Report;
import models.validators.MissionValidator;

/**
 * 従業員テーブルの操作に関わる処理を行うクラス
 */
public class MissionService extends ServiceBase {

    /**
     * 指定されたページ数の一覧画面に表示するデータを取得し、MissionViewのリストで返却する
     * @param page ページ数
     * @return 表示するデータのリスト
     */
    public List<MissionView> getPerPage(int page) {
        List<Mission> missions = em.createNamedQuery(JpaConst.Q_MIS_GET_ALL, Mission.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();

        return MissionConverter.toViewList(missions);
    }

    /**
     * 従業員テーブルのデータの件数を取得し、返却する
     * @return 従業員テーブルのデータの件数
     */
    public long countAll() {
        long misCount = (long) em.createNamedQuery(JpaConst.Q_MIS_COUNT, Long.class)
                .getSingleResult();

        return misCount;
    }

    /**
     * idを条件に取得したデータをMissionViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public MissionView findOne(int id) {
        Mission m = findOneInternal(id);
        return MissionConverter.toView(m);
    }


    /**
     * 画面から入力された従業員の登録内容を元にデータを1件作成し、従業員テーブルに登録する
     * @param ev 画面から入力された従業員の登録内容
     * @param pepper pepper文字列
     * @return バリデーションや登録処理中に発生したエラーのリスト
     */
    public List<String> create(MissionView mv) {

        //登録日時、更新日時は現在時刻を設定する
        LocalDateTime now = LocalDateTime.now();
        mv.setCreatedAt(now);
        mv.setUpdatedAt(now);

        //登録内容のバリデーションを行う
        List<String> errors = MissionValidator.validate(this, mv);

        //バリデーションエラーがなければデータを登録する
        if (errors.size() == 0) {
            createCommit(mv);
        }

        //エラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力された従業員の更新内容を元にデータを1件作成し、従業員テーブルを更新する
     * @param ev 画面から入力された従業員の登録内容
     * @param pepper pepper文字列
     * @return バリデーションや更新処理中に発生したエラーのリスト
     */
    public List<String> update(MissionView mv) {

        //idを条件に登録済みの案件情報を取得する
        MissionView savedMis = findOne(mv.getId());
        System.out.println("登録済みの案件：" + savedMis.getId());

        savedMis.setTitle(mv.getTitle()); //変更後の案件名を設定する
        savedMis.setContent(mv.getContent()); //変更後の内容を設定する
        savedMis.setVisitStart(mv.getVisitStart()); //変更後の訪問予定日時を設定する
        savedMis.setVisitFinish(mv.getVisitFinish()); //変更後の訪問終了日時を設定する
        savedMis.setTrackSize(mv.getTrackSize()); //変更後のトラックサイズを設定する
        savedMis.setMoveStart(mv.getMoveStart()); //変更後の引っ越し予定日時を設定する
        savedMis.setMoveFinish(mv.getMoveFinish()); //変更後の引っ越し完了日時を設定する
        savedMis.setPayDue(mv.getPayDue()); //変更後の支払い完了日を設定する



        //更新日時に現在時刻を設定する
        LocalDateTime today = LocalDateTime.now();
        savedMis.setUpdatedAt(today);

        //更新内容についてバリデーションを行う
        List<String> errors = MissionValidator.validate(this, savedMis);

        //バリデーションエラーがなければデータを更新する
        if (errors.size() == 0) {
            update(savedMis);
        }

        //エラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * idを条件に従業員データを論理削除する
     * @param id
     */
    public void destroy(Integer id) {

        //idを条件に登録済みの従業員情報を取得する
        MissionView savedMis = findOne(id);

        //更新日時に現在時刻を設定する
        LocalDateTime today = LocalDateTime.now();
        savedMis.setUpdatedAt(today);

        //論理削除フラグをたてる
        savedMis.setDeleteFlag(JpaConst.MIS_DEL_TRUE);

        //更新処理を行う
        updateCommit(savedMis);

    }



    /**
     * idを条件にデータを1件取得し、Missionのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    private Mission findOneInternal(int id) {
        Mission m = em.find(Mission.class, id);

        return m;
    }

    /**
     * 従業員データを1件登録する
     * @param ev 従業員データ
     * @return 登録結果(成功:true 失敗:false)
     */
    private void createCommit(MissionView mv) {

        em.getTransaction().begin();
        em.persist(MissionConverter.toModel(mv));
        em.getTransaction().commit();

    }

    /**
     * 従業員データを更新する
     * @param ev 画面から入力された従業員の登録内容
     */
    private void updateCommit(MissionView mv) {

        em.getTransaction().begin();
        Mission m = findOneInternal(mv.getId());
        MissionConverter.copyViewToModel(m, mv);
        em.getTransaction().commit();

    }
    @SuppressWarnings("unused")
    public void createMissionEmployee(MissionEmployeeView mev) {
        MissionEmployee me = MissionEmployeeConverter.toModel(mev);
        em.getTransaction().begin();
        em.persist(me);
        em.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public List<MissionEmployeeView> getAssignedEmps (MissionView mv){
        Mission m = MissionConverter.toModel(mv);
        List<MissionEmployee> missionEmps = em.createNamedQuery(JpaConst.Q_GET_ASSIGNED)
                                            .setParameter(JpaConst.JPQL_PARM_MISSION, m)
                                            .getResultList();
        List<MissionEmployeeView> mevs = MissionEmployeeConverter.toViewList(missionEmps);

        return mevs;
    }

    public List<MissionCustomerView> getRegistered(MissionView mv){
        Mission m = MissionConverter.toModel(mv);
        List<MissionCustomer> mc = em.createNamedQuery(JpaConst.Q_GET_REGISTERED, MissionCustomer.class)
                                    .setParameter(JpaConst.JPQL_PARM_MISSION, m)
                                    .getResultList();
        List<MissionCustomerView> mcv = MissionCustomerConverter.toViewList(mc);
        return mcv;
    }

    public void createMissionCustomer(MissionCustomerView mcv) {
        MissionCustomer mc = MissionCustomerConverter.toModel(mcv);
        em.getTransaction().begin();
        em.persist(mc);
        em.getTransaction().commit();
    }

    public void createMissionReport(ReportView rv, MissionReportView mrv) {
        Report r = ReportConverter.toModel(rv);
        MissionReport mr = MissionReportConverter.toModel(mrv);
        System.out.println("日報ID：" + r.getId());
        System.out.println("案面日報のID：" + mr.getId());
        em.getTransaction().begin();
        em.persist(r);
        em.persist(mr);
        em.getTransaction().commit();
    }
}

