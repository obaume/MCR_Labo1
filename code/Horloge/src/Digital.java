public class Digital extends Clock{
    Digital(Chrono subject) {
        super(subject);
        id.setText(subject.name() + " : " + hour +"h "+ minute +"m "+ second +"s");
        id.setBounds(60,100,20,10);
    }
}
