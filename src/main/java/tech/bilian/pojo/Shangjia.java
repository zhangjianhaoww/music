package tech.bilian.pojo;

public class Shangjia {
    private Long shangjiaId;

    private Long master;

    //商家名称包括地点和店名 确保唯一性
    private String shangjiaName;


    public Long getShangjiaId() {
        return shangjiaId;
    }

    public void setShangjiaId(Long shangjiaId) {
        this.shangjiaId = shangjiaId;
    }

    public Long getMaster() {
        return master;
    }

    public void setMaster(Long master) {
        this.master = master;
    }

    public String getShangjiaName() {
        return shangjiaName;
    }

    public void setShangjiaName(String shangjiaName) {
        this.shangjiaName = shangjiaName;
    }
}
