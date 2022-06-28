package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class MissionView {
    /**
     * id
     */
    private Integer id;

    /**
     * 案件名
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 訪問予定日時
     */
    private LocalDateTime visitStart;


    /**
     * 訪問完了日時
     */
    private LocalDateTime visitFinish;

    /**
     * トラックサイズ
     */
    private String trackSize;

    /**
     *引っ越し予定日時
     */
    private LocalDateTime moveStart;

    /**
     *引っ越し終了日時
     */
    private LocalDateTime moveFinish;

    /**
     *支払い完了日
     */
    private LocalDateTime payDue;


    /**
     *登録日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    private LocalDateTime updatedAt;

    /**
     * 削除された従業員かどうか（現役：0、削除済み：1）
     */
    private Integer deleteFlag;
}
