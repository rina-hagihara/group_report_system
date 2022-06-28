package constants;

public enum AttributeConst {
  //フラッシュメッセージ
    FLUSH("flush"),

    //一覧画面共通
    MAX_ROW("maxRow"),
    PAGE("page"),

    //入力フォーム共通
    TOKEN("_token"),
    ERR("errors"),

    //ログイン中の従業員
    LOGIN_EMP("login_employee"),

    //ログイン画面
    LOGIN_ERR("loginError"),

    //従業員管理
    EMPLOYEE("employee"),
    EMPLOYEES("employees"),
    EMP_COUNT("employees_count"),
    EMP_ID("id"),
    EMP_CODE("code"),
    EMP_PASS("password"),
    EMP_NAME("name"),
    EMP_ADMIN_FLG("admin_flag"),

    //管理者フラグ
    ROLE_ADMIN(1),
    ROLE_GENERAL(0),

  //支払いフラグ
    PAY_TRUE(1),
    PAY_FALSE(0),

    //削除フラグ
    DEL_FLAG_TRUE(1),
    DEL_FLAG_FALSE(0),

    //日報管理
    REPORT("report"),
    REPORTS("reports"),
    REP_COUNT("reports_count"),
    REP_ID("id"),
    REP_DATE("report_date"),
    REP_TITLE("title"),
    REP_CONTENT("content"),

    //顧客管理
    CUSTOMER("customer"),
    CUSTOMERS("customers"),
    CUS_COUNT("employees_count"),
    CUS_ID("id"),
    CUS_CODE("code"),
    CUS_NAME("name"),
    CUS_TEL("tel"),
    CUS_ADDRESS("address"),
    CUS_MOVE_DAY("moveDay"),
    CUS_PAY_FLAG("payFlag"),

    //案件管理
    MISSION("mission"),
    MISSIONS("missions"),
    MIS_COUNT("missions_count"),
    MIS_ID("id"),
    MIS_TITLE("title"),
    MIS_CONTENT("content"),
    MIS_VISIT_START("visitStart"),
    MIS_VISIT_FINISH("visitFinish"),
    MIS_TRACK_SIZE("trackSize"),
    MIS_MOVE_START("moveStart"),
    MIS_MOVE_FINISH("moveFinish"),
    MIS_PAY_DUE("payDue"),

    MISSION_REPORTS("mission_reports"),
    MISSION_EMPLOYEES("mission_employees"),
    MISSION_CUSTOMERS("mission_customers"),

    //案件従業員
    ME_EMP_ID("emp_id"),
    ME_MIS_ID("mis_id"),

    //案件顧客
    MC_CUS_ID("cus_id"),
    MC_MIS_ID("mis_id"),

  //案件顧客
    MR_MIS_ID("mis_id");

    private final String text;
    private final Integer i;

    private AttributeConst(final String text) {
        this.text = text;
        this.i = null;
    }

    private AttributeConst(final Integer i) {
        this.text = null;
        this.i = i;
    }

    public String getValue() {
        return this.text;
    }

    public Integer getIntegerValue() {
        return this.i;
    }

}

