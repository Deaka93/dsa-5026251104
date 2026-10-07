package lw03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner registrations = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        Scanner checkins = new Scanner(Main.class.getResourceAsStream("checkins.txt"));

        int checkedIn = 0;
        int notRegistered = 0;
        int alreadyCheckedIn = 0;
        int rejected = 0;

        var studentSet = new LinkedHashMap<String, String>();
        while (registrations.hasNext()) {
            String student = registrations.next();
            studentSet.put(student, "Rejected (not registered)");
        }

        var checkinList = new ArrayList<String>();
        System.out.println("===== Event Check-In Results =====");
        while (checkins.hasNext()) {
            String student = checkins.next();
            if (!studentSet.containsKey(student)) {
                System.out.println(student + ": Rejected (not registered)");
                notRegistered++;
            } else if (studentSet.get(student).equals("Checked in")) {
                System.out.println(student + ":  Rejected (already checked in)");
                alreadyCheckedIn++;
            } else {
                System.out.println(student + ":  Checked in");
                studentSet.put(student, ": Checked in");
                checkedIn++;
            }
        }

        checkins.close();

        for(String student : studentSet.keySet()) {
            if (!checkinList.contains(student)) {
                System.out.println(student + ": " + studentSet.get(student));
                rejected++;
            }
        }

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + studentSet.size());
        System.out.println("Successful check-ins: " + checkedIn);
        System.out.println("Absent students: " + notRegistered);
        System.out.println("Rejected attempts: " + rejected);

    }
}
