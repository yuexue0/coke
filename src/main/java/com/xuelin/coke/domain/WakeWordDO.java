package com.xuelin.coke.domain;

public class WakeWordDO {
    private Integer id;

    private String clientId;

    private String openId;

    private Integer userId;

    private String speakerSn;

    private String wakeWord;

    private String wakeWordSpelling;

    private String type;

    private String status;

    private Integer createDt;

    private Integer updateDt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSpeakerSn() {
        return speakerSn;
    }

    public void setSpeakerSn(String speakerSn) {
        this.speakerSn = speakerSn == null ? null : speakerSn.trim();
    }

    public String getWakeWord() {
        return wakeWord;
    }

    public void setWakeWord(String wakeWord) {
        this.wakeWord = wakeWord == null ? null : wakeWord.trim();
    }

    public String getWakeWordSpelling() {
        return wakeWordSpelling;
    }

    public void setWakeWordSpelling(String wakeWordSpelling) {
        this.wakeWordSpelling = wakeWordSpelling == null ? null : wakeWordSpelling.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Integer createDt) {
        this.createDt = createDt;
    }

    public Integer getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Integer updateDt) {
        this.updateDt = updateDt;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        WakeWordDO other = (WakeWordDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClientId() == null ? other.getClientId() == null : this.getClientId().equals(other.getClientId()))
            && (this.getOpenId() == null ? other.getOpenId() == null : this.getOpenId().equals(other.getOpenId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getSpeakerSn() == null ? other.getSpeakerSn() == null : this.getSpeakerSn().equals(other.getSpeakerSn()))
            && (this.getWakeWord() == null ? other.getWakeWord() == null : this.getWakeWord().equals(other.getWakeWord()))
            && (this.getWakeWordSpelling() == null ? other.getWakeWordSpelling() == null : this.getWakeWordSpelling().equals(other.getWakeWordSpelling()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateDt() == null ? other.getCreateDt() == null : this.getCreateDt().equals(other.getCreateDt()))
            && (this.getUpdateDt() == null ? other.getUpdateDt() == null : this.getUpdateDt().equals(other.getUpdateDt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClientId() == null) ? 0 : getClientId().hashCode());
        result = prime * result + ((getOpenId() == null) ? 0 : getOpenId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getSpeakerSn() == null) ? 0 : getSpeakerSn().hashCode());
        result = prime * result + ((getWakeWord() == null) ? 0 : getWakeWord().hashCode());
        result = prime * result + ((getWakeWordSpelling() == null) ? 0 : getWakeWordSpelling().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateDt() == null) ? 0 : getCreateDt().hashCode());
        result = prime * result + ((getUpdateDt() == null) ? 0 : getUpdateDt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", clientId=").append(clientId);
        sb.append(", openId=").append(openId);
        sb.append(", userId=").append(userId);
        sb.append(", speakerSn=").append(speakerSn);
        sb.append(", wakeWord=").append(wakeWord);
        sb.append(", wakeWordSpelling=").append(wakeWordSpelling);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", createDt=").append(createDt);
        sb.append(", updateDt=").append(updateDt);
        sb.append("]");
        return sb.toString();
    }
}