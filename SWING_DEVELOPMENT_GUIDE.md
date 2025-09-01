# 🎨 Guía Completa: Desarrollo Swing Profesional en VS Code

## 📋 Índice
1. [Plugins y Extensiones](#plugins-y-extensiones)
2. [Herramientas Creadas](#herramientas-creadas)
3. [Snippets Personalizados](#snippets-personalizados)
4. [Workflows de Desarrollo](#workflows-de-desarrollo)
5. [Mejores Prácticas](#mejores-prácticas)
6. [Debugging Avanzado](#debugging-avanzado)
7. [Productividad Tips](#productividad-tips)

---

## 🚀 Plugins y Extensiones

### **Extensiones Instaladas:**
- ✅ **Code Generator For Java** - `sohibe.java-generate-setters-getters`
- ✅ **Code Runner** - `formulahendry.code-runner`
- ✅ **IntelliCode** - `visualstudioexptteam.vscodeintellicode`
- ✅ **GitHub Copilot** - `github.copilot`
- ✅ **Doxygen Documentation Generator** - `cschlosser.doxdocgen`

### **Cómo usar cada extensión:**

#### 🤖 **Code Generator For Java**
```java
// Selecciona una clase y usa:
// Ctrl+Shift+P -> "Generate Getters and Setters"
// Ctrl+Shift+P -> "Generate Constructor"
// Ctrl+Shift+P -> "Generate toString Method"

public class Book {
    private String title;
    private String author;
    // Genera automáticamente getters, setters, constructores
}
```

#### ⚡ **Code Runner**
```bash
# Ejecuta archivos Java directamente
# Ctrl+Alt+N o clic derecho -> "Run Code"
# Configuración automática para Swing apps
```

#### 🧠 **IntelliCode**
- Sugerencias inteligentes basadas en patrones
- Autocompletado contextual para Swing
- Recomendaciones de APIs más usadas

---

## 🛠️ Herramientas Creadas

### **1. SwingComponentFactory.java**
Factory pattern para crear componentes estilizados:

```java
// Botones estilizados
JButton primaryBtn = SwingComponentFactory.createPrimaryButton("Save");
JButton dangerBtn = SwingComponentFactory.createDangerButton("Delete");

// Campos de texto consistentes
JTextField field = SwingComponentFactory.createStyledTextField(20);

// Paneles con estilo
JPanel panel = SwingComponentFactory.createStyledPanel("Form");

// Look & Feel personalizado
SwingComponentFactory.setLookAndFeel();
```

### **2. SwingCodeGenerator.java**
Generador interactivo de código:

```bash
# Ejecutar:
java -cp target/classes com.bookstore.bookstore.swing.utils.SwingCodeGenerator

# Opciones disponibles:
1. Generate JFrame class      - Ventana completa
2. Generate JPanel class      - Panel reutilizable
3. Generate JDialog class     - Diálogo modal
4. Generate CRUD Panel        - Panel con tabla y formulario
5. Generate Form Panel        - Formulario dinámico
```

### **3. Aplicaciones de Ejemplo**
- **BookstoreMainFrame.java** - Aplicación básica con formularios
- **AdvancedBookManagerFrame.java** - CRUD completo con tabla

---

## 📝 Snippets Personalizados

### **Uso de Snippets (archivo `.vscode/java-swing.code-snippets`):**

#### **Crear JFrame completo:**
```java
// Escribir: swing-frame + Tab
// Genera estructura completa de JFrame con métodos organizados
```

#### **Crear JPanel:**
```java
// Escribir: swing-panel + Tab
// Genera panel con estructura MVC
```

#### **Botón con acción:**
```java
// Escribir: swing-button + Tab
JButton saveButton = new JButton("Save");
saveButton.addActionListener(e -> {
    // Action code here
});
panel.add(saveButton);
```

#### **Tabla con modelo:**
```java
// Escribir: swing-table + Tab
String[] columnNames = {"Column1", "Column2", "Column3"};
DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
JTable table = new JTable(tableModel);
table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
JScrollPane scrollPane = new JScrollPane(table);
```

#### **Formulario con GridBagLayout:**
```java
// Escribir: swing-form + Tab
// Genera estructura de formulario optimizada
```

---

## ⚡ Workflows de Desarrollo

### **1. Workflow Básico:**
```bash
1. Crear clase: swing-frame + Tab
2. Compilar: Ctrl+Shift+P -> "Tasks: Run Task" -> "Compile"
3. Ejecutar: F5 o Ctrl+Shift+P -> "Tasks: Run Task" -> "🎨 Run Basic Swing App"
4. Debug: F5 con breakpoints
```

### **2. Workflow Avanzado:**
```bash
1. Generar código: java SwingCodeGenerator
2. Usar Factory: SwingComponentFactory.createXXX()
3. Aplicar snippets para acelerar desarrollo
4. Ejecutar: "🚀 Run Advanced Swing App"
5. Profile con Java profiler integrado
```

### **3. Tasks Configuradas:**
- 🎨 **Run Basic Swing App** - Aplicación simple
- 🚀 **Run Advanced Swing App** - Aplicación con tabla
- 🛠️ **Run Swing Code Generator** - Generador de código
- 🔧 **Compile and Run Swing** - Compilar y ejecutar en un paso

---

## 🎯 Mejores Prácticas

### **1. Estructura del Proyecto:**
```
src/main/java/com/bookstore/bookstore/
├── swing/
│   ├── BookstoreMainFrame.java          # Ventana principal
│   ├── AdvancedBookManagerFrame.java    # Aplicación avanzada
│   ├── components/                      # Componentes custom
│   ├── dialogs/                         # Diálogos modales
│   ├── panels/                          # Paneles reutilizables
│   └── utils/
│       ├── SwingComponentFactory.java   # Factory components
│       └── SwingCodeGenerator.java      # Generador código
```

### **2. Patrones Recomendados:**

#### **Factory Pattern:**
```java
// ✅ Usar Factory
JButton btn = SwingComponentFactory.createPrimaryButton("Save");

// ❌ No crear directamente
JButton btn = new JButton("Save");
btn.setBackground(Color.BLUE);
btn.setForeground(Color.WHITE);
// ... más configuración repetitiva
```

#### **MVC Pattern:**
```java
public class BookPanel extends JPanel {
    // Model
    private BookService bookService;
    
    // View
    private JTable bookTable;
    private JTextField titleField;
    
    // Controller
    private void setupEventHandlers() {
        addButton.addActionListener(e -> addBook());
    }
}
```

### **3. Event Dispatch Thread:**
```java
// ✅ Correcto
SwingUtilities.invokeLater(() -> {
    // Update UI components
    label.setText("Updated!");
});

// ❌ Incorrecto - desde background thread
// label.setText("Updated!"); // Can cause EDT violations
```

### **4. Gestión de Recursos:**
```java
// ✅ Dispose listeners
public void cleanup() {
    // Remove listeners to prevent memory leaks
    button.removeActionListener(actionListener);
    table.getSelectionModel().removeListSelectionListener(selectionListener);
}
```

---

## 🐛 Debugging Avanzado

### **1. Configuración de Debug:**
```json
// .vscode/launch.json ya configurado con:
{
    "name": "🎨 Basic Swing App",
    "request": "launch",
    "mainClass": "com.bookstore.bookstore.swing.BookstoreMainFrame",
    "console": "integratedTerminal"
}
```

### **2. Breakpoints Útiles:**
- **Event Handlers** - actionPerformed, valueChanged
- **Model Updates** - setValueAt, addRow, removeRow
- **Validation** - validateForm, checkData
- **Lifecycle** - initializeComponents, setupLayout

### **3. Variables de Interés:**
- **Component State** - isVisible(), isEnabled(), getText()
- **Table State** - getSelectedRow(), getValueAt()
- **Model State** - getRowCount(), getColumnCount()

### **4. Common Issues y Solutions:**
```java
// EDT Violations
// Solución: Use SwingUtilities.invokeLater()

// Memory Leaks
// Solución: Remove listeners in cleanup()

// Layout Issues
// Solución: Use revalidate() and repaint()

// Thread Safety
// Solución: All UI updates on EDT
```

---

## 🚀 Productividad Tips

### **1. Keyboard Shortcuts:**
- **F5** - Debug current configuration
- **Ctrl+Shift+D** - Open debug view
- **Ctrl+Shift+P** - Command palette
- **Ctrl+Alt+N** - Run with Code Runner
- **Ctrl+Space** - Trigger IntelliSense

### **2. Code Generation:**
```java
// Generar getters/setters: Ctrl+Shift+P -> "Generate"
// Generar constructor: Select fields + Ctrl+Shift+P
// Documentación: /** + Enter (con Doxygen extension)
```

### **3. Live Templates Usage:**
```java
// Tipo: swing-frame + Tab = JFrame completo
// Tipo: swing-button + Tab = Botón con listener
// Tipo: swing-table + Tab = Tabla con modelo
// Tipo: swing-form + Tab = Formulario GridBag
```

### **4. Refactoring:**
- **F2** - Rename symbol
- **Ctrl+.** - Quick fix
- **Alt+Shift+R** - Refactor menu
- **Ctrl+Shift+O** - Organize imports

### **5. Multi-cursor Editing:**
- **Ctrl+Alt+Down** - Add cursor below
- **Ctrl+D** - Select next occurrence
- **Alt+Click** - Add cursor at position

---

## 🔄 Continuous Development

### **1. Auto-compile Setup:**
```json
// .vscode/settings.json
{
    "java.compile.nullAnalysis.mode": "automatic",
    "java.configuration.updateBuildConfiguration": "automatic"
}
```

### **2. Hot Reload:**
```bash
# Para cambios sin reiniciar:
# 1. Usar debug mode
# 2. Hacer cambios
# 3. Save (auto-recompile)
# 4. Hot swap automático
```

### **3. Testing Strategy:**
```java
// Unit tests para lógica de negocio
// UI tests con AssertJ Swing (opcional)
// Manual testing con aplicaciones de ejemplo
```

---

## 📚 Recursos Adicionales

### **Documentación:**
- [Oracle Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)
- [VS Code Java Extension Guide](https://code.visualstudio.com/docs/java/java-gui)

### **Librerías Complementarias:**
- **MigLayout** - Layout manager avanzado
- **JGoodies** - Componentes profesionales
- **AssertJ Swing** - Testing UI automatizado

### **Tools Externos:**
- **WindowBuilder** - GUI designer (Eclipse plugin)
- **NetBeans GUI Builder** - Visual designer
- **IntelliJ GUI Designer** - Form designer

---

## 🎯 Próximos Pasos

1. **Practica con las aplicaciones de ejemplo**
2. **Usa el generador de código para crear nuevas ventanas**
3. **Experimenta con los snippets**
4. **Integra con tu lógica de negocio Spring Boot**
5. **Explora patrones avanzados (Observer, Command)**

---

**🎉 ¡Ya tienes un entorno completo para desarrollo Swing profesional en VS Code!**

Para dudas o mejoras, consulta la documentación o experimenta con las herramientas creadas.
