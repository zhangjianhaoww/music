package tech.bilian.pojo;

/**
 * 版权拥有者的基本信息
 *
 */
public class Owner {
    private Long ownerId;

    private String ownerName;

    private String ownerAddress;

    private String phone;

    public Owner() {
    }

    public Owner(Long ownerId, String ownerName, String ownerAddress, String phone) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.ownerAddress = ownerAddress;
        this.phone = phone;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    boolean isFull(){
        return (ownerName!=null && ownerAddress!=null && phone!=null);
    }
}
