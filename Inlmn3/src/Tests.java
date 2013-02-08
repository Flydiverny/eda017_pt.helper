import java.util.*;
import se.markusmaga.lth.pt.register.*;

class Tests {

    public static void main(String[] args) {
        new Tests().run();
    }

    void run() {
        try {
            header("Testar klassen Person");
            testPerson();
            header("Testar klassen Register");
            testRegister();
            header("Alla tester OK!");
        } catch (Exception e) {
            header(String.format("NÃ¥got test gick fel (%s)", e.toString()));
        }
    }

    void header(String text) {
        System.out.println();
        System.out.println("==== " + text + " ====");
        System.out.println();
    }

    void subheader(String text) {
        System.out.println();
        System.out.println("   == " + text + " ==");
    }

    void test(boolean condition, String message) {
        System.out.println((condition ? "   OK" : "  FEL") + ": testar om " + message);
        if (!condition) {
            throw new RuntimeException("Fel i test...");
        }
    }

    void testPerson() {
        Person p1 = new Person("Amanda", "123");
        Person p2 = new Person("Adam", "321");
        test(p1.getName().equals("Amanda"), "getName ger namnet");
        test(p2.getName().equals("Adam"), "getName ger namnet");
        test(p1.getNumber().equals("123"), "getNumber ger numret");
        test(p2.getNumber().equals("321"), "getNumber ger numret");
    }

    void testRegister() {
        Register reg = new Register();
        Register anotherReg = new Register();

        subheader("testar insert");
        test(reg.insert("Amanda", "123"), "insert kan sÃ¤tta in i tomt register");
        test(anotherReg.insert("Amanda", "123"), "insert kan sÃ¤tta in i ett annat tomt register");
        test(!reg.insert("Amanda", "123"), "insert misslyckas med att sÃ¤tta in samma namn igen");
        test(reg.insert("Bodil", "234") &&
             reg.insert("Adam", "234"), "insert kan sÃ¤tta in nya namn");

        subheader("testar containsName (och, indirekt, insert)");
        test(reg.containsName("Amanda") &&
             reg.containsName("Bodil") &&
             reg.containsName("Adam"), "containsName hittar de namn som vi satt in");
        test(!reg.containsName("Homer"), "containsName misslyckas med at hitta namn som vi inte satt in");

        subheader("testar remove (och, indirekt, insert och containsName)");
        test(!reg.remove("Homer"), "remove misslyckas med att ta bort namn som vi inte satt in");
        test(reg.remove("Amanda"), "remove tar bort namn som vi satt in");

        test(!reg.containsName("Amanda"), "containsName fungerar efter att vi tagit bort namn");
        test(reg.containsName("Bodil"), "containsName hittar namn Ã¤ven efter att vi tagit bort annat namn");
        test(reg.containsName("Adam"), "containsName hittar namn Ã¤ven efter att vi tagit bort ytterligare ett annat namn");
        test(reg.remove("Adam") &&
             reg.remove("Bodil"), "remove tar bort namn som vi satt in");
        test(!reg.containsName("Adam") &&
             !reg.containsName("Bodil"), "containsName fungerar efter att vi tagit bort nÃ¥gra namn");

        subheader("testar findAll (och, indirekt, insert, containsName och remove)");
        anotherReg = new Register();
        test(anotherReg.findAll().size() == 0, "findAll ger tom lista fÃ¶r nytt register");
        test(reg.findAll().size() == 0, "findAll ger tom lista nÃ¤r alla Ã¤r borttagna");
        test(reg.insert("Amanda", "123") &&
             reg.insert("Bodil", "234") &&
             reg.insert("Adam", "234"), "insert kan sÃ¤tta in nya namn, Ã¤ven med samma nummer");
        List<Person> found = reg.findAll();
        test(found.size() == 3, "findAll ger rÃ¤tt antal personer");
        test(contains(found, "Adam") &&
             contains(found, "Bodil") &&
             contains(found, "Amanda"), "findAll hittar alla personer");
        found.clear();
        test(reg.findAll().size() == 3, "findAll skickar tillbaka kopia av listan med personer");
        test(reg.remove("Bodil"), "remove tar bort person ur lista");
        test(reg.findAll().size() == 2, "findAll ger lista med en vÃ¤n mindre efter remove");
        found = reg.findAll();
        test(found.size() == 2, "findAll ger rÃ¤tt antal personer");
        test(contains(found, "Adam") &&
             contains(found, "Amanda"), "findAll hittar person med givet namn");
        test(reg.insert("Bodil", "234"), "insert kan sÃ¤tta in nya namn");

        subheader("testar findByPartOfName (och, indirekt, insert)");
        found = reg.findByPartOfName("A");
        test(found.size() == 2, "findByPartOfName ger rÃ¤tt antal personer (2 st) som bÃ¶rjar pÃ¥ 'A'");
        test(contains(found, "Adam") &&
             contains(found, "Amanda"), "findByPartOfName hittar personer som bÃ¶rjar pÃ¥ 'A'");
        test(reg.insert("Matilda", "234"), "insert kan sÃ¤tta in ny person");
        found = reg.findByPartOfName("B");
        test(found.size() == 1, "findByPartOfName ger rÃ¤tt antal personer (1 st) som bÃ¶rjar med 'B'");
        test(contains(found, "Bodil"), "findAll hittar personer som bÃ¶rjar med 'B'");
        found = reg.findByPartOfName("da");
        test(found.size() == 3, "findByPartOfName ger rÃ¤tt antal personer (3 st) som innehÃ¥ller 'da'");
        test(contains(found, "Adam") &&
             contains(found, "Amanda") &&
             contains(found, "Matilda"), "findByPartOfName hittar personer som innehÃ¥ller 'da'");

        subheader("testar findByNumber (och, indirekt, insert och remove)");
        found = reg.findByNumber("123");
        test(found.size() == 1, "findByPartOfName ger rÃ¤tt antal personer (1 st)");
        test(contains(found, "Amanda"), "findByNumber hittar enstaka personer med givet nummer");
        found = reg.findByNumber("234");
        test(found.size() == 3, "findByNumber ger rÃ¤tt antal personer (3 st)");
        test(contains(found, "Bodil") &&
             contains(found, "Adam") &&
             contains(found, "Matilda"), "findByNumber hittar flera personer med givet nummer");
        reg.remove("Adam");
        found = reg.findByNumber("234");
        test(found.size() == 2, "findByNumber ger rÃ¤tt antal personer (2 st) efter en borttagning");
        test(contains(found, "Bodil") &&
             contains(found, "Matilda"), "findByNumber hittar flera personer med givet nummer efter borttagning");

        subheader("testar om findAll ger namnen i bokstavsordning (pÃ¥verkas av insert)");
        clear(reg);
        reg.insert("David", "123");
        reg.insert("Amanda", "234");
        reg.insert("Emma", "345");
        reg.insert("Adam", "456");
        reg.insert("Bodil", "567");
        found = reg.findAll();
        test(found.size() == 5 &&
             found.get(0).getName().equals("Adam") &&
             found.get(1).getName().equals("Amanda") &&
             found.get(2).getName().equals("Bodil") &&
             found.get(3).getName().equals("David") &&
             found.get(4).getName().equals("Emma"), "findAll ger namn i bokstavsordning");
        reg.remove("Amanda");
        found = reg.findAll();
        test(found.size() == 4 &&
             found.get(0).getName().equals("Adam") &&
             found.get(1).getName().equals("Bodil") &&
             found.get(2).getName().equals("David") &&
             found.get(3).getName().equals("Emma"), "findAll ger namn i bokstavsordning efter borttagning av nÃ¤st fÃ¶rsta namnet");
        reg.remove("Adam");
        found = reg.findAll();
        test(found.size() == 3 &&
             found.get(0).getName().equals("Bodil") &&
             found.get(1).getName().equals("David") &&
             found.get(2).getName().equals("Emma"), "findAll ger namn i bokstavsordning efter borttagning av fÃ¶rsta namnet");

        reg.remove("Emma");
        found = reg.findAll();
        test(found.size() == 2 &&
             found.get(0).getName().equals("Bodil") &&
             found.get(1).getName().equals("David"), "findAll ger namn i bokstavsordning efter borttagning av sista namnet");
    }

    boolean contains(List<Person> people, String name) {
        for (Person p : people) {
            if (p.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    void clear(Register reg) {
        for (Person p : reg.findAll()) {
            reg.remove(p.getName());
        }
    }
}