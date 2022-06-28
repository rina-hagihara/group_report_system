package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 案件日報データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_MR)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_MR_GET_REPORTS_BY_MIS,
            query = JpaConst.Q_MR_GET_REPORTS_BY_MIS_DEF)
})


@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class MissionReport {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.MR_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 日報
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.MR_COL_REPORT, nullable = false)
    private Report report;

    /**
     * 案件
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.MR_COL_MISSION, nullable = false)
    private Mission mission;

}