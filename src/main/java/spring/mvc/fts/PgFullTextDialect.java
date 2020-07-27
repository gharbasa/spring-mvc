package spring.mvc.fts;

public class  PgFullTextDialect extends org.hibernate.dialect.PostgreSQL82Dialect {
    
    public PgFullTextDialect() {
        registerFunction("fts", new PgFullTextFunction());
        //registerFunction("ts_rank", new StandardSQLFunction("ts_rank", DoubleType.INSTANCE));
        //registerFunction("to_tsquery", new StandardSQLFunction("to_tsquery", ObjectType.INSTANCE));
    }
    
}
