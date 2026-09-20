//class colour print merupakan turunan dari class printjob
public class ColourPrint extends PrintJob {

    //constructor 
    public ColourPrint(String id, int pages){
        super(id, pages);
    }

    //menimpa dengan metode perhitungan biaya khusus untuk colour print
    @Override
    public int calculateCharge() {
        int pages = getPages();
        //biaya untuk set up awal = 2000
        int total = 2000;

        //total biaya jika halaman kurang dari sama dengan 10
        if (pages <= 10) {
            total += pages * 1500;
        } else {
            //total biaya jika halaman lebih dari 10
            total += (10 * 1500) + ((pages - 10) * 1000);
        }
        return total;
    }
    
    //menimpa metode label() induk sehingga mengembalikan teks "Colour"
    @Override
    public String label() {
        return "Colour";
    }
}
