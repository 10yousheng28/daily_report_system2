package models;

import java.sql.Date;

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

@Entity
@NamedQueries({
        @NamedQuery( //フォローテーブルの情報すべてを取得（テスト用JPQL文。）
                name = "getAllFollowers",
                query = "SELECT f FROM Follow AS f ORDER BY f.id DESC"
        ),
        @NamedQuery( //ログインしている人がフォローしている従業員のみを取得
                name = "getMyAllFollowers",
                query = "SELECT f FROM Follow AS f WHERE f.employee = :employee ORDER BY f.id DESC"
        ),
        @NamedQuery( //現在のフォロー数をカウント
                name = "getMyFollowersCount",
                query = "SELECT COUNT(f) FROM Follow AS f WHERE f.employee = :employee"
                ),
        @NamedQuery( //ログインしている人のidとフォロー解除される人のidが一致する情報をすべて取得
                name = "getMyFollowerUnfollowed",
                query = "SELECT f FROM Follow AS f WHERE f.employee = :employee_id AND f.followed_employee = :followed_employee_id"
                ),
        @NamedQuery( //ログインしている人とフォローしようとしている人のidが一致するデータがfollowersテーブルにいくつあるか確認
                name = "getTheFollowerCount",
                query = "SELECT COUNT(f) FROM Follow AS f WHERE f.employee = :employee_id AND f.followed_employee = :followed_employee_id"
                )
})
@Table(name = "followers")

public class Follow {
    //フィールド

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //フォローする人のid
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    //フォローされている人のid
    @ManyToOne
    @JoinColumn(name = "followed_employee_id", nullable = false)
    private Employee followed_employee;

    //フォローした日
    @Column(name = "followed_at", nullable = false)
    private Date followed_at;

    //フォローされた人が日報を更新した時刻(保留中)
    //@ManyToOne
    //@JoinColumn(name = "report_updated_at", nullable = false)
    //private Report report_updated_at;

    //setter/getter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getFollowed_employee() {
        return followed_employee;
    }

    public void setFollowed_employee(Employee followed_employee) {
        this.followed_employee = followed_employee;
    }

    public Date getFollowed_at() {
        return followed_at;
    }

    public void setFollowed_at(Date followed_at) {
        this.followed_at = followed_at;
    }

    //public Report getReport_updated_at() {
      //  return report_updated_at;
    //}

    //public void setReport_updated_at(Report report_updated_at) {
    //    this.report_updated_at = report_updated_at;
    //}

}
