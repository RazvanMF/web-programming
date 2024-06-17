package org.example.jsptemplate.models;

import java.sql.Date;

// Perfect match from SQLite DB (this case, would be database first)
public class Model3 {
    public int Id;
    public String Field1;
    public int Field2;
    public Date Field3; //SQL Date

    public Model3(int id, String field1, int field2, Date field3) {
        Id = id;
        Field1 = field1;
        Field2 = field2;
        Field3 = field3;
    }
}
