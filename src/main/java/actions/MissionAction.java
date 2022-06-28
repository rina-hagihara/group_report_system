package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.CustomerView;
import actions.views.EmployeeView;
import actions.views.MissionCustomerView;
import actions.views.MissionEmployeeView;
import actions.views.MissionView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.CustomerService;
import services.EmployeeService;
import services.MissionService;

/**
 * 従業員に関わる処理を行うActionクラス
 *
 */
public class MissionAction extends ActionBase {

    private MissionService service;
    private EmployeeService empService;
    private CustomerService cusService;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new MissionService();
        empService = new EmployeeService();
        cusService = new CustomerService();

        //メソッドを実行
        invoke();

        service.close();
    }

    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {



            //指定されたページ数の一覧画面に表示するデータを取得
            int page = getPage();
            List<MissionView> missions = service.getPerPage(page);

            //全ての従業員データの件数を取得
            long missionCount = service.countAll();


            putRequestScope(AttributeConst.MISSIONS, missions); //取得した従業員データ
            putRequestScope(AttributeConst.MIS_COUNT, missionCount); //全ての従業員データの件数
            putRequestScope(AttributeConst.PAGE, page); //ページ数
            putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数



            //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
            String flush = getSessionScope(AttributeConst.FLUSH);
            if (flush != null) {
                putRequestScope(AttributeConst.FLUSH, flush);
                removeSessionScope(AttributeConst.FLUSH);
            }

            //一覧画面を表示
            forward(ForwardConst.FW_MIS_INDEX);


    }

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException {

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン




            putRequestScope(AttributeConst.MISSION, new MissionView()); //空の従業員インスタンス

            //新規登録画面を表示
            forward(ForwardConst.FW_MIS_NEW);

    }

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {



            //パラメータの値を元に従業員情報のインスタンスを作成する
            MissionView mv = new MissionView(
                    null,
                    getRequestParam(AttributeConst.MIS_TITLE),
                    getRequestParam(AttributeConst.MIS_CONTENT),
                    toTime(getRequestParam(AttributeConst.MIS_VISIT_START)),
                    toTime(getRequestParam(AttributeConst.MIS_VISIT_FINISH)),
                    getRequestParam(AttributeConst.MIS_TRACK_SIZE),
                    toTime(getRequestParam(AttributeConst.MIS_MOVE_START)),
                    toTime(getRequestParam(AttributeConst.MIS_MOVE_FINISH)),
                    toTime(getRequestParam(AttributeConst.MIS_PAY_DUE)),
                    null,
                    null,
                    AttributeConst.DEL_FLAG_FALSE.getIntegerValue());


            //従業員情報登録
            List<String> errors = service.create(mv);

            if (errors.size() > 0) {
                //登録中にエラーがあった場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.MISSION, mv); //入力された従業員情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //新規登録画面を再表示
                forward(ForwardConst.FW_MIS_NEW);

            } else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_MIS, ForwardConst.CMD_INDEX);
            }

        }


    /**
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unused")
    public void show() throws ServletException, IOException {


            //idを条件に案件データを取得する
            MissionView mv = service.findOne(toNumber(getRequestParam(AttributeConst.MIS_ID)));

            if (mv == null || mv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {
                System.out.println("showでデータが見つかりません");
                //データが取得できなかった、または論理削除されている場合はエラー画面を表示
                forward(ForwardConst.FW_ERR_UNKNOWN);
                return;
            }
            //案件を条件にアサインしている従業員をリストで呼び出す
            List<MissionEmployeeView> mevs = service.getAssignedEmps(mv);

            //案件を条件に登録している顧客を呼び出す
            List<MissionCustomerView> mcvs = service.getRegistered(mv);


            putRequestScope(AttributeConst.MISSION, mv); //取得した案件情報
            putRequestScope(AttributeConst.MISSION_EMPLOYEES, mevs);//取得したアサイン従業員情報
            putRequestScope(AttributeConst.MISSION_CUSTOMERS, mcvs);//取得したアサイン従業員情報



            //詳細画面を表示
            forward(ForwardConst.FW_MIS_SHOW);


    }

    public void assign() throws ServletException, IOException{
        //指定されたページの従業員一覧を取得
        int page = getPage();
        List<EmployeeView> employees = empService.getPerPage(page);
        putRequestScope(AttributeConst.EMPLOYEES, employees);


        long employeeCount = empService.countAll();
        putRequestScope(AttributeConst.EMP_COUNT, employeeCount); //全ての従業員データの件数

        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        MissionView mv = service.findOne(toNumber(getRequestParam(AttributeConst.MIS_ID)));
        putRequestScope(AttributeConst.MISSION, mv);

        //アサインページを表示
        forward(ForwardConst.FW_MIS_ASSIGN);
    }






    public void assignEmployee() throws ServletException, IOException{
        EmployeeView ev = empService.findOne(toNumber(getRequestParam(AttributeConst.ME_EMP_ID)));


        MissionView mv = service.findOne(toNumber(getRequestParam(AttributeConst.ME_MIS_ID)));



        MissionEmployeeView missionEmpView = new MissionEmployeeView();

        missionEmpView.setEmployee(ev);
        missionEmpView.setMission(mv);


        service.createMissionEmployee(missionEmpView);

        redirectMissionShow(ForwardConst.ACT_MIS, ForwardConst.CMD_SHOW, mv.getId());

    }

    public void register() throws ServletException, IOException {
      //指定されたページ数の一覧画面に表示するデータを取得
        int page = getPage();
        List<CustomerView> customers = cusService.getPerPage(page);

        //全ての従業員データの件数を取得
        long customerCount = service.countAll();

        putRequestScope(AttributeConst.CUSTOMERS, customers); //取得した従業員データ
        putRequestScope(AttributeConst.CUS_COUNT, customerCount); //全ての従業員データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数


        MissionView mv = service.findOne(toNumber(getRequestParam(AttributeConst.MIS_ID)));
        putRequestScope(AttributeConst.MISSION, mv);

        //一覧画面を表示

        forward(ForwardConst.FW_MIS_REGISTER);

    }

    public void registerCustomer() throws ServletException, IOException{
        CustomerView cv = cusService.findOne(toNumber(getRequestParam(AttributeConst.MC_CUS_ID)));
        MissionView mv = service.findOne(toNumber(getRequestParam(AttributeConst.MC_MIS_ID)));

        MissionCustomerView missionCusView = new MissionCustomerView();
        missionCusView.setCustomer(cv);
        missionCusView.setMission(mv);


        service.createMissionCustomer(missionCusView);
        redirectMissionShow(ForwardConst.ACT_MIS, ForwardConst.CMD_SHOW, mv.getId());
    }



    /**
     * 編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {


            //idを条件に従業員データを取得する
            MissionView mv = service.findOne(toNumber(getRequestParam(AttributeConst.MIS_ID)));

            if (mv == null || mv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {

                //データが取得できなかった、または論理削除されている場合はエラー画面を表示
                forward(ForwardConst.FW_ERR_UNKNOWN);
                return;
            }

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.MISSION, mv); //取得した従業員情報

            //編集画面を表示する
            forward(ForwardConst.FW_MIS_EDIT);

    }

    /**
     * 更新を行う
     * @throws ServletException
     * @throws IOException
     */
    public void update() throws ServletException, IOException {

        if (checkToken()) { //追記
            //パラメータの値を元に案件情報のインスタンスを作成する
            MissionView mv = new MissionView(
                    toNumber(getRequestParam(AttributeConst.MIS_ID)),
                    getRequestParam(AttributeConst.MIS_TITLE),
                    getRequestParam(AttributeConst.MIS_CONTENT),
                    toTime(getRequestParam(AttributeConst.MIS_VISIT_START)),
                    toTime(getRequestParam(AttributeConst.MIS_VISIT_FINISH)),
                    getRequestParam(AttributeConst.MIS_TRACK_SIZE),
                    toTime(getRequestParam(AttributeConst.MIS_MOVE_START)),
                    toTime(getRequestParam(AttributeConst.MIS_MOVE_FINISH)),
                    toTime(getRequestParam(AttributeConst.MIS_PAY_DUE)),
                    null,
                    null,
                    AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

            //従業員情報更新
            List<String> errors = service.update(mv);

            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.MISSION, mv); //入力された従業員情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //編集画面を再表示
                forward(ForwardConst.FW_MIS_EDIT);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirectMissionShow(ForwardConst.ACT_MIS, ForwardConst.CMD_INDEX, mv.getId());
            }
        }
    }

    /**
     * 論理削除を行う
     * @throws ServletException
     * @throws IOException
     */
    public void destroy() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //idを条件に従業員データを論理削除する
            service.destroy(toNumber(getRequestParam(AttributeConst.MIS_ID)));

            //セッションに削除完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_DELETED.getMessage());

            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_MIS, ForwardConst.CMD_INDEX);
        }
    }
}