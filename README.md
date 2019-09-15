University Java project aimed to connect to data base through JDBC. 
Postgres DB contains 4 instances: `Client`, `Company`, `Flight`, `Ticket` and are generally 
designed for airports.

The separate DAO object is created for every instance due to calls to `DaoFactory`. 
`DaoFactory` is a Singleton that opens connection to db only at once (in constructor 
or manually) and close it after all.

`FasadeJDBC` is a class to make general actions not to care about details. It can 
recreate all tables, insert example raws, show all instances rows and so on.
