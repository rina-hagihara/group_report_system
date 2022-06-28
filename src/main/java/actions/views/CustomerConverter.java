package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.Customer;

/**
 * 顧客データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class CustomerConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param ev EmployeeViewのインスタンス
     * @return Employeeのインスタンス
     */
    public static Customer toModel(CustomerView cv) {

        return new Customer(
                cv.getId(),
                cv.getCode(),
                cv.getName(),
                cv.getTel(),
                cv.getAddress(),
                cv.getMoveDay(),
                cv.getPayFlag() == null
                        ?null
                        :cv.getPayFlag() == AttributeConst.PAY_TRUE.getIntegerValue()
                                ? JpaConst.PAY_TRUE
                                : JpaConst.PAY_FALSE,
                cv.getCreatedAt(),
                cv.getUpdatedAt(),
                cv.getDeleteFlag() == null
                        ? null
                        : cv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                ? JpaConst.CUS_DEL_TRUE
                                : JpaConst.CUS_DEL_FALSE);
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param e Employeeのインスタンス
     * @return EmployeeViewのインスタンス
     */
    public static CustomerView toView(Customer c) {

        if(c == null) {
            return null;
        }

        return new CustomerView(
                c.getId(),
                c.getCode(),
                c.getName(),
                c.getTel(),
                c.getAddress(),
                c.getMoveDay(),
                c.getPayFlag() == null
                        ?null
                        :c.getPayFlag() == JpaConst.PAY_TRUE
                        ? AttributeConst.PAY_TRUE.getIntegerValue()
                        : AttributeConst.PAY_FALSE.getIntegerValue(),
                c.getCreatedAt(),
                c.getUpdatedAt(),
                c.getDeleteFlag() == null
                        ? null
                        : c.getDeleteFlag() == JpaConst.CUS_DEL_TRUE
                                ? AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                : AttributeConst.DEL_FLAG_FALSE.getIntegerValue());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<CustomerView> toViewList(List<Customer> list) {
        List<CustomerView> cvs = new ArrayList<>();

        for (Customer c : list) {
            cvs.add(toView(c));
        }

        return cvs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param e DTOモデル(コピー先)
     * @param ev Viewモデル(コピー元)
     */
    public static void copyViewToModel(Customer c, CustomerView cv) {
        c.setId(cv.getId());
        c.setCode(cv.getCode());
        c.setName(cv.getName());
        c.setTel(cv.getTel());
        c.setAddress(cv.getAddress());
        c.setMoveDay(cv.getMoveDay());
        c.setPayFlag(cv.getPayFlag());
        c.setCreatedAt(cv.getCreatedAt());
        c.setUpdatedAt(cv.getUpdatedAt());
        c.setDeleteFlag(cv.getDeleteFlag());

    }

}