public class UserCreator {
    public static User createUser() {
        Address.Geo geo = new Address.Geo("123456", "123456");
        Address address = new Address("Antonova", "2", "Kyiv", "03186", geo);
        Company company = new Company("Sony pictures", "", "SonyP");

        return new User(11, "Oleksandr", "Dymarchuk", "email@email.com", address, "123-456-789", "blabla.com", company);
    }
}