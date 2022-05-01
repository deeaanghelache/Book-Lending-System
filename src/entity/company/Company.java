package entity.company;

public class Company {
    private static int companyNumber = 1;
    private final int companyId;
    private String name;
    private String address;
    private String telephoneNumber;

    public Company(String name, String address, String telephoneNumber) {
        this.companyId = companyNumber;
        companyNumber++;
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String toString() {
        return "Company: " +
                "\n\tcompanyId = " + companyId +
                "\n\tname = " + name +
                "\n\taddress = " + address +
                "\n\ttelephoneNumber = " + telephoneNumber + "\n";
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
