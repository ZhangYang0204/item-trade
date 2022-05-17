package pers.zhangyang.itemtrade.meta;

import java.util.Objects;

public class GoodMeta {
    private String uuid;
    private String menuName;
    private int slot;
    private int pageIndex;
    private String data;
    private String twoData;
    private String threeData;
    private String fourData;


    public GoodMeta clone(){
        GoodMeta newOne=new GoodMeta();
        newOne.setData(this.data);
        newOne.setUuid(this.uuid);
        newOne.setMenuName(this.menuName);
        newOne.setSlot(this.slot);
        newOne.setPageIndex(this.pageIndex);
        newOne.setTwoData(this.twoData);
        newOne.setThreeData(this.threeData);
        newOne.setFourData(this.fourData);

        return newOne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodMeta goodMeta = (GoodMeta) o;
        return slot == goodMeta.slot && pageIndex == goodMeta.pageIndex && Objects.equals(uuid, goodMeta.uuid) && Objects.equals(menuName, goodMeta.menuName) && Objects.equals(data, goodMeta.data) && Objects.equals(twoData, goodMeta.twoData) && Objects.equals(threeData, goodMeta.threeData) && Objects.equals(fourData, goodMeta.fourData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, menuName, slot, pageIndex, data, twoData, threeData, fourData);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTwoData() {
        return twoData;
    }

    public void setTwoData(String twoData) {
        this.twoData = twoData;
    }

    public String getThreeData() {
        return threeData;
    }

    public void setThreeData(String threeData) {
        this.threeData = threeData;
    }

    public String getFourData() {
        return fourData;
    }

    public void setFourData(String fourData) {
        this.fourData = fourData;
    }
}
