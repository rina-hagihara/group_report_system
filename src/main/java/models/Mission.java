package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 案件データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_MIS)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_MIS_GET_ALL,
            query = JpaConst.Q_MIS_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_MIS_COUNT,
            query = JpaConst.Q_MIS_COUNT_DEF)

})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Mission {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.MIS_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 案件名
     */
    @Column(name = JpaConst.MIS_COL_TITLE, nullable = false)
    private String title;

    /**
     * 内容
     */
    @Lob
    @Column(name = JpaConst.MIS_COL_CONTENT, nullable = false)
    private String content;

    /**
     * 訪問予定日時
     */
    @Column(name = JpaConst.MIS_COL_VISIT_START)
    private LocalDateTime visitStart;


    /**
     * 訪問完了日時
     */
    @Column(name = JpaConst.MIS_COL_VISIT_FINISH)
    private LocalDateTime visitFinish;

    /**
     * トラックサイズ
     */
    @Column(name = JpaConst.MIS_COL_TRACK_SIZE, nullable = false)
    private String trackSize;

    /**
     *引っ越し予定日時
     */
    @Column(name = JpaConst.MIS_COL_MOVE_START)
    private LocalDateTime moveStart;

    /**
     *引っ越し終了日時
     */
    @Column(name = JpaConst.MIS_COL_MOVE_FINISH)
    private LocalDateTime moveFinish;

    /**
     *支払い完了日
     */
    @Column(name = JpaConst.MIS_COL_PAY_DUE)
    private LocalDateTime payDue;


    /**
     *登録日時
     */
    @Column(name = JpaConst.MIS_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.MIS_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

    /**
     * 削除された従業員かどうか（現役：0、削除済み：1）
     */
    @Column(name = JpaConst.MIS_COL_DELETE_FLAG, nullable = false)
    private Integer deleteFlag;

}