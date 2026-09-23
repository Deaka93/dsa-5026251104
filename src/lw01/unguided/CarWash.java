package lw01.unguided;

public class CarWash extends WashService{
    public CarWash(String id, int days, int units) {
        super(id, days, units);
    }
    
    @Override 
    public int calculateCharge(){
        int days = getDays();
        int totalCar = 15000;

        if (days <= 3) {
            totalCar += days * 35000;
        } else {
            totalCar += (3 * 35000) + ((days - 3) * 25000);
        }
        return totalCar;
    }

    @Override 
    public String label() {
        return "Car";
    }
}
