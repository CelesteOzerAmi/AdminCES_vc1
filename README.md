## Proyecto simulador de sitio AdminCES. 
Se identifican las siguientes funcionalidades con sus respectivos datos requeridos:

*A. Funcionalidades sin iniciar sesión.*
  1. Registrarse: Permite crear un nuevo usuario de admin. Requiere nombre, apellido, email, país de nacimiento y contraseña por defecto. Restricciones: email no debe estar utilizado en otro usuario tester o admin.
  2. Reiniciar contraseña: Permite reiniciar contraseña de un usuario propio o de tester. Requiere email, nueva contraseña. Restricciones: email debe estar registrado en un usuario.
  3. Iniciar sesión: Permite iniciar sesión con un usuario admin ya creado. Restricciones: email debe estar registrado en un usuario admin.

*B. Funcionalidades habiendo iniciado sesión.*
  1. Crear usuario de tester: Permite crear un usuario nuevo de tester. Requiere nombre, apellido, email, país de nacimiento, contraseña por defecto y tipo de tester (Junior, Senior, Lider). Restricciones: email no debe estar utilizado en otro usuario tester o admin.
  2. Reiniciar contraseña: Permite reiniciar contraseña de un usuario propio o de tester. Requiere email, nueva contraseña. Restricciones: email debe estar registrado en un usuario.
  3. Ver usuarios: Permite ver usuarios registrados de admin o tester. Se visualizan todos los datos registrados de cada perfil.
  4. Eliminar usuario de tester: Permite eliminar un usuario de tester ya creado.
  5. Ver perfil: Permite ver el perfil propio, permitiendo editar datos. Restricciones: email a editar no puede existir en otro usuario. No permite modificar tipo de perfil. 

---

## Descripción de la implementación

El proyecto fue diseñado siguiendo principios SOLID y mejores prácticas de arquitectura orientada a objetos:

* **Patrón de Diseño Singleton:** La clase `SistemaUsuarios` fue transformada en un Singleton de instancia única (`getInstance()`), encapsulando el estado global del sistema (lista de usuarios y sesión activa) y asegurando que no existan múltiples instancias competidoras.
* **Modelo de Dominio y Herencia:**
  * `Usuario` *(Abstracta)*: Clase base que define atributos comunes (`name`, `lastName`, `email`, `country`, `password`) y la firma abstracta para `cambiarContrasena()`.
  * `Admin`: Subclase con privilegios de gestión (alta de nuevos testers, consulta global de usuarios, etc.).
  * `Tester`: Subclase que añade el atributo específico `rol` (Junior, Senior, Líder).
* **Manejo Personalizado de Excepciones:**
  * `EmailExisteException`: Validada durante el registro y edición para impedir duplicados en el sistema.
  * `UsuarioNoEncontradoException`: Disparada al consultar o autenticar correos no registrados.
* **Procesamiento de Datos con Java Streams:** Búsquedas, filtrados y ordenamientos optimizados mediante la API de Streams (`stream().filter()`, `anyMatch()`, etc.).
* **Manejo Robusto de Consola:** Control de flujo interactivo que atrapa `InputMismatchException` / `NumberFormatException` evitando cierres inesperados al ingresar datos no válidos.

---

## Requisitos Previos

* **Java Development Kit (JDK):** Versión 17 o superior (compatible hasta Java 21+).
* **IDE:** IntelliJ IDEA, Eclipse, NetBeans, o la extensión de Java en VS Code.

---

## Instrucciones de Ejecución

1. **Clonar el repositorio:**
   `git clone https://github.com/CelesteOzerAmi/AdminCES_vc1`
   `cd AdminCES_vc1` 
2. **Compilar el proyecto via terminal**
    `javac -d out src/org/example/*.java`
3. **Ejecutar la aplicación**
    `java -cp out org.example.Main`

---
## Diagrama UML ##

<img width="1453" height="893" alt="Diagrama sin título" src="https://github.com/user-attachments/assets/3dfc78fd-5e72-4252-826b-78af9ccf633b" />
