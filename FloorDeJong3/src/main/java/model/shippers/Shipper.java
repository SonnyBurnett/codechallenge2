package model.shippers;

public enum Shipper {
    POSTNL ("PostNl") {
        @Override
        public double determineCosts(double weight) {
            return 6.95;
        }

        @Override
        public int determineDuration(double weight) {
            return 1;
        }
    },
    BELGIOPOSTO ("BelgioPosto"){
        @Override
        public double determineCosts(double weight) {
            return 1.95 + weight;
        }

        @Override
        public int determineDuration(double weight) {
            return 2;
        }
    },
    DHL ("DHL"){
        @Override
        public double determineCosts(double weight) {
            return 12.95 + (1.5 * weight);
        }

        @Override
        public int determineDuration(double weight) {
            if (weight < 10) {
                return 4;
            } else {
                return 8;
            }
        }
    };

    private final String name;

    Shipper (String name) {
        this.name = name;
    }

    public double determineCosts(double weight){
        return 0;
    }

    public int determineDuration(double weight){
        return 0;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return this.name;
    }
}
