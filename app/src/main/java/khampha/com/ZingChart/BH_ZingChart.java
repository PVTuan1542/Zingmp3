package khampha.com.ZingChart;

public class BH_ZingChart {
    private byte[] anh;
    private String ten;
    private String casi;
    private int top;

    public BH_ZingChart(int top, String ten, String casi, byte[] anh ) {
        this.anh = anh;
        this.ten = ten;
        this.casi = casi;
        this.top = top;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCasi() {
        return casi;
    }

    public void setCasi(String casi) {
        this.casi = casi;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }
}
