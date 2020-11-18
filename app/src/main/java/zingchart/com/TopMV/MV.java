package zingchart.com.TopMV;

public class MV {
    private byte[] anh;
    private String ten;
    private String casi;

    public MV(byte[] anh, String ten, String casi) {
        this.anh = anh;
        this.ten = ten;
        this.casi = casi;
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
}
