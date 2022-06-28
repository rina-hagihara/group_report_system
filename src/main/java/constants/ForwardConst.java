package constants;

public enum ForwardConst {
  //action
    ACT("action"),
    ACT_TOP("Top"),
    ACT_EMP("Employee"),
    ACT_REP("Report"),
    ACT_AUTH("Auth"),
    ACT_CUS("Customer"),
    ACT_MIS("Mission"),

    //command
    CMD("command"),
    CMD_NONE(""),
    CMD_INDEX("index"),
    CMD_SHOW("show"),
    CMD_SHOW_LOGIN("showLogin"),
    CMD_LOGIN("login"),
    CMD_LOGOUT("logout"),
    CMD_NEW("entryNew"),
    CMD_CREATE("create"),
    CMD_EDIT("edit"),
    CMD_UPDATE("update"),
    CMD_DESTROY("destroy"),
    CMD_ASSIGN_EMP("assignEmployee"),
    CMD_ASSIGN("assign"),
    CMD_REGISTER("register"),
    CMD_REGISTER_CUS("registerCustomer"),
    //jsp
    FW_ERR_UNKNOWN("error/unknown"),
    FW_TOP_INDEX("topPage/index"),
    FW_LOGIN("login/login"),
    FW_EMP_INDEX("employees/index"),
    FW_EMP_SHOW("employees/show"),
    FW_EMP_NEW("employees/new"),
    FW_EMP_EDIT("employees/edit"),
    FW_REP_INDEX("reports/index"),
    FW_REP_SHOW("reports/show"),
    FW_REP_NEW("reports/new"),
    FW_REP_EDIT("reports/edit"),
    FW_CUS_INDEX("customers/index"),
    FW_CUS_SHOW("customers/show"),
    FW_CUS_NEW("customers/new"),
    FW_CUS_EDIT("customers/edit"),
    FW_MIS_INDEX("mission/index"),
    FW_MIS_SHOW("mission/show"),
    FW_MIS_NEW("mission/new"),
    FW_MIS_EDIT("mission/edit"),
    FW_MIS_ASSIGN("mission/assign"),
    FW_MIS_REGISTER("mission/register");



    /**
     * 文字列
     */
    private final String text;

    /**
     * コンストラクタ
     */
    private ForwardConst(final String text) {
        this.text = text;
    }

    /**
     * 値(文字列)取得
     */
    public String getValue() {
        return this.text;
    }

}
