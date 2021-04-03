package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
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

    //フォローした時刻
    @Column(name = "followed_at", nullable = false)
    private Timestamp followed_at;

    //フォローされた人が日報を更新した時刻
    @ManyToOne
    @JoinColumn(name = "report_updated_at", nullable = false)
    private Report report_updated_at;

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

    public Timestamp getFollowed_at() {
        return followed_at;
    }

    public void setFollowed_at(Timestamp followed_at) {
        this.followed_at = followed_at;
    }

    public Report getReport_updated_at() {
        return report_updated_at;
    }

    public void setReport_updated_at(Report report_updated_at) {
        this.report_updated_at = report_updated_at;
    }

}
