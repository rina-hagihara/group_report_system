package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.CustomerConverter;
import actions.views.CustomerView;
import constants.JpaConst;
import models.Customer;
import models.validators.CustomerValidator;

/**
 * 従業員テーブルの操作に関わる処理を行うクラス
 */
public class CustomerService extends ServiceBase {

    /**
     * 指定されたページ数の一覧画面に表示するデータを取得し、EmployeeViewのリストで返却する
     * @param page ページ数
     * @return 表示するデータのリスト
     */
    public List<CustomerView> getPerPage(int page) {
        List<Customer> customers = em.createNamedQuery(JpaConst.Q_CUS_GET_ALL, Customer.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();

        return CustomerConverter.toViewList(customers);
    }

    /**
     * 従業員テーブルのデータの件数を取得し、返却する
     * @return 従業員テーブルのデータの件数
     */
    public long countAll() {
        long cusCount = (long) em.createNamedQuery(JpaConst.Q_CUS_COUNT, Long.class)
                .getSingleResult();

        return cusCount;
    }


    /**
     * idを条件に取得したデータをEmployeeViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public CustomerView findOne(int id) {
        Customer c = findOneInternal(id);
        return CustomerConverter.toView(c);
    }

    /**
     * 社員番号を条件に該当するデータの件数を取得し、返却する
     * @param code 社員番号
     * @return 該当するデータの件数
     */
    public long countByCode(String code) {

        //指定した社員番号を保持する従業員の件数を取得する
        long customers_count = (long) em.createNamedQuery(JpaConst.Q_CUS_COUNT_RESISTERED_BY_CODE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_CODE, code)
                .getSingleResult();
        return customers_count;
    }

    /**
     * 画面から入力された従業員の登録内容を元にデータを1件作成し、従業員テーブルに登録する
     * @param ev 画面から入力された従業員の登録内容
     * @param pepper pepper文字列
     * @return バリデーションや登録処理中に発生したエラーのリスト
     */
    public List<String> create(CustomerView cv) {


        //登録日時、更新日時は現在時刻を設定する
        LocalDateTime now = LocalDateTime.now();
        cv.setCreatedAt(now);
        cv.setUpdatedAt(now);

        //登録内容のバリデーションを行う
        List<String> errors = CustomerValidator.validate(this, cv, true);

        //バリデーションエラーがなければデータを登録する
        if (errors.size() == 0) {
            createCommit(cv);
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
    public List<String> update(CustomerView cv) {

        //idを条件に登録済みの従業員情報を取得する
        CustomerView savedCus = findOne(cv.getId());

        boolean validateCode = false;
        if (!savedCus.getCode().equals(cv.getCode())) {
            //社員番号を更新する場合

            //社員番号についてのバリデーションを行う
            validateCode = true;
            //変更後の社員番号を設定する
            savedCus.setCode(cv.getCode());
        }

        savedCus.setName(cv.getName()); //変更後の氏名を設定する
        savedCus.setTel(cv.getTel()); //変更後の電話番号を設定する
        savedCus.setAddress(cv.getAddress()); //変更後の住所を設定する
        savedCus.setMoveDay(cv.getMoveDay()); //変更後の住所を設定する
        savedCus.setPayFlag(cv.getPayFlag()); //変更後の支払いフラグを設定する

        //更新日時に現在時刻を設定する
        LocalDateTime today = LocalDateTime.now();
        savedCus.setUpdatedAt(today);

        //更新内容についてバリデーションを行う
        List<String> errors = CustomerValidator.validate(this, savedCus, validateCode);

        //バリデーションエラーがなければデータを更新する
        if (errors.size() == 0) {
            updateCommit(savedCus);
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
        CustomerView savedCus = findOne(id);

        //更新日時に現在時刻を設定する
        LocalDateTime today = LocalDateTime.now();
        savedCus.setUpdatedAt(today);

        //論理削除フラグをたてる
        savedCus.setDeleteFlag(JpaConst.CUS_DEL_TRUE);

        //更新処理を行う
        update(savedCus);

    }



    /**
     * idを条件にデータを1件取得し、Employeeのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    private Customer findOneInternal(int id) {
        Customer c = em.find(Customer.class, id);

        return c;
    }

    /**
     * 従業員データを1件登録する
     * @param ev 従業員データ
     * @return 登録結果(成功:true 失敗:false)
     */
    private void createCommit(CustomerView cv) {

        em.getTransaction().begin();
        em.persist(CustomerConverter.toModel(cv));
        em.getTransaction().commit();

    }

    /**
     * 従業員データを更新する
     * @param ev 画面から入力された従業員の登録内容
     */
    private void updateCommit(CustomerView cv) {

        em.getTransaction().begin();
        Customer c = findOneInternal(cv.getId());
        CustomerConverter.copyViewToModel(c, cv);
        em.getTransaction().commit();

    }

}

