package edviron01.Fee.Management.System.New.bean;

public class Pay {

    private  int student_id;
   private int tp;


    public Pay(int student_id,int tp) {
        this.student_id = student_id;
        this.tp=tp;

    }



    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getTp() {
        return tp;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }
}
