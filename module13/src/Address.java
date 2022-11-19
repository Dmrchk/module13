public class Address {

    static class Geo {
        private String lat;
        private String lng;

        public Geo(String lat, String lng) {
            this.lat = lat;
            this.lng = lng;
        }

        public Geo() {
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        @Override
        public String toString() {
            return "Geo{" + "lat='" + lat + '\'' + ", lng='" + lng + '\'' + '}';
        }
    }

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Address.Geo geo;

    public Address(String street, String suite, String city, String zipcode, Address.Geo geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Address.Geo getGeo() {
        return geo;
    }

    public void setGeo(Address.Geo geo) {
        this.geo = geo;
    }

    @Override
    public String toString() {
        return "Address{" + "street='" + street + '\'' + ", suite='" + suite + '\'' + ", city='" + city + '\''
                + ", zipcode='" + zipcode + '\'' + ", geo=" + geo + '}';
    }
}