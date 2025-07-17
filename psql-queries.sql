SELECT 'DROP TABLE IF EXISTS ' || quote_ident(tablename) || ' CASCADE;'
FROM pg_tables
WHERE schemaname = 'public'
ORDER BY tablename;


SELECT 'select count(*) from ' || quote_ident(tablename) ||' ;'
FROM pg_tables
WHERE schemaname = 'public'
ORDER BY tablename;


SELECT
    relname AS table_name,
    reltuples::bigint AS row_count
FROM
    pg_class
WHERE
    relkind = 'r' -- 'r' stands for relation (table)
    AND relnamespace = (SELECT oid FROM pg_namespace WHERE nspname = 'public')
ORDER BY
    relname;


SELECT
    'SELECT COUNT(*) AS ' || quote_ident(relname) || '_count FROM ' || quote_ident(schemaname) || '.' || quote_ident(relname) || ';'
FROM
    pg_stat_user_tables
WHERE
    schemaname = 'public'
ORDER BY
    relname;