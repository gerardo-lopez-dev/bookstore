#!/bin/bash
# 🚀 Scripts de Desarrollo Rápido para Bookstore
# Coloca este archivo en la raíz del proyecto y ejecuta: chmod +x dev-scripts.sh

# Colores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Función para mostrar títulos
print_title() {
    echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
    echo -e "${BLUE}  $1${NC}"
    echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
}

# Función para mostrar éxito
print_success() {
    echo -e "${GREEN}✅ $1${NC}"
}

# Función para mostrar advertencia
print_warning() {
    echo -e "${YELLOW}⚠️  $1${NC}"
}

# Función para mostrar error
print_error() {
    echo -e "${RED}❌ $1${NC}"
}

# Limpiar y compilar
clean_compile() {
    print_title "🧹 LIMPIANDO Y COMPILANDO"
    ./mvnw clean compile
    if [ $? -eq 0 ]; then
        print_success "Proyecto limpio y compilado correctamente"
    else
        print_error "Error en la compilación"
    fi
}

# Ejecutar tests
run_tests() {
    print_title "🧪 EJECUTANDO TESTS"
    ./mvnw test
    if [ $? -eq 0 ]; then
        print_success "Todos los tests pasaron"
    else
        print_error "Algunos tests fallaron"
    fi
}

# Ejecutar aplicación
run_app() {
    print_title "🚀 INICIANDO APLICACIÓN"
    print_warning "La aplicación se ejecutará en http://localhost:8080"
    print_warning "H2 Console disponible en http://localhost:8080/h2-console"
    print_warning "Base de datos: H2 (archivo persistente en ./data/bookstore_db)"
    print_warning "La base de datos se crea automáticamente si no existe"
    print_warning "Presiona Ctrl+C para detener"
    ./mvnw spring-boot:run
}

# Ejecutar aplicación en modo debug
run_debug() {
    print_title "🐛 INICIANDO EN MODO DEBUG"
    print_warning "Aplicación: http://localhost:8080"
    print_warning "H2 Console: http://localhost:8080/h2-console"
    print_warning "Debug puerto: 5005"
    print_warning "Base de datos: H2 (archivo persistente)"
    print_warning "Conecta tu debugger al puerto 5005"
    ./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
}

# Build completo
full_build() {
    print_title "🏗️  BUILD COMPLETO"
    ./mvnw clean compile test package
    if [ $? -eq 0 ]; then
        print_success "Build completo exitoso"
        print_success "JAR generado en target/"
    else
        print_error "Error en el build"
    fi
}

# Mostrar información del proyecto
project_info() {
    print_title "📋 INFORMACIÓN DEL PROYECTO"
    echo -e "${YELLOW}Proyecto:${NC} Bookstore Spring Boot"
    echo -e "${YELLOW}Versión Java:${NC} $(java -version 2>&1 | head -n 1)"
    echo -e "${YELLOW}Maven:${NC} $(./mvnw --version | head -n 1)"
    echo -e "${YELLOW}Puerto aplicación:${NC} 8080"
    echo -e "${YELLOW}Puerto debug:${NC} 5005"
    echo ""
    echo -e "${BLUE}📊 CONFIGURACIÓN DE BASE DE DATOS:${NC}"
    echo -e "${BLUE}┌─────────────────────────────────────────────────────────────┐${NC}"
    echo -e "${BLUE}│${NC} H2: Base de datos en archivo ./data/bookstore_db            ${BLUE}│${NC}"
    echo -e "${BLUE}│${NC} Auto-creación: Activada                                    ${BLUE}│${NC}"
    echo -e "${BLUE}│${NC} Consola H2: http://localhost:8080/h2-console               ${BLUE}│${NC}"
    echo -e "${BLUE}│${NC} JDBC URL: jdbc:h2:file:./data/bookstore_db                 ${BLUE}│${NC}"
    echo -e "${BLUE}│${NC} Usuario: sa (sin contraseña)                               ${BLUE}│${NC}"
    echo -e "${BLUE}└─────────────────────────────────────────────────────────────┘${NC}"
}

# Mostrar dependencias
show_dependencies() {
    print_title "📦 ÁRBOL DE DEPENDENCIAS"
    ./mvnw dependency:tree
}

# Verificar actualizaciones
check_updates() {
    print_title "🔍 VERIFICANDO ACTUALIZACIONES"
    ./mvnw versions:display-dependency-updates
}

# Limpiar todo
deep_clean() {
    print_title "🗑️  LIMPIEZA PROFUNDA"
    print_warning "Eliminando archivos temporales y caché..."
    ./mvnw clean
    rm -rf .mvn/wrapper/maven-wrapper.jar 2>/dev/null || true
    rm -rf target/ 2>/dev/null || true
    print_success "Limpieza completa realizada"
}

# Modo desarrollo (watch)
dev_mode() {
    print_title "⚡ MODO DESARROLLO"
    print_warning "Iniciando en modo desarrollo con recarga automática"
    print_warning "Los cambios se recargarán automáticamente"
    print_warning "Base de datos: H2 (archivo persistente)"
    print_warning "H2 Console: http://localhost:8080/h2-console"
    ./mvnw spring-boot:run -Dspring-boot.run.fork=false
}

# Función principal
main_menu() {
    clear
    echo -e "${BLUE}"
    echo "  ██████╗  ██████╗  ██████╗ ██╗  ██╗███████╗████████╗ ██████╗ ██████╗ ███████╗"
    echo "  ██╔══██╗██╔═══██╗██╔═══██╗██║ ██╔╝██╔════╝╚══██╔══╝██╔═══██╗██╔══██╗██╔════╝"
    echo "  ██████╔╝██║   ██║██║   ██║█████╔╝ ███████╗   ██║   ██║   ██║██████╔╝█████╗  "
    echo "  ██╔══██╗██║   ██║██║   ██║██╔═██╗ ╚════██║   ██║   ██║   ██║██╔══██╗██╔══╝  "
    echo "  ██████╔╝╚██████╔╝╚██████╔╝██║  ██╗███████║   ██║   ╚██████╔╝██║  ██║███████╗"
    echo "  ╚═════╝  ╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚══════╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝╚══════╝"
    echo -e "${NC}"
    echo -e "${YELLOW}🛠️  HERRAMIENTAS DE DESARROLLO${NC}"
    echo ""
    echo "1.  🧹  Limpiar y Compilar"
    echo "2.  🧪  Ejecutar Tests"
    echo "3.  🚀  Ejecutar Aplicación"
    echo "4.  🐛  Ejecutar en Debug"
    echo "5.  ⚡  Modo Desarrollo (Watch)"
    echo "6.  🏗️   Build Completo"
    echo "7.  📋  Información del Proyecto"
    echo "8.  📦  Mostrar Dependencias"
    echo "9.  🔍  Verificar Actualizaciones"
    echo "10. 🗑️   Limpieza Profunda"
    echo "0.  ❌  Salir"
    echo ""
    echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
    read -p "Selecciona una opción [0-10]: " choice
    
    case $choice in
        1) clean_compile ;;
        2) run_tests ;;
        3) run_app ;;
        4) run_debug ;;
        5) dev_mode ;;
        6) full_build ;;
        7) project_info ;;
        8) show_dependencies ;;
        9) check_updates ;;
        10) deep_clean ;;
        0) 
            print_success "¡Hasta luego! 👋"
            exit 0
            ;;
        *)
            print_error "Opción inválida"
            read -p "Presiona Enter para continuar..."
            ;;
    esac
    
    echo ""
    read -p "Presiona Enter para volver al menú principal..."
    main_menu
}

# Si se ejecuta sin argumentos, mostrar menú
if [ $# -eq 0 ]; then
    main_menu
else
    # Permitir ejecutar funciones directamente
    case $1 in
        "clean") clean_compile ;;
        "test") run_tests ;;
        "run") run_app ;;
        "debug") run_debug ;;
        "dev") dev_mode ;;
        "build") full_build ;;
        "info") project_info ;;
        "deps") show_dependencies ;;
        "updates") check_updates ;;
        "deep-clean") deep_clean ;;
        *)
            echo "Uso: $0 [clean|test|run|debug|dev|build|info|deps|updates|deep-clean]"
            echo "O ejecuta sin argumentos para el menú interactivo"
            ;;
    esac
fi
