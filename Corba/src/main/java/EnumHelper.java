import CampusServerApp.Campus;

public class EnumHelper {

    public static String getCampusName(Campus campus) {
        switch (campus.value()) {
            case 0:
                return "KKL";
            case 1:
                return "WST";
            case 2:
                return "DVL";
        }
        return "ERROR";
    }

    public static int getCampusValue(Campus campus) {
        return campus.value() + 1;
    }

    public static Campus getCampus(int i) {
        return Campus.from_int(i - 1);
    }
}