package khtn.hw05;

public class Class {
    private String MaLop;
    private String TenLop;

    // Constructor
    public Class(String MaLop, String TenLop) {
        this.MaLop = MaLop;
        this.TenLop = TenLop;
    }

    // Getter methods
    public String getMaLop () {
        return MaLop;
    }

    public String getTenLop() {
        return TenLop;
    }

    // Setter methods (if needed)
    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public void setTenLop(String TenLop) {
        this.TenLop = TenLop;
    }
}
