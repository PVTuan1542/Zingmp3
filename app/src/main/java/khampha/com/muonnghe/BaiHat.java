package khampha.com.muonnghe;


public class BaiHat {
    private byte[] anh;
    private String ten;

    public BaiHat(byte[] anh, String ten) {
        this.anh = anh;
        this.ten = ten;
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
}



