package enums;

public enum DATABASE {
    DBNAME("project0"),
    DBURL("jdbc:postgresql://localhost:5432/"+DBNAME.value),
    USER("admin"),PASS("carbon14");

    private String value;

    private DATABASE(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
