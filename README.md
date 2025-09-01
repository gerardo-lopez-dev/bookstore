# 📚 Bookstore Application

Una aplicación completa de gestión de librería desarrollada con **Spring Boot** y **Java Swing**, que combina una interfaz web REST API con una interfaz gráfica de escritorio.

## 🎯 Características

- **🖥️ Interfaz Gráfica**: Aplicación Swing para gestión visual
- **💾 Base de Datos**: H2 Database con persistencia
- **🔄 Spring Boot**: Framework moderno con auto-configuración
- **📊 JPA/Hibernate**: ORM para manejo de datos
- **🛠️ Maven**: Gestión de dependencias y build

## 🏗️ Arquitectura

```
📁 src/main/java/com/bookstore/bookstore/
├── 📄 BookstoreApplication.java          # Aplicación principal Spring Boot
├── 📁 model/
│   └── 📄 Book.java                      # Entidad JPA Book
├── 📁 repository/
│   └── 📄 BookRepository.java            # Repositorio JPA
├── 📁 service/
│   ├── 📄 BookService.java               # Interfaz de servicio
│   └── 📁 impl/
│       └── 📄 BookServiceImpl.java       # Implementación de servicio
└── 📁 swing/
    ├── 📄 AdvancedBookManagerFrame.java  # Interfaz Swing avanzada
    ├── 📄 BookstoreMainFrame.java        # Interfaz Swing básica
    ├── 📄 SwingBookstoreApplication.java # Aplicación Swing principal
    ├── 📁 config/
    │   └── 📄 GuiAvailableCondition.java # Condición GUI
    └── 📁 utils/
        ├── 📄 SwingCodeGenerator.java    # Generador de código
        └── 📄 SwingComponentFactory.java # Factory de componentes
```

## 🚀 Tecnologías

- **Java 21** - Lenguaje de programación
- **Spring Boot 3.5.5** - Framework principal
- **Spring Data JPA** - Acceso a datos
- **H2 Database** - Base de datos embebida
- **Java Swing** - Interfaz gráfica
- **Lombok** - Reducción de código boilerplate
- **Maven** - Gestión de dependencias

## 📋 Prerrequisitos

- **Java 21** o superior
- **Maven 3.6+** (incluido mvnw)
- **Git** (opcional)

## ⚡ Inicio Rápido

### 1. Clonar el repositorio
```bash
git clone https://github.com/gerardo-lopez-dev/bookstore.git
cd bookstore
```

### 2. Compilar el proyecto
```bash
./mvnw clean compile
```

### 3. Ejecutar la aplicación

#### 🌐 Aplicación Web (Spring Boot)
```bash
./mvnw spring-boot:run
```
- API REST disponible en: `http://localhost:8080`
- Consola H2: `http://localhost:8080/h2-console`

#### 🖥️ Aplicación Swing
```bash
# Aplicación Swing básica
./mvnw compile
java -cp target/classes com.bookstore.bookstore.swing.BookstoreMainFrame

# Aplicación Swing avanzada
java -cp target/classes com.bookstore.bookstore.swing.AdvancedBookManagerFrame

# Aplicación Swing con Spring Boot
./mvnw spring-boot:run -Dspring-boot.run.main-class=com.bookstore.bookstore.swing.SwingBookstoreApplication
```

## 🔧 Tareas Disponibles

El proyecto incluye múltiples tareas de Maven configuradas:

### 🏗️ Compilación y Build
```bash
./mvnw clean              # Limpiar proyecto
./mvnw compile             # Compilar código
./mvnw clean compile       # Limpiar y compilar
./mvnw package             # Crear JAR
./mvnw install             # Instalar en repositorio local
```

### 🧪 Testing
```bash
./mvnw test                # Ejecutar tests
./mvnw test -X             # Tests con salida detallada
./mvnw clean test jacoco:report  # Tests con cobertura
./mvnw verify              # Verificaciones completas
```

### 🚀 Ejecución
```bash
./mvnw spring-boot:run     # Aplicación web
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"  # Debug mode
```

### 🔍 Utilidades
```bash
./mvnw dependency:tree     # Árbol de dependencias
./mvnw versions:display-dependency-updates  # Verificar actualizaciones
```

## 📊 Base de Datos

### Configuración H2
- **URL**: `jdbc:h2:file:./data/bookstore_db`
- **Usuario**: `root`
- **Contraseña**: `root`
- **Consola Web**: `http://localhost:8080/h2-console`

### Modelo de Datos
```sql
-- Tabla: BOOK
CREATE TABLE book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) UNIQUE NOT NULL,
    price DOUBLE NOT NULL,
    stock INTEGER NOT NULL,
    description TEXT,
    available BOOLEAN NOT NULL DEFAULT TRUE
);
```

## 🖼️ Interfaces Gráficas

### 1. BookstoreMainFrame
- Interfaz básica para gestión de libros
- Operaciones CRUD simples
- Ideal para usuarios básicos

### 2. AdvancedBookManagerFrame
- Interfaz avanzada con más funcionalidades
- Búsquedas y filtros
- Gestión de inventario
- Reportes básicos

### 3. SwingBookstoreApplication
- Integración completa con Spring Boot
- Acceso a todos los servicios
- Configuración automática

## 🐛 Desarrollo

### Modo Desarrollo
```bash
# Aplicación web con recarga automática
./mvnw spring-boot:run -Dspring-boot.run.fork=false

# Compilar y ejecutar Swing
./mvnw compile && java -cp target/classes com.bookstore.bookstore.swing.AdvancedBookManagerFrame
```

### Debug
```bash
# Aplicación web en modo debug (puerto 5005)
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```

## 📁 Estructura del Proyecto

```
bookstore/
├── 📄 README.md                    # Este archivo
├── 📄 pom.xml                      # Configuración Maven
├── 📄 DEVELOPMENT.md               # Guía de desarrollo
├── 📄 SWING_DEVELOPMENT_GUIDE.md   # Guía Swing
├── 📄 dev-scripts.sh               # Scripts de desarrollo
├── 📁 data/                        # Base de datos H2
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/                # Código fuente
│   │   └── 📁 resources/           # Recursos y configuración
│   └── 📁 test/                    # Tests
└── 📁 target/                      # Archivos compilados
```

## 🤝 Contribución

1. Fork el proyecto
2. Crear una rama feature (`git checkout -b feature/amazing-feature`)
3. Commit los cambios (`git commit -m 'Add amazing feature'`)
4. Push a la rama (`git push origin feature/amazing-feature`)
5. Abrir un Pull Request

## 📝 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 👨‍💻 Autor

**Gerardo López**
- GitHub: [@gerardo-lopez-dev](https://github.com/gerardo-lopez-dev)

## 🔗 Enlaces Útiles

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [H2 Database](https://www.h2database.com/)
- [Java Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)
- [Maven Documentation](https://maven.apache.org/guides/)

## 📈 Roadmap

- [ ] Implementar autenticación y autorización
- [ ] Agregar API de categorías
- [ ] Implementar sistema de reservas
- [ ] Añadir notificaciones en tiempo real
- [ ] Crear dashboard de analytics
- [ ] Implementar sistema de reportes
- [ ] Añadir soporte para múltiples idiomas
- [ ] Crear aplicación móvil

---

⭐ **¡No olvides dar una estrella al proyecto si te resulta útil!** ⭐
