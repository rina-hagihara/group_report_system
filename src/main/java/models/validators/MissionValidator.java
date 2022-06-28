package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.MissionView;
import constants.MessageConst;
import services.MissionService;

/**
 * 従業員インスタンスに設定されている値のバリデーションを行うクラス
 *
 */
public class MissionValidator {

    /**
     * 従業員インスタンスの各項目についてバリデーションを行う
     * @param service 呼び出し元Serviceクラスのインスタンス
     * @param mv MissionViewのインスタンス
     * @param codeDuplicateCheckFlag 社員番号の重複チェックを実施するかどうか(実施する:true 実施しない:false)
     * @param passwordCheckFlag パスワードの入力チェックを実施するかどうか(実施する:true 実施しない:false)
     * @return エラーのリスト
     */
    public static List<String> validate(
            MissionService service, MissionView mv) {
        List<String> errors = new ArrayList<String>();

        //タイトルのチェック
        String titleError = validateTitle(mv.getTitle());
        if (!titleError.equals("")) {
            errors.add(titleError);
        }


        return errors;
    }



    /**
     * タイトルに入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param name 氏名
     * @return エラーメッセージ
     */
    private static String validateTitle(String title) {

        if (title == null || title.equals("")) {
            return MessageConst.E_NOTITLE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
}
