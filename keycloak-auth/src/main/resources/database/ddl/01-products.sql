DO $$
  DECLARE
    r RECORD;
  BEGIN
    FOR r IN SELECT schema_name
             FROM information_schema.schemata
             WHERE schema_name NOT IN ('information_schema', 'pg_catalog', 'pg_toast')
      LOOP
        EXECUTE 'CREATE TABLE IF NOT EXISTS ' || r.schema_name || '.products (' ||
                'id SERIAL PRIMARY KEY,' ||
                'name VARCHAR(50) NOT NULL,' ||
                'price DOUBLE PRECISION NOT NULL);';
      END LOOP;
END$$;
;