package khampha.com.CaSi;

public class Casi {
    private int id;
    private String ten;
    private byte [] anh;

    public Casi( int id,String ten, byte [] anh) {
        this.anh = anh;
        this.ten = ten;
        this.id = id;
    }

    public Casi(String ten, byte[] anh) {
        this.ten = ten;
        this.anh = anh;
    }

    public byte [] getAnh() {
        return anh;
    }

    public void setAnh(byte [] anh) {
        this.anh = anh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
