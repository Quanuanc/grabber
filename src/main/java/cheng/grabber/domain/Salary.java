package cheng.grabber.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String str;
    private Integer min;
    private Integer max;
    private Double times;
    private TimesType timesType;
    private Double annualMin;
    private Double annualMax;
    @JsonIgnore
    @OneToOne(mappedBy = "salary")
    private Job job;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String salaryStr) {
        this.str = salaryStr;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer salaryMin) {
        this.min = salaryMin;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer salaryMax) {
        this.max = salaryMax;
    }

    public Double getTimes() {
        return times;
    }

    public void setTimes(Double salaryTimes) {
        this.times = salaryTimes;
    }

    public TimesType getTimesType() {
        return timesType;
    }

    public void setTimesType(TimesType salaryTimesType) {
        this.timesType = salaryTimesType;
    }

    public Double getAnnualMin() {
        return annualMin;
    }

    public void setAnnualMin(Double annualSalaryMin) {
        this.annualMin = annualSalaryMin;
    }

    public Double getAnnualMax() {
        return annualMax;
    }

    public void setAnnualMax(Double annualSalaryMax) {
        this.annualMax = annualSalaryMax;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public enum TimesType {
        MONTH,
        DAY,
    }
}
