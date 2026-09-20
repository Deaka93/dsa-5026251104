//class monoprint merupakan turunan dari clas printjob
public class MonoPrint extends PrintJob {

    //constructor
    public MonoPrint(String id, int pages){
        //kata kunci yang memanggil constructtor untuk memproses id dan jumlah halaman
        super(id, pages);
    }

    //menimpa dengan metode perhitungan biaya khusus print monokrom
    @Override 
    public int calculateCharge() {
        return getPages() * 500;
    }

    //menimpa metode label() induk sehingga mengembalikan teks "mono"
    @Override 
    public String label() {
        return "Mono";
    }
}
