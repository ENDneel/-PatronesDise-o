package oscarblancarte.ipd.builder;

import oscarblancarte.ipd.builder.dto.Employee;

/**
 * @author oblancarte
 */
public class BuilderMain {

    public static void main(String[] args) {
        Employee emp = new Employee.EmployeeBuilder()
                .setName("Jessica Karina Panama Mazhenda")
                .setGender("Female")
                .setAge(29)
                .setAdress("Andres " 
                        + "Viscaino", "Chaullabamba", "Ecuador", "170504")
                .addContacs("Byram Chimbo", "0982457633", "593", "Casa", 
                        "Sector Yanuncay", "Ecuador"
                        , "Ecuador", "170504")
                .addPhones("4567890234", null, "Celular")
                .addPhones("7788990099", null, "Casa")
                .setDepartment("Finanzas")
                .build();
        System.out.println(emp);
    }
}
