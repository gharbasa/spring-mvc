package spring.mvc.fts;

import java.util.List;

import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.BooleanType;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PgFullTextFunction implements SQLFunction {

	private static Logger log = LoggerFactory.getLogger(PgFullTextFunction.class);
	
    /* Column name of TSVECTOR field in PgSQL table */
    public static final String FTS_VECTOR_FIELD = "search_text";

    @Override
    public Type getReturnType(Type firstArgumentType, Mapping mapping) throws QueryException {
    	return new BooleanType();
    }

    @Override
    public boolean hasArguments() {
        return true;
    }

    @Override
    public boolean hasParenthesesIfNoArguments() {
        return false;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public String render(Type firstArgumentType, List arguments, SessionFactoryImplementor factory) throws QueryException {
    	/*
    	String searchString = (String) arguments.get(0);
        return FTS_VECTOR_FIELD + " @@ to_tsquery(" + searchString + ")";
        */
    	String fragment = null;
        String ftsConfiguration = null;
        String field = null;
        String value = null;
        log.debug("PgFullTextFunction arguments {}", arguments.size());
        if (arguments.size() == 3) {
            ftsConfiguration = (String) arguments.get(0);
            field = (String) arguments.get(1);
            value = (String) arguments.get(2);
            fragment = "to_tsvector(" + ftsConfiguration + ", " + field + ") @@ " 
            			+ "plainto_tsquery(" + ftsConfiguration + ", " + value + ")";
            log.debug("3 arguments");
        } else {
            field = (String) arguments.get(0);
            value = (String) arguments.get(1);
            fragment = "to_tsvector(" + field + ") @@ " + "plainto_tsquery('" + value + "')";
            log.debug("not 3 arguments");
       }
       log.debug("fragment {}", fragment);
       return fragment;
    }
}
