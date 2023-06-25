package cheng.grabber.domain.vo;

import java.time.LocalDateTime;
import java.util.List;

public class KeywordVo {
    private String keyword;
    private LocalDateTime createTime;
    private List<JobVo> jobList;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public List<JobVo> getJobList() {
        return jobList;
    }

    public void setJobList(List<JobVo> jobList) {
        this.jobList = jobList;
    }
}
