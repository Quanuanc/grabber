package cheng.grabber.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String area;
    private String salary;
    private Integer salaryMin;
    private Integer salaryMax;
    private Double salaryTimes;
    private SalaryTimesType salaryTimesType;
    private Double annualSalaryMin;
    private Double annualSalaryMax;
    private String companyName;
    @OneToMany(mappedBy = "job", cascade = CascadeType.PERSIST)
    private List<InfoTag> infoTagList;
    @OneToMany(mappedBy = "job", cascade = CascadeType.PERSIST)
    private List<CompanyTag> companyTagList;
    @OneToMany(mappedBy = "job", cascade = CascadeType.PERSIST)
    private List<FooterTag> footerTagList;
    private String infoDesc;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id", nullable = false, referencedColumnName = "id")
    @JsonIgnore
    private Keyword keyword;

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Integer getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Integer salaryMin) {
        this.salaryMin = salaryMin;
    }

    public Integer getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Integer salaryMax) {
        this.salaryMax = salaryMax;
    }

    public Double getSalaryTimes() {
        return salaryTimes;
    }

    public void setSalaryTimes(Double salaryTimes) {
        this.salaryTimes = salaryTimes;
    }

    public SalaryTimesType getSalaryTimesType() {
        return salaryTimesType;
    }

    public void setSalaryTimesType(SalaryTimesType salaryTimesType) {
        this.salaryTimesType = salaryTimesType;
    }

    public Double getAnnualSalaryMin() {
        return annualSalaryMin;
    }

    public void setAnnualSalaryMin(Double annualSalaryMin) {
        this.annualSalaryMin = annualSalaryMin;
    }

    public Double getAnnualSalaryMax() {
        return annualSalaryMax;
    }

    public void setAnnualSalaryMax(Double annualSalaryMax) {
        this.annualSalaryMax = annualSalaryMax;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<InfoTag> getInfoTagList() {
        return infoTagList;
    }

    public void setInfoTagList(List<InfoTag> infoTagList) {
        this.infoTagList = infoTagList;
    }

    public List<CompanyTag> getCompanyTagList() {
        return companyTagList;
    }

    public void setCompanyTagList(List<CompanyTag> companyTagList) {
        this.companyTagList = companyTagList;
    }

    public List<FooterTag> getFooterTagList() {
        return footerTagList;
    }

    public void setFooterTagList(List<FooterTag> footerTagList) {
        this.footerTagList = footerTagList;
    }

    public String getInfoDesc() {
        return infoDesc;
    }

    public void setInfoDesc(String infoDesc) {
        this.infoDesc = infoDesc;
    }

    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
                ", area='" + area + '\'' +
                ", salary='" + salary + '\'' +
                ", companyName='" + companyName + '\'' +
                ", infoTagList=" + infoTagList +
                ", companyTagList=" + companyTagList +
                ", footerTagList=" + footerTagList +
                ", infoDesc='" + infoDesc + '\'' +
                '}';
    }

    public void addInfoTag(InfoTag tag) {
        if (this.infoTagList == null) {
            this.infoTagList = new ArrayList<>();
        }
        this.infoTagList.add(tag);
        tag.setJob(this);
    }

    public void addFooterInfoTag(FooterTag tag) {
        if (this.footerTagList == null) {
            this.footerTagList = new ArrayList<>();
        }
        this.footerTagList.add(tag);
        tag.setJob(this);
    }

    public void addCompanyTag(CompanyTag tag) {
        if (this.companyTagList == null) {
            this.companyTagList = new ArrayList<>();
        }
        this.companyTagList.add(tag);
        tag.setJob(this);
    }

    public enum SalaryTimesType {
        MONTH,
        DAY,
    }
}
