package lw01.prelab;

public abstract class PrintJob implements Chargeable {
    //variabel penyimpan id dan jumlah halaman yang hanya bisa diakses di kelas ini
    private String id;
    private int pages;

    //protected constructor berarti hanya bisa dipanggil oleh kelas keturunannya
    protected PrintJob(String id, int pages) {
        //jika jumlah halaman kurang dari nol maka akan melempar error
        if (pages <= 0) {
            throw new IllegalArgumentException("Pages must be greater than 0");
        }
        //menyimpan nilai parameter ke dalam variabel kelas milik objek 
        this.id = id;
        this.pages = pages;
    }

    //metode untuk mengambil nilai id dari luar kelas
    public String getId() {
        return id;
    }

    //metode untuk mengambil nilai pages dari luar kelas
    public int getPages() {
        return pages;
    }

    //mendeklarasikan ulang metode dari Chargeable sebagai abstract
    @Override
    public abstract int calculateCharge();

    //metode overloading dari calculateCharge (nama sama tapi punya parameter)
    public int calculateCharge(int copies) {
        if (copies <= 0){
            throw new IllegalArgumentException("Copies must be greater than 0");
        }
        //mengembalikan total biaya, jumlah salinan dikalikan biaya satu cetakan
        return copies * calculateCharge();
    }

    //mengembalikan label bawaan
    public String label() {
        return "Print";
    }

    //mengembalikan format ringkasan data
    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}