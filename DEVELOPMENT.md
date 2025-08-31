# Herramientas de Desarrollo - Bookstore Spring Boot

Este proyecto incluye una configuración completa de herramientas para el desarrollo con Spring Boot en VS Code.

## 🛠️ Tareas Disponibles

Puedes acceder a todas las tareas mediante:
- **Ctrl+Shift+P** → "Tasks: Run Task"
- **Terminal** → "Run Task..."

### 📦 Tareas de Build

| Tarea | Descripción | Comando |
|-------|-------------|---------|
| **Clean** | Limpia archivos compilados | `./mvnw clean` |
| **Compile** | Compila el código fuente | `./mvnw compile` |
| **Clean Compile** | Limpia y compila | `./mvnw clean compile` |
| **Package** | Empaqueta en JAR | `./mvnw package` |
| **Install** | Instala en repositorio local | `./mvnw install` |
| **Build Full** | Build completo con tests | `./mvnw clean compile test package` |

### 🚀 Tareas de Ejecución

| Tarea | Descripción | Puerto Debug |
|-------|-------------|--------------|
| **Run Application** | Ejecuta la aplicación | - |
| **Run Application (Debug Mode)** | Ejecuta con debug habilitado | 5005 |
| **Dev Watch** | Modo desarrollo con recarga | - |

### 🧪 Tareas de Testing

| Tarea | Descripción |
|-------|-------------|
| **Run Tests** | Ejecuta todas las pruebas |
| **Test (Verbose)** | Tests con salida detallada |
| **Test with Coverage** | Tests con reporte de cobertura |
| **Verify** | Ejecuta verificaciones completas |

### 🔧 Tareas de Mantenimiento

| Tarea | Descripción |
|-------|-------------|
| **Dependency Tree** | Muestra árbol de dependencias |
| **Check Updates** | Verifica actualizaciones disponibles |
| **Format Code** | Formatea código (requiere spotless) |

## 🐛 Configuración de Debug

### Configuraciones Disponibles

1. **Debug Spring Boot Application**
   - Inicia la aplicación principal en modo debug
   - Clase principal: `com.bookstore.bookstore.BookstoreApplication`

2. **Debug Tests**
   - Debuggea pruebas unitarias específicas

3. **Attach to Remote Debug**
   - Se conecta a una instancia remota en puerto 5005
   - Útil cuando ejecutas "Run Application (Debug Mode)"

4. **Debug Current Test File**
   - Debuggea el archivo de test actual

### Cómo Debuggear

1. **Debug directo:**
   - F5 o "Run and Debug" → "Debug Spring Boot Application"

2. **Debug remoto:**
   - Ejecuta tarea "Run Application (Debug Mode)"
   - Luego "Run and Debug" → "Attach to Remote Debug"

## ⚡ Flujo de Trabajo Recomendado

### Desarrollo Diario
```bash
1. Clean Compile    # Limpia y compila
2. Run Tests        # Verifica que todo funciona
3. Dev Watch        # Modo desarrollo
```

### Antes de Commit
```bash
1. Build Full       # Build completo con tests
2. Verify          # Verificaciones adicionales
```

### Debug de Problemas
```bash
1. Run Application (Debug Mode)  # Inicia con debug
2. Attach to Remote Debug       # Conecta debugger
```

## 📝 Configuraciones de VS Code

### Configuraciones Automáticas
- Build automático al guardar
- Organización automática de imports
- Configuración optimizada para Spring Boot
- Exclusión de archivos innecesarios del explorador

### Extensiones Recomendadas
- Extension Pack for Java
- Spring Boot Extension Pack
- Lombok Annotations Support

## 🔥 Atajos de Teclado Útiles

| Atajo | Acción |
|-------|--------|
| **Ctrl+Shift+P** | Comando palette |
| **Ctrl+Shift+`** | Nueva terminal |
| **F5** | Iniciar debugging |
| **Shift+F5** | Detener debugging |
| **F9** | Toggle breakpoint |
| **F10** | Step over |
| **F11** | Step into |

## 🚨 Troubleshooting

### Problemas Comunes

1. **Error "Java not found"**
   - Verifica que Java 21 esté instalado
   - Configura JAVA_HOME correctamente

2. **Tests fallan**
   - Ejecuta "Clean" antes de "Run Tests"
   - Verifica que H2 database esté funcionando

3. **Debug no se conecta**
   - Asegúrate de ejecutar "Run Application (Debug Mode)" primero
   - Verifica que el puerto 5005 esté libre

4. **Build falla**
   - Ejecuta "Clean" primero
   - Verifica conexión a internet para dependencias

### Logs y Diagnóstico
- Usa "Test (Verbose)" para logs detallados
- Revisa la terminal integrada para errores
- Usa "Dependency Tree" para problemas de dependencias

## 📊 Puertos por Defecto

| Servicio | Puerto |
|----------|--------|
| Aplicación Spring Boot | 8080 |
| Debug Java | 5005 |
| H2 Console | 8080/h2-console |

¡Feliz desarrollo! 🎉
