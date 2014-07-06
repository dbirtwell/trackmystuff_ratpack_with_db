import com.google.inject.AbstractModule
import groovy.sql.Sql
import gstorm.Gstorm

class GstormModule extends AbstractModule {
    @Override
    protected void configure() {
        Gstorm gstorm = new Gstorm(
                Sql.newInstance("jdbc:hsqldb:file:./db/testdb", "sa", "", "org.hsqldb.jdbcDriver")
        )
        gstorm.stormify(Item)
        bind(Gstorm).toInstance(gstorm)
    }
}
