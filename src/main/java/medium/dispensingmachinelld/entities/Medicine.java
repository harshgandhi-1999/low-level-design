package medium.dispensingmachinelld.entities;

public class Medicine {
    private final String id;
    private String name;
    private String description;
    private MedicineType medicineType;
    private double basePrice;
    private int dosageUnit;


    public Medicine(String id, String name, String description, MedicineType medicineType, double basePrice, int dosageUnit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.medicineType = medicineType;
        this.basePrice = basePrice;
        this.dosageUnit = dosageUnit;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MedicineType getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(MedicineType medicineType) {
        this.medicineType = medicineType;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getDosageUnit() {
        return dosageUnit;
    }

    public void setDosageUnit(int dosageUnit) {
        this.dosageUnit = dosageUnit;
    }
}
