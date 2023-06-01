package contact;
public class ContactList {
    public static Contact[] findAll() {
        return new Contact[]{
                new Contact("Joe", "2125846587"),
                new Contact("Fred", "646553541"),
                new Contact("Keisha", "9175945563")
        };
    }
}
