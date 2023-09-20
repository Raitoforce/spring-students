# spring-students

## Steps to mount the project

- _Install java 17_
- _Install maven_
- _Install postgres_

## Steps for mount
- _mvn install_
- _mvn spring-boot:run_

## Postgres Schema

#### Create Database
```psql
CREATE DATABASE 'studentdb';
```
#### Create Table
```psql
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);
```

#### Find All Students
```psql
SELECT * FROM students;
```

#### Find by Id (X)
```psql
SELECT * FROM students WHERE id = X;
```

#### Find by Id (X)
```psql
SELECT * FROM students WHERE id = X;
```

#### Insert One Student (X)
```psql
INSERT INTO students (first_name, last_name, email, age)
VALUES ('John', 'Doe', 'john.doe@example.com', 25);
```

#### Update One Student field (email)
```psql
UPDATE students
SET email = 'nuevo.email@example.com'
WHERE id = X;
```

#### Delete by id(X)
```psql
DELETE FROM students WHERE id = X;
```

## Script with Python

### Install before run the script
``` cmd
  pip install psycopg2
```


```python
import psycopg2
from psycopg2 import sql

# Configura los datos de conexión a la base de datos
db_config = {
    'host': 'tu_host',
    'database': 'tu_base_de_datos',
    'user': 'tu_usuario',
    'password': 'tu_contraseña'
}

try:
    conn = psycopg2.connect(**db_config)
    cursor = conn.cursor()
except psycopg2.Error as e:
    print("Error al conectar a la base de datos:", e)
    exit(1)

data_to_insert = [
    ('Juan', 'Pérez', 'juan@example.com'),
    ('María', 'Gómez', 'maria@example.com'),
    ('Carlos', 'López', 'carlos@example.com')
]


try:
    for row in data_to_insert:
        insert_query = sql.SQL("INSERT INTO students (first_name, last_name, email) VALUES ({}, {}, {})").format(
            sql.Identifier(row[0]),
            sql.Identifier(row[1]),
            sql.Identifier(row[2])
        )
        cursor.execute(insert_query)
    conn.commit()
except psycopg2.Error as e:
    print("Error al insertar datos:", e)
    conn.rollback()
    exit(1)

try:
    cursor.execute("SELECT COUNT(*) FROM students")
    count = cursor.fetchone()[0]
    print(f"Hay {count} registros de estudiantes.")
except psycopg2.Error as e:
    print("Error al obtener el conteo de registros:", e)

conn.close()

```





