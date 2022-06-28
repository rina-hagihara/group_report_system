package constants;

public interface JpaConst {
    /**
     * DB関連の項目値を定義するインターフェース
     * ※インターフェイスに定義した変数は public static final 修飾子がついているとみなされる
     */

  //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "daily_report_system";

    //データ取得件数の最大値
    int ROW_PER_PAGE = 15; //1ページに表示するレコードの数

    //従業員テーブル
    String TABLE_EMP = "employees"; //テーブル名
    //従業員テーブルカラム
    String EMP_COL_ID = "id"; //id
    String EMP_COL_CODE = "code"; //社員番号
    String EMP_COL_NAME = "name"; //氏名
    String EMP_COL_PASS = "password"; //パスワード
    String EMP_COL_ADMIN_FLAG = "admin_flag"; //管理者権限
    String EMP_COL_CREATED_AT = "created_at"; //登録日時
    String EMP_COL_UPDATED_AT = "updated_at"; //更新日時
    String EMP_COL_DELETE_FLAG = "delete_flag"; //削除フラグ

    int ROLE_ADMIN = 1; //管理者権限ON(管理者)
    int ROLE_GENERAL = 0; //管理者権限OFF(一般)
    int EMP_DEL_TRUE = 1; //削除フラグON(削除済み)
    int EMP_DEL_FALSE = 0; //削除フラグOFF(現役)

    //日報テーブル
    String TABLE_REP = "reports"; //テーブル名
    //日報テーブルカラム
    String REP_COL_ID = "id"; //id
    String REP_COL_EMP = "employee_id"; //日報を作成した従業員のid
    String REP_COL_REP_DATE = "report_date"; //いつの日報かを示す日付
    String REP_COL_TITLE = "title"; //日報のタイトル
    String REP_COL_CONTENT = "content"; //日報の内容
    String REP_COL_CREATED_AT = "created_at"; //登録日時
    String REP_COL_UPDATED_AT = "updated_at"; //更新日時

    //顧客テーブル
    String TABLE_CUS = "customers"; //テーブル名
    //顧客テーブルカラム
    String CUS_COL_ID = "id"; //id
    String CUS_COL_CODE = "code"; //顧客番号
    String CUS_COL_NAME = "name"; //顧客名
    String CUS_COL_TEL = "tel"; //電話番号
    String CUS_COL_ADDRESS = "address"; //住所
    String CUS_COL_MOVE_DATE = "move_date"; //引っ越し日時
    String CUS_COL_PAY_FLAG = "pay_flag"; //支払い状況
    String CUS_COL_CREATED_AT = "created_at"; //登録日時
    String CUS_COL_UPDATED_AT = "updated_at"; //更新日時
    String CUS_COL_DELETE_FLAG = "delete_flag"; //削除フラグ

    int PAY_TRUE = 1; //支払い済み
    int PAY_FALSE = 0; //未払い
    int CUS_DEL_TRUE = 1; //削除フラグON(削除済み)
    int CUS_DEL_FALSE = 0; //削除フラグOFF(現役)

    //案件テーブル
    String TABLE_MIS = "missions"; //テーブル名
    //案件テーブルカラム
    String MIS_COL_ID = "id"; //id
    String MIS_COL_TITLE = "title"; //案件名
    String MIS_COL_CONTENT = "content"; //内容
    String MIS_COL_VISIT_START = "visit_start"; //訪問予定日時
    String MIS_COL_VISIT_FINISH = "visit_finish"; //訪問完了日時
    String MIS_COL_TRACK_SIZE = "track_size"; //トラックサイズ
    String MIS_COL_MOVE_START = "move_start"; //引っ越し予定日時
    String MIS_COL_MOVE_FINISH = "move_finish"; //引っ越し完了日時
    String MIS_COL_PAY_DUE = "pay_due"; //支払い完了日
    String MIS_COL_CREATED_AT = "created_at"; //登録日時
    String MIS_COL_UPDATED_AT = "updated_at"; //更新日時
    String MIS_COL_DELETE_FLAG = "delete_flag"; //削除フラグ

    int MIS_DEL_TRUE = 1; //削除フラグON(削除済み)
    int MIS_DEL_FALSE = 0; //削除フラグOFF(現役)

    //案件従業員テーブル
    String TABLE_ME = "missionemployees"; //テーブル名
    //案件従業員テーブルカラム
    String ME_COL_ID = "id"; //id
    String ME_COL_EMPLOYEE = "employee_id"; //案件名
    String ME_COL_MISSION = "mission_id"; //内容

    //案件顧客テーブル
    String TABLE_MC = "missioncustomers"; //テーブル名
    //案件顧客テーブルカラム
    String MC_COL_ID = "id"; //id
    String MC_COL_CUSTOMER = "customer_id"; //案件名
    String MC_COL_MISSION = "mission_id"; //内容

    //案件従業員テーブル
    String TABLE_MR = "missionreports"; //テーブル名
    //案件従業員テーブルカラム
    String MR_COL_ID = "id"; //id
    String MR_COL_REPORT = "report_id"; //案件名
    String MR_COL_MISSION = "mission_id"; //内容

    //Entity名
    String ENTITY_EMP = "employee"; //従業員
    String ENTITY_REP = "report"; //日報
    String ENTITY_CUS = "customer"; //顧客
    String ENTITY_MIS = "mission"; //従業員
    String ENTITY_ME = "missionemployee"; //案件従業員
    String ENTITY_MC = "missioncustomer"; //案件従業員
    String ENTITY_MR = "missionreport"; //案件従業員

    //JPQL内パラメータ
    String JPQL_PARM_CODE = "code"; //社員番号
    String JPQL_PARM_PASSWORD = "password"; //パスワード
    String JPQL_PARM_EMPLOYEE = "employee"; //従業員
    String JPQL_PARM_MISSION = "mission"; //日報
    //NamedQueryの nameとquery
    //全ての従業員をidの降順に取得する
    String Q_EMP_GET_ALL = ENTITY_EMP + ".getAll"; //name
    String Q_EMP_GET_ALL_DEF = "SELECT e FROM Employee AS e ORDER BY e.id DESC"; //query
    //全ての従業員の件数を取得する
    String Q_EMP_COUNT = ENTITY_EMP + ".count";
    String Q_EMP_COUNT_DEF = "SELECT COUNT(e) FROM Employee AS e";
    //社員番号とハッシュ化済パスワードを条件に未削除の従業員を取得する
    String Q_EMP_GET_BY_CODE_AND_PASS = ENTITY_EMP + ".getByCodeAndPass";
    String Q_EMP_GET_BY_CODE_AND_PASS_DEF = "SELECT e FROM Employee AS e WHERE e.deleteFlag = 0 AND e.code = :" + JPQL_PARM_CODE + " AND e.password = :" + JPQL_PARM_PASSWORD;
    //指定した社員番号を保持する従業員の件数を取得する
    String Q_EMP_COUNT_RESISTERED_BY_CODE = ENTITY_EMP + ".countRegisteredByCode";
    String Q_EMP_COUNT_RESISTERED_BY_CODE_DEF = "SELECT COUNT(e) FROM Employee AS e WHERE e.code = :" + JPQL_PARM_CODE;
    //全ての日報をidの降順に取得する
    String Q_REP_GET_ALL = ENTITY_REP + ".getAll";
    String Q_REP_GET_ALL_DEF = "SELECT r FROM Report AS r ORDER BY r.id DESC";
    //全ての日報の件数を取得する
    String Q_REP_COUNT = ENTITY_REP + ".count";
    String Q_REP_COUNT_DEF = "SELECT COUNT(r) FROM Report AS r";
    //指定した従業員が作成した日報を全件idの降順で取得する
    String Q_REP_GET_ALL_MINE = ENTITY_REP + ".getAllMine";
    String Q_REP_GET_ALL_MINE_DEF = "SELECT r FROM Report AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE + " ORDER BY r.id DESC";
    //指定した従業員が作成した日報の件数を取得する
    String Q_REP_COUNT_ALL_MINE = ENTITY_REP + ".countAllMine";
    String Q_REP_COUNT_ALL_MINE_DEF = "SELECT COUNT(r) FROM Report AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE;
  //全ての顧客をidの降順に取得する
    String Q_CUS_GET_ALL = ENTITY_CUS + ".getAll"; //name
    String Q_CUS_GET_ALL_DEF = "SELECT c FROM Customer AS c ORDER BY c.id DESC"; //query
    //全ての顧客の件数を取得する
    String Q_CUS_COUNT = ENTITY_CUS + ".count";
    String Q_CUS_COUNT_DEF = "SELECT COUNT(c) FROM Customer AS c";
  //指定した社員番号を保持する従業員の件数を取得する
    String Q_CUS_COUNT_RESISTERED_BY_CODE = ENTITY_CUS + ".countRegisteredByCode";
    String Q_CUS_COUNT_RESISTERED_BY_CODE_DEF = "SELECT COUNT(c) FROM Customer AS c WHERE c.code = :" + JPQL_PARM_CODE;
    //全ての案件をidの降順に取得する
    String Q_MIS_GET_ALL = ENTITY_MIS + ".getAll"; //name
    String Q_MIS_GET_ALL_DEF = "SELECT m FROM Mission AS m ORDER BY m.id DESC"; //query
    //全ての案件の件数を取得する
    String Q_MIS_COUNT = ENTITY_MIS + ".count";
    String Q_MIS_COUNT_DEF = "SELECT COUNT(m) FROM Mission AS m";
  //ログイン中の従業員の全ての案件を取得する
    String Q_ME_GET_ALL_MINE = ENTITY_ME + ".getAllMine";
    String Q_ME_GET_ALL_MINE_DEF = "SELECT me FROM MissionEmployee AS me WHERE me.employee = :" + JPQL_PARM_EMPLOYEE;
    //案件の対象の日報を呼び出す
    String Q_MR_GET_REPORTS_BY_MIS = ENTITY_MR + ".getRportsByMission";
    String Q_MR_GET_REPORTS_BY_MIS_DEF = "SELECT mr FROM MissionReport AS mr WHERE mr.mission = :" + JPQL_PARM_MISSION;
    //案件をもとにその案件にアサインしている従業員を呼び出す
    String Q_GET_ASSIGNED = ENTITY_ME + ".getAssigned";
    String Q_GET_ASSIGNED_DEF = "SELECT me FROM MissionEmployee AS me WHERE me.mission = :" + JPQL_PARM_MISSION;
  //案件をもとにその案件に登録している顧客を呼び出す
    String Q_GET_REGISTERED = ENTITY_MC + ".getRegistered";
    String Q_GET_REGISTERED_DEF = "SELECT mc FROM MissionCustomer AS mc WHERE mc.mission = :" + JPQL_PARM_MISSION;


}
