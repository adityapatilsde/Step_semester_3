package WEEK4.class_problems;

public class BusRoute {

    String routeCode;
    String routeName;
    int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 1);
    }

    int compareTo(BusRoute other) {

        if (this.priority != other.priority) {
            return other.priority - this.priority;
        }

        int result = this.routeCode.compareToIgnoreCase(other.routeCode);

        if (result != 0) {
            return result;
        }

        return 0;
    }

    static BusRoute[] rankRoutes(BusRoute[] routes) {

        BusRoute[] result = new BusRoute[routes.length];

        for (int i = 0; i < routes.length; i++) {
            result[i] = routes[i];
        }

        for (int i = 0; i < result.length - 1; i++) {

            for (int j = 0; j < result.length - 1 - i; j++) {

                if (result[j].compareTo(result[j + 1]) > 0) {

                    BusRoute temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        BusRoute[] routes = {
                new BusRoute("RT205L", "Airport Express", 3),
                new BusRoute("rt201j", "City Central", 4),
                new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = rankRoutes(routes);

        System.out.print("[");

        for (int i = 0; i < ranked.length; i++) {

            System.out.print("\"" + ranked[i].routeCode + "\"");

            if (i < ranked.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }
}

