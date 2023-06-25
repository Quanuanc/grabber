package cheng.grabber.domain.vo;

import java.util.List;

public class JobVo {
    private String name;
    private String area;
    private String salary;
    private String companyName;
    private List<InfoTagVo> infoTagList;
    private List<CompanyTagVo> companyTagList;
    private List<FooterTagVo> footerTagList;
    private String infoDesc;

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

    public List<InfoTagVo> getInfoTagList() {
        return infoTagList;
    }

    public void setInfoTagList(List<InfoTagVo> infoTagList) {
        this.infoTagList = infoTagList;
    }

    public List<CompanyTagVo> getCompanyTagList() {
        return companyTagList;
    }

    public void setCompanyTagList(List<CompanyTagVo> companyTagList) {
        this.companyTagList = companyTagList;
    }

    public List<FooterTagVo> getFooterTagList() {
        return footerTagList;
    }

    public void setFooterTagList(List<FooterTagVo> footerTagList) {
        this.footerTagList = footerTagList;
    }

    public String getInfoDesc() {
        return infoDesc;
    }

    public void setInfoDesc(String infoDesc) {
        this.infoDesc = infoDesc;
    }
}
