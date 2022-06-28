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
 * 案件顧客データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_MC)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_GET_REGISTERED,
            query = JpaConst.Q_GET_REGISTERED_DEF)
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class MissionCustomer {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.MC_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 顧客
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.MC_COL_CUSTOMER, nullable = false)
    private Customer customer;

    /**
     * 案件
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.MC_COL_MISSION, nullable = false)
    private Mission mission;

}