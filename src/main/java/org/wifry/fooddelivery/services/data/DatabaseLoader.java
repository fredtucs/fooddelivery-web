package org.wifry.fooddelivery.services.data;

import com.northconcepts.datapipeline.core.DataReader;
import com.northconcepts.datapipeline.core.Record;
import com.northconcepts.datapipeline.excel.ExcelDocument;
import com.northconcepts.datapipeline.excel.ExcelReader;
import org.fluttercode.datafactory.impl.DataFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.wifry.fooddelivery.model.*;
import org.wifry.fooddelivery.util.Md5Utils;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Profile("default")
@PropertySource("classpath:database.properties")
public class DatabaseLoader {

    @Value("${hibernate.hbm2ddl.auto}")
    private String dbhbm2ddl;

    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @PostConstruct
    public void init() {


        if (dbhbm2ddl.isEmpty())
            return;

        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);

        Session session = sessionFactory.openSession();

        String path = "E:\\Fredy\\dev\\fooddelivery-web\\src\\data\\";
//        try {
//            path = new ClassPathResource("data").getFile().getPath() + "/";
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        List<Permission> permissionList = new ArrayList<>();
        ExcelDocument document = new ExcelDocument().open(new File(path + "Permission.xlsx"));
        DataReader reader = new ExcelReader(document).setSheetName("data").setFieldNamesInFirstRow(true);
        reader.open();
        try {
            Record record;
            while ((record = reader.read()) != null) {
                Permission permission = new Permission(record.getField(0).getValueAsString(), record.getField(1).getValueAsString(), record.getField(2).getValueAsString(), Status.ACTIVO);
                session.save(permission);
                permissionList.add(permission);
            }
        } finally {
            reader.close();
        }

        Role role = new Role("ADMIN", "ADMIN", null, true, Status.ACTIVO);
        role.setPermissions(new HashSet<>(permissionList));
        session.save(role);

        Set<Role> roles = new HashSet<>(Arrays.asList(role));

        User user = new User("admin", Md5Utils.hash("demo"), false, true, true, true, true, roles, Status.ACTIVO);
        session.save(user);

        DataFactory factory = new DataFactory();

        for (int i = 0; i < 5; i++) {
            Category category = new Category(factory.getRandomText(10, 15), factory.getRandomText(20, 25), Status.ACTIVO);
            session.save(category);
        }

        session.flush();
        session.close();
    }


    private Long generateRandomId(long leftLimit, long rightLimit) {
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

}
