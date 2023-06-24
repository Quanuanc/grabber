package cheng.grabber.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String area;
    private String salary;
    private String companyName;
    @OneToMany(mappedBy = "job", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<InfoTag> infoTagList;
    @OneToMany(mappedBy = "job", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CompanyTag> companyTagList;
    @OneToMany(mappedBy = "job", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<FooterTag> footerTagList;
    private String infoDesc;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
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
}
