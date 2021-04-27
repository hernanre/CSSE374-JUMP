public class Supply {
    private String name;
    private int quantityAvailable;
    private int quantityOriginally;
    private String type;
    private String unit;

    public Supply(String name, int quantityAvailable,int quantityOriginally, String type, String unit){
        setName(name);
        setQuantityAvailable(quantityAvailable);
        setQuantityOriginally(quantityOriginally);
        setType(type);
        setUnit(unit);
    }

    public String getName() {
        return name;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public int getQuantityOriginally() {
        return quantityOriginally;
    }

    public String getType() {
        return type;
    }

    public String getUnit() {
        return unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public void setQuantityOriginally(int quantityOriginally) {
        this.quantityOriginally = quantityOriginally;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

