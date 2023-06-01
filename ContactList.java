public class ContactList {
    public static ContactList[] findAll() {
        return new ContactList[]{
                new Contact("Citizen Kane", "1987654321"),
                new Contact("Casablanca", "1234567891"),
        };
    }
}
