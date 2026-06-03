Proyecto simulador de sitio AdminCES. 
Se identifican las siguientes funcionalidades con sus respectivos datos requeridos:

A. Funcionalidades sin iniciar sesión.
  1. Registrarse: Permite crear un nuevo usuario de admin. Requiere nombre, apellido, email, país de nacimiento y contraseña por defecto. Restricciones: email no debe estar utilizado en otro usuario tester o admin.
  2. Reiniciar contraseña: Permite reiniciar contraseña de un usuario propio o de tester. Requiere email, nueva contraseña. Restricciones: email debe estar registrado en un usuario.
  3. Iniciar sesión: Permite iniciar sesión con un usuario admin ya creado. Restricciones: email debe estar registrado en un usuario admin.

B. Funcionalidades habiendo iniciado sesión.
  1. Crear usuario de tester: Permite crear un usuario nuevo de tester. Requiere nombre, apellido, email, país de nacimiento, contraseña por defecto y tipo de tester (Junior, Senior, Lider). Restricciones: email no debe estar utilizado en otro usuario tester o admin.
  2. Reiniciar contraseña: Permite reiniciar contraseña de un usuario propio o de tester. Requiere email, nueva contraseña. Restricciones: email debe estar registrado en un usuario.
  3. Ver usuarios: Permite ver usuarios registrados de admin o tester. Se visualizan todos los datos registrados de cada perfil.
  4. Eliminar usuario de tester: Permite eliminar un usuario de tester ya creado.
  5. Ver perfil: Permite ver el perfil propio, permitiendo editar datos. Restricciones: email a editar no puede existir en otro usuario. No permite modificar tipo de perfil. 

<img width="4256" height="2016" alt="image" src="https://github.com/user-attachments/assets/c1f2e306-3f36-4627-8f89-36f40476ecdb" />
